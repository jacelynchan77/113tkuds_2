// file name: LC28_StrStr_NoticeSearch.java
import java.io.*;
import java.util.*;

/** Find the first index of needle in haystack (return -1 if not found).
 *  Input:
 *    Either:
 *      Line 1: haystack
 *      Line 2: needle
 *    OR
 *      Line 1: haystack needle
 *  Output:
 *    index or -1
 */
public class LC28_StrStr_NoticeSearch {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line1 = br.readLine();
        if (line1 == null || line1.trim().isEmpty()) {
            System.out.println(-1);
            return;
        }

        String haystack, needle;

        StringTokenizer st = new StringTokenizer(line1);
        if (st.countTokens() >= 2) {
            // if both haystack and needle are on the same line
            haystack = st.nextToken();
            needle   = st.nextToken();
        } else {
            // haystack is first line, needle is second line
            haystack = line1;
            String line2 = br.readLine();
            needle = (line2 == null) ? "" : line2.trim();
        }

        System.out.println(strStr(haystack, needle));
    }

    /** KMP search: returns first index of needle in haystack, or -1. */
    static int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) return 0;
        if (m > n) return -1;

        int[] lps = buildLPS(needle);
        int i = 0, j = 0; // i for haystack, j for needle
        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++; j++;
                if (j == m) return i - m; // match found
            } else {
                if (j > 0) j = lps[j - 1];
                else i++;
            }
        }
        return -1;
    }

    /** Build longest prefix-suffix (LPS) array for KMP. */
    static int[] buildLPS(String p) {
        int m = p.length();
        int[] lps = new int[m];
        int len = 0;
        for (int i = 1; i < m; ) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }
}
