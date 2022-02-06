package model;

public class ChildThread extends Thread{
    Child child;
    public ChildThread(Child child) {
        super();
        this.child = child;
    }

    @Override
    public void run() {
        child.catchSanta();
    }
}
