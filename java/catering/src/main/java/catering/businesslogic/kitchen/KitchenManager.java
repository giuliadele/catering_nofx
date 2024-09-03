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

    private void notifyFileCreated(RiepilogativeFile fr) {
        for (KitchenEventReciever er: this.eventRecievers){
            er.updateFileCreated(fr);
        }
    }
    private void notifyAddedTask(RiepilogativeFile fr, Task task) {
        for (KitchenEventReciever er: this.eventRecievers){
            er.updateAddedTask(fr, task);
        }
    }


}
