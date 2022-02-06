package main.java.solver;

public class Main {

    public static void main(String[] args) {
	// write your code here
        WordSquares wordSquares = new WordSquares(Integer.parseInt(args[0]), args[1]);
        wordSquares.firstWords();
    }
}
