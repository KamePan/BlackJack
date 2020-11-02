import java.util.Scanner;

public class Gamble {

    private Player player;
    private Dealer dealer;
    private Integer bet;

    public Gamble(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    public void start() throws InterruptedException {
        bet = player.bet();
        //给庄家和玩家发牌并展示庄家明牌和玩家的牌
        scatter();
        boolean isGambleOver = false;
        boolean isInsureFail = false;
        boolean isSkip = false;
        while( !isGambleOver && !isSkip ){
            System.out.println("您的选择有: ");
            System.out.println("1.叫牌");
            System.out.println("2.停牌");
            if (player.canDoubleBet(bet)) System.out.println("3.双倍");
            /*如果庄家第一张为 A 且没有保险失败 且玩家的余额足够支付 1/2 bet 的保险金，则显示保险*/
            if (dealer.mayBeBlackJack()&&!isInsureFail&&player.getBalance() >= Math.floor(bet*3/2)) {
                String str = "";
                if (!player.canDoubleBet(bet)) str += "3.";
                else str+="4.";
                System.out.println(str + "保险");
            }

            Scanner scanner = new Scanner(System.in);
            Integer choise = scanner.nextInt();
            /*当显示保险选项，而且没有双倍的时候，保险的选项从 3 映射到 4*/
            if(choise == 3 && !player.canDoubleBet(bet) && dealer.mayBeBlackJack()) choise = 4;
            switch (choise) {
                case 1:
                    /*叫牌*/
                    player.getOneCard();
                    break;
                case 2:
                    /*停牌*/
                    player.show();
                    isSkip = true;
                    break;
                case 3:
                    /*双倍*/
                    if (!player.canDoubleBet(bet)) {
                        System.out.println("没钱的人不可以双倍");
                        break;
                    }
                    bet = 2*bet;
                    player.getOneCard();
                    isSkip = true;
                    break;
                case 4:
                    /*没有保险选项时，选择 4 */
                    if (!dealer.mayBeBlackJack()) {
                        System.out.println("当前不能选择保险");
                        break;
                    }
                    dealer.show();
                    /*保险成功，输了一半的赌注，游戏结束*/
                    if(dealer.isBlackJack()){
                        System.out.println("保险成功，输了一半的赌注");
                        player.lose((int) Math.floor(bet*0.5));
                        isGambleOver =true;
                        break;
                    }
                    /*保险失败，失去了一半的赌注，可以继续摸牌*/
                    System.out.println("保险失败，失去了一半的赌注，请继续游戏");
                    isInsureFail = true;
                    player.loseInsurance(bet);
                    break;
                default:
                    System.out.println("没有这个选项，对不起请重新来过");
            }
            Thread.sleep(1000);
            /*检测玩家是否手牌点数大于 21，游戏结束*/
            if (checkGambleOver()){
                player.lose(bet);
                isGambleOver=true;
                System.out.println("大于21点，输了......");
            }
        }
        /*如果没有因为手牌点数 >21 或者 保险成功导致游戏结束，则进入庄家拿牌阶段*/
        if(!isGambleOver) {
            dealer.show();
            Thread.sleep(1000);
            /*庄家拿牌，拿到 17 点之后或者大于 21 点后结束*/
            dealer.getCards();
            /*检测庄家是否大于21点，没有则比较庄家点数和玩家点数*/
            if (dealer.check()) {
                /*1. 玩家点数大于庄家点数，或者点数相同时玩家手牌数量小于庄家手牌数量，玩家胜利
                * 2. 庄家点数大于玩家点数，或者点数相同时庄家手牌数量小于玩家手牌数量，庄家胜利
                * 3. 点数相同且手牌数相同，平局
                * */
                if((dealer.getValue() < player.getValue())||(dealer.getValue() == player.getValue()
                        && dealer.getHandCardsLength() > player.getHandCardsLength())) {
                    player.win(bet);
                    System.out.println("您获胜了!!!!!!!!!!");
                }else if ((dealer.getValue() > player.getValue())||(dealer.getValue() == player.getValue()
                        && dealer.getHandCardsLength() < player.getHandCardsLength())) {
                    player.lose(bet);
                    System.out.println("输了......");
                }else {
                    player.draw();
                    System.out.println("平局了。");
                }
            } else {
                /*庄家点数大于 21 点，玩家胜利*/
                player.win(bet);
                System.out.println("您获胜了!!!!!!!!!!");
            }
        }
        Thread.sleep(1000);
        System.out.println("当前余额为: " + player.getBalance());
    }

    public boolean checkGambleOver(){
        return !player.check();
    }

    public void scatter(){
        player.firstlyGetCards();
        dealer.firstlyGetCards();
        dealer.firstlyShow();
        player.show();
    }

}
