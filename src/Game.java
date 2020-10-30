import java.util.Scanner;

public class Game {

    private Player player;
    private Gamble gamble;
    private Deck deck;
    private Integer win;
    private Integer lose;
    private Integer netEarnings;

    public void init() {
        player = new Player();
        deck = new Deck();
    }

    public void start() {
        init();
        System.out.println("---------------------------------");
        System.out.println("           Black Jack            ");
        System.out.println("---------------------------------");
        menu();
    }

    public void menu(){
        System.out.println("1. 开始新一轮");
        System.out.println("2. 统计数据");
        System.out.println("3. 结束游戏");
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning) {
            Integer choise = scanner.nextInt();
            switch (choise) {
                case 1:
                    isRunning = false;
                    new Gamble().start();
                    break;
                case 2:
                    System.out.println("当前胜场： " + win);
                    System.out.println("当前负场： " + lose);
                    System.out.println("净赚金额： " + netEarnings);
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("游戏结束....欢迎下次光临");
                    break;
                default:
                    System.out.println("无效选项，请重新输入");
            }
        }
    }
}
