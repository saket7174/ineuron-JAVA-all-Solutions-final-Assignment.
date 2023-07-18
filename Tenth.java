
// Java program that reads a set of integers from the user and stores them in a list. 
// The program then finds the second largest and second smallest elements in the list:
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tenth{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = new ArrayList<>();
        // list called numbers to store the input integers. 
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        System.out.println("Enter the elements:");

        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            numbers.add(num);
        }

        // Find the second largest and second smallest elements
        int secondLargest = findSecondLargest(numbers);
        int secondSmallest = findSecondSmallest(numbers);

        System.out.println("Second largest element: " + secondLargest);
        System.out.println("Second smallest element: " + secondSmallest);
    }

    public static int findSecondLargest(List<Integer> numbers) {
        Collections.sort(numbers);
        int size = numbers.size();

        if (size >= 2) {
            return numbers.get(size - 2);
        }

        return -1; // If there are less than two elements
    }

    public static int findSecondSmallest(List<Integer> numbers) {
        Collections.sort(numbers);
        int size = numbers.size();

        if (size >= 2) {
            return numbers.get(1);
        }

        return -1; // If there are less than two elements
    }
}

