package SimpleWs;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

/**
 * Controller for the connection to the STOMP server.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class StompController implements StompSessionHandler  {
    @Value("${app-websocket}")
    String websocket;
    @Value("${app-topic}")
    String topic;
    private StompSession stompSession;

    @EventListener(value = ApplicationReadyEvent.class)
    public void connect() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        try {
            stompSession = stompClient
                    .connectAsync(websocket, this)
                    .get();
        } catch (Exception e) {
            log.info("Connection failed: {}", e.getMessage());
        }
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Connection to STOMP server established.\nSession: {}\nHeaders: {}\n", session, connectedHeaders);

        subscribe(topic);
    }

    public void subscribe(String topicId) {
        log.info("Subscribing to topic: {}", topicId);
        stompSession.subscribe(topicId, this);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        if (payload == null) {
            log.info("Received null payload. Headers: {}", headers);
            return;
        }

        try {
            Greeting message = (Greeting) payload;
            log.info("Received message: {}", message.getContent());
        } catch (ClassCastException e) {
            log.info("Payload type mismatch. Received: {}", payload.getClass());
        }
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Greeting.class;  // Changed to match what you expect to receive
    }
    /**
     * Unsubscribe and close connection before destroying this instance (e.g. on application shutdown).
     */
    @PreDestroy
    void onShutDown() {
        if (stompSession != null) {
            stompSession.disconnect();
        }
    }
    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.info("Transport error occurred: {}", exception.getMessage());
        if (!session.isConnected()) {
            log.info("Attempting to reconnect...");
            connect();
        }
    }
    @Override
    public void handleException(StompSession session, StompCommand command,
                                StompHeaders headers, byte[] payload, Throwable exception) {
        log.info("Handling exception: {}", exception.getMessage());
    }
}