/* *****************************************************************************
 *  Name:              Nick Oskin
 *  Coursera User ID:  123456
 *  Last modified:     24.04.2023
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats3 {
    private double[] thresholds;
    private int trials;

    /**
     * Выполняем trials экспериментов над сеткой размерности n x n
     */
    public PercolationStats3(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("Размер сетки должен быть положительным числом");
        if (trials <= 0) throw new IllegalArgumentException("Количество экспериментов должно быть положительным числом");

        this.trials = trials;
        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation3 perc = new Percolation3(n);

            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);

                perc.open(row, col);
            }


            double threshold = (double) perc.numberOfOpenSites() / (n * n);
            thresholds[i] = threshold;
        }
    }

    /**
     * Вычисляем среднее значение, стандартное отклонение и 95% доверительный интервал
     * для порога протекания через сетку
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return StdStats.mean(thresholds) - 1.96 * StdStats.stddev(thresholds) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return StdStats.mean(thresholds) + 1.96 * StdStats.stddev(thresholds) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        // int n = 50;
        int trials = Integer.parseInt(args[1]);
        // int trials = 20;

        PercolationStats3 stats = new PercolationStats3(n, trials);


        System.out.println("Среднее значение: " + stats.mean());
        System.out.println("Стандартное отклонение: " + stats.stddev());
        System.out.println("95% доверительный интервал: [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
