package chess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Observable;

@Service
public class Observer {

    private SimpMessagingTemplate webSocket;
    private Observable observable;

    @Autowired
    public Observer(Observable observable,SimpMessagingTemplate webSocket ) {
        this.observable = observable;
        this.webSocket = webSocket;
        observable.addObserver(
                (o, arg) ->
                        webSocket.convertAndSend("/topic/greetings",new ChessMessage((String) arg)));
    }
}
