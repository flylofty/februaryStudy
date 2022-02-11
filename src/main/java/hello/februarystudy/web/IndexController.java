package hello.februarystudy.web;

import hello.februarystudy.config.oauth.LoginUser;
import hello.februarystudy.config.oauth.dto.SessionUser;
import hello.februarystudy.service.posts.PostsService;
import hello.februarystudy.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    //@GetMapping("/")
    public String index(Model model) { // Model 을 통해 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 작성된 CustomOAuth2UserService 에서 로그인 성공 시 세션에 SessionUser 를 저장하도록 구성함

        if (user != null) { // 세션에 저장된 값이 있을 때만 model 에 userName 으로 등록함, 세션에 저장된 값이 없으면 model 엔 아무런 값이 없는 상태이므로 로그인 버튼이 보이게 됨
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/")
    public String indexV2(Model model, @LoginUser SessionUser user) { // 기존에 (User) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선 됨

        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) { // 세션에 저장된 값이 있을 때만 model 에 userName 으로 등록함, 세션에 저장된 값이 없으면 model 엔 아무런 값이 없는 상태이므로 로그인 버튼이 보이게 됨
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
