public class Square {
    protected int position;
    public Player[] visitors;

    public Square(int position) {
        this.position = position;
        this.visitors = new Player[0];
    }

    public int getPosition() {
        return position;
    }

    public Player[] getVisitors() {
        return visitors;
    }

    public void setPosition(int position) {position = position;
    }

    public void setVisitors(Player[] visitors) {
        this.visitors = visitors;
    }

    @Override
    public String toString() {
        return "Square{" +
                ", visitors=" + visitors +
                ", Position=" + position +
                '}';
    }

    public void addVisitor(Player player) {
        if(visitors.length == 0) {
            this.visitors = new Player[2];
        }

        if (this.visitors[0] == null) {
            this.visitors[0] = player;
        }else {
            this.visitors[1] = player;
        }
    }


}
