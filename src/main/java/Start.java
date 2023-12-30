public class Start extends Square {
    protected int goMoney = 20;
    public Start(int position) {
        super(position);
    }

    public int getGoMoney() {
        return goMoney;
    }

    public void passingExitSquare(Player player) {
        player.setMoney(player.getMoney() + this.goMoney);
    }
}
