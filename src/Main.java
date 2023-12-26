import java.util.ArrayList;
import java.util.Arrays;
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
        while(play) {
            System.out.println("Turn " + playerOne.getName() + ", press ENTER to roll the dice");
            scanner.nextLine();
            int diceResultOne = playerOne.rollDice();
            //DELETE VISITOR FROM PREVIOUS SQUARE
            Player[] previousVisitors = board.squares[playerOne.getPosition()].getVisitors();
            for (int i = 0; i < previousVisitors.length; i++) {
                if(previousVisitors[i] != null) {
                    if (previousVisitors[i].getId() == playerOne.getId()) {
                        board.squares[playerOne.getPosition()].visitors[i] = null;
                    }
                }
            }
            //ADD VISITOR TO SQUARE
            int newPosition = board.calculatePosition(playerOne.getPosition(), diceResultOne);
            System.out.println("Result: " + diceResultOne);
            board.squares[newPosition].addVisitor(playerOne);
            playerOne.setPosition(newPosition);

            Thread.sleep(1000);

            System.out.println("Turn " + playerTwo.getName() + ", press ENTER to roll the dice");
            scanner.nextLine();
            int diceResultTwo = playerOne.rollDice();
            //DELETE VISITOR FROM PREVIOUS SQUARE
            Player[] previousVisitorsTwo = board.squares[playerTwo.getPosition()].getVisitors();
            for (int i = 0; i < previousVisitorsTwo.length; i++) {
                if(previousVisitorsTwo[i] != null) {
                    if(previousVisitorsTwo[i].getId() == playerTwo.getId()) {
                        board.squares[playerTwo.getPosition()].visitors[i] = null;
                    }
                }

            }
            //ADD VISITOR TO SQUARE
            int newPositionTwo = board.calculatePosition(playerTwo.getPosition(), diceResultTwo);
            board.squares[newPositionTwo].addVisitor(playerTwo);
            playerTwo.setPosition(newPositionTwo);
            System.out.println("Result: " + diceResultTwo);
            Thread.sleep(1000);
            System.out.println(board.showBoardInTerminal());
        }


    }
}