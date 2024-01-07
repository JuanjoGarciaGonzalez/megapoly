package Megapoly.Squares;
import Megapoly.*;

public class Property extends Square {

    protected int rent;
    protected int price;
    protected Player owner;

    protected String color;

    public Property(int position, String color) {
        super(position);
        this.rent = 20;
        this.price = 50;
        this.color = color;
    }

    public int getRent() {
        return rent;
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public String getColor() {
        return color;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void ownSquare(Player player) {
        this.owner = player;
        player.setMoney(player.getMoney() - this.price);
    }

    public boolean haveToPay(Player player) {
        if(this.owner != null && this.owner.getId() != player.getId()) {
            player.setMoney(player.getMoney() - this.rent);
            return true;
        }else {
            return false;
        }
    }
}
