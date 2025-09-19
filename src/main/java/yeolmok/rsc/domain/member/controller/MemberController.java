package yeolmok.rsc.domain.member.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yeolmok.rsc.domain.member.vo.MemberVO;

@RestController
public class MemberController {

    @PostMapping("/login")
    public String login(HttpSession session,
                        @RequestBody MemberVO memberVO) {
        session.setAttribute("member", memberVO);
        session.setMaxInactiveInterval(30 * 60);

        return "로그인 성공: " + memberVO.getName();
    }

    @GetMapping("/me")
    public String me(HttpSession session) {
        return "현재 로그인 사용자: " + ((MemberVO) session.getAttribute("member")).getName();
    }
}
