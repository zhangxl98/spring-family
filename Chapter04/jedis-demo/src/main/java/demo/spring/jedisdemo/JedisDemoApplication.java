package demo.spring.jedisdemo;

import demo.spring.jedisdemo.model.Coffee;
import demo.spring.jedisdemo.model.CoffeeOrder;
import demo.spring.jedisdemo.model.OrderState;
import demo.spring.jedisdemo.repository.CoffeeRepository;
import demo.spring.jedisdemo.service.CoffeeOrderService;
import demo.spring.jedisdemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
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


    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private JedisPoolConfig jedisPoolConfig;


    public static void main(String[] args) {
        SpringApplication.run(JedisDemoApplication.class, args);
    }

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host) {
        return new JedisPool(jedisPoolConfig(), host);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        repositoryMethod();


        log.info("jedisPoolConfig: {}", jedisPoolConfig);

        // try-with-resource语法
        try (Jedis jedis = jedisPool.getResource()) {
            coffeeService.findAllCoffee().forEach(c -> {
                jedis.hset("springbucks-menu",
                        c.getName(),
                        Long.toString(c.getPrice().getAmountMinorLong()));
            });

            Map<String, String> hgetAll = jedis.hgetAll("springbucks-menu");

            log.info("AllCoffee: {}", hgetAll);

            String lattePrice = jedis.hget("springbucks-menu", "latte");

            log.info("lattePrice: {}", Money.of(CurrencyUnit.of("CNY"), Long.parseLong(lattePrice)));
        }


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
}
