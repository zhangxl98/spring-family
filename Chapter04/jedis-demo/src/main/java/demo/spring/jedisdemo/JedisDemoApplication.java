package demo.spring.jedisdemo;

import demo.spring.jedisdemo.model.Coffee;
import demo.spring.jedisdemo.model.CoffeeOrder;
import demo.spring.jedisdemo.model.OrderState;
import demo.spring.jedisdemo.repository.CoffeeRepository;
import demo.spring.jedisdemo.service.CoffeeOrderService;
import demo.spring.jedisdemo.service.CoffeeService;
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
public class JedisDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    public static void main(String[] args) {
        SpringApplication.run(JedisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        repositoryMethod();

    }

    private void repositoryMethod() {
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
