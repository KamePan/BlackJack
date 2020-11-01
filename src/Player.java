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

    public Player(Deck deck){
        balance = 1000;
        hand = new Hand(deck);
    }
    public Player(Deck deck, int balance) {
        this.balance = balance;
        hand = new Hand(deck);
    }

    public void show(){
        System.out.print("该玩家的手牌为");
        for(int i=0;i<hand.getLength();i++){
            System.out.print((i==0?":":";")+hand.get(i));
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
        System.out.println("当前余额为： " + getBalance());
        System.out.println("请输入要投注的金额： ");
        Scanner scanner = new Scanner(System.in);
        Integer bet = scanner.nextInt();
        while (true){
            if (bet > getBalance())
                System.out.println("投注金额超过余额，请重新输入");
            else break;
        }
        scanner.close();
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

    public int select(){
        System.out.println("您的选择有:");
        System.out.println("1.双倍");
        System.out.println("2.叫牌");
        System.out.println("3.停牌");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.close();
        return option;
    }
}
