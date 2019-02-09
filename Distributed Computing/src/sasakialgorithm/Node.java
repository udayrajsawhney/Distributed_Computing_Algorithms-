package sasakialgorithm;

public class Node {
    private int data;
    private int area;
    private boolean marked;

    public Node(int data) {
        this.data = data;
        this.area = 0;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

}
