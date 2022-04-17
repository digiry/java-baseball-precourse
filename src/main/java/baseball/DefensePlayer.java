package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.Set;

public class DefensePlayer {
    final int NONE_NUMBER = 0;
    final int NUMBERS_LENGTH = 3;
    private Integer[] numbers;
    private final RandomUtil randomUtil;

    public DefensePlayer(RandomUtil util) {
        randomUtil = util;
        initializeNumbers();
    }

    public DefensePlayer() {
        randomUtil = new NextstepRandomUtil();
        initializeNumbers();
    }

    private void initializeNumbers() {
        if (numbers == null) {
            numbers = new Integer[NUMBERS_LENGTH];
        }
        numbers[0] = NONE_NUMBER;
        numbers[1] = NONE_NUMBER;
        numbers[2] = NONE_NUMBER;
    }

    public interface RandomUtil {
        int getNumberInRange(final int startInclusive, final int endInclusive);
    }

    public static class NextstepRandomUtil implements RandomUtil {

        @Override
        public int getNumberInRange(final int startInclusive, final int endInclusive) {
            return Randoms.pickNumberInRange(startInclusive, endInclusive);
        }
    }

    public void makeRandomNumbers() {
        Set<Integer> unique_number_set = new HashSet<>();

        while (unique_number_set.size() < NUMBERS_LENGTH) {
            int any_number = randomUtil.getNumberInRange(1,9);
            unique_number_set.add(any_number);
        }

        int index = 0;
        for (int pick_number : unique_number_set) {
            numbers[index] = pick_number;
            index++;
        }
    }

    public int GetNumberAt(final int index) {
        assert index < NUMBERS_LENGTH : "Out of Numbers indices";

        return numbers[index];
    }

    public final Integer[] GetNumbers() {
        return numbers;
    }
}
