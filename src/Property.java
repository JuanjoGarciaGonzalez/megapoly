public class Property extends Square {

    protected int rent;
    protected int price;
    protected Player owner;

    public Property(int position) {
        super(position);
        this.rent = 20;
        this.price = 50;
    }

    public void ownSquare(Player player) {
        this.owner = player;
        player.setMoney(player.getMoney() - this.price);
    }

    public boolean haveToPay(Player player) {
        if(this.owner.getId() == player.getId()) {
            player.setMoney(player.getMoney() - this.rent);
            return false;
        }else {
            return true;
        }
    }
}
