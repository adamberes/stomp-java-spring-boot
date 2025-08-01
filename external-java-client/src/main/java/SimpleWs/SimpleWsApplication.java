package SimpleWs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

//https://github.com/GleidsonSilva/springboot-ws-client/blob/master/src/main/java/gs/ws/SimpleWsHandler.java
/**
 * Sample application for understanding how does springboot can be used as a WS client.
 */
@SpringBootApplication
public class SimpleWsApplication {

    private final String webSocketUri = "ws://localhost:8080/gs-guide-websocket";

    public static void main(String[] args) {
        SpringApplication.run(SimpleWsApplication.class, args);
    }

    @Bean
    public WebSocketConnectionManager wsConnectionManager() {

        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient();
        //Generates a web socket connection
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                standardWebSocketClient,
                new SimpleWsHandler(), //Must be defined to handle messages
                this.webSocketUri);

        //Will connect as soon as possible
        manager.setAutoStartup(true);

        return manager;
    }
}