package com.example.messagingstompwebsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Value("${app-simple-broker}")
	String simpleBroker;
	@Value("${app-application-prefix}")
	String applicationPrefix;
	@Value("${app-endpoint}")
	String endpoint;
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(simpleBroker);
		config.setApplicationDestinationPrefixes(applicationPrefix);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		log.info("registerStompEndpoints");
		registry.addEndpoint(endpoint);
	}

}
