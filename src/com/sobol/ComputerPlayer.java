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
        int[] coordinates = new int[2];
        coordinates = analyse(gameField);
        gameField.setFieldElement(coordinates[0], coordinates[1], getPlayerSign());
        gameField.incrementMoves();
    }

    public int[] analyse(GameField gameField) {
        int[] analyseResult = new int[2];
        int lastValue = 0;
        int fieldSize = gameField.getFIELD_SIZE();

        int cellPoints =0;
        for(int i=0; i<fieldSize; i++) {
            for (int j=0; j<fieldSize; j++) {
                if (gameField.getFieldElement(i,j) == ' ') {
                    cellPoints = cellAssessment(i,j, gameField);
                    if(cellPoints > lastValue) {
                        lastValue = cellPoints;
                        analyseResult[0] = i;
                        analyseResult[1] = j;
                    }
                }
            }
        }
    return analyseResult;
    }//analyse

    private int cellAssessment(int line, int column, GameField gameField) {
        int result =0;
        int defendPoints =0;
        int attackPoints =0;
        int G = 0;
        int Q =0;

        defendPoints = checkAdverseDiagonal(line,column,'X',gameField) +
                checkMainDiagonal(line,column,'X',gameField) +
                checkHorizontal(line,column,'X',gameField) +
                checkVertical(line,column,'X',gameField);

        attackPoints = checkAdverseDiagonal(line,column,'O',gameField) +
                checkMainDiagonal(line,column,'O',gameField) +
                checkHorizontal(line,column,'O',gameField) +
                checkVertical(line,column,'O',gameField);

        if (attackPoints >= gameField.getSCORE_TO_WIN()) {
            G = Integer.MAX_VALUE;
        } else {
            G = FactorialUtil.factorial(attackPoints+2);
        }
        Q = FactorialUtil.factorial(defendPoints+2);

        result = G + Q;
        return result;
    }
    //проверка диагонали \
    private int checkMainDiagonal(int line,int column,char sign, GameField gameField) {
        int count = 0;

        for(int i = line - gameField.getSCORE_TO_WIN()-1; i < (line + gameField.getSCORE_TO_WIN()-1); i++) {
            for(int j = column - gameField.getSCORE_TO_WIN()-1; j < column + gameField.getSCORE_TO_WIN()-1; j++) {
                if (i > 0 && j > 0 && i < gameField.getFIELD_SIZE() && j < gameField.getFIELD_SIZE()) {
                    if (gameField.getFieldElement(i,j) == sign) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    //проверка диагонали /
    private int checkAdverseDiagonal(int line,int column,char sign, GameField gameField) {
        int count = 0;

        for(int i = line - gameField.getSCORE_TO_WIN()-1; i < (line + gameField.getSCORE_TO_WIN()-1); i++) {
            for(int j = column + gameField.getSCORE_TO_WIN()-1; j < column + gameField.getSCORE_TO_WIN()-1; j--) {
                if (i > 0 && j > 0 && i < gameField.getFIELD_SIZE() && j < gameField.getFIELD_SIZE()) {
                    if (gameField.getFieldElement(i,j) == sign) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    //проверка вертикали
    private int checkVertical(int line,int column,char sign, GameField gameField) {
        int count = 0;

        for(int i = line - gameField.getSCORE_TO_WIN()-1; i < (line + gameField.getSCORE_TO_WIN()-1); i++) {
            int j = column;
            if (i > 0 && j > 0 && i < gameField.getFIELD_SIZE() && j < gameField.getFIELD_SIZE()) {
                if (gameField.getFieldElement(i,j) == sign) {
                    count++;
                }
            }
        }
        return count;
    }

    //проверка горизонтали
    private int checkHorizontal(int line,int column,char sign, GameField gameField) {
        int count = 0;

        int i = line;
        for(int j = column - gameField.getSCORE_TO_WIN()-1; j < column + gameField.getSCORE_TO_WIN()-1; j++) {
            if (i > 0 && j > 0 && i < gameField.getFIELD_SIZE() && j < gameField.getFIELD_SIZE()) {
                if (gameField.getFieldElement(i,j) == sign) {
                    count++;
                }
            }
        }

        return count;
    }
}//class