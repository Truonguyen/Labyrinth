/*
Truong Nguyen
COP4520 Spring 2021
Programming Assignment 2
*/
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class CrystalVase {
    private static ReentrantLock lock = new ReentrantLock(true);
    static boolean avai = true;
    static final int nThreads = 10;

    private static void showRoom() {
        lock.lock();
        try {
            if (avai) {
                System.out.println(Thread.currentThread().getName() + " is entering showroom. Showroom is now BUSY");
                avai = false;
            }

        } finally {
            System.out.println(Thread.currentThread().getName() + " is exiting. Showroom is now AVAILABLE\n");
            avai = true;
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        System.out.println("Minotaur welcome guests, showroom is AVAILABLE");
        System.out.println("There are " + nThreads + " guests in total\n");
        Thread[] guestList = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            guestList[i] = new Thread(() -> showRoom());
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
