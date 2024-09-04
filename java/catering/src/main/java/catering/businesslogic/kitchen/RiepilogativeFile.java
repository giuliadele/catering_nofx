package catering.businesslogic.kitchen;

import java.util.ArrayList;

public class RiepilogativeFile {
    private ArrayList<Task> tasks;
    private Task currentTask;

    public Task addTask(int duration, int quantity) {
        Task currentTask = new Task(duration,quantity);
        tasks.add(currentTask);
        return currentTask;

    }

    public ArrayList<Task> getTasks() {return tasks;}

    public void reorderTask(Task task, int position) {
        tasks.remove(task);
        tasks.add(position, task);
    }

    public boolean hasTask(Task task) {
        return tasks.contains(task);
    }
}
