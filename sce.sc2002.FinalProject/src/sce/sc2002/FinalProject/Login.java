package sce.sc2002.FinalProject;


import java.util.*;


/**
 When the program started it require the instance of
 this class to run to create a login option
 @author Nguyen Tuan
 @version 1.0
 @since 11/2/2023
*/

public class Login extends Password{

	
	public Login() {
		super();
	}

	/**
	 * This method will control the flow of the login system.
	 *----------------------------------------------------------------------------------- */
	public void display(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("_________Login Screen__________");
		
		
		// Input Phase
		while(!logedIn()) {
			System.out.print("UserID: ");
			String input_userid = sc.next();
			
			
			System.out.print("Password: ");
			
			String input_password = readPassword();
			validate(input_userid, input_password);
			if (!logedIn()) {
				System.out.println("UserID or password is incorrect!\n\n");
			}
			else {
				System.out.println("__________Access Granted!____________");
			}
		}
	}
	

}
