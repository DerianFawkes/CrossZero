package com.sobol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Scanner;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fawkes
 * Date: 12.11.13
 * Time: 22:13
 * To change this template use File | Settings | File Templates.
 */
public class Menu {

    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 100;

    public void showMainMenu () {
        System.out.println(
                "       *Игровое меню*\n" +
                "Игрок против компьютера (pvc)[unavailable]\n" +
                "  Игрок против игрока (pvp)\n" +
                "    Сетевая игра (net)\n" +
                "          Выход (ext)\n"   +
                "----------------------------\n" +
                "Для выбора введите команду указанную в скобках\n"
        );
    }

    public void chooseMenu () {
        Scanner in = new Scanner (System.in);
        String choice;
        while(true) {
            showMainMenu();
            choice = in.next();
            if (choice.equals("pvc")) {
                System.out.println("Недоступно\n");
            }
            else if (choice.equals("pvp")) {
                playPVP();
            }
            else if (choice.equals("net")) {
                playNET();
            }
            else if (choice.equals("ext")) {
                break;
            }
            else {
                System.out.println("Повторите ввод");
            }
        }
    }

    public boolean playAgain () {
        System.out.println("Хотите сыграть еще раз?(yes\\no)");
        Scanner in = new Scanner (System.in);
        String choice;
        while(true) {
            choice = in.nextLine();
            if (choice.equals("yes")) {
                return true;
            }
            else if (choice.equals("no")) {
                return false;
            }
            else {
                System.out.println("Повторите ввод");
            }
        }
    }

    private void playPVP() {

        GameField gameField = new GameField(enterGameFieldSize());
        gameField.showField();

        Player PlayerX = new HumanPlayer("Player№1", 'X');
        Player PlayerO = new HumanPlayer("Player№2", 'O');


        Player currentPlayer = PlayerX;
        while (true) {
            System.out.println("Ходит " + currentPlayer.getPlayerName());
            currentPlayer.makeAMove(gameField);
            gameField.showField();
            if (gameField.checkWin(currentPlayer.getPlayerSign())) {
                System.out.println ("Победил " + currentPlayer.getPlayerName());
                if (playAgain() == true) {
                    gameField.makeDefaultField();
                    currentPlayer = PlayerX;
                    gameField.showField();
                }
                else
                    return;
            }
            else if (gameField.checkDraw()) {
                System.out.println("Ничья");
                if (playAgain() == true) {
                    gameField.makeDefaultField();
                    currentPlayer = PlayerX;
                    gameField.showField();
                }
                else
                    return;
            }
            else {
            currentPlayer = gameField.switchTurn(currentPlayer, PlayerX, PlayerO);
            }
        }
    }

    public void playPVC () {
        GameField gameField = new GameField(enterGameFieldSize());
        gameField.showField();

        Player PlayerX = new HumanPlayer("Player№1", 'X');
        Player PlayerO = new ComputerPlayer('O');


        Player currentPlayer = PlayerX;
        while (true) {
            System.out.println("Ходит " + currentPlayer.getPlayerName());
            currentPlayer.makeAMove(gameField);
            gameField.showField();
            if (gameField.checkWin(currentPlayer.getPlayerSign())) {
                System.out.println ("Победил " + currentPlayer.getPlayerName());
                if (playAgain() == true) {
                    gameField.makeDefaultField();
                    currentPlayer = PlayerX;
                    gameField.showField();
                }
                else
                    return;
            }
            else if (gameField.checkDraw()) {
                System.out.println("Ничья");
                if (playAgain() == true) {
                    gameField.makeDefaultField();
                    currentPlayer = PlayerX;
                    gameField.showField();
                }
                else
                    return;
            }
            else {
                currentPlayer = gameField.switchTurn(currentPlayer, PlayerX, PlayerO);
            }
        }
    }

    public void playNET() {
        try {
            ServerSocket socket = new ServerSocket(8189);
            System.out.println("Ожидание подключения");
            Socket incoming = socket.accept();

            try {
                InputStream inputStream = incoming.getInputStream();
                OutputStream outputStream = incoming.getOutputStream();

                Scanner in = new Scanner(inputStream);
                PrintWriter out = new PrintWriter(outputStream, true);

                GameField gameField = new GameField(enterGameFieldSize());

                Player PlayerX = new HumanPlayer("Player№1", 'X');
                Player PlayerO = new HumanNetPlayer("RemotePlayer", 'O', out, in);
                out.println("Welcome. Waiting for opponent to move");
                gameField.showFieldtoNet(out);

                Player currentPlayer = PlayerX;

                while (true) {
                    System.out.println("Ходит " + currentPlayer.getPlayerName());
                        currentPlayer.makeAMove(gameField);

                    if (gameField.checkWin(currentPlayer.getPlayerSign())) {
                        System.out.println ("Победил " + currentPlayer.getPlayerName());
                        out.println("Победил " + currentPlayer.getPlayerName());
                        break;
                    }
                    else if (gameField.checkDraw()) {
                        System.out.println("Ничья");
                        out.println("Ничья");
                        break;
                    }
                    else {
                        currentPlayer = gameField.switchTurn(currentPlayer, PlayerX, PlayerO);
                    }
                }
            }
            finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private int enterGameFieldSize() {
        while(true) {
            System.out.println("Введите размер поля");
            Scanner in = new Scanner (System.in);
            int size = in.nextInt();
            if (size < MIN_FIELD_SIZE || size > MAX_FIELD_SIZE) {
                System.out.println("Некорректное значение. Размер поля должен быть в диапазоне от 3 до 100");
            }
            else {
                return size;
            }
        }
    }

}
