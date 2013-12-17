package com.sobol;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Fawkes
 * Date: 22.11.13
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public class HumanNetPlayer extends HumanPlayer {

    PrintWriter out;
    Scanner in;

    public HumanNetPlayer () {
        super("N/A", 'O');
    }

    public HumanNetPlayer (String name, char sign, PrintWriter printWriter, Scanner scanner) {
        super(name, sign);
        out = printWriter;
        in = scanner;
    }

    public void makeAMove(GameField gameField) {
        int index;
        int FIELD_SIZE = gameField.getFIELD_SIZE();
        gameField.showFieldtoNet(out);
        out.println("->Enter cell number");
        do {

            index = in.nextInt()-1;
            if (index < 0 || index >= FIELD_SIZE*FIELD_SIZE) {
                out.println("->Incorrect value, try again");
            }
            else if (gameField.getFieldElement(index/FIELD_SIZE,index%FIELD_SIZE) == 'X' || gameField.getFieldElement(index/FIELD_SIZE,index%FIELD_SIZE) == 'O'){
                out.println("->Current cell is occupied");
            }
            else {
                gameField.setFieldElement(index/FIELD_SIZE, index%FIELD_SIZE, getPlayerSign());
                gameField.setLastMove(index/FIELD_SIZE, index%FIELD_SIZE);
                gameField.incrementMoves();

                break;
            }
        }
        while (true);
    }
}
