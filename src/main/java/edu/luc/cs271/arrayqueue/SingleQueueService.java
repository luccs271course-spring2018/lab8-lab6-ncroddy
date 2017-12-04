package edu.luc.cs271.arrayqueue;

import java.util.Scanner;

public class SingleQueueService {

  /** Service time per customer in ms. */
  static final int SERVICE_TIME = 2000;

  public static void main(final String[] args) throws InterruptedException {
    // TODO read successive input lines until EOF and try to add them to the queue
    final SimpleQueue<String> queue = new FixedArrayQueue<>(5);
    for (int i = 0; i < args.length; i++) {
      queue.offer(args[i]);
    }

    // queue for customer names

    // lock object for thread safety
    final Object lock = new Object();

    // background activity for serving customers
    final Thread consumer =
        new Thread(
            () -> {
              while (true) {
                String current;
                int remaining;
                synchronized (lock) {
                  current = queue.poll();
                  // TODO try to take next name from queue
                  remaining = queue.size();
                  // TODO determine resulting size of queue

                }
                if (current == null) {
                  System.out.println("no one waiting");
                } else {
                  System.out.println(current + " is being served, " + remaining + " still waiting");
                }
                try {
                  Thread.sleep(SERVICE_TIME);
                } catch (final InterruptedException ex) {
                  return;
                }
              }
            });
    consumer.setDaemon(true);
    consumer.start();

    // foreground activity for reading customer names from input
    final Scanner input = new Scanner(System.in);
    System.out.print("enter next customer: ");
    while (input.hasNextLine()) {
      final String name = input.nextLine();
      boolean result;
      synchronized (lock) {
        if (queue.offer(name) == false) {

          result = false; // TODO try to add this name tothe queue
        } else {
          queue.offer(name);
          result = true;
        }
      }
      if (result) {
        System.out.println(name + " has joined the queue");
      } else {
        System.out.println("queue full, " + name + " unable to join");
      }
    }
  }
}
