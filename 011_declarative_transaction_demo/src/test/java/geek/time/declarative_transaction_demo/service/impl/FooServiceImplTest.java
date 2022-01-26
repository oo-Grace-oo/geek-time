package geek.time.declarative_transaction_demo.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import geek.time.declarative_transaction_demo.exception.RollbackException;
import geek.time.declarative_transaction_demo.service.FooService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/01/26
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
class FooServiceImplTest {

    @Autowired
    private FooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void insertRecord() {
        log.error("AAA : {}", jdbcTemplate.queryForList("select  count(1) as cnt from  FOO where BAR = 'AAA'").get(0).get("cnt"));
        fooService.insertRecord();
        log.error("AAA : {}", jdbcTemplate.queryForList("select  count(1) as cnt from  FOO where BAR = 'AAA'").get(0).get("cnt"));
    }

    @Test
    void insertThenRollback() {
        log.error("BBB : {}", jdbcTemplate.queryForList("select  count(1) as cnt from  FOO where BAR = 'BBB'").get(0).get("cnt"));
        try {
            fooService.insertThenRollback();
        } catch (RollbackException e) {
            log.error("BBB : {}",
                    jdbcTemplate.queryForList("select  count(1) as cnt from  FOO where BAR = 'BBB'").get(0).get("cnt"));
            // log.warn("BBB {}",
            // jdbcTemplate
            // .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'",
            // Long.class));
        }

    }

    @Test
    void invokeInsertThenRollback() {

        log.error("BBB : {}", jdbcTemplate.queryForList("select  count(1) as cnt from  FOO where BAR = 'BBB'").get(0).get("cnt"));
        try {
            fooService.invokeInsertThenRollback();
        } catch (RollbackException e) {
            log.error("BBB : {}",
                    jdbcTemplate.queryForList("select  count(1) as cnt from  FOO where BAR = 'BBB'").get(0).get("cnt"));
        }
    }
}