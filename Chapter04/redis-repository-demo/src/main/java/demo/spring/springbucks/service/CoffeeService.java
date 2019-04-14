package demo.spring.springbucks.service;

import demo.spring.springbucks.model.Coffee;
import demo.spring.springbucks.model.CoffeeCache;
import demo.spring.springbucks.repository.CoffeeCacheRepository;
import demo.spring.springbucks.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/12/19 10:38 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
@Service
@Slf4j
public class CoffeeService {

    public static final String CACHE = "springbucks-coffee";

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeCacheRepository coffeeCacheRepository;

    /**
     * @param   [name]
     * @return  java.util.Optional<demo.spring.springbucks.model.Coffee>
     * @Author      zhangxl98
     * @Date        4/14/19 9:57 AM
     * @OS          Ubuntu 18.04 LTS
     * @Device      DELL-Inspiron-15-7559
     * @Description 使用 Redis Repository
     *
     */
    public Optional<Coffee> findSimpleCoffeeFromCache(String name) {

        // 首先从 Redis 中查找
        Optional<CoffeeCache> cache = coffeeCacheRepository.findOneByName(name);
        // 如果存在数据
        if (cache.isPresent()) {
            CoffeeCache coffeeCache = cache.get();
            // 利用 Redis 中的数据创建出 Coffee 对象
            Coffee coffee = Coffee.builder()
                    .name(coffeeCache.getName())
                    .price(coffeeCache.getPrice())
                    .build();
            log.info("Coffee [ {} ] find in cache", coffee);
            return Optional.of(coffee);
        } else {
            // 如果不存在，就从数据库查找
            Optional<Coffee> coffee = findOneCoffee(name);
            // 将查找出的 Coffee 拼接成 CoffeeCache
            coffee.ifPresent(
                    c -> {
                        CoffeeCache coffeeCache = CoffeeCache.builder()
                                .id(c.getId())
                                .name(c.getName())
                                .price(c.getPrice())
                                .build();

                        log.info("Save Coffee [ {} ] to cache", coffeeCache);

                        // 保存 CoffeeCache 到 Redis 中
                        coffeeCacheRepository.save(coffeeCache);
                    }
            );
            return coffee;
        }

    }

    /**
     * 从数据库查找 Coffee
     * @param name
     * @return
     */
    public Optional<Coffee> findOneCoffee(String name) {

        /**
         * SpingData JPA ExampleMatcher 实例查询
         */
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(), matcher));

        log.info("Coffee Find: {}", coffee);

        return coffee;
    }

    @Cacheable
    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }
}
