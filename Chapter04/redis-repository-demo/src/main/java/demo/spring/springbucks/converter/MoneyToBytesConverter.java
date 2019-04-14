package demo.spring.springbucks.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/14/19 9:08 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 类型转换
 */
@WritingConverter
public class MoneyToBytesConverter implements Converter<Money, byte[]> {
    @Override
    public byte[] convert(Money money) {
        return Long.toString(money.getAmountMajorLong()).getBytes(StandardCharsets.UTF_8);
    }
}
