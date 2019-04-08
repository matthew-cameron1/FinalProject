package entity;
/**
*Entity class to organize different entities within the GUI game
*/
public class Entity {
    /**
    *Private variables for the Entity class, keeping track of position (x,y) and identity
    */
    private String id;
    private int x, y;
    
    /**
    *Entity constructor 
    *@param id: String, x: int, y: int
    */
    public Entity(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
    *Changes the x and y coordinates of the entity based on the input values of move
    *@param distX: int, distY: int
    */
    public void move(int distX, int distY) {
        setX(getX() + distX);
        setY(getY() + distY);
    }
    
    //Getters and Setters:
    
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
