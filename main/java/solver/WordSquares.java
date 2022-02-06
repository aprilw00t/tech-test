package main.java.solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WordSquares {
    private final int size;
    private final String letters;

    private List<String> horizontal;
    private List<String> vertical;
    private List<String> allWords;

    List<String> result = new LinkedList<>();

    public List<String> getVertical() {
        return vertical;
    }

    public List<String> getHorizontal() {
        return horizontal;
    }

    public WordSquares(int size, String letters) {
        this.letters = letters;
        allWords = readWordList(letters, size);
        this.size = size;
        this.horizontal = new ArrayList<>(size);
        this.vertical = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            vertical.add(i, "");
        }
    }
//creates empty arrays when wordsquare object is created
    public List<String> chooseFirstWord() {
        for (String word : allWords) {
            //     Integer columnNumber = 0;
            solver(0);
        }
        return result;
    }

    void solver(Integer columnNumber) {
        if (horizontal.size() == size) {
            if (lettersMatch(letters)) {
                result = List.copyOf(horizontal);
                System.out.println(result);
            }
            return;
        }
//returns when the arraylist is equal to the size
        for (String filteredWord : listOfPossibleWords(columnNumber, allWords)) {
            addWordToSquare(filteredWord);
            updateWordBeginnings();
            solver(columnNumber + 1);
            horizontal.remove(horizontal.size() - 1);
            updateWordBeginnings();
        }
    }
    //filters the list of words
    private boolean lettersMatch(String initialString) {
        StringBuilder word = new StringBuilder();
        for (String wor : horizontal) {
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
        for (int i = 0; i < size; i++) {
            vertical.set(i, "");
        }
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < horizontal.size(); i++) {
                vertical.set(j, vertical.get(j) + String.valueOf(horizontal.get(i).charAt(j)));
            }
        }
    }
    public void addWordToSquare(String word) {
        horizontal.add(word);
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
        return (wordToCheck.startsWith(vertical.get(columnNumber))) && wordToCheck.length() == size;
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



