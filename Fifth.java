
//5. java program to demonstrate difference between abstract class and interface 
// Abstract class
abstract class Animal {
    // Abstract method
    public abstract void sound();
    
    // Concrete method
    public void sleep() {
        System.out.println("Sleeping");
    }
}

// Interface
interface Jumpable {
    // Abstract method
    void jump();
    
    // Default method
    default void showMaxHeight() {
        System.out.println("Maximum jumping height is 10 feet.");
    }
}

// Concrete class implementing the abstract class and interface
class Dog extends Animal implements Jumpable {//interface methods are by default public and abstract
    // Implementing abstract method from Animal class
    public void sound() {
        System.out.println("Woof!");
    }
    
    // Implementing abstract method from Jumpable interface
    public void jump() {
        System.out.println("The dog jumps.");
    }
}

public class Fifth {
    public static void main(String[] args) {
        // Creating an object of the Dog class
        Dog dog = new Dog();
        
        // Invoking methods from abstract class
        dog.sound();
        dog.sleep();
        
        // Invoking methods from interface
        dog.jump();
        dog.showMaxHeight();
    }
}
//Here 1st "woof!" then "sleeping"
// simply as we know in abstract class we'r having common work but different feature



