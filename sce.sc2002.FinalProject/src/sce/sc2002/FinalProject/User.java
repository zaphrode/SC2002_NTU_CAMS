package sce.sc2002.FinalProject;

public abstract class User {
    private String userID; // User's ID
    private String faculty; // User's faculty information

    public User(String userID, String password, String faculty) {
        this.faculty = faculty;
    }

    public String getUserID() {
        return userID;
    }

    public String getFaculty() {
        return faculty;
    }
}
