package demo.spring.springbucks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/14/19 8:40 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
@RedisHash(value = "springbucks-coffee", timeToLive = 60)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeCache {
    @Id
    private Long id;
    @Indexed
    private String name;
    private Money price;
}
