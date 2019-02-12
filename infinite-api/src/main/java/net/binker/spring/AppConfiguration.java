package net.binker.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class AppConfiguration extends WebMvcConfigurationSupport {

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		//registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login");
//		//super.addInterceptors(registry);
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
	}

}
