package org.spongepowered.api.text.raw;

public enum FormatCode {

    BLACK("black", "0"),
    DARK_BLUE("dark_blue", "1"),
    DARK_GREEN("dark_green", "2"),
    DARK_AQUA("dark_aqua", "3"),
    DARK_RED("dark_red", "4"),
    DARK_PURPLE("dark_purple", "5"),
    GOLD("gold", "6"),
    GRAY("gray", "7"),
    DARK_GRAY("dark_gray", "8"),
    BLUE("blue", "9"),
    GREEN("green", "10"),
    AQUA("aqua", "11"),
    RED("red", "12"),
    LIGHT_PURPLE("light_purple", "13"),
    YELLOW("yellow", "14"),
    WHITE("white", "15"),

    OBFUSCATED("obfuscated", "k"),
    BOLD("bold", "l"),
    STRIKETHROUGH("strikethrough", "m"),
    UNDERLINE("underline", "n"),
    ITALIC("italic", "o"),

    RESET("reset", "r");

    private final String name;
    private final String code;

    FormatCode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

}
