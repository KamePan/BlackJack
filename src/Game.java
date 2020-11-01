import java.util.Scanner;

public class Game {

    private Player player;
    private Dealer dealer;
    private Deck deck;
    private Integer win;
    private Integer lose;
    private Integer draw;
    private Integer netEarnings;
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
                System.out.println("赠送 1000 游戏币，祝游戏愉快 ~");
            }
            menu();
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
                    new Gamble(player,dealer).start();
                    break;
                case 2:
                    System.out.println("当前胜场: " + win);
                    System.out.println("当前负场: " + lose);
                    System.out.println("平局场次: " + draw);
                    System.out.println("净赚金额: " + netEarnings);
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
