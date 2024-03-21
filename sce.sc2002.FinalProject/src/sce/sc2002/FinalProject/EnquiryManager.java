package sce.sc2002.FinalProject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnquiryManager {
    private final List<Enquiry> enquiries;

    public EnquiryManager() {
        this.enquiries = new ArrayList<>();
    }

    public void createEnquiry(Camp camp, Student author, String subject, String description) {
        Enquiry enquiry = new Enquiry(camp, author, subject, description);
        enquiries.add(enquiry);
    }

    public List<Enquiry> getUnresolvedEnquiries() {
        return enquiries.stream()
                .filter(e -> !e.getResolved())
                .collect(Collectors.toList());
    }

    public List<Enquiry> getResolvedEnquiries() {
        return enquiries.stream()
                .filter(Enquiry::getResolved)
                .collect(Collectors.toList());
    }

    public void resolveEnquiry(int enquiryID) {
        Enquiry enquiry = findEnquiryById(enquiryID);
        if (enquiry != null) {
            enquiry.resolveEnquiry();
        } else {
            throw new IllegalArgumentException("Enquiry with ID " + enquiryID + " not found");
        }
    }

    private Enquiry findEnquiryById(int enquiryID) {
        return enquiries.stream()
                .filter(e -> e.getEnquiryID() == enquiryID)
                .findFirst()
                .orElse(null);
    }
    
    public void editDescription(int enquiryID, String newDescription) {
        Enquiry enquiry = findEnquiryById(enquiryID);
        if (enquiry == null) {
            throw new IllegalArgumentException("Enquiry with ID " + enquiryID + " not found");
        }
        enquiry.editDescription(newDescription);
    }

    // Add this method to EnquiryManager
    public void deleteEnquiry(int enquiryID) {
        Enquiry enquiry = findEnquiryById(enquiryID);
        if (enquiry != null) {
            enquiries.remove(enquiry);
        } else {
            throw new IllegalArgumentException("Enquiry with ID " + enquiryID + " not found");
        }
    }


    public ArrayList<Enquiry> getEnquiries() {
        return new ArrayList<>(enquiries); // return copy to prevent encapsulation
    }

}

