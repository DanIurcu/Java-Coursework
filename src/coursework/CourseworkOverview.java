/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Danny & Dan Iurcu
 */
public class CourseworkOverview extends CommonCode { // aka Requirements
     public ArrayList<Requirements> courseworkOverview = new ArrayList<>();
     public int maxID = 0;
     
     public void readAllRequirements(ArrayList<String> Courses){
         ArrayList<String> readRequirements = new ArrayList();
         courseworkOverview.clear();
         for(String c: Courses){
            readRequirements = readTextFile(appDir + "\\Courses"+"\\"+c+"\\Requirements.txt");
            System.out.println(readRequirements.get(0));
            if ("File not found".equals(readRequirements.get(0))) {
                CreateNewRequirementsFile(c);
            } else {
                for (String str : readRequirements) {
                    String[] tmp = str.split("\t");
                    Requirements r = new Requirements();
                    r.setRequirementID(Integer.parseInt(tmp[0]));
                    r.setCourse(tmp[1]);
                    r.setDayte(tmp[2]);
                    r.setCoursework(tmp[3]);
                    r.setRequirement(tmp[4]);
                    courseworkOverview.add(r);
                    maxID=Integer.parseInt(tmp[0]);
                }
            }
         }
     }
     
     public void CreateNewRequirementsFile(String courseName){
         try{
             File NewFile = new File(appDir+"\\Courses"+"\\"+courseName+"\\Requirements.txt");
             NewFile.createNewFile();
         }catch (IOException ex) {
             System.out.println("Error while creating main requirements.txt file");
         }
     }
     
     public ArrayList<Requirements> getAllRequirements(){
         return courseworkOverview;
     }
     
     public int getMaxID(){
         maxID++;
         return maxID;
     }
     
     public void addRequirement(int maxID,String courseName,String courseworkName){
         String newRequirement="";
         newRequirement=JOptionPane.showInputDialog(null,"insert Requirement");
         if(!newRequirement.equals(null)){
             Requirements myRequirement= new Requirements(maxID,courseName,courseworkName,newRequirement);
             courseworkOverview.add(myRequirement);
             writeRequirement(myRequirement);
         }
         else{
             out.println("User has canceled the requirement insertion");
         }
     }
     
     
     public void writeRequirement(Requirements newRequirement){
        String path=appDir+"\\Courses"+"\\"+newRequirement.getCourse();
        String tmp = newRequirement.getRequirementID() + "\t";
        tmp += newRequirement.getCourse() + "\t";
        tmp += newRequirement.getDayte() + "\t";
        tmp += newRequirement.getCoursework()+"\t";
        tmp += newRequirement.getRequirement();
            try {
                FileWriter fw = new FileWriter(path+"\\Requirements.txt", true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(tmp);
                pw.close();  
            } catch (IOException e) {
                out.println("Error while trying to write new requirement in "+path);
            }
     }
     
     public ArrayList getFilteredRequirements(String courseworkName){
         ArrayList<String> filteredRequirements =new ArrayList();
         
         for(Requirements r:courseworkOverview){
             if(r.getCoursework().equals(courseworkName)){
                 filteredRequirements.add(r.getRequirement());
             }
         }
         return filteredRequirements;
     }
     
     public void updateRequirements(String OldName,String NewName){
        for(Requirements r: courseworkOverview){
            if(r.getCourse().equals(OldName)){
                r.setCourse(NewName);
            }
        }
    }
}
