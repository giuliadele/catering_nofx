package catering.businesslogic.kitchen;

public interface KitchenEventReciever {
     void updateFileCreated(RiepilogativeFile fr);
     void updateAddedTask(RiepilogativeFile fr, Task task);
     void updateRearrangedTask(RiepilogativeFile fr, Task task);

    void updateTaskAssigned(Task task,RiepilogativeFile fr);

    void updateTaskDeleted(Task task);

    void updateTaskCompleted(Task task, RiepilogativeFile currentFile);

    void updateFileFound(RiepilogativeFile fr);
}
