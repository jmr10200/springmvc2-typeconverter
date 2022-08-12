package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        log.info("convert source={}", source);
        return source.getIp() + ":" + source.getPort();
    }
}
// 이렇게 타입 컨버터를 이렇게 하나하나 구현해야한다면, 직접 컨버터를 만드는 것과 다를바가 없다.
// 타입 컨버터를 등록하고 관리하면서 편리하게 변환 기능을 제공하는 역할을 하는 것이 필요하다.

// 참고 1
// 스프링은 문자, 숫자, 불린, Enum 등 일반적인 타입에 대한 대부분의 컨버터를 기본 제공한다.
// Converter, ConverterFactory, GenericConverter 의 구현체를 찾아보면 확인 가능하다.

// 참고 2
// 스프링은 용도에 따라 다양한 방식의 타입컨버터를 제공한다
// Converter : 기본 타입 컨버터
// ConverterFactory : 전체 클래스 계층 구조가 필요할 때
// GenericConverter : 정교한 구현, 대상 필드의 어노테이션 정보 사용 가능
// ConditionalGenericConverter : 특정 조건이 참인 경우에만 실행