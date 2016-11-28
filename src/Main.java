import java.util.LinkedList;
import java.util.List;
public class Main {
    public static void main(String[] args) {

        double[][] matrix1 = {{8.30, 2.62, 4.10, 1.90}, {3.92, 8.45, 7.78, 2.46}, {3.77, 7.21, 8.04, 2.28}, {2.21, 2.65, 1.69, 6.99}};
        List<Matrix> vectors = new LinkedList<>();
        int size = matrix1.length;
        Matrix vector0 = new Matrix(size, 1);
        vector0.set(0, 0, 1);
        vector0.print();
        vectors.add(vector0);
        for (int i = 1; i <= size; ++i) {
            Matrix temp = new Matrix(matrix1);
            vectors.add(temp.multiply(vectors.get(i - 1)));
        }
        Matrix eigenvalues = new Matrix(size, size + 1);
        for (int i = 0; i < size; ++i) {
            eigenvalues.addColumn(i, vectors.get(size - 1 - i));
        }
        eigenvalues.addColumn(size, vectors.get(size));
        eigenvalues.print();
        for (int step = 0; step < size; ++step) {
            eigenvalues.findAndSwapMaxElementInColumn(step, step);
            System.out.println();
            eigenvalues.print();
            double div = eigenvalues.get(step, step);
            eigenvalues.divRow(step, div);
            for (int i = step; i < size - 1; ++i) {
                eigenvalues.substractRows(step, i + 1, step);
            }
            System.out.println();
            eigenvalues.print();
        }
        Matrix solutions = new Matrix(size, 1);
        for (int step = size - 1; step >= 0; --step) {
            double value = eigenvalues.get(step, size);
            for (int j = step + 1; j < size; j++) {
                value -= eigenvalues.get(step, j) * solutions.get(j, 0);
            }
            solutions.set(step, 0, value);
        }
        solutions.print();
    }
}