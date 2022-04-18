package baseball;

public class GameView {
    public void printInputRequestMessage() {
        System.out.print("숫자를 입력해주세요: ");
    }

    public void printGameResult(int ball_count, int strike_count) {
        String message = "";

        if (ball_count > 0) {
            message += String.format("%d볼", ball_count);
        }
        if (strike_count > 0) {
            message += String.format("%d스트라이크", strike_count);
        }
        if (ball_count == 0 && strike_count == 0) {
            message = "낫싱";
        }

        System.out.println(message);
    }

    public void printStageDoneMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    public void printMenuMessage() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
    }

    public void printCloseMessage() {
        System.out.println("게임 종료");
    }
}
