/*
 * Developed by udaysawhney on 12/2/19 12:20 PM.
 * Last modified 12/2/19 12:18 PM.
 * Github : https://github.com/udayrajsawhney
 * Copyright (c) 2019. All rights reserved.
 */

package sasakialgorithm;

import javafx.util.Pair;

import java.util.*;

public class Sasaki {

    Pair<Integer, Integer> dataPair;
    Pair<Node, Node> nodePair;

    /*
     * Utility function for sorting values in local computation */
    private void sortNode(Pair<Node, Node> node) {
        int temp;
        if (node.getKey().getData() > node.getValue().getData()) {
            temp = node.getKey().getData();
            node.getKey().setData(node.getValue().getData());
            node.getValue().setData(temp);
            if (node.getKey().isMarked() && node.getValue().isMarked()) {

            } else if (node.getKey().isMarked()) {
                node.getKey().setMarked(false);
                node.getValue().setMarked(true);
            } else if (node.getValue().isMarked()) {
                node.getKey().setMarked(true);
                node.getValue().setMarked(false);
            }
        }
    }

    /*
     * Function Implementing Sasaki's Algorithm */
    public void algorithm(List<Integer> list, int n) {
        List<Pair<Node, Node>> network = new ArrayList<Pair<Node, Node>>();
        Queue<Event> channel = new LinkedList<>();
        Node leftNode, rightNode;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                leftNode = new Node(Integer.MIN_VALUE);
                rightNode = new Node(list.get(i));
                rightNode.setMarked(true);
            } else if (i == n - 1) {
                leftNode = new Node(list.get(i));
                leftNode.setMarked(true);
                rightNode = new Node(Integer.MAX_VALUE);
            } else {
                leftNode = new Node(list.get(i));
                rightNode = new Node(list.get(i));
            }
            network.add(new Pair<>(leftNode, rightNode));
        }
        int rounds = 0;
        while (rounds < n) {
            System.out.println("Round = " + rounds);
            for (int i = 0; i < network.size(); i++) {
                if (i == 0) {
                    System.out.print("[" + network.get(i).getValue().getData() + "]" + "(" + network.get(i).getValue().getArea() + ")" + " <-> ");
                } else if (i == n - 1) {
                    System.out.print("[" + network.get(i).getKey().getData() + "]" + "(" + network.get(i).getKey().getArea() + ")");
                } else {
                    System.out.print("[" + network.get(i).getKey().getData() + "," + network.get(i).getValue().getData() + "]" + "(" + network.get(i).getValue().getArea() + ")" + "<->");
                }
            }
            System.out.println();
            for (int i = 0; i < network.size(); i++) {
                if (i == n - 1) break;
                Pair<Node, Node> current, next;
                current = network.get(i);
                next = network.get(i + 1);
                Event send = new Event("Send", i + 1, i + 2, current.getValue().getData());
                channel.add(send);
                Event recieve = new Event("Receive", i + 1, i + 2, current.getValue().getData());
                channel.add(recieve);
                Event send2 = new Event("Send", i + 2, i + 1, next.getKey().getData());
                channel.add(send2);
                Event recieve2 = new Event("Receive", i + 2, i + 1, next.getKey().getData());
                channel.add(recieve2);
                if (current.getValue().getData() > next.getKey().getData()) {
                    int temp = next.getKey().getData();
                    next.getKey().setData(current.getValue().getData());
                    current.getValue().setData(temp);
                    if (current.getValue().isMarked() && next.getKey().isMarked()) {
                        current.getValue().setMarked(true);
                        next.getKey().setMarked(true);
                    } else if (current.getValue().isMarked()) {
                        current.getValue().setMarked(false);
                        next.getKey().setMarked(true);
                        next.getKey().setArea(next.getKey().getArea() - 1);
                        next.getValue().setArea(next.getValue().getArea() - 1);
                    } else if (next.getKey().isMarked()) {
                        current.getValue().setMarked(true);
                        next.getKey().setMarked(false);
                        next.getKey().setArea(next.getKey().getArea() + 1);
                        next.getValue().setArea(next.getValue().getArea() + 1);
                    }
                }
            }
            for (int i = 0; i < network.size(); i++) sortNode(network.get(i));

            while (!channel.isEmpty()) {
                Event event = channel.element();
                channel.remove();
                System.out.println(" Event <--> " + event.name + ":" + event.src + "->" + event.dest + "::" + event.msg);
            }
            System.out.println();

            if (rounds == n - 1) {
                System.out.println("Final Round");
                for (int i = 0; i < network.size(); i++) {
                    if (i == 0) {
                        System.out.print(network.get(i).getValue().getData() + " ");
                    } else if (i == n - 1) {
                        System.out.print(network.get(i).getKey().getData() + " ");
                    } else {
                        if (network.get(i).getKey().getArea() >= 0) {
                            System.out.print(network.get(i).getKey().getData() + " ");
                        } else {
                            System.out.print(network.get(i).getValue().getData() + " ");
                        }
                    }
                }
                System.out.println();
            }
            rounds += 1;
        }
    }

}
