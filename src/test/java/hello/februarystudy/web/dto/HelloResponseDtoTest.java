package hello.februarystudy.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); // ...1, 2, 검증하고 싶은 대상을 메소드 인자로 받음

        // 메소드 체이닝이 지원되어 isEqualTo 와 같이 메소드를 이어서 사용할 수 있음
        // assertThat 에 있는 값과 isEqualTo 의 값을 비교해서 같을 때만 성공
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}