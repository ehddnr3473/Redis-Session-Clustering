package yeolmok.rsc.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession // Redis를 HTTP 세션 저장소로 사용
public class SessionConfig {}
