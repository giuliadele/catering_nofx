package catering.businesslogic.kitchen;

import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RiepilogativeFile {
    private ArrayList<Task> tasks;
    private Task currentTask;
    private int id;

    public RiepilogativeFile(int id){
        this.id = id;
        this.tasks = new ArrayList<>();
    }


    public Task addTask(int duration, int quantity) {
        Task currentTask = new Task(duration,quantity);
        tasks.add(currentTask);
        return currentTask;

    }

    public ArrayList<Task> getTasks() {return tasks;}

    public boolean isPresent(Task task){
        for (Task t: tasks){
            if (t.equals(task)){
                return true;
            }
        }
        return false;
    }

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
                return over;
            }

        }
        return over;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    private int getId() {
        return this.id;
    }
    private void setId(int id) {
        this.id = id;
    }

    public void setCompleted(Task task) {
        for(int i = 0;i<tasks.size();i++){
            if(tasks.get(i).equals(task)){
                tasks.get(i).setCompleted();

                break;
            }

        }
    }


    /*PERSISTENCY--------------------------*/
    public static void saveFile(RiepilogativeFile fr) {

        String fileInsert = "INSERT INTO catering.riepilogativefiles (id) VALUES ("+fr.getId()+");";
        PersistenceManager.executeUpdate(fileInsert);


    }

    public static void useFile(RiepilogativeFile fr) {

        String insert = "SELECT * from catering.riepilogativefiles WHERE id="+fr.getId()+";";
        PersistenceManager.executeQuery(insert, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                fr.setId(rs.getInt("id"));

            }
        });

        String insert2 = "SELECT * from catering.task WHERE fileID="+fr.getId()+";";
        PersistenceManager.executeQuery(insert2, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int durata = rs.getInt("duration");

                int quantity = rs.getInt("quantity");

                int completed = rs.getInt("completed");
                System.out.println("Durata:"+durata+"Quantita:"+quantity+"Completato:"+completed);
            }
        });


    }



    public static void saveTask(RiepilogativeFile fr, Task task) {
        String insert = "INSERT INTO catering.task (duration,quantity,completed,cookID,fileID) VALUES ("+task.getDuration()+","+task.getQuantity()+","+0+", "+0+","+fr.getId()+");";
        PersistenceManager.executeUpdate(insert);
    }

    /*FAR SI CHE INSERISCA IN BASE AL FOGLIO RIEPILOGATIVO*/
    public static void saveAssignedTask(Task task,RiepilogativeFile fr) {
        String insert = "INSERT INTO catering.cooks (id) VALUES ("+task.getCook().getId()+");";
        PersistenceManager.executeUpdate(insert);
        String insert2 = "UPDATE catering.task SET cookID = "+task.getCook().getId()+" WHERE quantity = "+task.getQuantity()+" and duration = "+task.getDuration()+" and fileID = "+fr.getId()+";";
        PersistenceManager.executeUpdate(insert2);

    }


    public static void removeTask(Task task) {

            String insert = "DELETE FROM catering.task WHERE duration = "+task.getDuration()+" AND quantity = "+task.getQuantity()+" AND cookID ="+task.getCook().getId()+";";
            PersistenceManager.executeUpdate(insert);

    }


    public static void setCompletedTask(Task task, RiepilogativeFile currentFile) {
        String insert2 = "UPDATE catering.task SET completed = "+1+" WHERE quantity = "+task.getQuantity()+" AND duration = "+task.getDuration()+" AND fileID= "+currentFile.getId()+";";
        PersistenceManager.executeUpdate(insert2);
    }



}
