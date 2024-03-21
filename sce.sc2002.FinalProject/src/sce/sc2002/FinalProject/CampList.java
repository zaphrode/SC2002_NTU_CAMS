package sce.sc2002.FinalProject;

import java.util.*;


public class CampList{

    private Login currentUser;
    private ArrayList<Camp> campList;

    public CampList(Login user){
        currentUser = user;
        campList    = new ArrayList<Camp>();
    }

    private Scanner sc = new Scanner(System.in);
    
    public ArrayList<Camp> getCampList(){return campList;}

    private void tempDelay(){
        System.out.println("Press Enter to go Back!");
        sc.nextLine();

        // Pseudo clear screen
        for (int i = 0; i < 100; i++){
            System.out.println();
        }

        return;
    }

    public void createCamp(){
        addCamp(Camp.createCamp(currentUser, this));
    }

    private void addCamp(Camp camp){
        System.out.println("Adding Camp to the list...");
        campList.add(camp);
    }

    public void deleteCamp(){
        System.out.println("Deleting Camp Screen: ");
        Scanner sc = new Scanner(System.in);
        Camp camp_ith;

        if (currentUser.getRole().equals("staff")){
            
            System.out.print("Camp's name: ");
            String delCampName = sc.nextLine();

            for (int i = 0; i < campList.size(); i++){
                camp_ith = campList.get(i);

                if (camp_ith.getCampName().equals(delCampName)){
                    System.out.print("Confirm delete (Y/N):");
                    String choice = sc.next();

                    if (choice.equals("Y") || choice.equals("y")){
                        campList.remove(i);
                        System.out.println("Camp remove Successfully!");
                        return;
                    }
                    else {
                        System.out.println("Cancelling...");
                        return;
                    }
                }
            }

            System.out.println("Could not find the matching name!");
        }
        else {
            System.out.println("You don't have enough authority to delete the Camp!");
        }

        tempDelay();
    }

    public void chooseCampToEdit(){
        System.out.println("Editing Camp Screen:");

        if (currentUser.getRole().equals("staff")){
            Scanner sc = new Scanner(System.in);
            viewCreatedCamp();
            System.out.print("Camp's name': ");
            String campName = sc.nextLine();

            for (int i = 0; i < campList.size(); i++){

                // Check matching camp's name AND matching staft UserID
                if (campName.equals(campList.get(i).getCampName()) &&
                    currentUser.getUserid().equals(campList.get(i).getStaffInCharge())){
                    
                    campList.get(i).editCamp(currentUser);
                }

                break;
            }
            
            System.out.println("There is no camp available to edit!");
        }
        else {
            System.out.println("You don't have enough authority to edit camp");
        }

        tempDelay();
    }

    public void viewCreatedCamp(){
        int index = 1;

        if (currentUser.getRole().equals("staff")){
            for (int i = 0; i < campList.size(); i++){

                // Check for matching UserID
                if (campList.get(i).isStaffInCharge(currentUser)){
                    System.out.println(index + ". " + campList.get(i).getCampName());
                    index++;
                }
            }
        }
        else {
            System.out.println("You don't have enough authority to view created camp");
        }

        if (index == 1){
            System.out.println("You haven't created any camp!\n\n\n");
        }

        tempDelay();
    }

    public void viewAllCamp(){
        int index = 1;
        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.allowToView(currentUser)){
                System.out.print(index + ". " + camp_ith.getCampName() + "\t");

                if (camp_ith.isCommittee(currentUser)){
                    System.out.print("[Commitee]");
                }
                else if (camp_ith.isAttendee(currentUser)){
                    System.out.print("[Attendee]");
                }
                else if (camp_ith.isStaffInCharge(currentUser)){
                    System.out.print("[In Charge]");
                }

                System.out.println();
                index++;
            }
        }

        if (index == 1){
            System.out.println("There is no camp to view");
        }

        tempDelay();
        
        
    }

    // This method that I've implemented will ask the Staff to choose to print the list of
    // Attendee or the list of Committee already so you don't have to worry about it!
    public void viewStudentList(){


        for (int i = 0; i < 100; i++){
            System.out.println();
        }

        if (currentUser.getRole().equals("student")){
            System.out.println("You don't have enough authority to view the Student list");
            tempDelay();
            return;
        }

        System.out.println("View Student List Window:");
        viewCreatedCamp();

        System.out.print("Camp's name: ");
        String campName = sc.nextLine();

        // Scan throught the list of camps to check the matching Camp base on the name provided
        for (int i = 0; i < campList.size(); i++){
            Camp currentCamp = campList.get(i);

            if (currentCamp.getCampName().equals(campName)){
                System.out.println("View Options:");
                System.out.println("1. Attendee");
                System.out.println("2. Commitee");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        currentCamp.printAttendeeList(currentUser);
                        break;
                
                    case 2:
                        currentCamp.printCommitteList(currentUser);
                        break;

                    default:
                        System.out.println("Invalid choice");
                        System.out.println("Press Enter to go Back!");
                        sc.nextLine();

                        tempDelay();
                }
                return;
            }
        }
    }

    public void viewAvailableCamp(){
        
    }

    public void viewSpecificCamp(){
        
    }

    

    public void registerCamp(){
        if (currentUser.getRole().equals("student")){
            









        }
    }
    


}
