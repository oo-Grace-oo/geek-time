package geek.time.mybatis_demo_020;

import geek.time.mybatis_demo_020.mapper.CoffeeMapper;
import geek.time.mybatis_demo_020.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
class MyBatisDemoApplicationTests {

    @Autowired
    private CoffeeMapper mapper;

    @Test
    public void test() {

        Coffee c = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        int count = mapper.save(c);
        log.warn("Save {} Coffee: {}", count, c);

        c = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0)).build();
        count = mapper.save(c);
        log.warn("Save {} Coffee: {}", count, c);

        c = mapper.findById(c.getId());
        log.warn("Find Coffee: {}", c);
    }

}
