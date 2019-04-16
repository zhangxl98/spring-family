package demo.spring.reactivemongodemo.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/16/19 8:04 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description <code>Long</code>转换为<code>Money</code>
 */
public class MoneyReadConverter implements Converter<Long, Money> {
    @Override
    public Money convert(Long aLong) {
        return Money.of(CurrencyUnit.of("CNY"),aLong);
    }
}
