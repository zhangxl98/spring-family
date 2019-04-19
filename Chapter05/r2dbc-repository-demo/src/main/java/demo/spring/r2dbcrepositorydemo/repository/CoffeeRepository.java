package demo.spring.r2dbcrepositorydemo.repository;

import demo.spring.r2dbcrepositorydemo.model.Coffee;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/19/19 10:16 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
public interface CoffeeRepository extends ReactiveCrudRepository<Coffee,Long> {

    @Query("select * from t_coffee where name = $1")
    Flux<Coffee> findByName(String name);
}
