package catering.businesslogic.kitchen;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.user.User;

import java.util.ArrayList;

public class KitchenManager {

    private ArrayList<RiepilogativeFile> files;
    private boolean found = true;
    private RiepilogativeFile currentFile;
    private User user = null;
    private int duration;
    private int quantity;

    private ArrayList<KitchenEventReciever> eventRecievers;

    public User getUser() {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        return u;
    }

    public RiepilogativeFile createRiepilogativeFile() throws UseCaseLogicException {
        user = getUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        RiepilogativeFile fr = new RiepilogativeFile();
        files.add(fr);
        notifyFileCreated(fr);
        return fr;
    }

    public Task defineTask(int duration, int quantity) throws UseCaseLogicException{
        if(currentFile == null){
            throw new UseCaseLogicException();
        }
        Task task = currentFile.addTask(duration,quantity);
        notifyAddedTask(currentFile,task);
        return task;
    }

    public void reorderTask(Task task,int position) throws UseCaseLogicException{
        if(currentFile != null || currentFile.hasTask(task)){
            if(position <0 || position>= currentFile.getTasks().size()){
                throw new UseCaseLogicException();
            }else {

                currentFile.reorderTask(task,position);
                notifyRearrangedTask(currentFile,task);
            }
        }else throw new UseCaseLogicException();
    }

    public void defineCook(int id)throws UseCaseLogicException{
        Task task = null;
        if(currentFile != null){
            Cook cook = new Cook(id);

            task = currentFile.assignTask(cook);
            notifyTaskAssigned(task);

            }else throw new UseCaseLogicException();

    }

    public void deleteTask(Task task) throws UseCaseLogicException {
        if(currentFile != null){

            currentFile.deleteTask(task);
            notifyTaskDeleted(task);

        }else throw new UseCaseLogicException();

    }

    public void setCompleted(Task task) throws UseCaseLogicException {
        if(currentFile != null){

            currentFile.setCompleted(task);
            notifyTaskCompleted(task);

        }else throw new UseCaseLogicException();
    }

    private void notifyTaskCompleted(Task task) {
        for (KitchenEventReciever er: this.eventRecievers){
            er.updateTaskCompleted(task);
        }
    }


    private void notifyTaskDeleted(Task task) {
        for (KitchenEventReciever er: this.eventRecievers){
            er.updateTaskDeleted(task);
        }
    }



    private void notifyTaskAssigned(Task task) {
        for (KitchenEventReciever er: this.eventRecievers){
            er.updateTaskAssigned(task);
        }
    }

    private void notifyFileCreated(RiepilogativeFile fr){
        for (KitchenEventReciever er: this.eventRecievers){
            er.updateFileCreated(fr);
        }
    }
    private void notifyAddedTask(RiepilogativeFile fr, Task task) {
        for (KitchenEventReciever er: this.eventRecievers){
            er.updateAddedTask(fr,task);
        }
    }
    private void notifyRearrangedTask(RiepilogativeFile fr, Task task) {
        for (KitchenEventReciever er: this.eventRecievers){
            er.updateRearrangedTask(fr,task);
        }
    }


}

