package catering.businesslogic;

import catering.businesslogic.event.EventManager;
import catering.businesslogic.kitchen.KitchenManager;
import catering.businesslogic.menu.MenuManager;
import catering.businesslogic.recipe.RecipeManager;
import catering.businesslogic.user.UserManager;
import catering.persistence.KitchenPersistence;
import catering.persistence.MenuPersistence;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private MenuManager menuMgr;
    private RecipeManager recipeMgr;
    private UserManager userMgr;
    private EventManager eventMgr;

    private MenuPersistence menuPersistence;
    private KitchenManager ktnMgr;
    private KitchenPersistence kitchenPersistence;
    private CatERing() {
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        eventMgr = new EventManager();
        menuPersistence = new MenuPersistence();
        kitchenPersistence = new KitchenPersistence();
        ktnMgr = new KitchenManager();
        ktnMgr.addEventReceiver(kitchenPersistence);
        menuMgr.addEventReceiver(menuPersistence);
    }


    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public EventManager getEventManager() { return eventMgr; }

    public KitchenManager getKitchenManager() {
        return ktnMgr;
    }
}
