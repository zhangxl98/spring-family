package demo.spring.reactivemongodemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/16/19 7:59 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description Coffee 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {
    private String id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
