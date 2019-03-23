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
     public ArrayList<Note> coursworkItem = new ArrayList<>();
     public int max = 0;
     
     public void CreateNewCoursesFile(){
         try{
             File NewCourseFile = new File("Courses.txt");
             NewCourseFile.createNewFile();
         }catch (IOException ex) {
             out.println("Error");
         }
     }
     
     public void CreateCourseFolder(String CourseName){
         String path=appDir+"\\Courses"+"\\"+CourseName;
         new File(appDir+"/Courses/"+CourseName).mkdirs();
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
         
         
     }
