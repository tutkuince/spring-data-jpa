### Transactions and Concurrency
#### Delaying the resource-local connection acquisition
In Hibernate, the database connection acquisition, as well as the connection release, are relative to the type of the currently running transaction:
- resource-local: For JDBC transactions, that operate with a single DataSource, the Connection is acquired right when the transaction starts and is closed when the transaction ends (either commit or rollback)
- JTA: For XA transactions, that span over multiple DataSources, the Connection is acquired upon executing the first Statement and is released after each Statement execution.
The aggressive connection release mechanism can be skipped if the underlying application server allows us to do so.
<hr/>

- Our goal is to make the resource-local transaction behave like JTA and delay the connection acquisition until Hibernate needs to execute the first JDBC Statement of the currently running unit-of-work.
- The reason why resource-local transaction requires a database connection from the very beginning.
- Hibernate needs to check the underlying JDBC Connection auto-commit status, and disable it if the Connection is set to auto-commit.
This way, Hibernate can control the transaction boundaries and make sure that the unit-of-work JDBC Statements are executed in the context of the same database transaction.
- Although this behavior is correct since we cannot know if the auto-commit flag was set or not, we could hint Hibernate to skip this check since we already know that all JDBC Connections run in manual commit mode.
- Therefore, if you are using resource-local transactions (which is quite the norm when using Spring framework), you should definitely configure the connection pool (e.g. HikariCP) to disable the auto-commit commit, 
and provide the connection acquisition delay Hibernate configuration property
#### READ_WRITE vs READ_ONLY Transactions
In Hibernate, transactions help manage database operations reliably by ensuring atomicity, consistency, isolation, and durability (ACID properties). Hibernate transactions can be either read-only or read-write, and understanding their differences is important for performance optimization.
#### Read-Only Transactions
A read-only transaction is used when you only want to fetch data from the database without modifying it. These transactions can optimize performance by avoiding unnecessary locks and persistence context updates.
<p></p>

<b>Key Characteristics:</b>

- Hibernate does not track changes to entities within the transaction.
- No dirty checking (Hibernate does not check for modified entities at commit time).
- Can be optimized by the database for better performance.
- Used for SELECT queries only.

<b>Why use it?</b>
- Reduces overhead by skipping unnecessary checks.
- Improves performance, especially when dealing with large datasets.

#### Read-Write Transactions
A read-write transaction allows both reading and modifying database records. This is the default transaction type.
<p></p>

<b>Key Characteristics:</b>

- Supports INSERT, UPDATE, DELETE operations.
- Hibernate tracks entity changes using the persistence context.
- Dirty checking is performed to detect modified entities and automatically update the database on commit.
- May require database locking mechanisms to prevent conflicts.

<b>Why use it?</b>
- Needed for any database modification.
- Ensures changes are persisted safely with rollback support.


### Transaction Propagation in Hibernate
In Hibernate (when used with Spring), transaction propagation defines how transactions behave when a method is called inside another transactional method. Spring provides several propagation types to control how transactions are managed.
#### Types of Transaction Propagation in Hibernate
1. REQUIRED (Default)
Uses the existing transaction if available, otherwise creates a new one.
- If the calling method already has a transaction, the same transaction is used.
- If there's no transaction, a new one is created.
- Use case: When you want all operations to be in a single transaction.
- ***If RuntimeException occurs in either of the two methods, the whole transaction will rollback***
2. REQUIRES_NEW
Always creates a new transaction, suspending the existing one (if any).
- If there's an active transaction, it is paused, and a new transaction is created.
- The old transaction resumes after the new one completes.
- Use case: When you need a separate transaction (e.g., logging, audit, notifications).
- Real-life Example: If an order fails, logging the failure should not roll back if the order transaction fails.
3. SUPPORTS
Uses a transaction if available, otherwise runs without one.
- If a transaction exists, it participates in it.
- If no transaction exists, it runs non-transactionally.
- Use case: When transactions are optional (e.g., read operations that may or may not require a transaction).
4. MANDATORY
Requires an existing transaction, otherwise throws an exception.
- If there's an active transaction, it joins it.
- If no transaction exists, it throws TransactionRequiredException.
- Use case: When a method must be called within a transaction, such as database updates.
5. NOT_SUPPORTED
Runs without a transaction, suspending any existing transaction.
- If a transaction exists, it is paused during execution.
- The method executes outside a transactional context.
- Use case: When a method should not be inside a transaction (e.g., long-running batch jobs, reporting).
- Real-life Example: Running a background job that should not be affected by transaction rollbacks.
6. NEVER
Does not allow a transaction; throws an exception if one exists.
- If no transaction exists, the method runs normally.
- If a transaction does exist, an exception is thrown.
- Use case: When a method must not be executed within a transaction.
- Warning: If called within a transaction, it will fail.
7. NEVER
Creates a nested transaction within an existing one.
- If a transaction exists, it creates a savepoint inside it.
- If the nested transaction fails, only changes inside it are rolled back (not the parent transaction).
- If no transaction exists, it behaves like REQUIRED.
- Use case: When part of a transaction can be rolled back separately.
- Real-life Example: Updating an order, but if order history logging fails, only the history rollback happens, not the entire order update.
##### When to Use Which?
- REQUIRED → Most common, use for regular transactional methods.
- REQUIRES_NEW → When you need a separate transaction (e.g., logging, sending emails).
- SUPPORTS → Use for optional transactions (e.g., read operations).
- MANDATORY → Use when the method must run inside a transaction.
- NOT_SUPPORTED → For operations that should never run inside a transaction (e.g., background jobs).
- NEVER → When a transaction is strictly forbidden.
- NESTED → When you need sub-transactions that can roll back independently.


