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
            board.deleteVisitorFromSquare(playerOne);

            //ADD VISITOR TO SQUARE
            board.addVisitorToSquare(playerOne, diceResultOne);

            Thread.sleep(1000);

            System.out.println("Turn " + playerTwo.getName() + ", press ENTER to roll the dice");
            scanner.nextLine();
            int diceResultTwo = playerOne.rollDice();

            //DELETE VISITOR FROM PREVIOUS SQUARE
            board.deleteVisitorFromSquare(playerTwo);

            //ADD VISITOR TO SQUARE
            board.addVisitorToSquare(playerTwo, diceResultTwo);

            Thread.sleep(1000);

            //SHOW BOARD STATUS AT END OF A ROUND
            System.out.println(board.showBoardInTerminal());
        }


    }
}