package entity;

import javafx.animation.AnimationTimer;
import java.util.ArrayList;

public class Player extends Entity {

    private String boardIcon;

    private boolean completed;
    
    private AnimationTimer timer = new AnimationTimer(); 
    
    private double recentTime;
    
	private ArrayList<double> playerScores = new ArrayList<double>();

    public Player(String name, String boardIcon, int x, int y) {
        super(name, x, y);
        this.boardIcon = boardIcon;
        this.completed = false;
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
    
    public AnimationTimer getTimer(){
        return timer;
    }
    
    public void setTimer(AnimationTimer timer){
        this.timer = timer;
    }
    
    public double getRecentTime(){
        return recentTime;
    }
    
    public void setRecentTime(double recentTime){
        this.recentTime = recentTime;
    }
    
    public ArrayList<double> getPlayerScores(){
        return playerScores;
    }
    
    public void addScore(double recentTime){
        this.playerScores.add(recentTime);
    }
    
    public double getTopScore(){
		double highest = 0;
		for(double recentTime : playerScores){
			if (highest == 0){
				highest = recentTime;			
			} else if (recentTime>highest){
				highest = recentTime;			
			}			
		}
		return highest;	
	}
    
    
}

