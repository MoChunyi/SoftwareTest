package indi.mcy.ST.lab4;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TestBubbleSort {
    private BubbleSort bubbleSort;
    @Before
    public void setBubbleSort() throws Exception {
        bubbleSort = new BubbleSort();
    }
    @Test
    public void testBubbleSort() {
        int testArray[] = {2,8,4,1,2,9};
        bubbleSort.BubbleSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

}
