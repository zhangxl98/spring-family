package demo.spring.springbucks.repository;

import demo.spring.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/12/19 10:33 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}