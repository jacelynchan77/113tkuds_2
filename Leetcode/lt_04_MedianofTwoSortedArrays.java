// lt_04_MedianofTwoSortedArrays.java

class Solution {
    // Binary search on the smaller array to find a correct partition
    // Time: O(log(min(m,n))), Space: O(1)
    public double findMedianSortedArrays(int[] A, int[] B) {
        // Ensure A is the smaller array
        if (A.length > B.length) return findMedianSortedArrays(B, A);

        int m = A.length, n = B.length;
        int totalLeft = (m + n + 1) / 2; // number of elements on the left side of partition
        int lo = 0, hi = m;

        while (lo <= hi) {
            int i = lo + (hi - lo) / 2;        // cut in A
            int j = totalLeft - i;             // cut in B

            int Aleft  = (i == 0) ? Integer.MIN_VALUE : A[i - 1];
            int Aright = (i == m) ? Integer.MAX_VALUE : A[i];
            int Bleft  = (j == 0) ? Integer.MIN_VALUE : B[j - 1];
            int Bright = (j == n) ? Integer.MAX_VALUE : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                // Found the correct partition
                if (((m + n) % 2) == 1) {
                    // Odd length: max of left side
                    return Math.max(Aleft, Bleft);
                } else {
                    // Even length: average of max(left) and min(right)
                    int leftMax = Math.max(Aleft, Bleft);
                    int rightMin = Math.min(Aright, Bright);
                    return (leftMax + rightMin) / 2.0;
                }
            } else if (Aleft > Bright) {
                // i is too big, move left
                hi = i - 1;
            } else {
                // Bleft > Aright -> i is too small, move right
                lo = i + 1;
            }
        }

        // Should never reach here if inputs follow constraints
        throw new IllegalArgumentException("Invalid input arrays.");
    }
}

public class lt_04_MedianofTwoSortedArrays {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] nums1a = {1, 3};
        int[] nums2a = {2};
        System.out.printf("Output: %.5f%n", sol.findMedianSortedArrays(nums1a, nums2a)); // 2.00000

        // Example 2
        int[] nums1b = {1, 2};
        int[] nums2b = {3, 4};
        System.out.printf("Output: %.5f%n", sol.findMedianSortedArrays(nums1b, nums2b)); // 2.50000

        // Extra tests
        int[] nums1c = {};
        int[] nums2c = {1};
        System.out.printf("Output: %.5f%n", sol.findMedianSortedArrays(nums1c, nums2c)); // 1.00000

        int[] nums1d = {0, 0};
        int[] nums2d = {0, 0};
        System.out.printf("Output: %.5f%n", sol.findMedianSortedArrays(nums1d, nums2d)); // 0.00000

        int[] nums1e = {};
        int[] nums2e = {2,3};
        System.out.printf("Output: %.5f%n", sol.findMedianSortedArrays(nums1e, nums2e)); // 2.50000
    }
}
