package geek.time.error_code_demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import geek.time.error_code_demo.exception.CustomDuplicatedKeyException;

/**
 * <p>
 *
 * </p>
 *
 * @author User
 * @date 2022/01/26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ErrorCodeDemoApplicationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test(expected = CustomDuplicatedKeyException.class)
    public void testThrowingCustomException() {
        try {
            jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'a')");
            jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'b')");
        } catch (Exception e) {
            log.error("exception" ,e);
            throw e;
        }
    }
}