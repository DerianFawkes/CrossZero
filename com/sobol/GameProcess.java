package com.sobol;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Fawkes
 * Date: 16.11.13
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public interface GameProcess {

    public Player switchTurn (Player currentPlayer, Player one, Player two);

    public boolean checkWin (char sign);
}
