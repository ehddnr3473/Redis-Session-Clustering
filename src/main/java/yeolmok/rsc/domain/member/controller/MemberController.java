package yeolmok.rsc.domain.member.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yeolmok.rsc.domain.member.vo.MemberVO;

@RestController
public class MemberController {

    @PostMapping("/login")
    public String login(HttpSession session,
                        @RequestBody MemberVO memberVO) {
        session.setAttribute("memberName", memberVO.getName());
        session.setMaxInactiveInterval(30);

        return "로그인 성공: " + memberVO.getName();
    }
}
