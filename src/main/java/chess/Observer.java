package chess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Observable;

@Service
public class Observer {

    @Autowired
    private SimpMessagingTemplate webSocket;
    private Observable observable = new EventSource();

    public Observer() {
        observable.addObserver(
                (o, arg) ->
                        webSocket.convertAndSend("/topic/greetings",new ChessMessage((String) arg)));
    }
}
