package chess.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "singleton")
public class Counter {
    private int count;

    public void incCount() {
        count++;
    }

    public void decCount() {
        count--;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
