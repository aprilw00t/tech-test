package main.java.solver;

public class Main {

    public static void main(String... args) {
        WordSquares wordSquares = new WordSquares(Integer.parseInt(args[0]), args[1]);
        wordSquares.chooseFirstWord();
    }
}
