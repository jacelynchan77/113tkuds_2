
public class GradeStatisticsSystem {

    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        // 1. Calculate average, max, min
        int sum = 0;
        int max = scores[0];
        int min = scores[0];
        for (int score : scores) {
            sum += score;
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
        }
        double average = (double) sum / scores.length;

        // 2. Count grades A-F
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        // 3. Count students above average
        int aboveAverage = 0;
        for (int score : scores) {
            if (score > average) {
                aboveAverage++;
            }
        }

        // 4. Print report
        System.out.println("Score Report:");
        System.out.println("Scores: ");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println("\nAverage score: " + average);
        System.out.println("Max score: " + max);
        System.out.println("Min score: " + min);
        System.out.println("Grade distribution:");
        System.out.println("A: " + countA);
        System.out.println("B: " + countB);
        System.out.println("C: " + countC);
        System.out.println("D: " + countD);
        System.out.println("F: " + countF);
        System.out.println("Number of students above average: " + aboveAverage);
    }
}
