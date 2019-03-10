package entity;

public class Player extends Entity {

    private String boardIcon;

    private boolean completed;

    public Player(String name, String boardIcon, int x, int y) {
        super(name, x, y);
        this.boardIcon = boardIcon;
        this.completed = false;
    }

    @Override
    public void move(int distX, int distY) {
        if (distX > 0) {
            setBoardIcon("> ");
        }
        if (distX < 0) {
            setBoardIcon(" <");
        }
        if (distY > 0) {
            setBoardIcon("v ");
        }
        if (distY < 0) {
            setBoardIcon("^ ");
        }
    }

    public String getBoardIcon() {
        return boardIcon;
    }

    public void setBoardIcon(String boardIcon) {
        this.boardIcon = boardIcon;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
