package compiler.utility;

public enum ColorMessage {

    RED,
    BLUE,
    GREEN,
    YELLOW,
    WHITE,
    PINK;

    private static String getEscape(ColorMessage color) {
        switch (color) {
            case RED:
                return "\u001b[38;5;160m";
            case GREEN:
                return "\u001b[38;5;82m";
            case YELLOW:
                return "\u001b[33m";
            case BLUE:
                return "\u001b[38;5;26m";
            case PINK:
                return "\u001b[38;5;205m";
            default:
                return "\u001b[38;5;255m"; //WHITE
        }
    }

    public static void makeColor(ColorMessage color, String data) {
         System.out.println(getEscape(color) + data + "\u001b[0m");
    }
}
