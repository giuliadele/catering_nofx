package catering.businesslogic.kitchen;

public interface KitchenEventReciever {
    public void updateFileCreated(RiepilogativeFile fr);
    public void updateAddedTask(RiepilogativeFile fr, Task task);
    public void updateRearrangedTask(RiepilogativeFile fr, Task task);
}
