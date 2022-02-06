package test;

import main.java.solver.WordSquares;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WordSquareTest {
    @Test
    public void updateHorizontalTest() {
        WordSquares wordSquares = new WordSquares(4, "eeeeddoonnnsssrv");
        wordSquares.addWordToSquare("lols");
        Assert.assertEquals("lols", wordSquares.getRows().get(0));
    }

    @Test
    public void updateVerticalTest() {
        WordSquares wordSquares = new WordSquares(4, "eeeeddoonnnsssrv");
        wordSquares.addWordToSquare("lols");
        wordSquares.addWordToSquare("mops");
        wordSquares.updateWordBeginnings();
        Assert.assertEquals("lm", wordSquares.getColumns().get(0));
        Assert.assertEquals("oo", wordSquares.getColumns().get(1));
        Assert.assertEquals("lp", wordSquares.getColumns().get(2));
    }

    @Test
    public void solverTest() {
        WordSquares wordSquares = new WordSquares(4, "aaccdeeeemmnnnoo");
        Assert.assertEquals(List.of("moan", "once", "acme", "need"), wordSquares.chooseFirstWord());
    }
}

