package demo.spring.mongodemo;

import com.mongodb.client.result.UpdateResult;
import demo.spring.mongodemo.converter.MoneyReadConverter;
import demo.spring.mongodemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
public class MongoDemoApplication implements ApplicationRunner {
    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MongoDemoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee latte = mongoTemplate.save(Coffee.builder().name("latte").price(Money.of(CurrencyUnit.of("CNY"), 30)).createTime(new Date()).updateTime(new Date()).build());
        log.info("Latte: {}", latte);
        Thread.sleep(1000);
        Coffee espresso = mongoTemplate.save(Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"), 20)).createTime(new Date()).updateTime(new Date()).build());
        log.info("Latte: {}", espresso);

        List<Coffee> coffeeList = mongoTemplate.find(Query.query(Criteria.where("name").is("latte")), Coffee.class);
        log.info("latteSize: {}", coffeeList.size());
        coffeeList.forEach(c -> log.info("latte: {}", c));

        Thread.sleep(1000);
        // 更新文档
        UpdateResult result = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("latte")), new Update().set("price", Money.of(CurrencyUnit.of("CNY"), 50)).currentDate("updateTime"), Coffee.class);
        log.info("UpdateCoffeeCount: {}", result.getModifiedCount());
        log.info("UpdateCoffeeItem: {}", mongoTemplate.findById(latte.getId(), Coffee.class));


        List<Coffee> coffees = mongoTemplate.findAll(Coffee.class);
        log.info("coffeeSize: {}", coffees.size());
        coffees.forEach(c -> log.info("AllCoffee: {}", c));

        // 删除所有文档
        coffees.forEach(c -> mongoTemplate.remove(c));
    }
}
