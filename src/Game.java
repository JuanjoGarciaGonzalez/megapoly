import java.util.Scanner;

public class Game {
    public Player playerOne;
    public Player playerTwo;
    public Board board;
    public Scanner scanner;

    public void play() throws InterruptedException {
        scanner = new Scanner(System.in);
        //INICIALIZAR LOS JUGADORES
        initPlayers();

        //GENERATE BOARD
        generateBoard();

        //ROLL THE DICE PER TURNS UNTIL PLAY IS TRUE
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
        System.out.println(board.showBoardInTerminal());
    }

    public void startGame() throws InterruptedException {
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
