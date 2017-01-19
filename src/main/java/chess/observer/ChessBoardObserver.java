package chess.observer;

import chess.hardware.ChessBoardEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChessBoardObserver {

    @Autowired
    public ChessBoardObserver(ChessBoardEventSource chessBoardEventSource, SimpMessagingTemplate webSocket) {
        chessBoardEventSource.addObserver(
                (o, arg) ->
                        webSocket.convertAndSend("/topic/moves", arg));
    }
}
