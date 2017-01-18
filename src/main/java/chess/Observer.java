package chess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Observable;

@Service
public class Observer {

    @Autowired
    public Observer(Observable observable, SimpMessagingTemplate webSocket) {
        observable.addObserver(
                (o, arg) ->
                        webSocket.convertAndSend("/topic/moves", arg));
    }
}
