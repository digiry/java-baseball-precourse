package baseball;

public interface State {
    void viewUpdate();
    String readInput();
    void evaluatePlayerData(String input);
    void nextState();
}
