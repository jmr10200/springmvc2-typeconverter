package hello.typeconverter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 스프링에 컨버터 등록하기
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
        // 스프링은 내부에서 ConversionService 를 제공한다.
        // WebMvcConfigurer 가 제공하는 addFormatters() 를 이용해서 컨버터를 등록한다.

        // 등록 후 실행결과 1 (로그)
        // h.t.converter.StringToIntegerConverter   : convert source=20
        // data = 20
        // 등록한 컨버터가 동작함을 알 수 있다.
        // 그런데, 생각해보면 등록전에도 잘 동작했었다. 이는 스프링이 기본으로 제공하는 컨버터가 동작했기 때문이다.
        // 컨버터를 추가하면, 추가한 컨버터가 기본으로 제공되는 컨버터보다 높은 우선순위를 가진다.

        // 실행결과 2 (로그)
        // h.t.converter.StringToIpPortConverter    : convert source=127.2.2.2:8080
        // ipPort IP = 127.2.2.2
        // ipPort PORT = 8080
        // 쿼리스트링이 객체타입(IpPort)으로 잘 변환된 것을 확인할 수 있다.
        // -> 처리과정
        // @RequestParam 은 @RequestParam 을 처리하는 ArgumentResolver 인
        // RequestParamMethodArgumentResolver 에서 ConversionService 를 사용해서 타입변환 한다.
        // 부모클래스와 다양한 외부클래스를 호출하는 등 복잡한 내부 과정을 거친다.
    }
}
