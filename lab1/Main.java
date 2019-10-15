package maxim;

import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        long[] p = new long[11];
        float[] x = new float[12];
        double[][] f = new double[11][12];
        int k = 0;

        // заполнение массива p
        for (int i = 23; i >= 3; i--) {
            if ((i % 2) == 1) {
                p[k] = i;
                k++;
            }
        }

        // заполнение массива x
        for (int i = 0; i < x.length; i++) {
            x[i] = (float) (random() * 25 - 15);
        }

        // заполнение и вывод двумерного массива f
        for (int i = 0; i < p.length; i++) {
            switch ((int) p[i]) {
                case 21:
                    for (int j = 0; j < x.length; j++) {
                        double a = pow(atan(((x[j] - 2.5) / 25) - 1), 3);
                        double b = pow(2 * (PI + pow(2 * x[j], 2)), 2) + 0.75;
                        f[i][j] = pow(a, b);
                        System.out.printf("%.2f", f[i][j]);
                        System.out.print(" \t");
                    }
                    break;
                case 5:
                case 7:
                case 13:
                case 15:
                case 17:
                    for (int j = 0; j < x.length; j++) {
                        f[i][j] = asin(sin(PI / (0.25 - pow(x[j] * (4 - x[j]), 3))));
                        System.out.printf("%.2f", f[i][j]);
                        System.out.print(" \t");
                    }
                    break;
                default: {
                    for (int j = 0; j < x.length; j++) {
                        double a = atan(1 / (exp(abs(x[j]))));
                        double b = pow(pow(2 / x[j], 3), pow(3 / x[j], 2)) + 1;
                        double c = pow(tan(cbrt(x[j])), a * b);
                        f[i][j] = atan(1 / exp(abs(c)));
                        System.out.printf("%.2f", f[i][j]);
                        System.out.print(" \t");
                    }
                }
            }
            System.out.println();
        }
    }
}