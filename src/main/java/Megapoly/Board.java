package Megapoly;

import java.util.Arrays;
import Megapoly.Squares.*;

public class Board {
    Square[] squares = new Square[20];
    String[] squaresNames = {
            "GO",
            "Mediterranean Avenue",
            "Baltic Avenue",
            "BANK",
            "Reading Railroad",
            "VISIT PRISON",
            "Oriental Avenue",
            "LUCKY",
            "Vermont Avenue",
            "BANK",
            "PARKING",
            "Connecticut Avenue",
            "St. Charles Place",
            "Electric Company",
            "LUCKY",
            "PRISON",
            "States Avenue",
            "BANK",
            "LUCKY",
            "Virginia Avenue"
    };


    public Board() {
        for (int i = 0; i < squares.length; i++) {
            switch (i) {
                case 0:
                    squares[i] = new Start(i);
                    break;
                case 3, 9, 17:
                    squares[i] = new Bank(i);
                    break;
                case 5, 15:
                    squares[i] = new Prison(i);
                    break;
                case 7:
                    squares[i] = new Lucky(i, "You receive 15Mm for the profits of your investments\n", 15);
                    break;
                case 14:
                    squares[i] = new Lucky(i, "The Treasury demands 30Mm from you for irregularities\n", -30);
                    break;
                case 18:
                    squares[i] = new Lucky(i, "You win 50Mm in the local lottery\n", 50);
                    break;
                case 10:
                    squares[i] = new Parking(i);
                    break;
                case 1, 2, 4:
                    squares[i] = new Property(i, "BROWN");
                    break;
                case 6, 8:
                    squares[i] = new Property(i, "ORANGE");
                    break;
                case 11, 12, 13:
                    squares[i] = new Property(i, "YELLOW");
                    break;
                case 16, 19:
                    squares[i] = new Property(i, "BLUE");
                    break;
                default:
                    break;
            }

            squares[i].setName(squaresNames[i]);
        }

    }

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    public String showBoardInTerminal() {
        String boardString = "\nBOARD GAME:\n";
        for (Square square : squares) {
            Player[] visitors = square.getVisitors();
            if (visitors != null && visitors.length != 0) {
                String[] visitorsFormatted = new String[2];
                for(int i = 0; i < visitors.length; i++) {
                    if (visitors[i] != null) {
                        visitorsFormatted[i] = visitors[i].getName().substring(0, 1);
                    }

                }


                if(visitorsFormatted[0] == null && visitorsFormatted[1] == null) {
                    boardString += " |__| ";
                }else {
                    boardString += " |";
                    for (String visitorInitial : visitorsFormatted) {
                        if (visitorInitial != null) {
                            boardString += visitorInitial;
                        }


                    }
                    if(visitorsFormatted[1]==null) {
                        boardString += "_";
                    }
                    boardString += "| ";
                }



            }else {
                boardString += " |__| ";
            }

        }
        boardString += "\n";
        for(Square square : squares) {
            if(square.getPosition() < 10){
                boardString += " |0"+square.getPosition()+"| ";
            }else {
                boardString += " |"+square.getPosition()+"| ";
            }

        }

        return boardString;
    }

    public String showBoardSquares() {
        String boardString = "\nBOARD SQUARES:\n";
        for(Square square : squares) {
            String colorProperty = "";
            if (square instanceof Property) {
                colorProperty = "|" + ((Property) square).getColor();
            }
            boardString += square.getPosition()+":" + square.getName() + colorProperty + "\n";


        }

        return boardString;
    }

    @Override
    public String toString() {
        return "Board{" +
                "Squares=" + Arrays.toString(squares) +
                '}';
    }

    public void addVisitorToSquare(Player player, int diceResult) {
        int newPosition = this.calculatePosition(player.getPosition(), diceResult);
        System.out.println("Result: " + diceResult);
        int oldPosition = player.getPosition();
        this.squares[newPosition].addVisitor(player);
        player.setPosition(newPosition);

        this.playerAction(player, newPosition, oldPosition);
    }

    public void deleteVisitorFromSquare(Player player) {
        Player[] previousVisitors = this.squares[player.getPosition()].getVisitors();
        for (int i = 0; i < previousVisitors.length; i++) {
            if(previousVisitors[i] != null) {
                if (previousVisitors[i].getId() == player.getId()) {
                    this.squares[player.getPosition()].visitors[i] = null;
                }
            }
        }
    }

    public int calculatePosition(int current, int resultDice) {
        if(current + resultDice >= 20) {
            return (current + resultDice) - 20;
        }else {
            return (current + resultDice);
        }
    }

    public void playerAction(Player player, int position, int oldPosition) {
        if(position < oldPosition) {
            System.out.println("You pass the start square, you get 100Mm");
            player.setMoney(player.getMoney() + 100);
        }else if(position == 5 || position == 15) {
            System.out.println("You are in the prision square, you lose your turn for 3 rolls, unless you roll a 5 on the die");
            player.setInPrision(true);
        }else if(position == 10) {
            System.out.println("You are in the parking, rest your shift");
        }else if(position == 3 || position == 9 || position == 17) {
            System.out.println("You have to pay a bank tax, 30Mm will be subtracted");
            player.setMoney(player.getMoney() - 30);
        }else if(position == 7 || position == 14 || position == 18) {
            System.out.println("You are in a lucky square, your card is:");
            System.out.println(((Lucky) this.squares[position]).getMessage());
            player.setMoney(player.getMoney() + ((Lucky) this.squares[position]).getOperation());
        }else {
            if (((Property) this.squares[position]).getOwner() == null) {
                System.out.printf("You are in a property square (%s), you can buy it for %s", this.squares[position].getName(), ((Property) this.squares[position]).getPrice());
                System.out.println("Do you want to buy it? (Y/N)");
                String answer = Game.scanner.nextLine();
                if (answer.equals("Y") || answer.equals("y")) {
                    ((Property) this.squares[position]).ownSquare(player);
                    System.out.printf("You bought the property %s for %s\n", this.squares[position].getName(), ((Property) this.squares[position]).getPrice());
                }else {
                    System.out.println("You didn't buy the property");
                }
            } else {
                System.out.printf("You are in a property square, you have to pay the rent of %s for %s\n",((Property) this.squares[position]).getRent(), this.squares[position].getName());
                player.setMoney(player.getMoney() - ((Property) this.squares[position]).getRent());

                Player playerOwner = ((Property) this.squares[position]).getOwner();
                playerOwner.setMoney(playerOwner.getMoney() + ((Property) this.squares[position]).getRent());
            }
        }
    }
}
