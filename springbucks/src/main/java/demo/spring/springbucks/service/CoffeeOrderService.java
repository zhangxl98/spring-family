package demo.spring.springbucks.service;

import demo.spring.springbucks.model.CoffeeOrder;
import demo.spring.springbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/12/19 10:48 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
@Service
@Slf4j
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository repository;
    @Autowired
    private DatabaseClient client;

    public Mono<Long> create(CoffeeOrder order) {
        return repository.save(order);
    }
}
