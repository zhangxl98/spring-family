package demo.spring.r2dbcrepositorydemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/19/19 10:13 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("t_coffee")
public class Coffee {
    @Id
    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
