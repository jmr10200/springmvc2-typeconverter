package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class IpPort {

    // lombok 의 @EqualsAndHashCode 는 모든 필드를 사용해서 equals(), hashcode() 생성한다.
    // 즉, 모든 필드의 값이 같다면 a.equals(b) 의 결과가 true 가된다.

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
