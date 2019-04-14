package demo.spring.springbucks;

import demo.spring.springbucks.model.Coffee;
import demo.spring.springbucks.model.CoffeeOrder;
import demo.spring.springbucks.model.OrderState;
import demo.spring.springbucks.repository.CoffeeRepository;
import demo.spring.springbucks.service.CoffeeOrderService;
import demo.spring.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@EnableCaching(proxyTargetClass = true)
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

    /**
     * @Author      zhangxl98
     * @Date        4/13/19 9:23 PM
     * @OS          Ubuntu 18.04 LTS
     * @Device      DELL-Inspiron-15-7559
     * @param redisConnectionFactory
     * @return  org.springframework.data.redis.core.RedisTemplate<java.lang.String,demo.spring.springbucks.model.Coffee>
     * @Description 自定义 RedisTemplate
     *
     */
    @Bean
    public RedisTemplate<String, Coffee> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Coffee> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        repositoryMethod();


//        springCache();

//        redisCache();

        Optional<Coffee> coffee = coffeeService.findOneCoffee("latte");
        log.info("Coffee: {}", coffee);

        for (int i = 0; i < 5; i++) {
            coffee = coffeeService.findOneCoffee("latte");
        }

        log.info("Value from Redis: {}", coffee);

    }


    private void redisCache() throws InterruptedException {
        /**
         * @Author zhangxl98
         * @Date 4/13/19 8:59 PM
         * @OS Ubuntu 18.04 LTS
         * @Device DELL-Inspiron-15-7559
         * @param
         * @return void
         * @Description 将从数据库中取出的数据临时存放到 redis 中
         *
         */
        log.info("Count: {}", coffeeService.findAllCoffee().size());
        for (int i = 0; i < 5; i++) {
            log.info("Reading from cache.");
            coffeeService.findAllCoffee();
        }
        Thread.sleep(5_000);
        log.info("Reading after refresh.");
//        coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c.getName()));
        List<Coffee> allCoffee = coffeeService.findAllCoffee();
    }

    /*
    private void springCache() {
        log.info("Count: {}", coffeeService.findAllCoffee().size());
        for (int i = 0; i < 10; i++) {
            log.info("Reading from cache.");
            coffeeService.findAllCoffee();
        }
        coffeeService.reloadCoffee();
        log.info("Reading after refresh.");
        coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c.getName()));
    }

    private void repositoryMethod() {
        // 查询所有的 Coffee 种类
        log.info("All Coffee: {}", coffeeRepository.findAll());

        // 按 name 查询 Coffee
        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = coffeeOrderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID: {}", coffeeOrderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", coffeeOrderService.updateState(order, OrderState.INIT));
        }
    }
    */
}
