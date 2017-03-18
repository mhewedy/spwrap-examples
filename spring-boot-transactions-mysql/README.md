Shows that `spwrap` now can joined Spring Transactions.

In this exmples shows when calling spwrap in a service method that is annotated with spring @Transactional annotation, any exception that causes spring to rollback the transaction will causes the stored procedure to be rollbacked as well.
