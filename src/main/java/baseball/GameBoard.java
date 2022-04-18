package baseball;

import baseball.states.CloseState;
import baseball.states.DoneState;
import baseball.states.ExitState;
import baseball.states.InitState;
import baseball.states.InputState;
import baseball.states.MenuState;
import baseball.states.ResultState;

public class GameBoard {
    private GameController gameController;

    private InitState initState;
    private InputState inputState;
    private ResultState resultState;
    private DoneState doneState;
    private MenuState menuState;
    private CloseState closeState;
    private ExitState exitState;
    private State gameState;

    public GameBoard(GameController controller) {
        gameController = controller;
        initializeGameStates(this, gameController);
    }

    void initializeGameStates(GameBoard board, GameController controller) {
        initState = new InitState(board, controller);
        inputState = new InputState(board, controller);
        resultState = new ResultState(board, controller);
        doneState = new DoneState(board, controller);
        menuState = new MenuState(board, controller);
        closeState = new CloseState(board, controller);
        exitState = new ExitState(board, controller);
    }

    public void run() {
        setState(initState);

        while (gameState.equals(exitState) == false) {
            gameState.viewUpdate();
            String input = gameState.readInput();
            gameState.evaluatePlayerData(input);
            gameState.nextState();
        }
    }

    public void setState(State state) {
        gameState = state;
    }

    public State getInitState() {
        return initState;
    }

    public State getInputState() {
        return inputState;
    }

    public State getResultState() {
        return resultState;
    }

    public State getDoneState() {
        return doneState;
    }

    public State getMenuState() {
        return menuState;
    }

    public State getCloseState() {
        return closeState;
    }

    public State getExitState() {
        return exitState;
    }

}
