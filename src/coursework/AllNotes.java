/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.util.ArrayList;
import java.io.IOException;
/**
 *
 * @author Danny & Dan Iurcu
 */
class AllNotes extends CommonCode {
    
    private ArrayList<Note> allNotes= new ArrayList<>();
    private String crse = "";
    private int maxID = 0;
    
    AllNotes() {
        readAllNotes();
    }
    
    private void readAllNotes() {
        ArrayList<String> readNotes = new ArrayList<>();
        
        readNotes = readTextFile(appDir + "\\Notes.txt");
        System.out.println(readNotes.get(0));
        
        if ("File not found".equals(readNotes.get(0))) {
            
        } else {
            
            allNotes.clear();
            for (String str : readNotes) {
                
                String[] tmp = str.split("\t");
                Note n = new Note();
                n.setNoteID(Integer.parseInt(tmp[0]));
                n.setCourse(tmp[1]);
                n.setDayte(tmp[2]);
                n.setNote(tmp[3]);
                allNotes.add(n);
            }
        }
    }
    
    public void addNote(int maxID, String course, String note) {
        Note myNote = new Note(maxID, course, note);
        allNotes.add(myNote);
        writeAllNotes();
    }
    
    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }
    
    private void writeAllNotes() {
        String path = appDir + "\\Notes.txt";
        ArrayList<String> writeNote = new ArrayList<>();
        for (Note n : allNotes) {
            String tmp = n.getNoteID() + "\t";
            tmp += n.getCourse() + "\t";
            tmp += n.getDayte() + "\t";
            tmp += n.getNote();
            writeNote.add(tmp);
        }
        try {
            writeTextFile(path, writeNote);
        } catch (IOException ex) {
            System.out.println("Problem! " + path);
        }
    } 
    
    public int getMaxID() {
        maxID++;
        return maxID;
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
}
