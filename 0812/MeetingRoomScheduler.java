
import java.util.*;

public class MeetingRoomScheduler {

    // Part 1: Minimum number of meeting rooms required
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && interval[0] >= minHeap.peek()) {
                minHeap.poll(); // reuse room
            }
            minHeap.offer(interval[1]);
        }
        return minHeap.size();
    }

    // Part 2: Given N rooms, maximize total meeting time scheduled (no overlaps in same room)
    // Greedy approach: sort intervals by end time, select compatible meetings greedily until N rooms used
    public static int maxTotalMeetingTime(int[][] intervals, int N) {
        if (intervals == null || intervals.length == 0 || N <= 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1])); // sort by end time

        // Each room will keep track of last meeting end time
        PriorityQueue<Integer> rooms = new PriorityQueue<>(); // min-heap of room next available time
        int totalTime = 0;

        for (int[] interval : intervals) {
            // If a room is free before meeting starts, assign this meeting to that room
            if (!rooms.isEmpty() && rooms.peek() <= interval[0]) {
                rooms.poll();
                rooms.offer(interval[1]);
                totalTime += interval[1] - interval[0];
            } else if (rooms.size() < N) {
                // If room available, assign meeting
                rooms.offer(interval[1]);
                totalTime += interval[1] - interval[0];
            }
            // else no room free, skip this meeting
        }
        return totalTime;
    }

    public static void main(String[] args) {
        int[][] meetings1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Min rooms needed: " + minMeetingRooms(meetings1)); // 2

        int[][] meetings2 = {{9, 10}, {4, 9}, {4, 17}};
        System.out.println("Min rooms needed: " + minMeetingRooms(meetings2)); // 2

        int[][] meetings3 = {{1, 5}, {8, 9}, {8, 9}};
        System.out.println("Min rooms needed: " + minMeetingRooms(meetings3)); // 2

        int[][] meetings4 = {{1, 4}, {2, 3}, {4, 6}};
        int N = 1;
        int maxTime = maxTotalMeetingTime(meetings4, N);
        System.out.println("Max total meeting time with " + N + " room: " + maxTime); // 5
    }
}
