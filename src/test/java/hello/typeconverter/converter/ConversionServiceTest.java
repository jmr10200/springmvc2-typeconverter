package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        // 등록
        // DefaultConversionService 는 ConversionService 인터페이스를 구현했는데, 컨버터 등록 기능도 제공한다.
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        // 사용
        assertThat(conversionService.convert("100", Integer.class)).isEqualTo(100);
        assertThat(conversionService.convert(500, String.class)).isEqualTo("500");
        assertThat(conversionService.convert("127.1.1.1:8080", IpPort.class)).isEqualTo(new IpPort("127.1.1.1", 8080));
        assertThat(conversionService.convert(new IpPort("127.0.0.1", 8080), String.class)).isEqualTo("127.0.0.1:8080");
    }
}
/* ConversionService */
// 타입 컨버터를 하나하나 직접 찾아서 타입변환에 사용하는 것은 번거롭다.
// 그래서 스프링은 개별 컨버터를 모아두고 사용할수 있는 기능을 제공하는데, 컨버전서비스 이다.

// 등록과 사용의 분리
// 컨버터를 등록할 때는 타입 컨버터를 명확하게 알아야 한다. 반면에 사용할 때는, 타입 컨버터를 몰라도 된다.
// 타입 컨버터 들은 모두 컨버전서비스 내부에 숨어서 제공된다.
// 즉, 사용자는 컨버전서비스 인터페이스에만 의존하면 된다.
// 물론, 컨버전 서비스를 등록하는 부분과 사용하는 부분을 분리하고 의존관계 주입을 사용해야 한다.

/** 인터페이스 분리 원칙 ISP : Interface Segregation Principle */
// 인터페이스 분리 원칙은 클라이언트가 자신이 이용하지 않는 메소드에 의존하지 않아야 한다.
//   DefaultConversionService 는 다음 두 인터페이스를 구현했다.
//    ConversionService : 컨버터 사용
//    ConverterRegistry : 컨버터 등록
// 이렇게 인터페이스를 분리하면 사용하는 클라이언트와 등록 및 관리하는 클라이언트의 관심사를 분리할 수 있다.
// 컨버터를 사용하는 클라이언트는 ConversionService 만 의존하면 되므로, 등록 및 관리는 몰라도 된다.
// 즉, 컨버터를 사용하는 클라이언트는 꼭 필요한 메소드만 알게된다. 이렇게 인터페이스를 분리하는 것을 ISP 라 한다.

// 스프링은 내부에서 ConversionService 를 사용해서 타입변환한다.
// 예를들어, @RequestParam 같은 곳에서 이 기능을 사용한다.