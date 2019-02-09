package alternatealgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Modulus {

    public List<Integer> input() {
        int n;
        List<Integer> list = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes");
        n = sc.nextInt();
        System.out.println("Enter the value for each process");
        for (int i = 0; i < n; i++) list.add(sc.nextInt());

        return list;
    }

    private static void sortDuplet(List<Node> network, int prev, int next) {
        List<Integer> temp = new ArrayList<>();
        temp.add(network.get(prev).getData());
        temp.add(network.get(next).getData());
        Collections.sort(temp);
        network.get(prev).setData(temp.get(0));
        network.get(next).setData(temp.get(1));
    }

    private void sortTriplet(List<Node> network, int i) {
        List<Integer> temp = new ArrayList<>();
        temp.add(network.get(i).getData());
        temp.add(network.get(i - 1).getData());
        temp.add(network.get(i + 1).getData());
        Collections.sort(temp);
        network.get(i - 1).setData(temp.get(0));
        network.get(i).setData(temp.get(1));
        network.get(i + 1).setData(temp.get(2));
    }

    private void algorithm(List<Integer> list, int n) {
        List<Node> network = new ArrayList<>();
        int rounds = 1;
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            node.setData(list.get(i));
            node.setMark(i % 3);
            network.add(node);
        }
        while (rounds < n) {
            for (int i = 0; i < network.size(); i++) {
                if (network.get(i).getMark() == 1) {
                    if (i - 1 >= 0 && i + 1 < n) {
                        sortTriplet(network, i);
                    } else if (i + 1 == n) {
                        sortDuplet(network, i - 1, i);
                    } else if (i == 0) {
                        sortDuplet(network, i, i + 1);
                    }
                }
            }
            System.out.println("Round = " + rounds);
            for (int i = 0; i < network.size(); i++) {
                if (i != n - 1) {
                    System.out.print(network.get(i).getData() + "[" + network.get(i).getMark() + "]" + " <-> ");
                    network.get(i).setMark((network.get(i).getMark() + 1) % 3);
                } else {
                    System.out.print(network.get(i).getData() + "[" + network.get(i).getMark() + "]");
                    network.get(i).setMark((network.get(i).getMark() + 1) % 3);
                }
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

    public static void main(String[] args) {
        Modulus modulus = new Modulus();
        List<Integer> list;
        list = modulus.input();
        System.out.println("Result after Applying Distributed Algorithm");
        modulus.algorithm(list, list.size());
    }
}
