package demo.spring.r2dbcrepositorydemo.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/19/19 10:15 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
public class MoneyWriteConverter implements Converter<Money,Long> {
    @Override
    public Long convert(Money money) {
        return money.getAmountMinorLong();
    }
}
