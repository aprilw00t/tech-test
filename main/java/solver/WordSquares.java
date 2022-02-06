package main.java.solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WordSquares {
    private final int letterSize;
    private final String letters;

    private List<String> rows;
    private List<String> columns;
    private List<String> allWords;

    List<String> result = new LinkedList<>();

    public List<String> getColumns() {
        return columns;
    }

    public List<String> getRows() {
        return rows;
    }

    public WordSquares(int size, String letters) {
        //this.letters = letters;
        allWords = readWordList(letters, size);
        this.letterSize = size;
        this.rows = new ArrayList<>(size);
        this.columns = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            columns.add(i, "");
        }
        this.letters = letters;

    }
//creates empty arrays when wordsquare object is created
    public List<String> chooseFirstWord() {
        for (String word : allWords) {
            solver(0);
        }
        return result;
    }

    void solver(Integer columnNumber) {
        if (rows.size() == letterSize) {
            if (lettersMatch(letters)) {
                result = List.copyOf(rows);
                System.out.println(result);
            }
            return;
        }

//returns when the arraylist is equal to the size
        for (String filteredWord : listOfPossibleWords(columnNumber, allWords)) { //Make this for loop its own function?
            addWordToSquare(filteredWord);
            updateWordBeginnings();
            solver(columnNumber + 1);
            rows.remove(rows.size() - 1);
            updateWordBeginnings();
        }
    }
    //filters the list of words
    private boolean lettersMatch(String initialString) {
        StringBuilder word = new StringBuilder();
        for (String wor : rows) {
            word.append(wor);
        }
        String[] initialStringArray = initialString.split("");
        String[] wordArray = word.toString().split("");
        Arrays.sort(initialStringArray);
        Arrays.sort(wordArray);
        return Arrays.equals(wordArray, initialStringArray);
    }
//checks if the letters match starting letters
    public void updateWordBeginnings() {
        for (int i = 0; i < letterSize; i++) {
            columns.set(i, "");
        }
        for (int j = 0; j < letterSize; j++) {
            for (int i = 0; i < rows.size(); i++) {
                columns.set(j, columns.get(j) + String.valueOf(rows.get(i).charAt(j)));
            }
        }
    }
    public void addWordToSquare(String word) {
        rows.add(word);
    }

    public static List<String> readWordList(String letters, int size) {
        List<String> wordsFiltered = new ArrayList<>(size);
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/dictionary.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == size && line.matches("[" + letters + "]+")) {
                    wordsFiltered.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsFiltered;
    }

    public boolean isWordCompatible(String wordToCheck, Integer columnNumber) {
        return (wordToCheck.startsWith(columns.get(columnNumber)));
    }

    private List<String> listOfPossibleWords(Integer column, List<String> wordList) {
        List<String> possibleWords = new ArrayList<>();
        for (String wordsFromList : wordList) {
            if (isWordCompatible(wordsFromList, column)) {
                possibleWords.add(wordsFromList);
            }
        }
        return possibleWords;
    }
}



