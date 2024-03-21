package sce.sc2002.FinalProject;

import java.io.*;
import java.util.Scanner;

public class TestDraft {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc;
		try {
        sc = new Scanner(new File(System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));

		sc.useDelimiter(",");   //sets the delimiter pattern  
        }

        catch (final Exception e){
            sc = new Scanner(new File(System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));
            System.out.println("\n\n\n" + System.getProperty("user.dir"));
            		sc.useDelimiter(",");   //sets the delimiter pattern  

        }
        
	
	
//	try (PrintWriter writer = new PrintWriter(new File("file.csv"))) {
//	      StringBuilder stringbuilder = new StringBuilder();
//	      stringbuilder.append("iasdfasdf");
//	      stringbuilder.append(',');
//	      stringbuilder.append("Nasdfasdf");
//	      stringbuilder.append('\n');
//	      stringbuilder.append("asdfasdf");
//	      stringbuilder.append(',');
//	      stringbuilder.append("Joasdfasdf");
//	      stringbuilder.append('\n');
//	      writer.write(stringbuilder.toString());
//	      System.out.println("done!");
//	    } catch (FileNotFoundException exception) {
//	      System.out.println(exception.getMessage());
//	    }

		
		
		UserData userdata = new UserData("student");
		userdata.changePassword("KOH1", "password", null);
		
		
		
	}
}
