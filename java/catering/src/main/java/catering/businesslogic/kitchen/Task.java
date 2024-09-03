package catering.businesslogic.kitchen;

public class Task {
    private int duration;
    private int quantity;
    private boolean completed;
    private Cook cook;

    public Task(int duration, int quantity) {
        this.duration = duration;
        this.quantity = quantity;
    }
}
