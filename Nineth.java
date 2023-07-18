
// 9.Java program that implements a producer-consumer model using multithreading.
//  The program has a producer thread that generates random numbers and adds them to a queue,
//  and a consumer thread that reads the numbers from the queue and calculates their sum. 
// The program uses synchronization to ensure that the queue is accessed by only one thread at a time:
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Producer implements Runnable {
    private Queue<Integer> queue;
    private int maxSize;
    
    public Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }
    
    public void run() {
        Random random = new Random();
        
        while (true) {
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                int number = random.nextInt(15);
                queue.add(number);
                System.out.println("Produced: " + number);
                
                queue.notifyAll();
            }
        }
    }
}

class Consumer implements Runnable {
    private Queue<Integer> queue;
    
    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }
    
    public void run() {
        int stop = 0;
        while (stop<=10) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                int sum = 0;
                while (!queue.isEmpty()) {
                    int number = queue.poll();
                    sum += number;
                    System.out.println("Consumed: " + number);
                }
                
                System.out.println("Sum: " + sum);
                
                queue.notifyAll();  
                stop++;
            }
        }
      
    }
}

public class Nineth {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 10;
        
        Producer producer = new Producer(queue, maxSize);
        Consumer consumer = new Consumer(queue);
        
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        
        producerThread.start();
        consumerThread.start();
    }
}
