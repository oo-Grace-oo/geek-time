package geek.time.onlinecoffee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import geek.time.onlinecoffee.dao.Coffee;
import geek.time.onlinecoffee.dao.CoffeeOrder;
import geek.time.onlinecoffee.enums.OrderState;
import geek.time.onlinecoffee.repository.CoffeeOrderRepository;
import geek.time.onlinecoffee.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class OnlineCoffeeApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public static void main(String[] args) {
        SpringApplication.run(OnlineCoffeeApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    @Transactional()
    public void run(ApplicationArguments args) throws Exception {
        this.initOrders();
        this.find();
    }

    private void initOrders() {
        Coffee espresso = Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        coffeeRepository.save(espresso);
        log.warn("Coffee : {}", espresso.toString());
        Coffee latter = Coffee.builder().name("latter").price(Money.of(CurrencyUnit.of("CNY"), 30.0)).build();
        coffeeRepository.save(latter);
        log.warn("Coffee : {}", latter.toString());

        CoffeeOrder coffeeOrder = CoffeeOrder.builder().customer("Li Lei").items(Collections.singletonList(espresso))
                .state(OrderState.INIT).build();
        coffeeOrderRepository.save(coffeeOrder);
        log.warn("CoffeeOrder : {}", coffeeOrder.toString());

        coffeeOrder = CoffeeOrder.builder().customer("Li Lei").items(Arrays.asList(espresso, latter)).state(OrderState.INIT)
                .build();
        coffeeOrderRepository.save(coffeeOrder);
        log.warn("CoffeeOrder : {}", coffeeOrder.toString());
    }

    private void find() {
        coffeeRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).forEach(e -> log.warn("Coffee : {}", e.toString()));
        List<CoffeeOrder> list = coffeeOrderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.warn("findTop3ByOrderByUpdateTimeDescIdAsc : {}", this.getJoinedOrderId(list));
        list.forEach(e -> {
            log.warn("order : {}", e.getId());
            e.getItems().forEach(o -> log.warn("item : {}", o));
        });

        List<CoffeeOrder> latte = coffeeOrderRepository.findByItems_Name("latte");
        log.warn("findByItems_Name : {}", this.getJoinedOrderId(latte));
    }

    private String getJoinedOrderId(List<CoffeeOrder> list) {
        return list.stream().map(o -> o.getId().toString()).collect(Collectors.joining(","));
    }
}
