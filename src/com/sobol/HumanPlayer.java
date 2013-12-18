package src.com.sobol;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Fawkes
 * Date: 20.11.13
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class HumanPlayer extends Player {


     HumanPlayer (String name, char sign) {
        super(name, sign);
    }
    @Override
    public void makeAMove(GameField gameField) {
        gameField.showField();
        Scanner in = new Scanner (System.in);
        int index;
        int FIELD_SIZE = gameField.getFIELD_SIZE();
        System.out.println("->Введите номер ячейки");
        do {

            index = in.nextInt()-1;
            if (index < 0 || index >= FIELD_SIZE*FIELD_SIZE) {
                System.out.println("->Некорректное значение, введите другой номер");
            }
            else if (gameField.getFieldElement(index/FIELD_SIZE,index%FIELD_SIZE) == 'X' || gameField.getFieldElement(index/FIELD_SIZE,index%FIELD_SIZE) == 'O'){
                System.out.println("->Данная ячейка занята, введите другой номер");
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
