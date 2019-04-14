package demo.spring.springbucks;

import demo.spring.springbucks.converter.BytesToMoneyConverter;
import demo.spring.springbucks.converter.MoneyToBytesConverter;
import demo.spring.springbucks.model.Coffee;
import demo.spring.springbucks.service.CoffeeService;
import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@EnableRedisRepositories
public class SpringbucksApplication implements ApplicationRunner {

    @Autowired
    private CoffeeService coffeeService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbucksApplication.class, args);
    }



    /**
     * @param
     * @return org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer
     * @Author zhangxl98
     * @Date 4/14/19 8:26 AM
     * @OS Ubuntu 18.04 LTS
     * @Device DELL-Inspiron-15-7559
     * @Description <code>MASTER_PREFERRED</code>优先读主节点
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(Arrays.asList(new MoneyToBytesConverter(), new BytesToMoneyConverter()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Optional<Coffee> coffee = coffeeService.findSimpleCoffeeFromCache("latte");
        log.info("Coffee: {}", coffee);

        for (int i = 0; i < 5; i++) {
            coffee = coffeeService.findSimpleCoffeeFromCache("latte");
        }

        log.info("Value from Redis: {}", coffee);

    }
}
