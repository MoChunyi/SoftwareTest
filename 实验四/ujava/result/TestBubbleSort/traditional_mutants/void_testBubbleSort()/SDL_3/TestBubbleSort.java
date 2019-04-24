// This is a mutant program.
// Author : ysma

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;


public class TestBubbleSort
{

    private BubbleSort bubbleSort;

    public  void setBubbleSort()
        throws java.lang.Exception
    {
        bubbleSort = new BubbleSort();
    }

    public  void testBubbleSort()
    {
        int[] testArray = { 2, 8, 4, 1, 2, 9 };
        bubbleSort.BubbleSort( testArray );
    }

}
