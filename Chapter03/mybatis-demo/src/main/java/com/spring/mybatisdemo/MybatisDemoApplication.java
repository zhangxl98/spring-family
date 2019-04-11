package com.spring.mybatisdemo;

import com.spring.mybatisdemo.mapper.CoffeeMapper;
import com.spring.mybatisdemo.model.Coffee;
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
@MapperScan("com.spring.mybatisdemo.mapper")
@Slf4j
public class MybatisDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee coffee = Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"), 20)).build();

        Long id = coffeeMapper.save(coffee);

        log.info("Coffee {} => {}", id, coffee);

        coffee = coffeeMapper.findById(id);

        log.info("Coffee {}", coffee);
    }
}
