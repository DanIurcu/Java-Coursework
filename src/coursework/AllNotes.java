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
 * @author Danny
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
        allNotes.add(myNote);writeAllNotes();
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
}
