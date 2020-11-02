import java.util.Scanner;

public class Game {

    private Player player;
    private Dealer dealer;
    private Deck deck;
    private boolean isGameOver = false;

    // 创建Player的时候传入当前牌堆
    public void init() {
        deck = new Deck();
        player = new Player(deck);
        dealer = new Dealer(deck);
    }

    public void start() {
        init();
        System.out.println("---------------------------------");
        System.out.println("           Black Jack            ");
        System.out.println("---------------------------------");
        proceed();

    }

    public void proceed(){
        while(!isGameOver) {
            if (player.getBalance() == 0) {
                player.setBalance(1000);
                player.addFirst_balance(1000);
                System.out.println("赠送 1000 游戏币，祝游戏愉快 ~");
            }
            menu();
            /*重置手牌*/
            player.setHand(new Hand(deck));
            dealer.setHand(new Hand(deck));
        }
    }

    public void menu(){
        System.out.println("1. 开始新一轮");
        System.out.println("2. 统计数据");
        System.out.println("3. 结束游戏");
        Scanner scanner = new Scanner(System.in);
        boolean isCircling = true;
        while(isCircling && !isGameOver) {
            Integer choise = scanner.nextInt();
            switch (choise) {
                case 1:
                    isCircling = false;
                    try {
                        new Gamble(player,dealer).start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("当前胜场: " + player.getWin_num());
                    System.out.println("当前负场: " + player.getLose_num());
                    System.out.println("平局场次: " + player.getDraw_num());
                    System.out.println("净赚金额: " + (player.getBalance()-player.getFirst_balance()));
                    break;
                case 3:
                    isGameOver = true;
                    System.out.println("游戏结束....欢迎下次光临");
                    break;
                default:
                    System.out.println("无效选项，请重新输入");
            }
        }
    }
}
