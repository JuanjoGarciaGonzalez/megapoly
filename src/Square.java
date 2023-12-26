public class Square {
    protected Player owner;
    protected int position;
    public Player[] visitors;

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
