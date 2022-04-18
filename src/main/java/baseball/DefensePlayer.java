package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.Set;

public class DefensePlayer {
    private Integer[] numbers;
    private final RandomUtil randomUtil;
    private int ballCount;
    private int strikeCount;

    public DefensePlayer(RandomUtil util) {
        randomUtil = util;
        initializeNumbers();
        initializeEveryCounts();
    }

    public DefensePlayer() {
        randomUtil = new NextstepRandomUtil();
        initializeNumbers();
        initializeEveryCounts();
    }

    private void initializeNumbers() {
        if (numbers == null) {
            numbers = new Integer[Constant.NUMBERS_LENGTH];
        }
        numbers[0] = Constant.NONE_NUMBER;
        numbers[1] = Constant.NONE_NUMBER;
        numbers[2] = Constant.NONE_NUMBER;
    }

    private void initializeEveryCounts() {
        ballCount = 0;
        strikeCount = 0;
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

        while (unique_number_set.size() < Constant.NUMBERS_LENGTH) {
            int any_number = randomUtil.getNumberInRange(1,9);
            unique_number_set.add(any_number);
        }

        int index = 0;
        for (int pick_number : unique_number_set) {
            numbers[index] = pick_number;
            index++;
        }
    }

    public int getNumberAt(final int index) {
        assert index < Constant.NUMBERS_LENGTH : "Out of Numbers indices";

        return numbers[index];
    }

    public final Integer[] getNumbers() {
        return numbers;
    }

    public void evaluateData(String input_numbers) {
        Integer[] input_number_array = convertIntegerArray(input_numbers);

        ballCount = countBall(input_number_array);
        strikeCount = countStrike(input_number_array);
    }

    public Integer[] convertIntegerArray(String string_numbers) {
        Integer[] input_number_array = new Integer[Constant.NUMBERS_LENGTH];
        int index = 0;
        for (String letter : string_numbers.split("(?)")) {
            input_number_array[index] = Integer.valueOf(letter);
            index++;
        }
        return input_number_array;
    }

    int countBall(Integer[] input_numbers) {
        int count = 0;
        for (int i = 0; i < Constant.NUMBERS_LENGTH; i++) {
            count += checkBallCountFrom(i, input_numbers[i]);
        }
        return count;
    }

    int checkBallCountFrom(int index, Integer input_number) {
        int next_index = (index + 1) % Constant.NUMBERS_LENGTH;
        int next_next_index = (index + 2) % Constant.NUMBERS_LENGTH;
        int count = 0;

        if (numbers[next_index].equals(input_number)) {
            count++;
        }
        if (numbers[next_next_index].equals(input_number)) {
            count++;
        }

        return count;
    }

    public int getBallCount() {
        return ballCount;
    }

    int countStrike(Integer[] input_numbers) {
        int count = 0;
        for (int i = 0; i < Constant.NUMBERS_LENGTH; i++) {
            count += checkStrikeCountFrom(i, input_numbers[i]);
        }
        return count;
    }

    int checkStrikeCountFrom(int index, Integer input_number) {
        int count = 0;
        if (numbers[index].equals(input_number)) {
            count++;
        }

        return count;
    }

    public int getStrikeCount() {
        return strikeCount;
    }
}
