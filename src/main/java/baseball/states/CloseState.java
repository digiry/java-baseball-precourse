package baseball.states;

import baseball.GameBoard;
import baseball.GameController;
import baseball.State;

public class CloseState implements State {
    private GameBoard gameBoard;
    private GameController gameController;

    public CloseState(GameBoard board, GameController controller) {
        gameBoard = board;
        gameController = controller;
    }

    @Override
    public void viewUpdate() {
        gameController.printCloseMessage();
    }

    @Override
    public String readInput() {
        return gameController.getEmptyInputData();
    }

    @Override
    public void evaluatePlayerData(String input) {
        gameController.evaluatePlayerData(input);
    }

    @Override
    public void nextState() {
        gameBoard.setState(gameBoard.getExitState());
    }
}
