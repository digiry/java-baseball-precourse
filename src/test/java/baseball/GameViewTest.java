package baseball;

import baseball.GameView.ConsoleUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameViewTest {

    class FakeConsoleUtil implements ConsoleUtil {
        String testNumbers;
        FakeConsoleUtil(String numbers) {
            testNumbers = numbers;
        }

        @Override
        public String readLine() {
            return testNumbers;
        }
    }

    GameView makeGameViewWithFakeConsoleUtil(String test_numbers) {
        FakeConsoleUtil fakeConsoleUtil = new FakeConsoleUtil(test_numbers);

        GameView view = new GameView(fakeConsoleUtil);

        return view;
    }

    @Test
    @DisplayName("3자리 숫자가 숫자 입력 규칙에 맞는지 확인한다")
    void checkValidTripleNumbersFromUserInput() {
        GameView view = makeGameViewWithFakeConsoleUtil("123");

        String input = view.readNumbersInput();

        assertThat(input).matches(Constant.TRIPLE_NUMBER_REGEX_PATTERN);
    }

    @Test
    @DisplayName("4자리 숫자가 숫자 입력 규칙에 안 맞아 예외를 발생하는지 확인한다")
    void throwIllegalArgumentExceptionIfInputLengthIsOver() {
        GameView view = makeGameViewWithFakeConsoleUtil("1234");

        assertThatThrownBy(() -> {
            String input = view.readNumbersInput();
                }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자 아닌 문자가 있는 입력이 숫자 입력 규칙에 안 맞아 예외를 발생하는지 확인한다")
    void throwIllegalArgumentExceptionIfInputHasAlphabet() {
        GameView view = makeGameViewWithFakeConsoleUtil("A12");

        assertThatThrownBy(() -> {
            String input = view.readNumbersInput();
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
