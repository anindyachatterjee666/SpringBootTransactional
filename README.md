# SpringBootTransactional
SpringBoot project on Transactional annotation



In Spring Boot, the @Transactional annotation is used to define transaction boundaries for methods in service classes, typically in business logic. When a method is annotated with @Transactional, Spring manages the transaction lifecycle, including starting, committing, and rolling back the transaction. It ensures that the method runs within a transaction and enforces consistent transaction management across your application.

Key Concepts of @Transactional:
Transaction Boundaries:

When a method annotated with @Transactional is executed, Spring starts a new transaction (or joins an existing one if it's nested).
If the method completes successfully, the transaction is committed (saved to the database).
If there’s an exception (usually a runtime exception), the transaction is rolled back (discarding any changes made to the database).
Rollback Rules: By default, @Transactional will rollback only on unchecked exceptions (subclasses of RuntimeException) and errors (subclasses of Error). If you want to configure specific rollback behavior (for instance, to rollback on checked exceptions), you can use the rollbackFor attribute.

Propagation: Spring provides different propagation options for how transactions are handled in nested method calls. For example, if one method annotated with @Transactional calls another, Spring can control whether the same transaction should be shared or a new transaction should be created. The most common propagation types include:

REQUIRED: Default behavior, uses the existing transaction if one exists or creates a new one.
REQUIRES_NEW: Always creates a new transaction, suspending the existing one if there is one.
NESTED: Executes within a nested transaction (can roll back independently of the outer transaction).
Isolation: @Transactional allows you to set the isolation level, which determines how the transaction is isolated from other concurrent transactions (e.g., whether other transactions can see the changes made in the current one before it’s committed). Some common isolation levels are:

READ_COMMITTED: The transaction can’t read uncommitted changes.
SERIALIZABLE: The strictest isolation, where transactions are fully isolated from each other.
Timeout: You can specify a timeout for a transaction using the timeout attribute. This will automatically roll back the transaction if it exceeds the specified time limit.

Read-Only Transactions: The @Transactional annotation also has a readOnly attribute, which helps optimize performance when you're dealing with operations that only read data (no changes to the database). Setting readOnly=true hints to Spring and the underlying database that no write operations will occur, potentially optimizing transaction management.


How It Works Internally:
Spring uses AOP (Aspect-Oriented Programming) to apply the @Transactional behavior. When a method annotated with @Transactional is invoked, Spring creates a proxy around the method to intercept the call.
The proxy starts a transaction before the method execution and commits or rolls back the transaction after the method completes, depending on whether an exception occurs.
If no exception occurs, the transaction is committed (i.e., the changes are saved to the database). If an exception occurs, the transaction is rolled back.
In Summary:
@Transactional manages the transactional behavior of methods in Spring Boot.
It ensures consistency, supports rollback on failure, and provides various configuration options like propagation, isolation, and timeout.
It is widely used in service methods where database operations are involved.

