package com.pluralsight.NorthwindTradersAPI.util;

public class ConsoleColors {


    public static final String RESET = "\033[0m";


    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";


    public static final String BOLD = "\033[1m";


    public static final String BOLD_WHITE = "\033[1;97m";


    public static void printHeader(String text) {
        System.out.println(BOLD + CYAN + "\n" + "=".repeat(70) + RESET);
        System.out.println(BOLD + BLUE + text + RESET);
        System.out.println(BOLD + CYAN + "=".repeat(70) + RESET);
    }


    public static void printSection(String text) {
        System.out.println(BOLD + "\n" + text + RESET);
        System.out.println("-".repeat(text.length()));
    }


    public static void printSuccess(String text) {
        System.out.println(GREEN + "✓ " + text + RESET);
    }


    public static void printError(String text) {
        System.out.println(RED + "✗ " + text + RESET);
    }


    public static void printWarning(String text) {
        System.out.println(YELLOW + "⚠ " + text + RESET);
    }
}
