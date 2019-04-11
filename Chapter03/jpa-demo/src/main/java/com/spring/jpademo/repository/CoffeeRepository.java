package com.spring.jpademo.repository;

import com.spring.jpademo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/11/19 4:05 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description CrudRepository 增删改查
 */
public interface CoffeeRepository extends CrudRepository<Coffee,Long> {}
