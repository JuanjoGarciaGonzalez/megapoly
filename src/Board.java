import java.util.Arrays;

public class Board {
    Square[] squares = new Square[17];

    public Board() {
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new Square(i);
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
                if(square.getPosition() == 0) {
                    boardString += " SALIDA ";
                }else {
                    String[] visitorsFormatted = new String[2];
                    for(int i = 0; i < visitors.length; i++) {
                        if (visitors[i] != null) {
                            visitorsFormatted[i] = visitors[i].getName().substring(0, 1);
                        }

                    }

                    if(visitorsFormatted[0] == null && visitorsFormatted[1] == null) {
                        boardString += " _ ";
                    }else {
                        for (String visitorInitial : visitorsFormatted) {
                            if (visitorInitial != null) {
                                boardString += visitorInitial;
                            }

                        }
                    }
                }


            }else {
                boardString += " _ ";
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

    public int calculatePosition(int current, int resultDice) {
        if(current + resultDice > 17) {
            return (current + resultDice) - 16;
        }else {
            return (current + resultDice);
        }
    }
}
