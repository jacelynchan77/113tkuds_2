// file name: LC04_Median_QuakeFeeds.java

import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int j = 0; j < m; j++) B[j] = sc.nextDouble();

        System.out.printf("%.1f\n", findMedianSortedArrays(A, B));
    }

    public static double findMedianSortedArrays(double[] A, double[] B) {
        // pastikan A adalah array yang lebih pendek
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A);
        }

        int n = A.length;
        int m = B.length;
        int totalLeft = (n + m + 1) / 2;

        int lo = 0, hi = n;
        while (lo <= hi) {
            int i = (lo + hi) / 2;   // cut di A
            int j = totalLeft - i;   // cut di B

            double Aleft  = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft  = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                // sudah ketemu cut yg valid
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);
                } else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                hi = i - 1; // geser cut A ke kiri
            } else {
                lo = i + 1; // geser cut A ke kanan
            }
        }

        throw new IllegalArgumentException("Input tidak valid");
    }
}
