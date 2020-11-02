import java.util.Scanner;

public class Player {
    private int balance;
    private Hand hand;
    private int win_num;

    public void addFirst_balance(int external_money) {
        first_balance += external_money;
    }

    private int lose_num;
    private int draw_num;
    private int first_balance;

    public int getWin_num() {
        return win_num;
    }

    public int getLose_num() {
        return lose_num;
    }

    public int getDraw_num() {
        return draw_num;
    }

    public int getFirst_balance() {
        return first_balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Player(Deck deck){
        first_balance = balance = 1000;
        win_num = 0;
        lose_num = 0;
        draw_num = 0;
        hand = new Hand(deck);
    }

    public Player(Deck deck, int balance) {
        first_balance = this.balance = balance;
        win_num = 0;
        lose_num = 0;
        draw_num = 0;
        hand = new Hand(deck);
    }

    public void show(){
        System.out.print("玩家的手牌为");
        for(int i=0;i<hand.getLength();i++){
            System.out.print((i==0?": ":", ")+hand.get(i));
        }
        System.out.println();
    }

    public int getValue(){
        return hand.getValue();
    }

    public boolean check(){
        return getValue()<22;
    }

    public int bet(){
        System.out.println("当前余额为: " + getBalance());
        System.out.println("请输入要投注的金额: ");
        Scanner scanner = new Scanner(System.in);
        Integer bet = scanner.nextInt();
        while (true){
            if (bet > getBalance())
                System.out.println("投注金额超过余额，请重新输入");
            else break;
        }
        return bet;
    }

    public void firstlyGetCards(){
        hand.getOne();
        hand.getOne();
    }

    public void getOneCard(){
        hand.getOne();
        show();
    }

    public void win(int bet){
        balance += bet;
        win_num++;
    }
    public void lose(int bet){
        balance -= bet;
        lose_num++;
    }

    public void draw(){
        draw_num++;
    }
}
