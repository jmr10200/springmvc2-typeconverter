package hello.typeconverter.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MyNumberFormatterTest {

    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    void parse() throws ParseException {
        Number result = formatter.parse("10,000", Locale.KOREA);
        assertThat(result).isEqualTo(10000L); // Long 타입 주의
        // 결과로그
        // hello.typeconverter.formatter.MyNumberFormatter - text=10,000, locale=ko_KR
    }

    @Test
    void print() {
        String result = formatter.print(10000, Locale.KOREA);
        assertThat(result).isEqualTo("10,000");
        // 결과로그
        // hello.typeconverter.formatter.MyNumberFormatter - object=10000, locale=ko_KR
    }
}