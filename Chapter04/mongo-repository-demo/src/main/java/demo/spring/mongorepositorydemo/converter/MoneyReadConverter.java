package demo.spring.mongorepositorydemo.converter;

import demo.spring.mongorepositorydemo.model.Coffee;
import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/13/19 9:27 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description
 */
public class MoneyReadConverter implements Converter<Document, Money> {
    @Override
    public Money convert(Document document) {

        Document money = (Document) document.get("money");
        double amount = Double.parseDouble(money.getString("amount"));
        String currency = ((Document) money.get("currency")).getString("code");
        return Money.of(CurrencyUnit.of(currency),amount);
    }
}
