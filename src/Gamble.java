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
        scatter();
        boolean isGambleOver = false;
        boolean isSkip = false;
        while( !isGambleOver && !isSkip ){
            System.out.println("您的选择有: ");
            System.out.println("1.叫牌");
            System.out.println("2.停牌");
            if (player.getBalance() >= 2*bet) System.out.println("3.双倍");

            Scanner scanner = new Scanner(System.in);
            Integer choise = scanner.nextInt();
            switch (choise) {
                case 1:
                    player.getOneCard();
                    break;
                case 2:
                    player.show();
                    isSkip = true;
                    break;
                case 3:
                    if (player.getBalance() < 2*bet) {
                        System.out.println("没钱的人不可以双倍");
                        break;
                    }
                    bet = 2*bet;
                    player.getOneCard();
                    isSkip = true;
                    break;
                case 4:
                    ;
                    break;
                default:
                    System.out.println("没有这个选项，对不起请重新来过");
            }
            Thread.sleep(1000);
            if (checkGambleOver()){
                System.out.println("大于21点，输了......");
                player.setBalance(player.getBalance()-bet);
                isGambleOver=true;
                /*负场 + 1*/
            }
        }
        if(!isGambleOver){
            dealer.show();
            Thread.sleep(1000);
            dealer.getCards();
            if (dealer.check()){
                if(dealer.getValue() < player.getValue()){
                    System.out.println("您获胜了!!!!!!!!!!");
                    player.setBalance(player.getBalance()+bet);
                    /*胜场 + 1，若 BlackJack 则 BlackJack 场次 + 1 */
                }else if (dealer.getValue() > player.getValue()){
                    System.out.println("输了......");
                    player.setBalance(player.getBalance()-bet);
                    /*负场 + 1*/
                }else{
                    System.out.println("平局了。");
                    /*平场 + 1，总金额不变，若 BlackJack 则 BlackJack 场次 + 1*/
                }
            } else{
                System.out.println("您获胜了!!!!!!!!!!");
                player.setBalance(player.getBalance()+bet);
                /*胜场 + 1，若 BlackJack 则 BlackJack 场次 + 1 */
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
