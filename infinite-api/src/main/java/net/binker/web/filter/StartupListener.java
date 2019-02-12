package net.binker.web.filter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class StartupListener implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger(StartupListener.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//初始化

	}

	/**
	 * Shutdown servlet context (currently a no-op method).
	 *
	 * @param servletContextEvent The servlet context event
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.info("destroy startupListener context...");
	}

}
