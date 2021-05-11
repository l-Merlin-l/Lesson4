package main;

public class Main {
    private char abc = 'C';

    public static void main(String[] args) {
        Main main = new Main();

        Thread threadA = new Thread(() -> main.threadABC('A', 'C'));
        Thread threadB = new Thread(() -> main.threadABC('B', 'A'));
        Thread threadC = new Thread(() -> main.threadABC('C', 'B'));
        threadA.start();
        threadB.start();
        threadC.start();
    }

    private void threadABC(char next, char last) {
        try {
            synchronized (this){
                for (int i = 0; i < 5; i++) {
                    while (last != abc){
                        wait();
                    }
                    printMessage(next);
                    abc = next;
                    notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printMessage(char msg){
        System.out.print(msg);
    }
}
