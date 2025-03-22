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
