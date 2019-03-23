/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coursework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author Danny & Dan Iurcu
 */
public class Coursework extends JFrame implements ActionListener, KeyListener {
    
    CommonCode cc= new CommonCode(this);
    JPanel pnl = new JPanel(new BorderLayout());
    JTextArea txtNewNote = new JTextArea();
    JTextArea txtDisplayNotes = new JTextArea();
    ArrayList<String> note = new ArrayList<>();
    ArrayList<String> course = new ArrayList<>();
    
    JComboBox courseList = new JComboBox();
    String crse = "";
    AllNotes allNotes = new AllNotes();
    
    JTextField search = new JTextField();
    CourseworkItem courseworkItem = new CourseworkItem();
    
    public static void main(String[] args) {
        // This is required for the coursework.
        JOptionPane.showMessageDialog(null, "     This Coursework Project was"+"\n made by Danny Roberts and Dan Iurcu");
        Coursework prg = new Coursework();
    }

    // Using MVC
    public Coursework() {
        model();
        view();
        controller();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("NewNote".equals(ae.getActionCommand())){
            addNote(txtNewNote.getText());
            txtNewNote.setText("");
        }
        if ("Close".equals(ae.getActionCommand())){
            txtDisplayNotes.setText("");
        }
        if ("EditNote".equals(ae.getActionCommand())){
            txtDisplayNotes.setEditable(true);
        }
        if ("SaveNote".equals(ae.getActionCommand())){
            txtDisplayNotes.setEditable(false);
            allNotes.SaveEdittedNotes(txtDisplayNotes.getText(),courseList.getSelectedItem().toString());
        }
        if ("Exit".equals(ae.getActionCommand())){
            System.exit(0);
        }
        if ("Course".equals(ae.getActionCommand())) {
            crse = courseList.getSelectedItem().toString();
            System.out.println(crse);
        }
        if ("addCourse".equals(ae.getActionCommand())) {   
            courseList.addItem(courseworkItem.addCourse());
        }
        if ("deleteCourse".equals(ae.getActionCommand())) {
           courseworkItem.deleteCourse(String.valueOf(courseList.getSelectedItem()));
           courseList.removeItem(courseList.getSelectedItem());
        }
        if ("amendCourse".equals(ae.getActionCommand())) {
            String NewName=courseworkItem.amendCourse(courseList.getSelectedItem().toString());
            courseList.removeItem(courseList.getSelectedItem());
            courseList.addItem(NewName);
        }
        if ("SearchKeyword".equals(ae.getActionCommand())){
            String lyst = allNotes.searchAllNotesByKeyword("", 0, search.getText());
            txtDisplayNotes.setText(lyst);
        }
        if ("SearchOccurences".equals(ae.getActionCommand())) {
            String Cour=courseList.getSelectedItem().toString();
            int Counting=allNotes.CountOccurencesInCourse(search.getText(),Cour,0,0);
            txtDisplayNotes.setText("The number of Occurences in the "+Cour+" Course, of the word "+ search.getText()+ " is "+Counting);
        }
        if ("MostCommonDate".equals(ae.getActionCommand())) {
            String ResultDate=allNotes.MostCommonDate();
            txtDisplayNotes.setText("The date on which the most number of notes were made is "+ResultDate);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped not coded yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed not coded yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased not coded yet.");
    }
    
    private void model() {
        
        showAllCrse();
        
    }

    private void view() {
        Font fnt = new Font("Georgia", Font.PLAIN, 24);

        JMenuBar menuBar = new JMenuBar();
        JMenu note = new JMenu();

        note = new JMenu("Note");
        note.setToolTipText("Note tasks");
        note.setFont(fnt);

        note.add(cc.makeMenuItem("New", "NewNote", "Create a new note.", fnt));
        note.addSeparator();
        note.add(cc.makeMenuItem("Close", "Close", "Clear the current note.", fnt));
        
        menuBar.add(note);
        menuBar.add(cc.makeMenuItem("Exit", "Exit", "Close this program", fnt));
                
        for (String crse : course) {
            courseList.addItem(crse);
        }
        
        courseList.setFont(fnt);
        courseList.setMaximumSize(courseList.getPreferredSize());
        courseList.addActionListener(this);
        courseList.setActionCommand("Course");
        menuBar.add(courseList);
        
        JMenu cOptions = new JMenu();
        
        cOptions = new JMenu("Course Options");
        cOptions.setToolTipText("Make changes to course list");
        cOptions.setFont(fnt);
        
        cOptions.add(cc.makeMenuItem("Add Course", "addCourse", "Add Course to the drop down list.", fnt));
        cOptions.addSeparator();
        cOptions.add(cc.makeMenuItem("Amend Course", "amendCourse", "Amend current Course in the drop down list.", fnt));
        cOptions.addSeparator();
        cOptions.add(cc.makeMenuItem("Delete Course", "deleteCourse", "Delete current Course in the drop down list.", fnt));

        menuBar.add(cOptions);
        this.setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
        // Setting up the ButtonBar
        JButton button = null;
        button = cc.makeNavigationButton("Create", "NewNote",
                "Create a new note.",
                "New");
        toolBar.add(button);
        
        button = cc.makeNavigationButton("closed door", "Close",
                "Close this note.",
                "Close");
        toolBar.add(button);
        
        button = cc.makeNavigationButton("Edit", "EditNote",
                "Edit this note.",
                "Edit Note");
        toolBar.add(button);
        
        button = cc.makeNavigationButton("Save", "SaveNote",
                "Save this note.",
                "Save Note");
        toolBar.add(button);
        
        toolBar.addSeparator();
        button = cc.makeNavigationButton("Properties", "AddCoursework",
                "Add a Coursework for this Course.",
                "Add Coursework");
        toolBar.add(button);
        
        button = cc.makeNavigationButton("Numbered List", "AddRequirement",
                "Add a requirement for this coursework.",
                "Add requirement");
        toolBar.add(button);
        
        toolBar.addSeparator();
        button = cc.makeNavigationButton("exit", "Exit",
                "Exit from this program.",
                "Exit");
        
        toolBar.add(button);
        /*button = makeButton("display", "display",
                "display",
                "display");
        toolBar.add(button);*/
        
        toolBar.addSeparator();// This forces anything after it to the right.
        toolBar.add(Box.createHorizontalGlue());
        search.setMaximumSize(new Dimension(6900, 30));
        search.setFont(fnt);
        toolBar.add(search);
        toolBar.addSeparator();
        button = cc.makeNavigationButton("search", "SearchKeyword","Search for this text.","Search");
        toolBar.add(button);
        
        toolBar.addSeparator();
        button = cc.makeNavigationButton("Add to basket", "SearchOccurences",
                "Search Occurences",
                "SO");
        toolBar.add(button);
        
        toolBar.addSeparator();
        button = cc.makeNavigationButton("Card file", "MostCommonDate",
        "Show the date on which the most number of notes were created",
        "Most Common Date");
        toolBar.add(button);
        
        add(toolBar, BorderLayout.NORTH);
        
        JPanel pnlWest = new JPanel();
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setBorder(BorderFactory.createLineBorder(Color.black));
        
        txtNewNote.setFont(fnt);
        pnlWest.add(txtNewNote);

        JButton btnAddNote = new JButton("Add note");
        btnAddNote.setActionCommand("NewNote");
        btnAddNote.addActionListener(this);
        pnlWest.add(btnAddNote);
        
        add(pnlWest, BorderLayout.WEST);

        JPanel cen = new JPanel();
        cen.setLayout(new BoxLayout(cen, BoxLayout.Y_AXIS));
        cen.setBorder(BorderFactory.createLineBorder(Color.black));
        txtDisplayNotes.setFont(fnt);
        cen.add(txtDisplayNotes);
        
        add(cen, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Coursework - Danny Roberts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);  // Needed to ensure that the items can be seen.
    }


    private void controller() {
        DisplayAllNotes();
    }

    private void addNote(String text) {
        allNotes.addNote(allNotes.getMaxID(), crse, text);
        DisplayAllNotes();
    }
    
    private void showCrs(String text) {
        course.add(txtNewNote.getText());
        showAllCrse();
    }
    
  
    private String showAllCrse() {
                String allCourses="";
        try {
            FileReader fr = new FileReader("Courses.txt");
            BufferedReader br = new BufferedReader(fr);
        
        String frcourse;
        while ((frcourse = br.readLine()) != null)  {
            course.add(frcourse);
        }
        br.close();
        return allCourses;

        } catch(IOException e) {
            courseworkItem.CreateNewCoursesFile();
            out.println("File Not Found, Creating new one");
        }
        return allCourses;
    }
    
    private void DisplayAllNotes() {
        String txtNotes = "";
        
        for (Note n : allNotes.getAllNotes()) {
            txtNotes += n.getNote() + "\n";
        }
        
        txtDisplayNotes.setText(txtNotes);
        txtDisplayNotes.setEditable(false);
    }
}