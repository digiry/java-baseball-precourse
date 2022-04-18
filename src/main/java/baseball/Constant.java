package baseball;

public class Constant {
    public static final int NUMBERS_LENGTH = 3;
    public static final int NONE_NUMBER = 0;
    public static final String TRIPLE_NUMBER_REGEX_PATTERN = "^[0-9]{3}$";
    public static final String MENU_RETRY = "1";
    public static final String MENU_CLOSE = "2";
    public static final String MENU_REGEX_PATTERN = String.format("^[%s%s]$", MENU_RETRY, MENU_CLOSE);
    public static final String EMPTY_DATA = "";
}
