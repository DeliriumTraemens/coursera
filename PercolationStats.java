/* *****************************************************************************
 *  Name:              Nick Oskin
 *  Coursera User ID:  123456
 *  Last modified:     20.04.2023
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean ;
    private final double sd;
    private final double highConfidence;
    private final double lowConfidence;

    public PercolationStats(int n , int trials)
    {
        if(n <= 0 || trials <= 0) throw new IllegalArgumentException();

        double[] results = new double[trials];
        int testX;
        int testY;
        // System.out.println("BlahBlah");

        for(int i = 0 ; i < trials;i ++){
            Percolation perc2 = new Percolation(n);

            int counter=0;
            while(!perc2.percolates())
            {
                StdOut.println("Percolates ="+perc2.percolates());
                testX = (int)((StdRandom.uniformDouble() * n) + 1);
                testY = (int)((StdRandom.uniformDouble() * n) + 1);

                if(!perc2.isOpen(testY,testX))
                {
                    // System.out.println("Try #"+i);
                    perc2.open(testX,testY);// here we open random cell!
                }
                counter++;
            }
            results[i] = (double)(perc2.numberOfOpenSites()) / (n * n);

            // StdOut.println("Percolates ="+perc2.percolates());
            // StdOut.println("Counter is "+counter);
        }

        mean = StdStats.mean(results);
        sd = StdStats.stddev(results);

        lowConfidence =  mean - (1.96 * sd)/ Math.sqrt(trials);
        highConfidence =  mean + (1.96 * sd)/ Math.sqrt(trials);

    }

    public double mean(){
        return mean;
    }

    public double stddev()
    {
        return sd;
    }

    public double confidenceLo()
    {
        return lowConfidence;
    }

    public double confidenceHi(){
        return highConfidence;
    }
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        // int n = 50;
        int trials = Integer.parseInt(args[1]);
        // int trials = 20;

        PercolationStats stats = new PercolationStats(n, trials);



        System.out.println("Среднее значение: " + stats.mean());
        System.out.println("Стандартное отклонение: " + stats.stddev());
        System.out.println("95% доверительный интервал: [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
