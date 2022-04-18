package baseball;

import baseball.DefensePlayer.RandomUtil;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class DefensePlayerTest {
    class FakeRandomUtil implements RandomUtil {
        Integer[] tempArray;
        int current_index;

        FakeRandomUtil(Integer[] testNumbers) {
            tempArray = testNumbers;
            current_index = 0;
        }

        @Override
        public int getNumberInRange(int startInclusive, int endInclusive) {
            int random_number = tempArray[current_index];
            current_index++;
            current_index = current_index % tempArray.length;
            return random_number;
        }
    }

    DefensePlayer makePlayerWithFakeRandomUtil(String numbers) {
        Integer[] number_array = new Integer[numbers.length()];
        int index = 0;
        for (String letter : numbers.split("(?)")) {
            number_array[index] = Integer.valueOf(letter);
            index++;
        }
        FakeRandomUtil fakeRandomUtil = new FakeRandomUtil(number_array);
        DefensePlayer player = new DefensePlayer(fakeRandomUtil);
        return player;
    }

    @Test
    @DisplayName("1에서 9사이에 임의로 생성된 값에서 중복 없는 서로 다른 수가 될 때까지 반복하여 3자리 수를 생성하는지 검사한다")
    void makeRandomNumbersWithoutDuplicated() {
        DefensePlayer player = makePlayerWithFakeRandomUtil("1123");

        player.makeRandomNumbers();

        assertThat(Arrays.asList(player.getNumbers())).contains(1, 2, 3);
    }

    @ParameterizedTest
    @CsvSource(value = {"415:1", "451:1", "214:2", "241:2", "412:2", "312:3", "456:0"}, delimiter = ':')
    @DisplayName("123과 비교해서 볼 개수를 제대로 판정하는지 검사한다")
    void checkBallCount(String input_numbers, int expected_ball_count) {
        DefensePlayer player = makePlayerWithFakeRandomUtil("123");

        player.makeRandomNumbers();
        player.evaluateData(input_numbers);

        assertThat(player.getBallCount()).isEqualTo(expected_ball_count);
    }

    @ParameterizedTest
    @CsvSource(value = {"145:1", "425:1", "453:1", "124:2", "423:2", "143:2", "123:3", "456:0"}, delimiter = ':')
    @DisplayName("123과 비교해서 스트라이크 개수를 제대로 판정하는지 검사한다")
    void checkStrikeCount(String input_numbers, int expected_strike_count) {
        DefensePlayer player = makePlayerWithFakeRandomUtil("123");

        player.makeRandomNumbers();
        player.evaluateData(input_numbers);

        assertThat(player.getStrikeCount()).isEqualTo(expected_strike_count);
    }

    @Test
    @DisplayName("123과 동일한 입력에 3스트라이크인지 확인한다")
    void winTripleStrike() {
        DefensePlayer player = makePlayerWithFakeRandomUtil("123");
        player.makeRandomNumbers();
        player.evaluateData("123");

        boolean result = player.isTripleStrike();

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"145", "425", "453", "124", "423", "143", "456", "415", "451", "214", "241", "412", "312"})
    @DisplayName("123과 비교해서 모두 3스트라이크가 아닌지 확인한다")
    void checkTripleStrike(String input_numbers) {
        DefensePlayer player = makePlayerWithFakeRandomUtil("123");
        player.makeRandomNumbers();
        player.evaluateData(input_numbers);

        boolean result = player.isTripleStrike();

        assertThat(result).isEqualTo(false);
    }
}
