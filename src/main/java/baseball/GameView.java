package baseball;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

public class GameView {
    private final ConsoleUtil consoleUtil;

    public GameView() {
        consoleUtil = new NextstepConsoleUtil();
    }

    public GameView(ConsoleUtil util) {
        consoleUtil = util;
    }

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

    public String readNumbersInput() {
        String input_numbers = consoleUtil.readLine();

        if (validateNumbersInput(input_numbers) == false) {
            throw new IllegalArgumentException(String.format("입력값 규칙에 맞지 않음: %s", input_numbers));
        }

        return input_numbers;
    }

    private boolean validateNumbersInput(String input) {
        boolean match = Pattern.matches(Constant.TRIPLE_NUMBER_REGEX_PATTERN, input);

        return match;
    }

    public String readMenuInput() {
        String menu = consoleUtil.readLine();

        if (validateMenuInput(menu) == false) {
            throw new IllegalArgumentException(String.format("없는 메뉴 선택함: %s", menu));
        }

        return menu;
    }

    private boolean validateMenuInput(String menu) {
        boolean match = Pattern.matches(Constant.MENU_REGEX_PATTERN, menu);

        return match;
    }

    public interface ConsoleUtil {
        String readLine();
    }

    public static class NextstepConsoleUtil implements ConsoleUtil {

        @Override
        public String readLine() {

            return Console.readLine();
        }
    }
}
