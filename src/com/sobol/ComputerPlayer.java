package src.com.sobol;

/**
 * Created with IntelliJ IDEA.
 * User: Fawkes
 * Date: 20.11.13
 * Time: 11:44
 * To change this template use File | Settings | File Templates.
 */
public class ComputerPlayer extends Player {

    public ComputerPlayer(char sign) {
        super("Computer",sign);
    }

    @Override
    public void makeAMove(GameField gameField) {

    }

    /*public int[] Analyse(GameField gameField) {

        int lastMoveLine = gameField.getLastMoveLine();
        int lastMoveColumn = gameField.getLastMoveLine();
        int fieldSize = gameField.getFIELD_SIZE();
        //проверка диагонали \
        int count = 0;
        int i = lastMoveLine, j = lastMoveColumn, m = lastMoveLine, n = lastMoveColumn;
        while(true) {
            if (--i >= 0 && --j >= 0 && field[i][j] == sign) {
                count++;
            }
            else if (++m < fieldSize && ++n < fieldSize && field[m][n] == sign) {
                count++;
            }
            else if (count >= gameField.getSCORE_TO_WIN()-2) {
                return true;
            }
            else {
                break;
            }
        }

        //проверка диагонали /
        count = 1;
        i = lastMoveLine;
        j = lastMoveColumn;
        m = lastMoveLine;
        n = lastMoveColumn;
        while(true) {
            if (--i >= 0 && ++j > fieldSize && field[i][j] == sign) {
                count++;
            }
            else if (++m < fieldSize && --n >= 0 && field[m][n] == sign) {
                count++;
            }
            else if (count >= SCORE_TO_WIN) {
                return true;
            }
            else {
                break;
            }
        }

        //проверка вертикали
        count = 1;
        i = lastMoveLine;
        j = lastMoveColumn;
        m = lastMoveLine;
        n = lastMoveColumn;
        while(true) {
            if (--i >= 0 && field[i][j] == sign) {
                count++;
            }
            else if (++m < fieldSize && field[m][n] == sign) {
                count++;
            }
            else if (count >= SCORE_TO_WIN) {
                return true;
            }
            else {
                break;
            }
        }

        //проверка горизонтали
        count = 1;
        i = lastMoveLine;
        j = lastMoveColumn;
        m = lastMoveLine;
        n = lastMoveColumn;
        while(true) {
            if (--j >= 0 && field[i][j] == sign) {
                count++;
            }
            else if (++n < fieldSize && field[m][n] == sign) {
                count++;
            }
            else if (count >= SCORE_TO_WIN) {
                return true;
            }
            else {
                break;
            }
        }
        return false;
    }*/
}