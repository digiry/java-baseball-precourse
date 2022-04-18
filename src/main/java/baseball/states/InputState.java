package baseball.states;

import baseball.GameBoard;
import baseball.GameController;
import baseball.State;

public class InputState implements State {
    private GameBoard gameBoard;
    private GameController gameController;

    public InputState(GameBoard board, GameController controller) {
        gameBoard = board;
        gameController = controller;
    }

    @Override
    public void viewUpdate() {
        gameController.printInputRequestMessage();
    }

    @Override
    public String readInput() {
        return gameController.readNumbersInput();
    }

    @Override
    public void evaluatePlayerData(String input) {
        gameController.evaluatePlayerData(input);
    }

    @Override
    public void nextState() {
        gameBoard.setState(gameBoard.getResultState());
    }
}
