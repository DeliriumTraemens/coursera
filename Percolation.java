/* *****************************************************************************
 *  Name:              Nick Oskin
 *  Coursera User ID:  123456
 *  Last modified:     18.04.2023
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] grid;

    private final WeightedQuickUnionUF gridMap;

    private final int n;
    private int openCell;
    private final int top;
    private final int bottom;

    public Percolation(int n) {

        if (n <= 0) throw new IllegalArgumentException();
        gridMap = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n * n + 2];

        this.n = n;
        openCell = 0;
        top = 0;
        bottom = n * n + 1;
    }

    private void checkRange(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IllegalArgumentException("-----Out Of Range----------");
        }
    }

    private int indexOf(int row, int col) {
        checkRange(row, col);
        return (row - 1) * n + (col - 1);
    }

    public boolean isOpen(int row, int col) {
        checkRange(row, col);
        return grid[indexOf(row, col)];
    }

    public boolean isFull(int row, int col) {
        checkRange(row, col);
        if(!isOpen(row,col)) return false;
        return gridMap.find(indexOf(row, col)) == gridMap.find(top);
    }

    public boolean percolates() {
        return gridMap.find(top) == gridMap.find(bottom);
    }

    public int numberOfOpenSites() {
        return this.openCell;
    }

    public void open(int row, int col) {
        checkRange(row, col);
        int currentCellIndex = indexOf(row, col);
        grid[currentCellIndex] = true;
        this.openCell++;
        if (row == 1) {
            gridMap.union(currentCellIndex, top);
        }
        if (row == this.n) {
            gridMap.union(currentCellIndex, bottom);
        }
        if (row > 1 && isOpen(row - 1, col)) {
            assert (currentCellIndex > n);
            gridMap.union(currentCellIndex, currentCellIndex - n);
        }
        if (row < this.n && isOpen(row + 1, col)) {
            assert (currentCellIndex + n < n * n);
            gridMap.union(currentCellIndex, currentCellIndex + n);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            gridMap.union(currentCellIndex, currentCellIndex - 1);
        }
        if (col < this.n && isOpen(row, col + 1)) {
            gridMap.union(currentCellIndex, currentCellIndex + 1);
        }
    }
}
