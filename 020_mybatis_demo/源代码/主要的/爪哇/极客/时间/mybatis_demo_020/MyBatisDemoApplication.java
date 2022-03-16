package geek.time.mybatis_demo_020;

import geek.time.mybatis_demo_020.mapper.CoffeeMapper;
import geek.time.mybatis_demo_020.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("geek.time.mybatis_demo_020.mapper")
public class MyBatisDemoApplication  implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisDemoApplication.class, args);
    }

    @Autowired
    private CoffeeMapper mapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee c = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
//        int count = mapper.save(c);
//        log.warn("Save {} Coffee: {}", count, c);
//
//        c = Coffee.builder().name("latte")
//                .price(Money.of(CurrencyUnit.of("CNY"), 25.0)).build();
//        count = mapper.save(c);
//        log.warn("Save {} Coffee: {}", count, c);
//
//        c = mapper.findById(c.getId());
//        log.warn("Find Coffee: {}", c);
    }
}
