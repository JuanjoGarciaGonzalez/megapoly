import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        //INICIALIZAR LOS JUGADORES
        System.out.println("Enter the name for Player 1:");
        String nameOne = scanner.nextLine();
        System.out.println("Enter the name for Player 2:");
        String nameTwo = scanner.nextLine();

        Player playerOne = new Player(nameOne, 1);
        Player playerTwo = new Player(nameTwo, 2);

        //GENERATE BOARD
        Board board = new Board();
        Player[] players = {playerOne, playerTwo};
        board.squares[0].setVisitors(players);
        System.out.println(board.showBoardInTerminal());

        //ROLL THE DICE PER TURNS UNTIL PLAY IS TRUE
        boolean play = true;
        while(playerOne.getMoney() != 0 && playerTwo.getMoney() != 0) {
            System.out.println("Turn " + playerOne.getName() + ", press ENTER to roll the dice");
            scanner.nextLine();
            int diceResultOne = playerOne.rollDice();
            playerOne.setPosition(playerOne.getPosition() + diceResultOne);
            System.out.println("Result: " + diceResultOne);
            System.out.println("Position: " + playerOne.getPosition());

            Thread.sleep(1000);

            System.out.println("Turn " + playerTwo.getName() + ", press ENTER to roll the dice");
            scanner.nextLine();
            int diceResultTwo = playerOne.rollDice();
            playerTwo.setPosition(playerTwo.getPosition() + diceResultTwo);
            System.out.println("Result: " + diceResultTwo);
            System.out.println("Position: " + playerTwo.getPosition());
            Thread.sleep(1000);
            play = false;
        }


    }
}