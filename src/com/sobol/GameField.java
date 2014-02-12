package src.com.sobol;

import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Fawkes
 * Date: 21.10.13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public class GameField implements GameProcess {

    private int FIELD_SIZE;
    private int SCORE_TO_WIN;
    private int MAX_MOVES_VALUE;

    public static final char playerX = 'X';
    public static final char playerO = 'O';


    private char[][] field;
    private static int[][] indexedField;

    private int lastMoveLine;
    private int lastMoveColumn;
    private int moves = 0;

    public GameField (int size) {
        FIELD_SIZE = size;
        if (size <= 5) {
            SCORE_TO_WIN = size;
        }
        else SCORE_TO_WIN = 5;
        MAX_MOVES_VALUE = FIELD_SIZE*FIELD_SIZE;

        field = new char[FIELD_SIZE][FIELD_SIZE];
        indexedField = new int[FIELD_SIZE][FIELD_SIZE];
        int count = 1;
        for(int i=0;i<FIELD_SIZE; i++) {
            for(int j=0;j<FIELD_SIZE;j++) {
                indexedField[i][j] = count;
                count++;
            }
            System.out.println();
        }
        makeDefaultField();
    }

    public void showField () {
        for(int i=0;i<FIELD_SIZE; i++) {
            for(int j=0;j<FIELD_SIZE;j++) {
                if (field[i][j] == ' ') {
                    System.out.printf("[%3d]", indexedField[i][j]);
                }
                else {
                    System.out.print("[ "+field[i][j]+" ]");
                }
            }
            System.out.println();
        }
    }

    public void showField (PrintWriter out) {
        out.println();
        for(int i=0;i<FIELD_SIZE; i++) {
            for(int j=0;j<FIELD_SIZE;j++) {
                if (field[i][j] == ' ') {
                    out.printf("[%3d]", indexedField[i][j]);
                }
                else {
                    out.print("[ "+field[i][j]+" ]");
                }
            }
            out.println();
        }
    }

    public void makeDefaultField () {
        int count = 0;
        for(int i=0;i<FIELD_SIZE; i++) {
            for(int j=0;j<FIELD_SIZE;j++) {
                field[i][j] = ' ';
                count++;
            }
        }
        moves = 0;
    }


    /*public boolean makeAMove (char currentPlayer) {
        Scanner in = new Scanner (System.in);
        int index;
        System.out.println("->Введите номер ячейки");
        do {

            index = in.nextInt()-1;
            if (index < 0 || index >= FIELD_SIZE*FIELD_SIZE) {
                System.out.println("->Некорректное значение, введите другой номер.");
            }
            else if (field[index/FIELD_SIZE][index%FIELD_SIZE] == playerX || field[index/FIELD_SIZE][index%FIELD_SIZE] == playerO){
                System.out.println("->Данная ячейка занята, введите другой номер.");
            }
            else {
                field[index/FIELD_SIZE][index%FIELD_SIZE] = currentPlayer;
                break;
            }
        }
        while (true);

        return checkWin(index / FIELD_SIZE, index % FIELD_SIZE, currentPlayer);
    }*/


    public int getFIELD_SIZE() {
        return FIELD_SIZE;
    }

    public char getFieldElement(int line, int column) {
        return field[line][column];
    }

    public void setFieldElement(int line, int column, char sign) {
        field[line][column] = sign;
    }

    public void setLastMove (int line, int column) {
        lastMoveLine = line;
        lastMoveColumn = column;
    }

    public int getLastMoveLine() {
        return lastMoveLine;
    }

    public int getLastMoveColumn() {
        return lastMoveColumn;
    }

    public int getSCORE_TO_WIN() {
        return SCORE_TO_WIN;
    }

    public void incrementMoves () {
        moves++;
    }

    @Override
    public Player switchTurn(Player currentPlayer, Player one, Player two) {
        if (currentPlayer.equals(one)) {
            return two;
        }
        else {
            return one;
        }

    }

    @Override
    public boolean checkWin(char sign) {
        //проверка диагонали \
        int count = 1;
        int i = lastMoveLine, j = lastMoveColumn, m = lastMoveLine, n = lastMoveColumn;
        while(true) {
            if (--i >= 0 && --j >= 0 && field[i][j] == sign) {
                count++;
            }
            else if (++m < FIELD_SIZE && ++n < FIELD_SIZE && field[m][n] == sign) {
                count++;
            }
            else if (count >= SCORE_TO_WIN) {
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
            if (--i >= 0 && ++j < FIELD_SIZE && field[i][j] == sign) {
                count++;
            }
            else if (++m < FIELD_SIZE && --n >= 0 && field[m][n] == sign) {
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
            else if (++m < FIELD_SIZE && field[m][n] == sign) {
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
            else if (++n < FIELD_SIZE && field[m][n] == sign) {
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
    }

    public boolean checkDraw () {
        if (moves == MAX_MOVES_VALUE) {
            return true;
        }
        else return false;

    }
}

