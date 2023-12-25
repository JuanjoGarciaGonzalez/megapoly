import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //INICIALIZAR LOS JUGADORES
        System.out.println("Enter the name for Player 1:");
        String nameOne = scanner.nextLine();
        System.out.println("Enter the name for Player 2:");
        String nameTwo = scanner.nextLine();

        Player playerOne = new Player(nameOne);
        Player playerTwo = new Player(nameTwo);

        //ROLL THE DICE IN BUCLE
        boolean play = true;
        while(play) {
            System.out.println("Turn " + playerOne.getName());
            int diceResultOne = playerOne.rollDice();
            System.out.println("Result: " + diceResultOne);

            System.out.println("Turn " + playerTwo.getName());
            int diceResultTwo = playerOne.rollDice();
            System.out.println("Result: " + diceResultTwo);
        }


    }
}