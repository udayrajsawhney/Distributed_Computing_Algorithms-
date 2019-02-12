/*
 * Developed by udaysawhney on 12/2/19 12:20 PM.
 * Last modified 12/2/19 12:18 PM.
 * Github : https://github.com/udayrajsawhney
 * Copyright (c) 2019. All rights reserved.
 */

package alternatealgorithm;

import java.util.*;

public class Modulus {

    /*
    * Utility function for sorting boundary position nodes */
    private static void sortDuplet(List<Node> network, int prev, int next) {
        List<Integer> temp = new ArrayList<>();
        temp.add(network.get(prev).getData());
        temp.add(network.get(next).getData());
        Collections.sort(temp);
        network.get(prev).setData(temp.get(0));
        network.get(next).setData(temp.get(1));
    }

    /*
    * Utility function for sorting values in local computation */
    private void sortTriplet(List<Node> network, int i, Queue<Event> channel) {
        List<Integer> temp = new ArrayList<>();
        temp.add(network.get(i).getData());
        temp.add(network.get(i - 1).getData());
        temp.add(network.get(i + 1).getData());
        Event send = new Event("Send", i, i + 1, network.get(i - 1).getData());
        channel.add(send);
        Event send2 = new Event("Send", i + 2, i + 1, network.get(i + 1).getData());
        channel.add(send2);
        Event recieve = new Event("Receive", i, i + 1, network.get(i - 1).getData());
        channel.add(recieve);
        Event recieve2 = new Event("Receive", i + 2, i + 1, network.get(i + 1).getData());
        channel.add(recieve2);
        Collections.sort(temp);
        network.get(i - 1).setData(temp.get(0));
        network.get(i).setData(temp.get(1));
        network.get(i + 1).setData(temp.get(2));
    }

    /*
    * Function Implementing Alternate (n-1) Algorithm */
    public void algorithm(List<Integer> list, int n) {
        List<Node> network = new ArrayList<>();
        Queue<Event> channel = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            node.setData(list.get(i));
            node.setMark(i % 3);
            network.add(node);
        }
        int rounds = 0;
        while (rounds < n) {
            System.out.println("Round = " + rounds);
            for (int i = 0; i < network.size(); i++) {
                if (i != n - 1) {
                    System.out.print(network.get(i).getData() + "[" + network.get(i).getMark() + "]" + " <-> ");
                } else {
                    System.out.print(network.get(i).getData() + "[" + network.get(i).getMark() + "]");
                }
            }
            System.out.println();
            for (int i = 0; i < network.size(); i++) {
                if (network.get(i).getMark() == 1) {
                    if (i - 1 >= 0 && i + 1 < n) {
                        sortTriplet(network, i, channel);
                    } else if (i + 1 == n) {
                        Event send = new Event("Send", i, i + 1, network.get(i - 1).getData());
                        channel.add(send);
                        Event receive = new Event("Receive", i, i + 1, network.get(i - 1).getData());
                        channel.add(receive);
                        sortDuplet(network, i - 1, i);
                    } else if (i == 0) {
                        Event send = new Event("Send", i + 2, i + 1, network.get(i + 1).getData());
                        channel.add(send);
                        Event receive = new Event("Receive", i + 2, i + 1, network.get(i + 1).getData());
                        channel.add(receive);
                        sortDuplet(network, i, i + 1);
                    }
                }
            }

            for (int i = 0; i < network.size(); i++) {
                if (i != n - 1) {
                    network.get(i).setMark((network.get(i).getMark() + 1) % 3);
                } else {
                    network.get(i).setMark((network.get(i).getMark() + 1) % 3);
                }
            }
            while (!channel.isEmpty()) {
                Event event = channel.element();
                channel.remove();
                System.out.println("Event <--> " + event.name + ":" + event.src + "->" + event.dest + "::" + event.msg);
            }
            System.out.println();

            if (rounds == n - 1) {
                System.out.println("Final Round");
                for (int i = 0; i < network.size(); i++) System.out.print(network.get(i).getData() + " ");
                System.out.println();
            }
            rounds += 1;
        }
    }

}
