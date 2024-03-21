package sce.sc2002.FinalProject;

public abstract class Menu {
    protected boolean exit = false;
    protected Login currentUser;
    protected static CampList campList;

    public Menu(CampList campList){
        Menu.campList = campList;
    }

    abstract public void runMenu();
    abstract public void display();

    public void clearScreen(){
        for (int i = 0; i < 100; i ++)
            System.out.println();
    }
}


