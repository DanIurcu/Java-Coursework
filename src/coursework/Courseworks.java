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
public class Courseworks extends CommonCode {
    private int courseworkID = 0;
    private String course = "";
    private String dayte = "";
    private String coursework = "";
    
    public Courseworks() {
        
    }
    
    public Courseworks(int nid, String crs, String dt, String nt) {
        setCourseworkID(nid);
        setCourse(crs);
        setDayte(dt);
        setCoursework(nt);
    }
    
    public Courseworks(int max, String crs, String nt) {
        setCourseworkID(max);
        setCourse(crs);
        setDayte();
        setCoursework(nt);
    }
    
    public void setCourseworkID(int n) {
        int nid = n;
        // Any validation goes here.
        courseworkID = nid;
    }
    
    public void setCourse(String c) {
        String crse = c;
        // Any validation goes here.
        course = crse;
    }
    
    public int getCourseworkID() {
        // Any checking goes here.
        return courseworkID;
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
}
