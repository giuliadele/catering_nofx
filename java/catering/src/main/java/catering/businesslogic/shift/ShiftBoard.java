package catering.businesslogic.shift;

import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.kitchen.RiepilogativeFile;
import catering.businesslogic.kitchen.Task;

public class ShiftBoard {

    public void showDuration(Task task,RiepilogativeFile currentFile) throws UseCaseLogicException {
        if(currentFile != null){

            if(currentFile.isPresent(task)){
                System.out.println("Durata: "+task.getDuration());

            }

        }else throw new UseCaseLogicException();
    }
}
