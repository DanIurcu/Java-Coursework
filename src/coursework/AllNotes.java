/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.swing.JOptionPane;
/**
 *
 * @author Danny & Dan Iurcu
 */
class AllNotes extends CommonCode {
    
    private ArrayList<Note> allNotes= new ArrayList<>();
    private String crse = "";
    private int maxID = 0;
    
    AllNotes() {

    }
    
    public void readAllNotes(ArrayList<String> Courses) {
        ArrayList<String> readNotes = new ArrayList<>();
        
        allNotes.clear();
        for(String c: Courses){
            readNotes = readTextFile(appDir+"\\Courses"+"\\"+c+"\\Notes.txt");
            System.out.println(readNotes.get(0));
            
            if ("File not found".equals(readNotes.get(0))) {
                CreateNewNotesFile(c);
            } else {
                for (String str : readNotes) {

                    String[] tmp = str.split("\t");
                    Note n = new Note();
                    n.setNoteID(Integer.parseInt(tmp[0]));
                    n.setCourse(tmp[1]);
                    n.setDayte(tmp[2]);
                    n.setCoursework(tmp[3]);
                    n.setRequirement(tmp[4]);
                    n.setNote(tmp[5]);
                    allNotes.add(n);
                    maxID=Integer.parseInt(tmp[0]);
                }
            }
        }   
    }
    
    public void CreateNewNotesFile(String c){
       try{
             File NewFile = new File(appDir+"\\Courses"+"\\"+c+"\\Notes.txt");
             NewFile.createNewFile();
         }catch (IOException ex) {
             System.out.println("Error while creating main Notes.txt file");
         }
    }
    
    public void addNote(int maxID, String course, String coursework, String requirement, String note) {
        Note myNote = new Note(maxID, course, coursework, requirement, note);
        allNotes.add(myNote);
        writeAllNotes(myNote);
    }
    
     public void addNote(int maxID, String course, String coursework, String requirement) {
        String newNote = "";
        newNote=JOptionPane.showInputDialog(null,"insert Note");
        if(!newNote.equals(null)){
            Note myNote = new Note(maxID, course, coursework, requirement, newNote);
            allNotes.add(myNote);
            writeAllNotes(myNote);
        }
        else{
            out.println("User has canceled the note insertion");
        }
        
    }
    
    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }
    
    private void writeAllNotes(Note newNote) {
        String path = appDir+"\\Courses"+"\\"+newNote.getCourse()+"\\Notes.txt";
        try {
            FileWriter fw = new FileWriter(path, true);
            PrintWriter pw = new PrintWriter(fw);
            String tmp = newNote.getNoteID() + "\t";
            tmp += newNote.getCourse() + "\t";
            tmp += newNote.getDayte() + "\t";
            tmp += newNote.getCoursework() + "\t";
            tmp += newNote.getRequirement() + "\t";
            tmp += newNote.getNote();
            pw.println(tmp);
            pw.close();
        } catch (IOException ex) {
            System.out.println("Error while trying to write new note in " + path);
        }
    } 
    
    public void SaveEdittedNotes(String Course, String Coursework, String Requirement, String NewNotes){
        String path= appDir+"\\Courses"+"\\"+Course+"\\Notes.txt";
        boolean notfound=true;
        for( String note: NewNotes.split("\n")){
           notfound=true;
           for(Note n : allNotes){
               if(n.getRequirement().equals(Requirement)){
                   if(n.getNote().equals(note)){
                        notfound=false;
                   }
               }
           }
           if(notfound){
               Note myNote= new Note(getMaxID(),Course,Coursework,Requirement,note);
               allNotes.add(myNote);
               out.println(myNote.getNote().toString());
               writeAllNotes(myNote);
           }
        }
    }
    
    public int getMaxID() {
        maxID++;
        return maxID;
    }
    
    public ArrayList getFilteredNotes(String requirementName){
        ArrayList<String> filteredRequirements =new ArrayList();
         
        for(Note n : allNotes){
             if(n.getRequirement().equals(requirementName)){
                 filteredRequirements.add(n.getNote());
             }
        }
        return filteredRequirements;
    }
    
    public String searchAllNotesByKeyword(String noteList, int i, String s) {
       if (i == allNotes.size()) {
           return noteList;
       }
       if (allNotes.get(i).getNote().contains(s)) {
           noteList += allNotes.get(i).getNote() + "\n";
       }
       return searchAllNotesByKeyword(noteList,i+1,s);
    }
    
    public int CountOccurencesInCourse(String SearchText,String Course,int index,int counter){
        if(index<allNotes.size()){
            if(allNotes.get(index).getCourse().contains(Course)){
                if(allNotes.get(index).getNote().contains(SearchText)){
                    counter=CountOccurencesInCourse(SearchText,Course,index+1,counter+1);
                }
                else{
                    counter=CountOccurencesInCourse(SearchText,Course,index+1,counter);
                }
            }       
        return counter;
        }
        else{
            return counter;
        }
    }
    
    public String MostCommonDate(){
        String Temp1="";
        String Temp2="";
        String Date1[]=new String[2];
        String Date2[]=new String[2];
        String Result="";
        int i=0;
        int j=0;
        int Counter=0;
        int MaxOccurances=0;
        while(i<allNotes.size()){
            j=i+1;
            Counter=0;
            Temp1=allNotes.get(i).getDayte().toString();
            Date1=Temp1.split(" ",2);
            while(j<allNotes.size()){
                Temp2=allNotes.get(j).getDayte().toString();
                Date2=Temp2.split(" ",2);
                if(Date1[0].equals(Date2[0])){
                    Counter++;
                    }
                j++;
            }
            if(MaxOccurances<Counter){
                MaxOccurances=Counter;
                Result=Date1[0];
            }
            i++;      
        }
        return Result;
    }
    
    public void updateNotes(String OldName,String NewName){
        for(Note n: allNotes){
            if(n.getCourse().equals(OldName)){
                n.setCourse(NewName);
            }
        }
    }
}
