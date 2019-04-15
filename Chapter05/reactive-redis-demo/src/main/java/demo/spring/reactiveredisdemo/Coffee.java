package demo.spring.reactiveredisdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/14/19 5:16 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {
    private Long id;
    private String name;
    private Long price;
}
