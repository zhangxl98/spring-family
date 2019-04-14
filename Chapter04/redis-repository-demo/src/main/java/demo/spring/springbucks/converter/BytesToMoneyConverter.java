package demo.spring.springbucks.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/14/19 9:01 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 类型转换
 */
@ReadingConverter
public class BytesToMoneyConverter implements Converter<byte[], Money> {

    @Override
    public Money convert(byte[] bytes) {
        return Money.of(CurrencyUnit.of("CNY"), Long.parseLong(new String(bytes, StandardCharsets.UTF_8)));
    }
}
