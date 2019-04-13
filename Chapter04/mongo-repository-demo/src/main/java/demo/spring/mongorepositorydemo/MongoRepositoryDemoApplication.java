package demo.spring.mongorepositorydemo;

import demo.spring.mongorepositorydemo.converter.MoneyReadConverter;
import demo.spring.mongorepositorydemo.model.Coffee;
import demo.spring.mongorepositorydemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
public class MongoRepositoryDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoRepositoryDemoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 50))
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        List<Coffee> coffees = coffeeRepository.insert(Arrays.asList(latte, espresso));
        log.info("Coffees: {}", coffees);

        coffeeRepository.findAll(Sort.by("name")).forEach(c -> log.info("Coffees Sort By Name: {}", c));

        // update
        Thread.sleep(1000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 10));
        latte.setUpdateTime(new Date());

        coffeeRepository.save(latte);

        coffeeRepository.findByName("latte").forEach(c -> log.info("update latte: {}", c));

        coffeeRepository.deleteAll();
    }
}
