package game;

public class Player {

    private String name;
    private String boardIcon;
    private int x, y;

    private boolean completed;

    public Player(String name, String boardIcon, int x, int y) {
        this.name = name;
        this.boardIcon = boardIcon;
        this.x = x;
        this.y = y;
        this.completed = false;
    }

    public void move(int distX, int distY) {
        System.out.println("Called");
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
        setX(getX() + distX);
        setY(getY() + distY);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoardIcon() {
        return boardIcon;
    }

    public void setBoardIcon(String boardIcon) {
        this.boardIcon = boardIcon;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
