package catering.businesslogic.kitchen;

public class Task {
    private int duration;
    private int quantity;
    private boolean completed;
    private Cook cook;

    public Task(int duration, int quantity) {
        this.duration = duration;
        this.quantity = quantity;
        this.completed = false;
        this.cook = null;
    }

    public Cook getCook(){
        return this.cook;
    }

    public void assign(Cook cook) {
        this.cook = cook;
    }

    public void setCompleted() {
        this.completed = true;
    }
}
