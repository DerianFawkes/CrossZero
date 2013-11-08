package com.sobol;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Fawkes
 * Date: 21.10.13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public class GameField {

    private static int FIELD_SIZE;
    private static int SCORE_TO_WIN;


    private char[][] field;
    private static int[][] indexedField;


    public GameField (int size) {
        FIELD_SIZE = size;
        if (size <= 5) {
            SCORE_TO_WIN = size;
        }
        else SCORE_TO_WIN = 5;

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

    public void makeDefaultField () {
        int count = 0;
        for(int i=0;i<FIELD_SIZE; i++) {
            for(int j=0;j<FIELD_SIZE;j++) {
                field[i][j] = ' ';
                count++;
            }
        }
    }


    public boolean makeAMove (char currentPlayer) {
        Scanner in = new Scanner (System.in);
        int index;
        System.out.println("->Введите номер ячейки");
        do {

            index = in.nextInt()-1;
            if (index < 0 || index >= FIELD_SIZE*FIELD_SIZE) {
                System.out.println("->Некорректное значение, введите другой номер.");
            }
            else if (field[index/FIELD_SIZE][index%FIELD_SIZE] == 'X' || field[index/FIELD_SIZE][index%FIELD_SIZE] == 'O'){
                System.out.println("->Данная ячейка занята, введите другой номер.");
            }
            else {
                field[index/FIELD_SIZE][index%FIELD_SIZE] = currentPlayer;
                break;
            }
        }
        while (true);

        return checkBigField(index/FIELD_SIZE, index%FIELD_SIZE, currentPlayer);
    }

    public boolean checkField (char sign) {

        //проверка победы в строке или столбце
        for (int i = 0; i<FIELD_SIZE;i++) {
            if(field[i][0] == sign && field[i][1] == sign && field[i][2] == sign) {
                return true;
            }
            if(field[0][i] == sign && field[1][i] == sign && field[2][i] == sign) {
                return true;
            }
        }


        //проверка победы в диагоналях
        if(field[0][0] == sign && field[1][1] == sign && field[2][2] == sign) {
            return true;
        }

        if(field[2][0] == sign && field[1][1] == sign && field[0][2] == sign) {
            return true;
        }

        return false;
    }

    private boolean checkBigField (int line, int column, char sign) {
        //проверка диагонали \
        int count = 1;
        int i = line, j = column, m = line, n = column;
        while(true) {
            if (--i >= 0 && --j >= 0 && field[i][j] == sign) {
                count++;
            }
            else if (++m <= FIELD_SIZE && ++n <= FIELD_SIZE && field[m][n] == sign) {
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
        i = line;
        j = column;
        m = line;
        n = column;
        while(true) {
            if (--i >= 0 && ++j >= FIELD_SIZE && field[i][j] == sign) {
                count++;
            }
            else if (++m <= FIELD_SIZE && --n >= 0 && field[m][n] == sign) {
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
        i = line;
        j = column;
        m = line;
        n = column;
        while(true) {
            if (--i >= 0 && field[i][j] == sign) {
                count++;
            }
            else if (++m <= FIELD_SIZE && field[m][n] == sign) {
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
        i = line;
        j = column;
        m = line;
        n = column;
        while(true) {
            if (--j >= 0 && field[i][j] == sign) {
                count++;
            }
            else if (++n <= FIELD_SIZE && field[m][n] == sign) {
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

}

