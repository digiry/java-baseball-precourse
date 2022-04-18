package baseball;

public class GameController {
    private DefensePlayer defensePlayer;
    private GameView gameView;

    public GameController(DefensePlayer player, GameView view) {
        defensePlayer = player;
        gameView = view;
    }

    public void evaluatePlayerData(String data) {
        defensePlayer.evaluateData(data);
    }

    public int getBallCount() {
        return defensePlayer.getBallCount();
    }

    public int getStrikeCount() {
        return defensePlayer.getStrikeCount();
    }

    public boolean isTripleStrike() {
        return defensePlayer.isTripleStrike();
    }

    public void printInputRequestMessage() {
        gameView.printInputRequestMessage();
    }

    public void printGameResult() {
       gameView.printGameResult(defensePlayer.getBallCount(), defensePlayer.getStrikeCount());
    }

    public void printStageDoneMessage() {
        gameView.printStageDoneMessage();
    }

    public void printMenuMessage() {
        gameView.printMenuMessage();
    }

    public void printCloseMessage() {
        gameView.printCloseMessage();
    }

    public String readNumbersInput() {
        return gameView.readNumbersInput();
    }

    public String readMenuInput() {
        return gameView.readMenuInput();
    }

    public String getEmptyInputData() {
        return Constant.EMPTY_DATA;
    }
}
