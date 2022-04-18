package baseball.states;

import baseball.GameBoard;
import baseball.GameController;
import baseball.State;

public class DoneState implements State {
    private GameBoard gameBoard;
    private GameController gameController;

    public DoneState(GameBoard board, GameController controller) {
        gameBoard = board;
        gameController = controller;
    }

    @Override
    public void viewUpdate() {

    }

    @Override
    public String readInput() {
        return null;
    }

    @Override
    public void evaluatePlayerData(String input) {

    }

    @Override
    public void nextState() {

    }
}
