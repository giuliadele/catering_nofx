package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.kitchen.RiepilogativeFile;
import catering.businesslogic.kitchen.Task;

public class Test1CreateFile {
    public static void main(String[] args) throws UseCaseLogicException {



        //Test di Login e di Creazione di un Menu da parte di un Utente non Chef
/*        CatERing.getInstance().getUserManager().fakeLogin("Carlin");
        System.out.println("\nTest Operazione 1.a.1: Create Sheet");
        SummarySheet s = CatERing.getInstance().getSheetManager().createSummarySheet(15);
        System.out.println(s.toString());
*/

        //Test di Login e di Creazione di un Menu da parte di un Utente Chef
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("\nTest Operazione 1.a.1: Create Sheet\n\n");
        RiepilogativeFile fr = CatERing.getInstance().getKitchenManager().createRiepilogativeFile(1);
        Task t = CatERing.getInstance().getKitchenManager().defineTask(10,2);
       Task t1 = CatERing.getInstance().getKitchenManager().defineTask(11,3);
        CatERing.getInstance().getKitchenManager().defineCook(1);
        CatERing.getInstance().getKitchenManager().deleteTask(t);

        RiepilogativeFile fr2 = CatERing.getInstance().getKitchenManager().createRiepilogativeFile(2);
        Task t3 = CatERing.getInstance().getKitchenManager().defineTask(12,4);
        Task t4 = CatERing.getInstance().getKitchenManager().defineTask(13,5);

        CatERing.getInstance().getKitchenManager().setCompleted(t3);
        CatERing.getInstance().getKitchenManager().defineCook(2);
        CatERing.getInstance().getKitchenManager().defineCook(3);
        CatERing.getInstance().getKitchenManager().deleteTask(t4);

        RiepilogativeFile prova = CatERing.getInstance().getKitchenManager().checkExistingFile(fr);



        //------------------------------------------





    }
}
