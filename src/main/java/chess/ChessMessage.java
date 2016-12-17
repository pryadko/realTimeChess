package chess;

public class ChessMessage {
    private String name;

    public ChessMessage() {
    }

    public ChessMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}