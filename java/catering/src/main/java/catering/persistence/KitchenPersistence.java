package catering.persistence;

import catering.businesslogic.kitchen.KitchenEventReciever;
import catering.businesslogic.kitchen.RiepilogativeFile;
import catering.businesslogic.kitchen.Task;

public class KitchenPersistence implements KitchenEventReciever {

    @Override
    public void updateFileCreated(RiepilogativeFile fr) {

        RiepilogativeFile.saveFile(fr);
    }

    @Override
    public void updateAddedTask(RiepilogativeFile fr, Task task) {
        RiepilogativeFile.saveTask(fr,task);

    }

    @Override
    public void updateRearrangedTask(RiepilogativeFile fr, Task task) {

    }

    @Override
    public void updateTaskAssigned(Task task,RiepilogativeFile fr) {
        RiepilogativeFile.saveAssignedTask(task,fr);

    }

    @Override
    public void updateTaskDeleted(Task task) {
        RiepilogativeFile.removeTask(task);

    }

    @Override
    public void updateTaskCompleted(Task task, RiepilogativeFile currentFile) {
        RiepilogativeFile.setCompletedTask(task,currentFile);

    }

    @Override
    public void updateFileFound(RiepilogativeFile fr) {
        RiepilogativeFile.useFile(fr);
    }
}
