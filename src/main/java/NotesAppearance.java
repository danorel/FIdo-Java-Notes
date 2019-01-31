import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class NotesAppearance extends JFrame {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    private JFrame frame = this;
    // Frame panels as containers
    private JPanel main;

    // Menu bar items and menu bar itself
    private JMenuBar menu;
    private JMenu notes;
    private JMenuItem preferences;
    private JMenu tools;
    private JMenuItem close;
    private JMenuItem create;
    private JMenuItem save;
    private JMenuItem edit;
    private JMenuItem rename;
    private JMenuItem open;

    // Text area for writing there some notes
    private JTextArea area;
    private final int areaColumns = 37;
    private final int areaRows = 23;

    private JTabbedPane pane;

    public NotesAppearance(){
        this.setTitle("Dan's Notes");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void visualize(){
        // Menu bar and embedded menu items initialization
        menu = generateMenuBar();
        // Main panel initialization
        main = new JPanel();
        // Tabbed pane initialization
        pane = new JTabbedPane();
        checkForExistingNotes();
        // Adding the tabbed pane to the main panel
        main.add(pane, BorderLayout.CENTER);
        // Adding the panels and menu to the frame
        this.add(main, BorderLayout.CENTER);
        this.add(menu, BorderLayout.NORTH);
        this.setVisible(true);
    }

    private JMenuBar generateMenuBar(){
        JMenuBar temporaryBar = new JMenuBar();
        notes = new JMenu("Notes");
        tools = new JMenu("Tools");
        save = new JMenuItem("Save");
        save.addActionListener(new SaveMenuItemActionListener());
        edit = new JMenuItem("Edit");
        edit.addActionListener(new EditMenuItemActionListener());
        close = new JMenuItem("Close");
        close.addActionListener(new CloseMenuItemActionListener());
        preferences = new JMenuItem("Preferences");
        create = new JMenuItem("Create");
        create.addActionListener(new CreateMenuItemActionListener());
        rename = new JMenuItem("Rename");
        rename.addActionListener(new RenameMenuItemActionListener());
        open = new JMenuItem("Open");
        open.addActionListener(new OpenMenuItemActionListener());
        temporaryBar.add(notes);
        temporaryBar.add(tools);
        notes.add(preferences);
        notes.add(close);
        tools.add(open);
        tools.add(create);
        tools.add(rename);
        tools.add(save);
        tools.add(edit);
        return temporaryBar;
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

    private class CreateMenuItemActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            area = new JTextArea(areaRows, areaColumns);
            area.setBackground(new Color(0xB2C0BE));
            pane.add("Note", area);
        }
    }

    private class SaveMenuItemActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            BufferedWriter writer;
            ArrayList<File> files = getDirectoryFiles();
            boolean fileNotExists = true;
            File existingFile = null;
            for (File file : files) {
                if ((file.getName().substring(0, 1).toUpperCase() + file.getName().substring(1, file.getName().length() - 4)).equals(pane.getTitleAt(pane.getSelectedIndex()))) {
                    fileNotExists = false;
                    existingFile = file;
                    break;
                }
            }
            if(fileNotExists) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        file.createNewFile();
                        writer = new BufferedWriter(new FileWriter(file.getPath()));
                        writer.write(area.getText());
                        ((JTextArea) pane.getComponentAt(pane.getSelectedIndex())).setEditable(false);
                        writer.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            } else {
                try {
                    writer = new BufferedWriter(new FileWriter(existingFile.getPath()));
                    area = (JTextArea) pane.getComponentAt(pane.getSelectedIndex());
                    writer.write(area.getText());
                    area.setEditable(false);
                    writer.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private class CloseMenuItemActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    private class EditMenuItemActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            ((JTextArea) pane.getComponentAt(pane.getSelectedIndex())).setEditable(true);
        }
    }

    private class RenameMenuItemActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
//            pane.setTitleAt(pane.getSelectedIndex(), );
        }
    }

    private class OpenMenuItemActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JFileChooser fileChooser = new JFileChooser();
            Frame innerFrame = new JFrame();
            innerFrame.setLocationRelativeTo(frame);
            if (fileChooser.showOpenDialog(innerFrame) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
                    String line;
                    String content = "";
                    while((line = reader.readLine()) != null){
                        content += line + "\n";
                    }
                    area = new JTextArea(areaRows, areaColumns);
                    area.append(content);
                    area.setBackground(new Color(0xB2C0BE));
                    pane.add("Note", area);
                    reader.close();
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}