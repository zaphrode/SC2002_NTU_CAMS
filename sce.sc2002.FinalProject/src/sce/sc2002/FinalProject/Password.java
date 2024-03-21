package sce.sc2002.FinalProject;

import java.io.Console;
import java.util.Scanner;

public class Password {
	private UserData studentData;
	private UserData staffData;
	private UserData userData;
	private boolean granted;
	private String userid;
	private String role;
	private String name;
	private final static String secretKey = "asdfljknqwe6238745fg23av3gv246hrejbq4tybetjysdafg5y";
	
	
	public Password() {
		studentData = new UserData("student");
		staffData = new UserData("staff");
		granted = false;
		userid = null;
		role = null;
	}
	
	public String getUserid(){
		if (granted) return userid;
		return null;
	}

	public String getRole(){
		if (granted) return role;
		return null;
	}

	public String getFaculty(){
		if (granted) return userData.getFaculty(userid);
		return null;
	}

	public boolean logedIn() {
		return granted;
	}
	
	public void signout() {
		granted = false;
		userid = null;
	}
	
	
	protected void validate(String userid, String password) {
		if (studentData.getPassword(userid, secretKey) == null && staffData.getPassword(userid, secretKey) == null) return;
		
		if (studentData.getPassword(userid, secretKey) != null && studentData.getPassword(userid, secretKey).equals(password)) {
			role = "student";
			granted = true;
			this.userid = userid;
			userData = studentData;
			
			if (studentData.getPassword(userid, secretKey).equals("password")) changePassword();
			return;
		}
		else if (staffData.getPassword(userid, secretKey) != null && staffData.getPassword(userid, secretKey).equals(password)) {
			role = "staff";
			granted = true;
			this.userid = userid;
			userData = studentData;
			if (staffData.getPassword(userid, secretKey).equals("password")) changePassword();
			return;
		}

		return;
	}
	
	protected String readPassword() {
		Console console = System.console();
		
		if (console == null) {

			Scanner sc = new Scanner(System.in);

			String password = sc.next();

			return password;
		}
		
		char[] passwordArray = console.readPassword();
		return new String(passwordArray);
	}
	
	public void changePassword() {
		Scanner sc = new Scanner(System.in);
		String newPassword;
		boolean changed = false;
		
		while (!changed) {
			System.out.println("-------Please change your password-------");
			System.out.print("New password: ");
			newPassword = readPassword();
			System.out.print("Confirm new password: ");
			String confirmPassword = readPassword();
			
			if (newPassword.equals(confirmPassword) && !newPassword.equals(userData.getPassword(userid, secretKey))) {
				userData.changePassword(userid, newPassword, secretKey);
				changed = true;
			}
			else {
				System.out.println("Confirmation Failed, please try again!");
			}
		}
		
	}
	
	
	
}
