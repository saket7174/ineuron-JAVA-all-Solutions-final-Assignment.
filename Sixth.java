
//6. java program that uses stream api to perform the operations on a large data set ,
//   such as shorting and filtering of data. 
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sixth{
    public static void main(String[] args) {
        // Creating a large data set
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numbers.add(i);
        }
        
        // Sorting the data set in descending order
        List<Integer> sortedList = numbers.stream()
                                          .sorted((a, b) -> b.compareTo(a))
                                          .collect(Collectors.toList());
        System.out.println("Sorted List: " + sortedList);
        
        // Filtering the data set to get even numbers
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());
        System.out.println("Even Numbers: " + evenNumbers);
    }
}

