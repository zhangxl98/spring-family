package com.spring.jpacomplexdemo.repository;

import com.spring.jpacomplexdemo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/11/19 4:31 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
