package sce.sc2002.FinalProject;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class CampInformation {
	private String 		campName;




	private Date        startDate;
	private Date    	endDate;
	private Date    	closingDate;


	private String 		avaialbleTo;
	private String 		location;

	private int    		attendeeSlot = 40;
	private int    		campCommSlots = 10;
	private String 		description;
	private String 		staffInCharge; //creator of the camp
	private boolean 	visibility = false; // Default to false

	private ArrayList<Student> 	  studentAttendees;
	private ArrayList<Student>    committeeList;
	private ArrayList<Enquiry>    enquiries;
	private ArrayList<Suggestion> suggestions;
	private ArrayList<Student>    blacklist;


	private CampList 				campList;  // The Camp need to know the campList to check for clashing.
	
	// Initializes the list in the constructors
	public CampInformation(CampList campList) {
		studentAttendees = new ArrayList<Student>();	 // list of 'Student' objects
		committeeList    = new ArrayList<Student>();     // list of 'Committee' objects
		enquiries        = new ArrayList<Enquiry>();	 // list of 'Enquiry' objects
		suggestions      = new ArrayList<Suggestion>();	 // list of 'Suggestion' objects
		blacklist        = new ArrayList<Student>(); 	 // list of 'Student' objects
		this.campList    = campList;
	}
	
	// Setter methods, please do not delete Tuan :") 
	public void setCampName(String campName)   		{this.campName      = campName;}


 	public void setStartDate(String startDate)      {this.startDate     = fromStringToDate(startDate);} 
 	public void setEndDate(String endDate)    		{this.endDate       = fromStringToDate(endDate);}
 	public void setClosingDate(String closingDate)  {this.closingDate   = fromStringToDate(closingDate);} 

	public void setAvailableTo(String availableTo)  {this.avaialbleTo   = availableTo;}
 	public void setLocation(String location)   		{this.location      = location;} 
	public void setAttendeeSlot(int attendeeSlot)  	{this.attendeeSlot  = attendeeSlot ;}
	public void setCampCommSlot(int campCommSlots)  {this.campCommSlots = campCommSlots;}
 	public void setDescription(String description)  {this.description   = description;} 
	public void setStaffInCharge(Login currentUser) {this.staffInCharge = currentUser.getUserid();} 
 	public void setVisibility(char x) {  
		if(x == 'Y') {
    		visibility = true;  }
  		else {visibility = false;} 
	}


	// Getter methods.
	public String  	getCampName()		{return campName;}
	public String  	getStaffInCharge()	{return staffInCharge;}
	public boolean 	getVisibility()		{return visibility;}
	public int 		getAttendeeSlot()	{return attendeeSlot;}
	public int 		getCampCommSlot()	{return campCommSlots;}
	public Date		getStartDate()		{return startDate;}
	public Date		getEndDate()		{return endDate;}
	public Date		getClosingDate()	{return closingDate;}
	public String   getFaculty()		{return avaialbleTo;}

	public ArrayList<Student> getAttendeeList() 	{return studentAttendees;}
	public ArrayList<Student> getCommitteeList()	{return committeeList;}

	public Date fromStringToDate(String dateText){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOutput = sdf.parse(dateText, new ParsePosition(0));
		return dateOutput;
	}

	public void committeeRegister(Login currentUser){

		// 1. Check if there is avalable slot
		if (campCommSlots > 0){

			// 2. Check if the current user is an Attendee of this camp
			if (isAttendee(currentUser)){
				System.out.println("You are currently an Attendee of this camp!");
			}

			// 3. Check if the user is blacklisted
			else if (isBlackListed(currentUser)){
				System.out.println("You have withdraw from this camp before.");
				System.out.println("You have been blacklisted");
			}

			// 4. Check for clashing 
			else if (isClash(currentUser)){
				System.out.println("The camp registering is clashing with one of the camp you have registered!");
			}

			// 5. If not, the user if good to register as a camp commitee
			else{
				committeeList.add(new Student(currentUser.getUserid(), currentUser.getFaculty()));
				campCommSlots--;
			}
		}
		else {
			System.out.println("Out of Slot for Camp Committee!");
		}
	}

	public boolean isCommittee(Login currentUser){
		for (int i = 0; i < committeeList.size(); i++){
			if (committeeList.get(i).getID().equals(currentUser.getUserid())){
				return true;
			}
		}
		return false;
	}

	public void attendeeRegister(Login currentUser){

		// 1. it check for available slots
		if (attendeeSlot > 0){

			// 2. Check if the user is currently a Camp Commitee
			if (isCommittee(currentUser)){
				System.out.println("You are currently an Commitee of this camp!");
			}

			// 3. Check if the user is blacklisted
			else if (isBlackListed(currentUser)){
				System.out.println("You have withdraw from this camp before.");
				System.out.println("You have been blacklisted");
			}

			// 4. Check for clashing 
			else if (isClash(currentUser)){
				System.out.println("The camp registering is clashing with one of the camp you have registered!");
			}

			// 5. If the user is not blacklisted or clash with other camps then it is oke to register the camp
			else{
				studentAttendees.add(new Student(currentUser.getUserid(), currentUser.getFaculty()));
				attendeeSlot--;
			}
		}
		else {
			System.out.println("Out of Slot for Attendee in this Camp!");
		}
	}

	public boolean isAttendee(Login currentUser){
		for (int i = 0; i < studentAttendees.size(); i++){
			if (studentAttendees.get(i).getID().equals(currentUser.getUserid())){
				return true;
			}
		}
		return false;
	}

	public void attendeeWithdrawal(Login currentUser){
		if (isCommittee(currentUser)){
			System.out.println("You are a Commitee of this Camp");
			System.out.println("You are not allowed to withdraw");
		}
		else if (isAttendee(currentUser)){
			for (int i = 0; i < studentAttendees.size(); i++){
				if (studentAttendees.get(i).getID().equals(currentUser.getUserid())){
					blacklist.add(studentAttendees.get(i));
					studentAttendees.remove(i);
					attendeeSlot++;
					return;
				}
			}
		}
		else {
			System.out.println("You haven't registered for this camp!");
		}
	}

	public boolean isBlackListed(Login currentUser){
		for (int i = 0; i < blacklist.size(); i++){
			if (blacklist.get(i).getID().equals(currentUser.getUserid())) return true;
		}
		return false;
	}
	
	public boolean isOutOfRegiterDate(){
		Date currentTime = new Date();

		if (currentTime.compareTo(closingDate) > 0){
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isClash(Login currentUser){
		ArrayList<Camp> camps = campList.getCampList();
		Camp camp_ith;

		for (int i = 0; i < camps.size(); i++){
			camp_ith = camps.get(i);

			// Only check overlaping if the currentUser is a member of the ith camp.
			if (camp_ith.isMemberOfCamp(currentUser)){
				if (camp_ith.getCampName().equals(campName)&&
				camp_ith.getStaffInCharge().equals(staffInCharge)) continue;
			
				// Check overlaping
				if (startDate.compareTo(camp_ith.getEndDate()) < 0 && 
					endDate.compareTo(camp_ith.getStartDate()) > 0){
					return true;
				}
			}
		}

		return false;
	}

	public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

	
	
	
}
