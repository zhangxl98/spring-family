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
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class SpringbucksApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbucksApplication.class, args);
    }

}
