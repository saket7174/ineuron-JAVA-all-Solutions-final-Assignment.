
// 2. wap to invoke parent class constructer from a child class .create child class object
// and parent class constructer must be invoked.also explain key points about constructer  
class Parent {
    public Parent() {//constructer
        System.out.println("Parent constructor invoked");
    }//invoked parent class const from child class using the " super"keyward
}

class Child extends Parent {
    public Child() {
        super(); // Invoking parent class constructor
        System.out.println("Child constructor invoked");
    }// as we know that Constructer is nothing but a special type of method
    //whose name is same as class name .
}

public class Second {
    public static void main(String[] args) {
        //creating the object of child class
        Child child = new Child();
        //.Here When the Child constructor is called, it first invokes the parent class constructor
        // using super(), and then proceeds to execute its own constructor code.
    }
}
