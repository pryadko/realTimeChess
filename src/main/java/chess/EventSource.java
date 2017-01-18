package chess;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventSource extends Observable implements Runnable {
    private static List<String> MOVES = Arrays.asList(
            "start"
            , "e2-e4", "c7-c5"
            , "g1-f3", "d7-d6"
            , "d2-d4", "c5-d4"
            , "f3-d4", "g8-f6"
            , "f2-f3", "e7-e5"
            , "d4-b3", "f8-e7"
            , "c2-c4", "a7-a5"
            , "c1-e3", "a5-a4"
            , "b3-c1", "b0-0"
            , "b1-c3", "d8-a5"
            , "d1-d2", "b8-a6"
            , "f1-e2", "a6-c5"
            , "w0-0", "c8-d7"
            , "a1-b1", "f8-c8"
            , "b2-b4", "-a4-b3"
            , "a2-b3", "a5-d8"
            , "c1-d3", "c5-e6"
            , "d3-b4", "d7-c6"
            , "f1-d1", "h7-h5"
            , "e2-f1", "h5-h4"
            , "d2-f2", "f6-d7"
            , "g2-g3", "a8-a3"
            , "f1-h3", "c8-a8"
            , "b4-c2", "a3-a6"
            , "c2-b4", "a6-a5"
            , "b4-c2", "b7-b6"
            , "d1-d2", "d8-c7"
            , "b1-d1", "e7-f8"
            , "g3-h4", "e6-f4"
            , "e3-f4", "e5-f4"
            , "h3-d7", "c7-d7"
            , "c2-b4", "a5-a3"
            , "b4-c6", "d7-c6"
            , "c3-b5", "a3-b3"
            , "b5-d4", "c6-c4"
            , "d4-b3", "c4-b3"
            , "f2-e2", "f8-e7"
            , "g1-g2", "b3-e6"
            , "h4-h5", "a8-a3"
            , "d2-d3", "a3-a2"
            , "d3-d2", "a2-a3"
            , "d2-d3", "a3-a7"
            , "d3-d5", "a7-c7"
            , "e2-d2", "e6-f6"
            , "d5-f5", "f6-h4"
            , "d1-c1", "c7-a7"
            , "d2-f4", "a7-a2"
            , "g2-h1", "h4-f2"
            , "c1-c8", "g8-h7"
            , "f4-h6"
    );

    private static List<Integer> TIMES = Arrays.asList(
            2
            ,2
            ,2
            ,10
            ,50
    );

    private Iterator<Integer> timer = TIMES.iterator();

    private String moved;

    public EventSource() {
        new Thread(this).start();
        moved = "";
    }

    @Override
    public void run() {
        Iterator<String> iterator = MOVES.iterator();
        waitASecond(50);
        while (iterator.hasNext()) {
            waitASecond(getTime());
            setChanged();
            String move = iterator.next();
            moved += move + " ";
            notifyObservers(move);
        }
    }

    private int getTime() {
        if (timer.hasNext()) return timer.next();
        timer = TIMES.iterator();
        return getTime();
    }

    private void waitASecond(int time) {
        try {
            Thread.sleep(time * 100);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
