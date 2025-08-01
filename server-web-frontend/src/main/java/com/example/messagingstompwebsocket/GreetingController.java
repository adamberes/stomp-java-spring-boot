package com.example.messagingstompwebsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.util.HtmlUtils;

@Slf4j
@Controller
public class GreetingController {


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {

		String s = "Hello, " + HtmlUtils.htmlEscape(message.getName() + "!"); //Preventing XSS Attacks
		log.info("Message: {}",message.getName());

		return new Greeting(s);
	}

}
