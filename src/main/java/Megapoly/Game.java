package Megapoly;

import java.util.Scanner;

public class Game {
    public Player playerOne;
    public Player playerTwo;
    public Board board;
    public static Scanner scanner = new Scanner(System.in);

    public void play() throws InterruptedException {
        // INIT THE PLAYERS
        initPlayers();

        //GENERATE BOARD
        generateBoard();

        //ROLL THE DICE PER TURNS UNTIL PLAY IS FALSE
        startGame();
    }

    public void initPlayers() {
        System.out.println("Enter the name for Player 1:");
        String nameOne = scanner.nextLine();
        System.out.println("Enter the name for Player 2:");
        String nameTwo = scanner.nextLine();

        playerOne = new Player(nameOne, 1);
        playerTwo = new Player(nameTwo, 2);
    }

    public void generateBoard() {
        board = new Board();
        Player[] players = {playerOne, playerTwo};
        board.squares[0].setVisitors(players);
        System.out.println(board.showBoardSquares());
        System.out.println(board.showBoardInTerminal());
    }

    public void startGame() throws InterruptedException {
        boolean play = true;
        int rounds = 0;
        while(play) {
            System.out.println("Turn " + playerOne.getName() + " with " + playerOne.getMoney()  + "Mm, press ENTER to roll the dice");
            scanner.nextLine();
            int diceResultOne = playerOne.rollDice();

            if(playerOne.isInPrision()) {
                this.prisionAction(playerOne, diceResultOne);
            }else {
                //DELETE VISITOR FROM PREVIOUS SQUARE
                board.deleteVisitorFromSquare(playerOne);

                //ADD VISITOR TO SQUARE
                board.addVisitorToSquare(playerOne, diceResultOne);
            }

            if(playerOne.getMoney() <= 0) {
                System.out.println(playerOne.getName() + " has no money left, " + playerTwo.getName() + " wins!");
                play = false;
                break;
            }

            Thread.sleep(1000);

            System.out.println("Turn " + playerTwo.getName() + " with " + playerTwo.getMoney()  + "Mm, press ENTER to roll the dice");
            scanner.nextLine();
            int diceResultTwo = playerTwo.rollDice();

            if(playerTwo.isInPrision()) {
                this.prisionAction(playerTwo, diceResultTwo);
            }

            //DELETE VISITOR FROM PREVIOUS SQUARE
            board.deleteVisitorFromSquare(playerTwo);

            //ADD VISITOR TO SQUARE
            board.addVisitorToSquare(playerTwo, diceResultTwo);

            if(playerTwo.getMoney() <= 0) {
                System.out.println(playerTwo.getName() + " has no money left, " + playerTwo.getName() + " wins!");
                play = false;
                break;
            }

            Thread.sleep(1000);

            //SHOW BOARD STATUS AT END OF A ROUND
            System.out.println(board.showBoardInTerminal());
        }
    }

    public void prisionAction(Player player , int diceResult) {
        if (player.isInPrision() && diceResult == 5) {
            player.setInPrision(false);
            System.out.printf("%s has rolled a 5, %s is free from prision %n", player.getName(), player.getName());
        } else if (player.isInPrision() && player.getPrisionSteps() < 2) {
            System.out.printf("%s is in prision, %s loses the turn", player.getName(), player.getName());
            player.setPrisionSteps(player.getPrisionSteps() + 1);
        }else if(player.isInPrision() && player.getPrisionSteps() == 2) {
            player.setPrisionSteps(0);
            player.setInPrision(false);
            System.out.printf("You have served your sentence in prison, you are free to play %s", player.getName());
        }
    }
}
