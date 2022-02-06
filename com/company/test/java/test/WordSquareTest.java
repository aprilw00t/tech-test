package com.company.test.java.test;

import main.java.solver.WordSquares;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WordSquareTest {
    @Test
    public void updateHorizontalTest() {
        WordSquares wordSquares = new WordSquares(4, "eeeeddoonnnsssrv");
        wordSquares.updateHorizontal("lols");
        Assert.assertEquals("lols", wordSquares.getHorizontal().get(0));
    }

    @Test
    public void updateVerticalTest() {
        WordSquares wordSquares = new WordSquares(4, "eeeeddoonnnsssrv");
        wordSquares.updateHorizontal("lols");
        wordSquares.updateHorizontal("mops");
        wordSquares.updateVertical();
        Assert.assertEquals("lm", wordSquares.getVertical().get(0));
        Assert.assertEquals("oo", wordSquares.getVertical().get(1));
        Assert.assertEquals("lp", wordSquares.getVertical().get(2));
    }

    @Test
    public void solverTest() {
        WordSquares wordSquares = new WordSquares(4, "aaccdeeeemmnnnoo");
        Assert.assertEquals(List.of("moan", "once", "acme", "need"), wordSquares.firstWords());
    }
}

