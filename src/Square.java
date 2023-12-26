public class Square {
    protected Player owner;
    protected int position;
    protected Player[] visitors;

    public Square(int position) {
        this.position = position;
        this.visitors = new Player[0];
    }

    public Player getOwner() {
        return owner;
    }

    public int getPosition() {
        return position;
    }

    public Player[] getVisitors() {
        return visitors;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setPosition(int position) {position = position;
    }

    public void setVisitors(Player[] visitors) {
        this.visitors = visitors;
    }

    @Override
    public String toString() {
        return "Square{" +
                "owner=" + owner +
                ", visitors=" + visitors +
                ", Position=" + position +
                '}';
    }
}
