package chess;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChessController {

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public ChessMessage  message(ChessMessage message) {
        
        return message;
    }
}
