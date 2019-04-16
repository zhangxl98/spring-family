package demo.spring.reactivemongodemo.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/16/19 8:07 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description <code>Money</code>转换为<code>Long</code>
 */
public class MoneyWriteConverter implements Converter<Money,Long> {
    @Override
    public Long convert(Money money) {
        return money.getAmountMinorLong();
    }
}

