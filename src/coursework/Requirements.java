/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

/**
 *
 * @author Dan
 */
public class Requirements extends CommonCode {
    private int requirementID = 0;
    private String course = "";
    private String dayte = "";
    private String coursework = "";
    private String requirement = "";
    
    public Requirements() {
        
    }
    
    public Requirements(int nid, String crs, String dt, String nt, String rt) {
        setRequirementID(nid);
        setCourse(crs);
        setDayte(dt);
        setCoursework(nt);
        setRequirement(rt);
    }
    
    public Requirements(int max, String crs, String nt, String rt) {
        setRequirementID(max);
        setCourse(crs);
        setDayte();
        setCoursework(nt);
        setRequirement(rt);
    }
    
    public void setRequirementID(int n) {
        int nid = n;
        // Any validation goes here.
        requirementID = nid;
    }
    
    public void setCourse(String c) {
        String crse = c;
        // Any validation goes here.
        course = crse;
    }
    
    public int getRequirementID() {
        // Any checking goes here.
        return requirementID;
    }
    
    public String getCourse() {
        // Any checking goes here.
        return course;
    }
    
    public void setDayte() {
        dayte = orderedDate;
    }
    
    public void setDayte(String d) {
        dayte = d;
    }
    
    public String getDayte() {
        return dayte;
    }
    
    public void setCoursework(String n) {
        // Any validation goes here.
        coursework = n;
    }
    
    public String getCoursework() {
        // Any checking goes here.
        return coursework;
    }
    public void setRequirement(String n){
        requirement=n;
    }
    public String getRequirement(){
        return requirement;
    }
}
