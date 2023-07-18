
//1. WAP in java that uses polymorphism by defining an interface called shape with methods to calculate the 
//   area and peremeter of a shape .Then create classes that implements the shape interface for different 
//   types of shapes ,such as circle and triangle .

interface Shape {
    double calculateArea();
    double calculatePerimeter();
}

class Circle implements Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    // area of circle = pi *r*r
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    // peremeter = 2*pi*r;
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Triangle implements Shape {
    private double sideA;
    private double sideB;
    private double sideC;
    
    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }
    
    @Override
    public double calculateArea() {
        // Using Heron's formula to calculate area
        //ie; a+b+c/2 and then herons formula
        double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }
    
    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }
}

public class First {
    public static void main(String[] args) {
        
        Shape circle = new Circle(5.0);
        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Circle Perimeter: " + circle.calculatePerimeter());
        
        Shape triangle = new Triangle(3.0, 4.0, 5.0);
        System.out.println("Triangle Area: " + triangle.calculateArea());
        System.out.println("Triangle Perimeter: " + triangle.calculatePerimeter());
    }
}


