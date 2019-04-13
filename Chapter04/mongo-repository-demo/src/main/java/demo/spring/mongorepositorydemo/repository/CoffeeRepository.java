package demo.spring.mongorepositorydemo.repository;

import demo.spring.mongorepositorydemo.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/13/19 9:38 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
public interface CoffeeRepository extends MongoRepository<Coffee,String> {
    List<Coffee> findByName(String name);
}
