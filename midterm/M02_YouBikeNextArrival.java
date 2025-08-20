import java.io.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(readNonEmptyLine(br).trim());

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String t = readNonEmptyLine(br).trim();
            times[i] = parseHHmmToMinutes(t);
        }

        String queryStr = readNonEmptyLine(br).trim();
        int q = parseHHmmToMinutes(queryStr);

        // Binary search: first element strictly greater than q
        int idx = firstGreater(times, q);

        if (idx == n) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHmm(times[idx]));
        }
    }

    // Find first index i where a[i] > target; returns a.length if none.
    private static int firstGreater(int[] a, int target) {
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private static int parseHHmmToMinutes(String s) {
        String[] parts = s.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    private static String toHHmm(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    // Reads next non-empty line (skips blank lines)
    private static String readNonEmptyLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().length() > 0) return line;
        }
        return ""; // for well-formed inputs this won't be reached
    }
}
