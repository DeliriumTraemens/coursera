/* *****************************************************************************
 *  Name:              Nick Oskin
 *  Coursera User ID:  123456
 *  Last modified:     24.04.2023
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation3 {
    public boolean[][] grid;   // храним состояние клетки
    private int n;              // размерность сетки
    private int top = 0;        // верхняя виртуальная клетка
    private int bottom;         // нижняя виртуальная клетка
    private int openSites;      // количество открытых клеток
    private WeightedQuickUnionUF uf;  // объект, реализующий алгоритм быстрого объединения

    /** Constructor
     * Создаем сетку размерности n x n и инициализируем не открытыми клетками
     */
    public Percolation3(int n) {
        if (n <= 0) throw new IllegalArgumentException("Размер сетки должен быть положительным числом");
        this.n = n;//Передаем размер сетки в поле объекта
        grid = new boolean[n][n];//Двумерный массив размерностью n, заданной в аргах конструктора

        //Заполняем сетку состояной  фолзами
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }

        bottom = n * n + 1; // Номер нижнего виртуального связующего

        // инициализируем объект для быстрого объединения
        uf = new WeightedQuickUnionUF(n * n + 2);

        // объединяем верхнюю и нижнюю виртуальную клетки с соответствующими реальными клетками
        for (int i = 1; i <= n; i++) {
            uf.union(getIndex(1, i), top);
            uf.union(getIndex(n, i), bottom);
        }
    }//Constructor end
    /**
     * Открыть клетку с координатами (row, col)
     */
    public void open(int row, int col) {
        checkBounds(row, col);

        // открываем клетку, если она не была уже открыта
        if (!grid[row - 1][col - 1]) {
            grid[row - 1][col - 1] = true;
            openSites++;

            // объединяем клетку с соседними открытыми клетками
            int p = getIndex(row, col);

            if (row > 1 && isOpen(row - 1, col)) {
                int q = getIndex(row - 1, col);
                uf.union(p, q);
            }
            if (row < n && isOpen(row + 1, col)) {
                int q = getIndex(row + 1, col);
                uf.union(p, q);
            }
            if (col > 1 && isOpen(row, col - 1)) {
                int q = getIndex(row, col - 1);
                uf.union(p, q);
            }
            if (col < n && isOpen(row, col + 1)) {
                int q = getIndex(row, col + 1);
                uf.union(p, q);
            }
        }
    }

    /**
     * Проверить, открыта ли клетка с координатами (row, col)
     */
    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return grid[row - 1][col - 1];
    }

    /**
     * Проверить, связана ли клетка с верхней виртуальной клеткой
     */
    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        // return uf.connected(getIndex(row, col), top);
        return uf.connected(getIndex(row, col), top);
    }

    /**
     * Получить количество открытых клеток
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * Проверить, связаны ли верхняя и нижняя виртуальные клетки, т.е. протекает ли вода через сетку
     */
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    /**
     * Проверить корректность индексов
     */
    private void checkBounds(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Недопустимые координаты клетки");
        }
    }

    /**
     * Преобразовать координаты клетки в одномерный индекс
     */
    private int getIndex(int row, int col) {
        return (row - 1) * n + col;
    }
}
