package net.binker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication()
public class Application extends SpringBootServletInitializer {

	//如果需要通过打包的方式在web容器中进行部署，
	//则需要继承 SpringBootServletInitializer
	// 覆盖configure(SpringApplicationBuilder)方法
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		//热部署
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(Application.class, args);
	}

}

