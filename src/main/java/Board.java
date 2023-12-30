import java.util.Arrays;

public class Board {
    Square[] squares = new Square[20];

    public Board() {
        for (int i = 0; i < squares.length; i++) {
            switch (i) {
                case 0:
                    squares[i] = new Start(i);
                    break;
                default:
                    squares[i] = new Property(i);
                    break;
            }
        }
    }

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    public String showBoardInTerminal() {
        String boardString = "Board:\n";
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