### Optimistic and Pessimistic Locking in Hibernate
In Hibernate, locking mechanisms help prevent data inconsistencies when multiple transactions access the same entity simultaneously. The two main types of locking are pessimistic locking and optimistic locking.

#### 1. Optimistic Locking

Assumes conflicts are rare and allows multiple transactions to work on the same data without immediate locks.
- Instead of locking a record, Hibernate detects conflicts using versioning.
- If another transaction has modified the data in the meantime, an error is thrown when committing.

How It Works:
- The entity has a @Version column (e.g., an integer or timestamp).
- When a transaction reads the entity, it also reads the version.
- Before updating, Hibernate checks if the version is still the same.
- If the version has changed (another transaction updated the record), it throws an OptimisticLockException.

Advantages:
- ✅ Better performance (no locks needed).
- ✅ Works well for applications with more reads than writes.
- ✅ Prevents dirty writes (when two transactions overwrite each other's changes).

Disadvantages:
- ❌ Conflict detection is late (only at commit time).
- ❌ If conflicts are frequent, it may lead to many transaction failures.

#### 2. Pessimistic Locking

Prevents conflicts by locking the data as soon as it is read.
- The database blocks other transactions from modifying or even reading the locked data until the first transaction is completed.
- Uses SQL SELECT ... FOR UPDATE to lock the row.

How It Works:
- A transaction reads an entity and locks it.
- Other transactions must wait until the lock is released before they can modify or read the data.
- The lock is released when the transaction completes (commit/rollback).

Advantages:
- ✅ Ensures immediate consistency (no lost updates).
- ✅ Suitable for high-contention scenarios (frequent updates).

Disadvantages:
- ❌ Performance overhead (locks reduce concurrency).
- ❌ Deadlocks possible if not used carefully.

### Isolation Level Rules
In Hibernate, isolation levels define how transactions interact with each other, preventing issues like dirty reads, non-repeatable reads, and phantom reads. These isolation levels are based on database transaction isolation levels, and Hibernate uses them through JDBC.

- Dirty Read: A transaction reads uncommitted changes of another transaction.
- Non-Repeatable Read: A transaction reads the same row twice, but the value changes between reads.
- Phantom Read: A transaction executes the same query twice and gets different sets of rows (due to inserts or deletes by other transactions).

#### Types of Isolation Levels
##### 1) READ UNCOMMITTED (Level 1)
Lowest isolation, highest performance, but risky.

- Transactions can see uncommitted changes from other transactions.
- Can lead to dirty reads, non-repeatable reads, and phantom reads.

Example:
1) Transaction 1 updates a product price but does not commit.
2) Transaction 2 reads the new price (even though it's uncommitted).
3) If Transaction 1 rolls back, Transaction 2 has read incorrect data.

Use Case: Not recommended unless you need maximum performance (e.g., logs, caching).

##### 2) READ COMMITTED (Level 2) - Default in Many Databases
Prevents dirty reads, but non-repeatable reads and phantom reads are possible.

- Transactions can only read committed data.
- If another transaction commits changes, subsequent reads will see the updated data.

Example:
1) Transaction 1 reads a product price.
2) Transaction 2 updates and commits a new price.
3) If Transaction 1 reads again, it sees the new value (non-repeatable read).

Use Case: Most common, suitable for general applications.

##### 3) REPEATABLE READ (Level 4)
Prevents dirty reads & non-repeatable reads, but allows phantom reads.

- Ensures consistent reads of a row within a transaction.
- Other transactions cannot update the row until the transaction completes.

Example: 
1) Transaction 1 reads a product price multiple times.
2) Transaction 2 tries to update the price but has to wait until Transaction 1 commits.

Use Case: Used in applications where consistent reads of a record are needed (e.g., banking).

##### 4) SERIALIZABLE (Level 8) - Highest Isolation
Prevents all concurrency issues but has the worst performance.

- Transactions execute sequentially (like a queue).
- Other transactions must wait before reading/writing to the same data.

