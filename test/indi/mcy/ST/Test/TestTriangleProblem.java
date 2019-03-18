package indi.mcy.ST.Test;

import indi.mcy.ST.TriangleProblem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
@RunWith(Parameterized.class)
public class TestTriangleProblem {
    private TriangleProblem triangleProblem;
    private int inputNumber;
    private boolean expectedResult;

    @Before
    public void initialize() {
        triangleProblem = new TriangleProblem();
    }

    public TestTriangleProblem(Integer inputNumber, Boolean expectedResult) {
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection inputNumbers() {
        return Arrays.asList(new Object[][] {
                {1, true},
                {2, true},
                {3, true},
                {4, false},
                {5, true},
                {8, true},
                {9, false},
                {14, false},
                {19, false},
                {24, false},
                {29, false},
                {50, true},
                {82, true},
                {83, true},
                {84, false}
        });
    }

    @Test
    public void testTriangleProblem() {
        System.out.println("Panameterized Number is: " + inputNumber);
        assertEquals(expectedResult, triangleProblem.triangleProblem(inputNumber));
    }
}
