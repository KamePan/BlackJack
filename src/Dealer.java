public class Dealer {
    private Hand hand;

    public void firstlyShow(){
        System.out.println("庄家的明牌为" + hand.get(0));
    }

    public void show(){
        System.out.print("庄家的手牌为");
        for(int i=0;i<hand.getLength();i++){
            System.out.print((i==0?":":";")+hand.get(i));
        }
        System.out.println();
    }

    public void firstlyGetCards(){
        hand.getOne();
        hand.getOne();
    }

    public void getCards(){
        while(hand.getValue()<17){
            hand.getOne();
        }
    }
    public boolean check(){
        return getValue()<22;
    }
    public int getValue(){
        return hand.getValue();
    }
}
