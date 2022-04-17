package baseball;

import baseball.DefensePlayer.RandomUtil;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("1에서 9사이에 임의로 생성된 값에서 중복 없는 서로 다른 수가 될 때까지 반복하여 3자리 수를 생성하는지 검사한다")
    void makeRandomNumbersWithoutDuplicated() {
        Integer[] test_numbers = {1,1,2,3};
        FakeRandomUtil fakeRandomUtil = new FakeRandomUtil(test_numbers);
        DefensePlayer player = new DefensePlayer(fakeRandomUtil);

        player.makeRandomNumbers();

        assertThat(Arrays.asList(player.GetNumbers())).contains(1, 2, 3);
    }
}
