package geek.time.declarative_transaction_demo.service;

import geek.time.declarative_transaction_demo.exception.RollbackException;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/01/26
 */
public interface FooService {

    void insertRecord();

    void insertThenRollback() throws RollbackException;

    void invokeInsertThenRollback() throws RollbackException;
}