Example: 

1) Transaction 1 reads a set of products.
2) While Transaction 1 is active, no other transaction can insert, update, or delete products.

Use Case: Used for highly sensitive data (e.g., financial systems, inventory).

#### Choosing the Right Isolation Level
Isolation Level	Use Case

- READ UNCOMMITTED	Logging, caching, fast but risky transactions
- READ COMMITTED (default)	General applications, avoids dirty reads
- REPEATABLE READ	Banking, e-commerce (to avoid double charges)
- SERIALIZABLE	High-security transactions, financial systems


🔹 Hibernate itself does not define isolation levels but relies on JDBC and database settings. <br/>
🔹 Choosing the right isolation level is a balance between consistency and performance. <br/>
🔹 READ COMMITTED is the most commonly used level, while SERIALIZABLE is the safest but slowest. <br/>

### Modifying Queries
Modifying queries in Hibernate refer to update, delete, and insert operations executed directly using HQL (Hibernate Query Language) or JPQL (Java Persistence Query Language) instead of modifying entities and persisting changes through the session.
HQL/JPQL for modifying queries when you want bulk updates/deletes.
Use @Modifying @Query in Spring Data JPA for clean repository methods.

Why Use Modifying Queries?
- Better Performance: Bypasses Hibernate's automatic dirty checking.
- Bulk Operations: Efficiently update or delete multiple records in a single query.
- Avoiding Entity Lifecycle Management: Directly modify data without loading entities into memory.

#### Modifying Queries with JPQL (@Modifying in Spring Data JPA)
In Spring Data JPA, you can use @Modifying with @Query for update and delete queries.

##### UPDATE Query
Used to update multiple records without loading them as objects.

- @Modifying annotation enables update/delete queries.
- Must be used inside a transaction (@Transactional).

##### DELETE Query
Used to remove multiple records directly.

- Use Case: Removing all canceled orders.

***Important Considerations***

✅ Use Transactions: Modifying queries must run inside a transaction.  <br/>
✅ Avoid Loading Large Datasets: Directly updating/deleting via queries prevents memory issues. <br/>
✅ Session Synchronization: Manually clear the session (session.clear()) if needed. <br/>

### Projection
A Projection is a way to retrieve only specific fields from an entity instead of the entire object. This helps with:

- Improving performance (fetch less data)
- Reducing memory usage
- Avoiding exposing sensitive fields (e.g., password)

#### Types of Projections in Spring DATA JPA
#### 1. Interface-based Projections (Most Common)

You create an interfae with getters that match the fields you want.
```
public interface UserSummary {
    String getName();
    String getEmail();
}
```
Then define your repository method:
```
List<UserSummary> findByStatus(String status);
```
This will return only the name and email fields of users with the given status.

#### 2. Class-based Projections (DTO Projections)
You define a DTO class with a constructor:

```
public class UserDTO {
    private final String name;
    private final String email;

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getters
}
```
And use JPQL with new keyword:
```
@Query("SELECT new com.example.dto.UserDTO(u.name, u.email) FROM User u WHERE u.status = :status")
List<UserDTO> findUsersByStatus(@Param("status") String status);
```
Note: The constructor must match the selected fields and their order.

#### Benefits of Using Projections
- Faster performance – fetch only what’s needed
- Avoids overfetching
- Cleaner APIs
- Protects sensitive data (like passwords)

#### Notes and Gotchas
- In interface-based projections, method names must match entity field names
- In DTO-based projections, use new keyword in JPQL with matching constructor
- Projections are read-only — you can’t persist changes using them

### Entity Graph
An Entity Graph in Spring Data JPA defines what related entities should be loaded along with the main entity.

In short:
- Without Entity Graph → associations (like @OneToMany, @ManyToOne) are usually LAZY loaded → meaning, they are loaded later when accessed.
- With Entity Graph → you can tell Hibernate to EAGERLY fetch certain relationships immediately in the same query.

✅ It helps you optimize queries     <br/>
✅ It helps avoid N+1 problems       <br/>
✅ It gives you fine-grained control over what is fetched. <br/>

### Entity Subgraph
An Entity Subgraph is a way to define nested fetching when using an @EntityGraph.

- EntityGraph: lets you define which fields of the main entity should be eagerly loaded.
- EntitySubgraph: lets you define which fields of the associated entities should also be eagerly loaded.

In simple terms:
- ***EntityGraph = top-level fields***
- ***EntitySubgraph = deeper-level fields (nested)***

```
@Entity
@NamedEntityGraph(
    name = "User.orders.address",
    attributeNodes = @NamedAttributeNode(value = "orders", subgraph = "orderWithAddress"),
    subgraphs = @NamedSubgraph(
        name = "orderWithAddress",
        attributeNodes = @NamedAttributeNode("shippingAddress")
    )
)
public class User {
    // ...
}
```
