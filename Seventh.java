
//7. Java program that implements a binary search algorithm to search for a target value in a sorted array.
//  It accepts user input for the target value and searches for it in the array, returning the index if found
// or a message if not found:
import java.util.Arrays;
import java.util.Scanner;

public class Seventh {
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};

        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();

        int index = binarySearch(arr, target);

        if (index != -1) {
            System.out.println("Target value found at index " + index);
        } else {
            System.out.println("Target value not found in the array.");
        }
    }
}


