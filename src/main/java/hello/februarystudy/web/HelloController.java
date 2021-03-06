package hello.februarystudy.web;

import hello.februarystudy.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, // RequestParam 외부에서 API 로 넘긴 파라미터를 가져오는 어노테이션
                                     @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }
}
