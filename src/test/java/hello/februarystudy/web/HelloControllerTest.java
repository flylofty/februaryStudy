package hello.februarystudy.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class) //Junit5에는 없다.
@ExtendWith(SpringExtension.class) // Junit5에서는 이것을 쓴다. ... 1
@WebMvcTest(controllers = HelloController.class) // ... 2 여러 스프링테스트 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
class HelloControllerTest {

    @Autowired // ... 3, 스프링이 관리하는 Bean 주입
    private MockMvc mvc; // ... 4 웹 API 를 테스트할 때 사용

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // ... 5, MocMvc 를 통해서 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // ... 6, HTTP 의 Header 의 Status 를 검증
                .andExpect(content().string(hello)); // ... 7, 컨트롤러에서 hello 를 리턴하는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))) // API 테스트할 때 사용될 요청 파라미터를 설정함, String 만 허용
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath, JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount))); // $ 을 기준으로 필드명을 명시함
    }
}