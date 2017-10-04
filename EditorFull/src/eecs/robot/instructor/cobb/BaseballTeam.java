package eecs.robot.instructor.cobb;

public abstract class BaseballTeam extends BaseballGame {
  public BaseballGame currentGame;
  public int score = 0;
  public static String hittingSelection, pitchingSelection;
  public int atBats;
  public boolean isTestingOn;
  public String name;
  public BaseballTeam myOpponent;

  public abstract void BattingStrategy();

  public String getHittingSelection() {
    return hittingSelection;
  }

  public String getPitchingSelection() {
    return pitchingSelection;
  }

  public abstract void pitchingStrategy();

  

  public void setMyOpponent(BaseballTeam opponent) {
    myOpponent = opponent;
  }

  public BaseballTeam getMyOpponent() {
    return myOpponent;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  public int getMyOpponentsScore() {
    return myOpponent.getScore();
  }

  public void addToScore(int score) {
    this.score += score;
  }

  public int getMyScore() {
    return this.score;
  }

  public static void main(String[] args) {
    startGame();
  }
}