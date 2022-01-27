package geek.time.another_error_code_demo;

import geek.time.another_error_code_demo.exception.CustomDuplicatedKeyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class AnotherErrorCodeDemoApplicationTests {

    @Autowired
    private JdbcTemplate myJdbcTemplate;

    @Test(expected = CustomDuplicatedKeyException.class)
    public void testThrowingCustomException() {
        try {
            myJdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'a')");
            myJdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'b')");
        } catch (Exception e) {
            log.error("exception" ,e);
            throw e;
        }
    }
}
