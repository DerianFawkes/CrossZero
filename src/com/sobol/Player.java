package src.com.sobol;

/**
 * Created with IntelliJ IDEA.
 * User: Fawkes
 * Date: 20.11.13
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public abstract class Player {

    private String name;
    private char playerSign;
    private int winCount;


    public Player () {
        name = "No name";
        playerSign = ' ';
        winCount = 0;
    }

    public Player (String name, char sign) {
        this.name = name;
        playerSign = sign;
        winCount = 0;
    }

    public abstract void makeAMove(GameField gameField);

    public char getPlayerSign () {
        return playerSign;
    }

    public String getPlayerName () {
        return name;
    }
}
