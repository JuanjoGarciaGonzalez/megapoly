public class Player {

    private String name;
    private int money;

    public Player(String name) {
        this.name = name;
        this.money = 100;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public int rollDice() {
        int number = (int)(Math.random()*6)+1;
        return number;
    }

}
