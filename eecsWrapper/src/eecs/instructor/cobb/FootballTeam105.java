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

public abstract class FootballTeam105 extends FootballGame105 {
	public FootballGame currentGame;
	public int score=0;
	public static String offPlay,defPlay;
    //public static String play="";
	//public static String defensivePlay="";
	public int offensiveDrives;
	public boolean isTestingOn;
	public String name;
	public FootballTeam105 myOpponent;
	public abstract void offensiveStrategy();
	
	public  String getOffensivePlay()
	{
		return offPlay;
	}
	
	public  String getDefensivePlay()
	{
		return defPlay;
	}
	
	public abstract void defensiveStrategy();
	
	
	public abstract void checkForTesting();
	
	public void setMyOpponent(FootballTeam105 opponent)
	{
		myOpponent=opponent;
	}
	
	public FootballTeam105 getMyOpponent()
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
