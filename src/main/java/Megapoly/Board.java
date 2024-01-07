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
                case 7, 14, 18:
                    squares[i] = new Lucky(i);
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
        this.squares[newPosition].addVisitor(player);
        player.setPosition(newPosition);
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
}
