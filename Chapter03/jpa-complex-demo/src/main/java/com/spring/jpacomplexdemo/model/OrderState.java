package com.spring.jpacomplexdemo.model;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/11/19 4:42 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 用于标识 CoffeeOrder 中 order 的状态    枚举
 */
public enum  OrderState {
    INIT, PAID, BREWING, BREWED, TAKEN, CANCELLED
}
