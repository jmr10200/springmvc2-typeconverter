package hello.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        // T parse(String text, Locale locale) : 문자를 객체로 변환
        log.info("text={}, locale={}", text, locale);
        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(text);
        // 참고 : Number 는 Integer, Long 과 같은 숫자 타입의 부모 클래스
    }

    @Override
    public String print(Number object, Locale locale) {
        // String print(T object, Locale locale) : 객체를 문자로 변환
        log.info("object={}, locale={}", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }
}
/* 포맷터 - Formatter */
// Converter 는 입력과 출력 타입에 제한없는 범용 타입 변환 기능을 제공한다.
// 일반적인 웹 어플리케이션 환경에서 boolean 타입을 Number 로 바꾸는 기능보다는
// 문자를 다른 타입으로 바꾸거나, 다른 타입을 문자로 바꾸는 상황이 많다.

// 웹 어플리케이션 환경에서 객체를 문자로, 문자를 객체로 변환하는 예
// 화면에 숫자를 출력해야 하는데, Integer -> String 출력시점에 숫자 10000 을 문자 "10,000" 으로 변경
// 날짜 객체를 문자 "2022-01-01 12:30:21" 과 같이 출력하거나 반대의 상환

// Locale
// 추가로 날짜 숫자의 표현 방법은 Locale 현지화 정보가 사용될 수 있다.

// 이렇게 객체를 특정한 포맷에 맞추어 문자로 출력하거나 그 반대 역할을 하는 것에 특화된 기능이
// 포맷터 (Formatter) 이다. 포맷터는 컨버터의 특별한 버전으로 이해하면 된다.

// Converter vs Formatter
// Converter : 범용 (객체 -> 객체)
// Formatter : 문자특화 (객체 -> 문자, 문자 -> 객체) + 현지화 (Locale) , 컨버터의 특별한 버전

/* Formatter 만들기 */
// String print(T object, Locale locale) : 객체를 문자로 변환
// T parse(String text, Locale locale) : 문자를 객체로 변환

// 참고
// 스프링은 용도에 따라 다양한 방식의 포맷터를 제공한다
// Formatter : 포맷터
// AnnotationFormatterFactory : 필드의 타입이나 어노테이션 정보를 활용할 수 있는 포맷터