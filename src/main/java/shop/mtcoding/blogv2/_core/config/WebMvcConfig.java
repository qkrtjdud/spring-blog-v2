package shop.mtcoding.blogv2._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/*리스너가 web.xml 를 추가 시켜 준다 생각하면 됨*/
@Configuration /* 설정파일은 @Configuration */
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        WebMvcConfigurer.super.addResourceHandlers(registry); /* 기존에 하던 일은 그대로 둬여함! */

        registry.addResourceHandler("/images/**") /* 외부에서 images 로 들어오면 일로 보내! 라고 알려줌 */
                .addResourceLocations("file:" + "./images/")
                .setCachePeriod(10) // 초 단위
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

}
