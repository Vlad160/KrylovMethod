import java.util.Formatter;

class Matrix {
    private Double matrix[][];
    private Integer rows;
    private Integer columns;

    public Matrix() {
        rows = 0;
        columns = 0;
    }

    public Matrix(int row, int column) {
        matrix = new Double[row][column];
        rows = row;
        columns = column;
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < columns; ++j)
                matrix[i][j] = 0.;
    }

    public Matrix(double matrix[][]) {
        this.rows = matrix.length;
        this.columns = matrix[rows - 1].length;
        this.matrix = new Double[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j)
                this.matrix[i][j] = matrix[i][j];
        }
    }

    public Matrix(Matrix matrix1) {
        rows = matrix1.getRows();
        columns = matrix1.getColumns();
        matrix = new Double[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j)
                matrix[i][j] = matrix1.get(i, j);
        }
    }

    public void print() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                Formatter fmt = new Formatter();
                fmt.format("%5.4g", matrix[i][j]);
                System.out.print(fmt + " ");
            }
            System.out.println();
        }
    }

    public void swapRows(int firstRow, int secondRow) {
        for (int j = 0; j < columns; ++j) {
            double temp = matrix[firstRow][j];
            matrix[firstRow][j] = matrix[secondRow][j];
            matrix[secondRow][j] = temp;
        }
    }

    public void divRow(int row, double value) {
        for (int j = 0; j < columns; ++j)
            matrix[row][j] /= value;
    }

    public void addColumn(int column, Matrix vector) {
        for (int i = 0; i < vector.getRows(); ++i) {
            matrix[i][column] = vector.get(i, 0);
        }
    }

    public double get(int row, int column) {
        return matrix[row][column];
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int row) {
        rows = row;
    }

    public void setColumns(int column) {
        columns = column;
    }

    public void substract(Matrix matr) {
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < columns; ++j)
                matrix[i][j] -= matr.matrix[i][j];
    }

    public Matrix multiply(Matrix mB) throws IllegalArgumentException {
        if (this.getColumns() != mB.getRows())
            throw new IllegalArgumentException("Columns!=Rows");
        Matrix res = new Matrix(this.getRows(), mB.getColumns());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < mB.getColumns(); j++) {
                for (int k = 0; k < mB.getRows(); k++) {
                    res.matrix[i][j] += this.matrix[i][k] * mB.matrix[k][j];
                }
            }
        }
        return res;
    }

    public void set(int row, int column, double item) {
        matrix[row][column] = item;
    }

    public static Matrix transpone(Matrix matrix1) {
        Matrix tmp = new Matrix(matrix1.getRows(), matrix1.getColumns());
        for (int i = 0; i < matrix1.getRows(); i++) {
            for (int j = 0; j < matrix1.getColumns(); j++) {
                tmp.set(i, j, matrix1.get(j, i));
            }
        }
        return tmp;
    }

    public void findAndSwapMaxElementInColumn(int column, int step) {
        double max = matrix[step][column];
        int index = column;
        for (int i = step; i < rows; ++i) {
            if (matrix[i][column] > max) {
                index = i;
                max = matrix[i][column];
            }
        }
        swapRows(step, index);
    }

    public void substractRows(int firstRow, int secondRow, int index) {
        double div = matrix[secondRow][index];
        for (int j = index; j < columns; ++j)
            matrix[secondRow][j] -= matrix[firstRow][j] * div;
    }

    public Double[][] getMatrix() {
        return matrix;
    }
}
