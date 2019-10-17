package com.journaldev.spring.model;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.PublicMetricsAutoConfiguration;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.EndpointHandlerMapping;
import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
import org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import({ EndpointAutoConfiguration.class, PublicMetricsAutoConfiguration.class,
		HealthIndicatorAutoConfiguration.class })
public class ActuateConfig {
	@Bean
	@Autowired
	public EndpointHandlerMapping endpointHandlerMapping(Collection<? extends MvcEndpoint> endpoints) {
		return new EndpointHandlerMapping(endpoints);
	}

	@Bean
	@Autowired
	public EndpointMvcAdapter metricsEndPoint(MetricsEndpoint delegate) {
		return new EndpointMvcAdapter(delegate);
	}

	@Bean
	@Autowired
	public HealthMvcEndpoint healthMvcEndpoint(HealthEndpoint delegate) {
		return new HealthMvcEndpoint(delegate, false);
	}
}
