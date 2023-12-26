public class Player {
    private int id;
    private String name;
    private int money;
    private int position;


    public Player(String name, int id) {
        this.name = name;
        this.money = 100;
        this.position = 0;
        this.id = id;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
