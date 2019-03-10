package entity;

public class Entity {
    private String id;
    private int x, y;

    public Entity(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void move(int distX, int distY) {
        setX(getX() + distX);
        setY(getY() + distY);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
