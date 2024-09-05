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

    public Task assignTask(Cook cook){

        Task over = null;
        for(int i = 0;i<tasks.size();i++){
            if(tasks.get(i).getCook() == null){
                tasks.get(i).assign(cook);

                over = tasks.get(i);
                break;
            }

        }
        return over;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public void setCompleted(Task task) {
        for(int i = 0;i<tasks.size();i++){
            if(tasks.get(i).equals(task)){
                tasks.get(i).setCompleted();


                break;
            }

        }
    }
}
