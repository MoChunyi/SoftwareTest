// This is a mutant program.
// Author : ysma

import org.junit.Before;
import org.junit.Test;


public class TestBackPack
{

    private BackPack backPack;

    public  void setBackPack()
        throws java.lang.Exception
    {
        backPack = new BackPack();
    }

    public  void testBackPack()
    {
        int m = 10;
        int n = 3;
        int[] w = { 3, 4, 5 };
        int[] p = { 4, 5, 6 };
        int[][] c = backPack.BackPack_Solution( m--, n, w, p );
        backPack.printPack( c, n, m );
    }

}
