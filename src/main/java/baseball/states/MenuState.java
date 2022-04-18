package baseball.states;

import baseball.Constant;
import baseball.GameBoard;
import baseball.GameController;
import baseball.State;

public class MenuState implements State {
    private GameBoard gameBoard;
    private GameController gameController;
    private String selectedMenu;

    public MenuState(GameBoard board, GameController controller) {
        gameBoard = board;
        gameController = controller;
    }

    @Override
    public void viewUpdate() {
        gameController.printMenuMessage();
    }

    @Override
    public String readInput() {
        setSelectedMenu(gameController.readMenuInput());
        return selectedMenu;
    }

    @Override
    public void evaluatePlayerData(String input) {
        gameController.evaluatePlayerData(gameController.getEmptyInputData());
    }

    @Override
    public void nextState() {
        gameBoard.setState(gameBoard.getCloseState());

        if (selectedMenu.equals(Constant.MENU_RETRY)) {
            gameBoard.setState(gameBoard.getInitState());
        }
    }

    private void setSelectedMenu(String menu) {
        selectedMenu = menu;
    }
}
