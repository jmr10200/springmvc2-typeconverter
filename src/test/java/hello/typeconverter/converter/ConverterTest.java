package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("100");
        assertThat(result).isEqualTo(100);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(1234);
        assertThat(result).isEqualTo("1234");
    }

    @Test
    void stringToIpPort() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

    @Test
    void ipPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.2", 8080);
        String result = converter.convert(source);
        assertThat(result).isEqualTo("127.0.0.2:8080");
    }

// 이렇게 타입 컨버터를 하나하나 직접 찾아서 타입변환에 사용하는 것은 번거롭다.
// 그래서 스프링은 개별 컨버터를 모아두고 사용할수 있는 기능을 제공하는데, ConversionService 이다.
}