package notes.frame;

import notes.listeners.action.ListenerConstants;
import notes.menu.MenuGenerator;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class NotesFrame extends JFrame {
    // Frame width and Frame height
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    // Pointer to the frame object
    private JFrame frame = this;

    // Frame panels as containers of the content and tabbed pane
    private JPanel main;

    // Text area for writing there some notes
    private JTextArea area;
    private final int areaColumns = 37;
    private final int areaRows = 23;

    private JTabbedPane pane;

    public NotesFrame(){
        this.setTitle("Dan's notes.Notes");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void visualize(){

        // Menu bar and embedded notes.menu items initialization
        JMenuBar menuBar = constructMenuBar();

        // Main panel initialization
        main = new JPanel();
        // Tabbed pane initialization
        pane = new JTabbedPane();
        checkForExistingNotes();
        // Adding the tabbed pane to the main panel
        main.add(pane, BorderLayout.CENTER);
        // Adding the panels and notes.menu to the frame
        this.add(main, BorderLayout.CENTER);
        this.add(menuBar, BorderLayout.NORTH);
        this.setVisible(true);
    }

    private ArrayList<File> getDirectoryFiles(){
        File directoryContent = new File("data");
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(directoryContent.listFiles()));
        return files;
    }

    private boolean checkForExistingNotes(){
        ArrayList<File> files = getDirectoryFiles();
        if(files.size() != 0){
            String content = "";
            for(File file : files){
                if(file.getName().substring(file.getName().length() - 3).equals("txt")){
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
                        String line;
                        while((line = reader.readLine()) != null){
                            content += line + "\n";
                        }
                    } catch (FileNotFoundException exception) {
                        exception.printStackTrace();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    area = new JTextArea(areaRows, areaColumns);
                    area.setBackground(new Color(0xB2C0BE));
                    area.append(content);
                    pane.add(Character.toUpperCase(file.getName().charAt(0)) + file.getName().substring(1, file.getName().length() - 4), area);
                }
                content = "";
            }
            return true;
        }
        return false;
    }

    private JMenuBar constructMenuBar(){
        MenuGenerator menuGenerator = new MenuGenerator();
        menuGenerator
                .defineMenus(Arrays.asList("Preferences", "Tools"))
                .defineMenuItemTo("Preferences", "Close")
                .defineMenuItemTo("Tools", "Open")
                .defineMenuItemTo("Tools", "Save")
                .defineMenuItemTo("Tools", "Create")
                .addActionListener("Save", ListenerConstants.SAVE)
                .addActionListener("Close", ListenerConstants.CLOSE)
                .addActionListener("Open", ListenerConstants.OPEN)
                .addActionListener("Create", ListenerConstants.CREATE)
                .generate();
        return menuGenerator.getMenuBar();
    }
}