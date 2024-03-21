package sce.sc2002.FinalProject;

public class CampApp {
	
	public static void main(String[] args) {
		Login loginScreen = new Login();
		
		boolean quit = false;

		while(!quit){

			loginScreen.display();


			//Pseudo clear screen
			for (int i = 0; i < 100; i++){
				System.out.println();
			}

			CampList campList = new CampList(loginScreen);

			Menu currentSection = returnMenu(loginScreen, campList);

			quit = currentSection.runMenu();
		}

		
		

		
		
	}

	public static Menu returnMenu(Login currentUser, CampList campList){
		if (currentUser.getRole().equals("staff")){
			return new StaffMenu(currentUser, campList);
		}
		else {
			return new StudentMenu(currentUser, campList);
		}
	}


	
}
