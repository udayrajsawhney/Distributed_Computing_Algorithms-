package sasakialgorithm;

import javafx.util.Pair;

import java.util.*;

public class Sasaki {

    Pair<Integer, Integer> dataPair;
    Pair<Node, Node> nodePair;

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

    public void sasaki(List<Integer> list, int n) {
        List<Pair<Node, Node>> network = new ArrayList<Pair<Node, Node>>();
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
        int rounds = 1;
        while (rounds < n) {
            for (int i = 0; i < network.size(); i++) {
                if (i == n - 1) break;
                Pair<Node, Node> current, next;
                current = network.get(i);
                next = network.get(i + 1);
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

    public static void main(String[] args) {
        int n;
        List<Integer> list = new ArrayList<Integer>();
        Sasaki ss = new Sasaki();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes");
        n = sc.nextInt();
        System.out.println("Enter the value for each process");
        for (int i = 0; i < n; i++) list.add(sc.nextInt());
        System.out.println("Result after applying Sasaki's Algorithm");
        ss.sasaki(list, n);
    }

}
