import com.sun.javafx.scene.control.skin.CustomColorDialog;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    private JTextArea notesArea;
    private final int notesAreaWidth = 37;
    private final int notesAreaHeight = 23;

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
        save.addActionListener(new EditMenuItemActionListener());
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

    private class CreateMenuItemActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            notesArea = new JTextArea(notesAreaHeight, notesAreaWidth);
            notesArea.setBackground(new Color(0xB2C0BE));
            pane.add("Note", notesArea);
        }
    }

    private class SaveMenuItemActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    file.createNewFile();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
                    notesArea = (JTextArea) pane.getComponentAt(pane.getSelectedIndex());
                    notesArea.setEditable(false);
                    writer.write(notesArea.getText());
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
            pane.getComponentAt(pane.getSelectedIndex()).setEnabled(true);
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
                    notesArea = new JTextArea(notesAreaHeight, notesAreaWidth);
                    notesArea.append(content);
                    notesArea.setBackground(new Color(0xB2C0BE));
                    pane.add("Note", notesArea);
                    reader.close();
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private class ToolsMenuKeyListener implements MenuKeyListener {

        public void menuKeyTyped(MenuKeyEvent event) {

        }

        public void menuKeyPressed(MenuKeyEvent event) {

        }

        public void menuKeyReleased(MenuKeyEvent event) {

        }
    }
}
