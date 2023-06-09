/* *****************************************************************************
 *  Name:              Nick Oskin
 *  Coursera User ID:  123456
 *  Last modified:     20.04.2023
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StopwatchCPU;

public class PercolationStats {

    private final double mean;
    private final double sd;
    private final double highConfidence;
    private final double lowConfidence;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        double[] results = new double[trials];
        int testX;
        int testY;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            int counter = 0;
            while (!percolation.percolates()) {
                testX = (int) ((StdRandom.uniformDouble() * n) + 1);
                testY = (int) ((StdRandom.uniformDouble() * n) + 1);

                if (!percolation.isOpen(testY, testX)) {
                    percolation.open(testX, testY);
                }
                counter++;
            }
            results[i] = (double) (percolation.numberOfOpenSites()) / (n * n);
        }

        mean = StdStats.mean(results);
        sd = StdStats.stddev(results);

        lowConfidence = mean - (1.96 * sd) / Math.sqrt(trials);
        highConfidence = mean + (1.96 * sd) / Math.sqrt(trials);

    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return sd;
    }

    public double confidenceLo() {
        return lowConfidence;
    }

    public double confidenceHi() {
        return highConfidence;
    }

    public static void main(String[] args) {
        StopwatchCPU stopWatch = new StopwatchCPU();
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);


        System.out.println("Mean: " + stats.mean());
        System.out.println("Stddev: " + stats.stddev());
        System.out.println(
                "95% confidence interval: [" + stats.confidenceLo() + ", " + stats.confidenceHi()
                        + "]");
        double stop = stopWatch.elapsedTime();
        System.out.println(stop+" is totaltime");
    }
}
