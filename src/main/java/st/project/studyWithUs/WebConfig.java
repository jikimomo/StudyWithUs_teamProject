package st.project.studyWithUs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import st.project.studyWithUs.argumentresolver.LoginMemberArgumentResolver;
import st.project.studyWithUs.interceptor.AdminInterceptor;
import st.project.studyWithUs.interceptor.LoginCheckIntercept;

import java.util.List;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //인터셉트 순서 체인식으로 적용. order(2) 가 있으면 .order(1)을 수행하고 다음 인터셉트 적용.
        registry.addInterceptor(new LoginCheckIntercept())
                .order(1) //첫번째 인터셉트
                .addPathPatterns("/**") //하위 전부 허용하되,
                .excludePathPatterns("/","/css/**", "/*.ico", "/error", "/signUp", "/login",
                        "/findID","/findPW" , "/kakaoLogin" ,"/logout", "/images/*.jpg","/images/*.png",
                         "/loginCheck", "/explain", "/validateMem", "/loginUser", "/findAllTeams",
                        "../images/*.jpg","/updateUserTeam","/validateEmail", "/board", "/findComment", "/deleteContent", "/boardSearch", "/detailContent",
                        "/detailContent/**", "/checkUser", "/pageable", "/saveContent", "/write","/getInfo","/naverLogin",
                        "/getimg","/getCheck","/teamImage/*","/adminImage/*","/css/*","/updateCommentCheck","/deleteCommentCheck","/captcha/*"
                        ); // url 에는 인터셉트 먹이지마.

        registry.addInterceptor(new AdminInterceptor())
                .order(2)
                .addPathPatterns("/userList", "/adminPage", "/teamList")
                .excludePathPatterns();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //만든 애노테이션 추가. @Login
        resolvers.add(new LoginMemberArgumentResolver());
    }

}
