package yeolmok.rsc.global.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticInterceptor implements HandlerInterceptor {

    private final String SESSION_KEY = "SESSION";

    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            Optional<Cookie> sessionCookie = Arrays.stream(cookies)
                    .filter(cookie -> SESSION_KEY.equals(cookie.getName()))
                    .findAny();

            log.info("session cookie: {}", sessionCookie);

            if (sessionCookie.isPresent()) {
                Cookie session = sessionCookie.get();
                String decodedSessionValue = new String(Base64.getDecoder().decode(session.getValue().getBytes()));
                // 세션 쿠키의 값은 세션 ID를 BASE64 인코딩한 값
                log.info("decoded value: {}", decodedSessionValue);

                if (!redisTemplate.hasKey("spring:session:sessions:" + decodedSessionValue)) {
                    log.warn("세션 쿠키 O, 저장소에 세션 쿠키 X");
                    return false;
                }
            }
        }

        return true;
    }
}
