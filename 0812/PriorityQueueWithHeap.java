
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;

class Task {

    String name;
    int priority;
    long timestamp; // Used to break ties by insertion order

    public Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return name + " (Priority: " + priority + ")";
    }
}

public class PriorityQueueWithHeap {

    private PriorityQueue<Task> pq;
    private long counter; // Increment for each task added (for tie-breaking)

    public PriorityQueueWithHeap() {
        // Max-heap: higher priority first, then earlier insertion first
        pq = new PriorityQueue<>(new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                if (t1.priority != t2.priority) {
                    return Integer.compare(t2.priority, t1.priority); // Higher priority first
                }
                return Long.compare(t1.timestamp, t2.timestamp); // Earlier timestamp first
            }
        });
        counter = 0;
    }

    public void addTask(String name, int priority) {
        pq.add(new Task(name, priority, counter++));
    }

    public Task executeNext() {
        if (pq.isEmpty()) {
            System.out.println("No tasks to execute.");
            return null;
        }
        return pq.poll();
    }

    public Task peek() {
        return pq.peek();
    }

    public void changePriority(String name, int newPriority) {
        // Rebuild heap to update priority
        ArrayList<Task> tasks = new ArrayList<>(pq);
        pq.clear();
        for (Task task : tasks) {
            if (task.name.equals(name)) {
                task.priority = newPriority;
            }
            pq.add(task);
        }
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap taskQueue = new PriorityQueueWithHeap();

        // Test case 1
        taskQueue.addTask("Backup", 1);
        taskQueue.addTask("Emergency Fix", 5);
        taskQueue.addTask("Update", 3);

        System.out.println("Next to execute: " + taskQueue.peek());

        System.out.println("Executing tasks:");
        Task t;
        while ((t = taskQueue.executeNext()) != null) {
            System.out.println("Executed: " + t);
        }

        // Test case 2 - changePriority
        taskQueue.addTask("Test", 2);
        taskQueue.addTask("Download", 4);
        taskQueue.changePriority("Test", 6);
        System.out.println("After changing priority:");
        while ((t = taskQueue.executeNext()) != null) {
            System.out.println("Executed: " + t);
        }
    }
}
