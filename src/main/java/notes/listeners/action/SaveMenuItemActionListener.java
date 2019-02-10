//package notes.listeners.action;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class SaveMenuItemActionListener implements ActionListener {
//    public void actionPerformed(ActionEvent event) {
//        BufferedWriter writer;
//        ArrayList<File> files = getDirectoryFiles();
//        boolean fileNotExists = true;
//        File existingFile = null;
//        for (File file : files) {
//            if ((file.getName().substring(0, 1).toUpperCase() + file.getName().substring(1, file.getName().length() - 4)).equals(pane.getTitleAt(pane.getSelectedIndex()))) {
//                fileNotExists = false;
//                existingFile = file;
//                break;
//            }
//        }
//        if(fileNotExists) {
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setCurrentDirectory(new File("data"));
//            if (fileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
//                File file = fileChooser.getSelectedFile();
//                try {
//                    file.createNewFile();
//                    writer = new BufferedWriter(new FileWriter(file.getPath()));
//                    writer.write(area.getText());
//                    pane.setTitleAt(pane.getSelectedIndex(), file.getName().substring(0, 1).toUpperCase() + file.getName().substring(1, file.getName().length() - 4));
//                    ((JTextArea) pane.getComponentAt(pane.getSelectedIndex())).setEditable(false);
//                    writer.close();
//                } catch (IOException exception) {
//                    exception.printStackTrace();
//                }
//            }
//        } else {
//            try {
//                writer = new BufferedWriter(new FileWriter(existingFile.getPath()));
//                area = (JTextArea) pane.getComponentAt(pane.getSelectedIndex());
//                writer.write(area.getText());
//                area.setEditable(false);
//                writer.close();
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//    }
//}