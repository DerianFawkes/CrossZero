package com.sobol;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        helloMsg();
        GameField gameField = new GameField(10);
        gameField.showField();

        char currentPlayer = 'X';
        boolean victory = false;
        while (!victory) {
            System.out.println("Ход игрока " + currentPlayer);
            victory = gameField.makeAMove(currentPlayer);
            gameField.showField();
            //victory = gameField.checkField(currentPlayer);
            if (victory == true) {
                System.out.println ("!!!!!!!!!!!!!!!!Победили " + currentPlayer +" !!!!!!!!!!!!!!!!!!!!");
            }
            currentPlayer = switchTurn(currentPlayer);
        }
    }

    public static void helloMsg() {
        System.out.println("Добро пожаловать в игру Крестики-Нолики");
    }

    public static char switchTurn(char currentPlayer) {
        if (currentPlayer == 'X') {
            return 'O';
        }
        else {
            return 'X';
        }

    }
}
