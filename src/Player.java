import java.util.Scanner;

public class Player {
    private int balance;
    private Hand hand;

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

    public Player(){
        balance = 1000;
        hand = new Hand();
    }
    public Player(int balance) {
        this.balance = balance;
        hand = new Hand();
    }

    public int bet(){
        System.out.println("请输入你的赌注");
        Scanner scanner=new Scanner(System.in);
        return scanner.nextInt();
    }

    public void firstlyGetCards(){
        hand.getOne();
        hand.getOne();
    }

    public void getOne(){

    }
}
