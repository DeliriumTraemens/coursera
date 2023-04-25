/* *****************************************************************************
 *  Name:              Nick Oskin
 *  Coursera User ID:  123456
 *  Last modified:     11.04.2023
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TestUFConnection {
    public static void main(String[] args) {
        int N= StdIn.readInt();
        QuickUnionUFSample uf = new QuickUnionUFSample(N);

        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if(!uf.connected(p,q)){
                uf.union(p,q);
                StdOut.println(p + " -- "+ q);
            }
        }
    }
}
