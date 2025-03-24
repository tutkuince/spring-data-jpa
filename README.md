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


#### Transaction Propagation in Hibernate
In Hibernate (when used with Spring), transaction propagation defines how transactions behave when a method is called inside another transactional method. Spring provides several propagation types to control how transactions are managed.
#### Types of Transaction Propagation in Hibernate
1. REQUIRED (Default)
Uses the existing transaction if available, otherwise creates a new one.
- If the calling method already has a transaction, the same transaction is used.
- If there's no transaction, a new one is created.
- Use case: When you want all operations to be in a single transaction.
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
