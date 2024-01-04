package Megapoly.Squares;
import Megapoly.*;

public class Bank extends Square{
    protected int price;
    public Bank(int position) {
        super(position);
        this.price = 20;
    }

    public void payToBank(Player player) {
        player.setMoney(player.getMoney() - this.price);
    }
}
