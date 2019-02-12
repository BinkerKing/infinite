package net.binker.web.filter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
@Order(1)
public class WebLogAspect {
	ObjectMapper mapper = new ObjectMapper();


	@Pointcut("execution(* net.riking..controller.*.*(..))")
	public void mapperCut() {
	}

	public WebLogAspect() {
	}

	@Around(value = "mapperCut()")
	public Object cacheProxy(ProceedingJoinPoint point) throws Throwable {
		return null;
	}
}
