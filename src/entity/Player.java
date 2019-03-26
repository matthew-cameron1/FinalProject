package entity;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
*Player class to keep track of individual scores, time, and sprites
*/
public class Player extends Entity {

    /**
    *Private instance variables for Player: Storing individual times and Score-related data
    */
    private String boardIcon;

    private boolean completed;
    
    private PlayerTimer timer = new PlayerTimer();
    
    private double recentTime;

    private Map<String, Double> playerScores = new HashMap<>();

    /**
    *Constructor with initial conditions and identification of player
    *@param name: String, boardIcon: String, x: int, y: int
    */
    public Player(String name, String boardIcon, int x, int y) {
        super(name, x, y);
        this.boardIcon = boardIcon;
        this.completed = false;
    }
    
    //getters and setters
    
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
        timer.stop();
        DecimalFormat df2 = new DecimalFormat(".##");
        addScore("Main level",  Double.valueOf(df2.format(timer.getTime() / 60.0)));
        this.setRecentTime( Double.valueOf(df2.format(timer.getTime() / 60.0)));
    }
    
    public PlayerTimer getTimer() {
        return timer;
    }
    
    public void setTimer(PlayerTimer timer){
        this.timer = timer;
    }
    
    public double getRecentTime(){
        return recentTime;
    }
    
    public void setRecentTime(double recentTime){
        this.recentTime = recentTime;
    }
    
    public Map<String, Double> getPlayerScores() {
        return playerScores;
    }
    
    public void addScore(String levelName, double recentTime) {
        this.playerScores.put(levelName, recentTime);
    }
    
    /**
    *Returns the level in which the player performed best
    */
    public String getBestLevel() {
        double best = 1000000.0;
        String levelName = "";

        for (Map.Entry<String, Double> entry : playerScores.entrySet()) {
            if (entry.getValue() < best) {
                best = entry.getValue();
                levelName = entry.getKey();
            }
        }
        return levelName;
    }
    /**
    *Gets the time for the specefic level
    */
    public double getTimeForLevel(String level) {
        return playerScores.get(level);
    }
}

