package SimpleWs;


import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Handle server connection in low level
 */
public class SimpleWsHandler implements WebSocketHandler {

    /**
     * Called when WS connects to the server.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }

    /**
     * Main method to handle server messages.
     * Do not use low level, dont mix up with Somp
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    }

    /**
     * Error handling.
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    }

    /**
     * Called when WS is closed.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        // TODO Auto-generated method stub
        return false;
    }

}