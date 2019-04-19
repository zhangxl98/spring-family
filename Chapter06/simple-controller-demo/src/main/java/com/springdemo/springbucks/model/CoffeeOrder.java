package com.springdemo.springbucks.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/12/19 10:30 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description CoffeeOrder 实体类
 */
@Entity
@Table(name = "T_ORDER")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder extends BaseEntity implements Serializable {
    private String customer;
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
