package geek.time.another_error_code_demo;

import geek.time.another_error_code_demo.exception.CustomDuplicatedKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AnotherErrorCodeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnotherErrorCodeDemoApplication.class, args);
    }



    @Bean
    public CustomSQLExceptionTranslatorRegistrar sqlExceptionTranslatorRegistrar() {
        CustomSQLExceptionTranslatorRegistrar registrar = new CustomSQLExceptionTranslatorRegistrar();
        Map<String, SQLExceptionTranslator> translators = new HashMap<>();
        SQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator() {
            @Override
            public DataAccessException translate(String task, String sql, SQLException ex) {
                return new CustomDuplicatedKeyException("覆盖所有异常");
            }
        };
        translators.put("H2", translator);
        registrar.setTranslators(translators);
        return registrar;
    }


}
