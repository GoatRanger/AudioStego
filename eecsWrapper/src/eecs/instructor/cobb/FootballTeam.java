/*
 * Created on Dec 24, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package eecs.instructor.cobb;

/**
 * @author Owner
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

public abstract class FootballTeam extends FootballGame {
	public static FootballGame currentGame;
	public int score=0;
	public int offensiveDrives;
	public boolean isTestingOn;
	public String name;
	public FootballTeam myOpponent;
	public abstract String offensiveStrategy(int down, int yardsToGo);
	
	
	public abstract String defensiveStrategy(int down, int yardsToGo);
	
	public abstract void checkForTesting();
	
	public void setMyOpponent(FootballTeam opponent)
	{
		myOpponent=opponent;
	}
	
	public FootballTeam getMyOpponent()
	{
		return myOpponent;
	}
	public  String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	public int getScore()
	{
		return score;
	}
	
	public void addToScore(int score)
	{
		this.score=this.score+score;
	}
	
	public int getMyScore(){
		return this.score;
	}
	
	public int getMyOpponentsScore(){
		return myOpponent.getScore();
	}
	
	public static void main(String[] args) {
	}
}
