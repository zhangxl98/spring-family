package com.springdemo.springbucks;

import com.springdemo.springbucks.model.Coffee;
import com.springdemo.springbucks.model.CoffeeOrder;
import com.springdemo.springbucks.model.OrderState;
import com.springdemo.springbucks.repository.CoffeeRepository;
import com.springdemo.springbucks.service.CoffeeOrderService;
import com.springdemo.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
public class SpringbucksApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbucksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 查询所有的 Coffee 种类
        log.info("All Coffee: {}",coffeeRepository.findAll());

        // 按 name 查询 Coffee
        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = coffeeOrderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID: {}", coffeeOrderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", coffeeOrderService.updateState(order, OrderState.INIT));
        }
    }
}
