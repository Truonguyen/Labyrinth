/*
Truong Nguyen
COP4520 Spring 2021
Programming Assignment 2
*/
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Labyrinth {
    // every thread have the same chance of going in
    private static ReentrantLock lock = new ReentrantLock(true);
    static boolean avai = true;
    static final int nThreads = 5;
    static boolean allVisited = false;
    static boolean cakePresent = true;
    static int counter = 0;

    private static void maze() {
        // lock.lock();
        boolean alreadyAte = false;

        do {
            // have 1 thread that will refill the cake when it is empty
            String refillThread = "Thread-1";

            // get the current thread name
            String currentThread = Thread.currentThread().getName();
            lock.lock();
            try {
                if (allVisited) {
                    break;
                }
                // if current thread is the refillThread
                if (refillThread.equals(currentThread)) {
                    // check if the cake is there or not
                    if (!cakePresent) {
                        // refill the cake
                        cakePresent = true;
                        // increase the counter by 1
                        counter++;
                        System.out.println("Counter Thread refill the cake " + counter + "/" + (nThreads - 1) + " times =================================");

                        if (counter == (nThreads - 1)) {
                            System.out.println(
                                    "\nCounter Thread announce \"ALL GUESTS HAVE VISITED THE LABYRINTH AT LEAST ONCE\"");
                            allVisited = true;
                        }
                    } else
                        System.out.println(
                                "Counter Thread visited the labyrith but no other guest have visited");
                } else {

                    if (alreadyAte) {
                        System.out.println(currentThread + " already ate so they left");
                    } else if (!cakePresent) {
                        System.out.println(currentThread + " visited labyrith but cake is not present ;-;");
                        continue;
                    } else if (!alreadyAte && cakePresent == true) {
                        // every other guest will eat the cake, leaving an empty plate
                        cakePresent = false;
                        alreadyAte = true;
                        System.out.println(currentThread + " visited the labyrith and eat the cake ^-^");
                    }
                }
            } finally {
                lock.unlock();
            }
        } while (true);

    }

    public static void main(String[] args) {
        System.out.println("There are " + nThreads + " guests in total\n");
        Thread[] guestList = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            guestList[i] = new Thread(() -> maze());
        }

        for (int i = 0; i < nThreads; i++) {
            guestList[i].start();
        }

        for (int i = 0; i < nThreads; i++) {
            try {
                guestList[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
