package baseball;

public class Application {
    public static void main(String[] args) {
        DefensePlayer player = new DefensePlayer();
        GameView view = new GameView();
        GameController controller = new GameController(player, view);
        GameBoard board = new GameBoard(controller);

        board.run();
    }
}
