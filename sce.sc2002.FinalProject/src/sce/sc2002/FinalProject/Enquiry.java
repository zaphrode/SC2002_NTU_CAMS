package sce.sc2002.FinalProject;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Represents an enquiry related to a camp in the Camp Application and Management System (CAMs).
 */
public class Enquiry {
    private static final AtomicInteger enquiryCount = new AtomicInteger(1);

    private int enquiryID;
    private String subject;
    private String description;
    private String reply;
    private boolean resolved;
    private Date timestamp;
    private Student author;
    private Staff replyAuthor;
    private Camp camp;
    private Student responsibleCommitteeMember;

    public Enquiry(Camp camp, Student author, String subject, String description) {
        validateNonNull(camp, "Camp cannot be null");
        validateNonNull(author, "Author cannot be null");

        this.enquiryID = enquiryCount.getAndIncrement();
        this.camp = camp;
        this.author = author;
        this.subject = subject;
        this.description = description;
        this.timestamp = new Date();  // Set only once during construction
        this.resolved = false;  // Default to unresolved
    }

    @Override
    public String toString() 
    {
        return "Enquiry ID: " + enquiryID +
            "\nSubject: " + subject +
            "\nDescription: " + description +
            "\nResolved: " + resolved +
            "\nTimestamp: " + timestamp +
            "\nAuthor: " + author +
            "\nReply Author: " + replyAuthor +
            "\nCamp: " + camp +
            "\nCommittee Member: " + responsibleCommitteeMember;
    }


    public String getDescription() {
        return description;
    }

    public String getReply() {
        return reply;
    }

    public int getEnquiryID() {
        return enquiryID;
    }

    public Camp getCamp() {
        return camp;
    }

    public Student getAuthor() {
        return author;
    }

    public Staff getReplyAuthor() {
        return replyAuthor;
    }

    public boolean getResolved() {
        return resolved;
    }

    public void setDescription(String description) {
        if (!resolved) {
            this.description = description;
        } else {
            throw new IllegalStateException("Cannot change description of resolved enquiry.");
        }
    }
    
    public void setReply(String reply) {
        if (!resolved) {
            this.reply = reply;
            // Optionally, if the reply is being set for the first time, automatically mark the enquiry as resolved
            if (!hasReply()) {
                resolveEnquiry();
            }
        } else {
            throw new IllegalStateException("Cannot change reply of resolved enquiry.");
        }
    }
    

    public int getEnquiryAgeInDays() 
    {
        Date now = new Date();
        long diff = now.getTime() - timestamp.getTime();
        return (int) (diff / (24 * 60 * 60 * 1000)); // Convert milliseconds to days
    }

    public void markAsResolved() 
    {
        resolved = true;
    }

    // Protected to ensure enquiry is not edited after being resolved
    protected void editDescription(String newDescription) 
    {
        if (!resolved) {
            this.description = newDescription;
        } else {
            throw new IllegalStateException("Cannot edit resolved enquiry.");
        }
    }

    public synchronized void assignToCommitteeMember(Student student) 
    {
        validateNonNull(student, "Student cannot be null");
        if (student.isCampCommittee() && !this.resolved) {
            this.responsibleCommitteeMember = student;
        } else {
            throw new IllegalStateException("Cannot assign an enquiry that is already resolved or student is not a committee member.");
        }
    }

    // Utility method for null checking
    private void validateNonNull(Object obj, String message) 
    {
        if (obj == null) 
        {
            throw new IllegalArgumentException(message);
        }
    }

    protected void resolveEnquiry() 
    {
        this.resolved = true;
    }

    public boolean hasReply() 
    {
        return reply != null && !reply.isEmpty();
    }
}

