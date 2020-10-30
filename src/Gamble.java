import java.util.Scanner;

public class Gamble {

    private Player player;
    private Dealer dealer;
    private Integer bet;
    private Deck deck;

    public void start() {
        bet = player.bet();
        scatter();
        boolean isGambleOver = false;
        boolean isSkip = false;
        while( !isGambleOver && !isSkip ){
            Integer choise = player.select();
            switch (choise) {
                case 1:
                    bet = 2*bet;
                    player.getOneCard();
                    isSkip = true;
                    break;
                case 2:
                    player.getOneCard();
                    break;
                case 3:
                    isSkip = true;
                    break;
                case 4:
                    ;
                    break;
                default:
                    ;
            }
            if (checkGambleOver()) isGambleOver=true;
        }
        if(!isGambleOver){
            dealer.show();
            dealer.getCards();
            if (dealer.check()){
                if(dealer.getValue() < player.getValue()){
                    player.setBalance(player.getBalance()+bet);
                    /*胜场 + 1，若 BlackJack 则 BlackJack 场次 + 1 */
                }else if (dealer.getValue() < player.getValue()){
                    player.setBalance(player.getBalance()-bet);
                    /*负场 + 1*/
                }else{
                    /*平场 + 1，总金额不变，若 BlackJack 则 BlackJack 场次 + 1*/
                }
            }
        }
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
