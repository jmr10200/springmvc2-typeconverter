package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IntegerToStringConverter implements Converter<Integer, String> {

    // Converter<Integer, String> : Integer 를 String 으로 변환

    @Override
    public String convert(Integer source) {
        log.info("converter source={}", source);
        return String.valueOf(source);
    }
}
