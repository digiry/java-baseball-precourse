package baseball;

import baseball.GameView.ConsoleUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {Constant.MENU_RETRY, Constant.MENU_CLOSE})
    @DisplayName("진행 여부 메뉴가 메뉴 입력 규칙에 맞는지 확인한다")
    void checkValidMenuFromUserInput(String input) {
        GameView view = makeGameViewWithFakeConsoleUtil(input);

        String menu = view.readMenuInput();

        assertThat(menu).matches(Constant.MENU_REGEX_PATTERN);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "A"})
    @DisplayName("메뉴 선택을 1, 2가 아닌 경우 예외를 발생하는지 확인한다")
    void throwIllegalArgumentExceptionIfMenuHasWrongChoice(String input) {
        GameView view = makeGameViewWithFakeConsoleUtil(input);

        assertThatThrownBy(() -> {
            String menu = view.readMenuInput();
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
