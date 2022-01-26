package geek.time.declarative_transaction_demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import geek.time.declarative_transaction_demo.exception.RollbackException;
import geek.time.declarative_transaction_demo.service.FooService;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/01/26
 */
@Service
public class FooServiceImpl implements FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into FOO (BAR) values ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("insert into FOO (BAR) values ('BBB')");
        throw new RollbackException();
    }

    /**
     * <p>
     *  测试时会发现 insertThenRollback 方法的事务没有生效，是因为@Transactional 是对你的类做代理来实现事务
     *  而你直接内部调用使用的不是代理类的方法，则事务不会生效。
     *  可以在本类中注入自己，然后调用 insertThenRollback 方法。
     *  @Autowired
     *  @Lazy
     *  private FooServiceImpl self;
     *  self.insertThenRollback()
     * </p>
     */
    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        this.insertThenRollback();
    }
}
