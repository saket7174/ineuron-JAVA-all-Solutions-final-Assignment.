
//3. java program that takes an integer form the user and throws an exception if it is negative 
//demonstrate Exception handling as sollution.
import java.util.Scanner;
public class Third {
    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);
        //taking user input Scanner class 
        try {
            System.out.print("Enter any no of your choice: ");
            int number = r.nextInt();
            
            if (number < 0) {
                throw new Exception("Negative number not allowed");
            }
            
            System.out.println("Entered number: " + number);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }
    }
}


