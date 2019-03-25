/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
public class CourseworkItem extends CommonCode {
     public ArrayList<Courseworks> courseworkItem = new ArrayList<>();
     public int maxID = 0;
     
     public void CreateNewCoursesFile(){
         try{
             File NewCourseFile = new File("Courses.txt");
             NewCourseFile.createNewFile();
         }catch (IOException ex) {
             out.println("Error");
         }
     }
     
     public void CreateCourseFolder(String courseName){
         String path=appDir+"\\Courses"+"\\"+courseName;
         new File(appDir+"/Courses/"+courseName).mkdirs();
         try{
             File outputCoursework = new File(path+"\\Coursework.txt");
             File outputRequirements = new File(path+"\\Requirements.txt");
             File outputNotes = new File(path+"\\Notes.txt");
             outputCoursework.createNewFile();
             outputRequirements.createNewFile();
             outputNotes.createNewFile();
         }catch (IOException ex) {
             out.println("Error while creating files");
         }
     }
     
     public void CreateNewCourseworksFile(String courseName){
         try{
             File NewFile = new File(appDir+"\\Courses"+"\\"+courseName+"\\Coursework.txt");
             NewFile.createNewFile();
         }catch (IOException ex) {
             System.out.println("Error while creating main Coursework.txt file");
         }
    }
     
     public ArrayList<Courseworks> getAllCourseworks(){
         return courseworkItem;
     }
     
     public int getMaxID() {
        maxID++;
        return maxID;
    }
     
     public void readAllCourseworks(ArrayList<String> Courses){
        ArrayList<String> readCourseworks = new ArrayList<>();
        courseworkItem.clear();
        for(String c: Courses){
            readCourseworks = readTextFile(appDir + "\\Courses"+"\\"+c+"\\Coursework.txt");
            System.out.println(readCourseworks.get(0));
            if ("File not found".equals(readCourseworks.get(0))) {
                CreateNewCourseworksFile(c);
            } else {
                for (String str : readCourseworks) {
                    String[] tmp = str.split("\t");
                    Courseworks n = new Courseworks();
                    n.setCourseworkID(Integer.parseInt(tmp[0]));
                    n.setCourse(tmp[1]);
                    n.setDayte(tmp[2]);
                    n.setCoursework(tmp[3]);
                    courseworkItem.add(n);
                    maxID=Integer.parseInt(tmp[0]);
                }
            }
        }
     }
     
     public String addCourse(){
         String newItem;
         newItem = JOptionPane.showInputDialog("Insert Course");
         
         try {
             FileWriter fw = new FileWriter("Courses.txt", true);
             PrintWriter pw = new PrintWriter(fw);
             CreateCourseFolder(newItem);
             pw.println(newItem);
             pw.close();  
         } catch (IOException e) {
             out.println("Error");
         }
         return newItem;
     }
     
     public void deleteCourse(String courseName) {
        String inputFileName = "Courses.txt";
        String outputFileName = "tempFile.txt";
        try {
            File inputFile = new File(inputFileName);
            File outputFile = new File(outputFileName);
   
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
        
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (!line.equals(courseName)) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
    if (inputFile.delete()) {
        
        if (!outputFile.renameTo(inputFile)) {
                throw new IOException("Could not rename " + outputFileName + " to " + inputFileName);
                }   
    } else {
            throw new IOException("Could not delete original input file " + inputFileName);
      }
        } catch (IOException ex) {
            ex.printStackTrace();
         }
     }
     
    public String amendCourse(String courseName) {
        String inputFileName = "Courses.txt";
        String outputFileName = "tempFile.txt";
        String AmmendCourse=null;
        try {
            File inputFile = new File(inputFileName);
            File outputFile = new File(outputFileName);
   
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
        AmmendCourse=JOptionPane.showInputDialog("Insert new name");
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (!line.equals(courseName)) {
                writer.write(line);
                writer.newLine();
            }
            else{
                writer.write(AmmendCourse);
                writer.newLine();
        }
        }
    }
    if (inputFile.delete()) {
        
        if (!outputFile.renameTo(inputFile)) {
                throw new IOException("Could not rename " + outputFileName + " to " + inputFileName);
                }   
    } else {
            throw new IOException("Could not delete original input file " + inputFileName);
      }
        } catch (IOException ex) {
            ex.printStackTrace();
         }
    return AmmendCourse; 
    }
    
    public void addCoursework(int maxID, String courseName){
        String newCoursework="";
        newCoursework = JOptionPane.showInputDialog(null,"Insert Coursework");
        if(!newCoursework.equals(null)){
            Courseworks myCoursework=new Courseworks(maxID,courseName,newCoursework);
            courseworkItem.add(myCoursework);
            writeCoursework(myCoursework);
        }
        else{
            out.println("User canceled the coursework insertion");
        }

    }
    
    private void writeCoursework(Courseworks newCoursework){
        String path=appDir+"\\Courses"+"\\"+newCoursework.getCourse();
        String tmp = newCoursework.getCourseworkID() + "\t";
        tmp += newCoursework.getCourse() + "\t";
        tmp += newCoursework.getDayte() + "\t";
        tmp += newCoursework.getCoursework();
            try {
                FileWriter fw = new FileWriter(path+"\\Coursework.txt", true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(tmp);
                pw.close();  
            } catch (IOException e) {
                out.println("Error while trying to write new coursework in "+path);
            }
    }
    
    public ArrayList getFilteredCourseworks(String courseName){
        ArrayList<String> filteredCourseworks = new ArrayList();
        
        for( Courseworks course:courseworkItem){
            if(course.getCourse().equals(courseName)){
                filteredCourseworks.add(course.getCoursework());
            }
        }
        return filteredCourseworks;
    }
}