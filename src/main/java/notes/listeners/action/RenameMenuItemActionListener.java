//package notes.listeners.action;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.util.ArrayList;
//
//public class RenameMenuItemActionListener implements ActionListener {
//    public void actionPerformed(ActionEvent event) {
//        String title = JOptionPane.showInputDialog("Rename the selected Note");
//        boolean fileNotExists = true;
//        ArrayList<File> files = getDirectoryFiles();
//        File existingFile = null;
//        for(File file : files){
//            if((file.getName().substring(0, 1).toUpperCase() + file.getName().substring(1, file.getName().length() - 4)).equals(pane.getTitleAt(pane.getSelectedIndex()))){
//                existingFile = file;
//                fileNotExists = false;
//            }
//        }
//        if(!fileNotExists){
//            try {
//                String content = "", line;
//                BufferedReader reader = new BufferedReader(new FileReader(existingFile.getPath()));
//                while((line = reader.readLine()) != null){
//                    content += line + "\n";
//                }
//                existingFile = new File("data/" + title);
//                BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + title));
//                writer.write(content);
//                writer.close();
//                reader.close();
//            } catch (FileNotFoundException exception) {
//                exception.printStackTrace();
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//        pane.setTitleAt(pane.getSelectedIndex(), title);
//    }
//}