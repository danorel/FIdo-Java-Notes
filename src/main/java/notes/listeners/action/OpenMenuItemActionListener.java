//package notes.listeners.action;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//
//public class OpenMenuItemActionListener implements ActionListener {
//    public void actionPerformed(ActionEvent event) {
//        JFileChooser fileChooser = new JFileChooser();
//        Frame innerFrame = new JFrame();
//        innerFrame.setLocationRelativeTo(frame);
//        if (fileChooser.showOpenDialog(innerFrame) == JFileChooser.APPROVE_OPTION) {
//            File file = fileChooser.getSelectedFile();
//            try {
//                BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
//                String line;
//                String content = "";
//                while((line = reader.readLine()) != null){
//                    content += line + "\n";
//                }
//                area = new JTextArea(areaRows, areaColumns);
//                area.append(content);
//                area.setBackground(new Color(0xB2C0BE));
//                pane.add("Note", area);
//                reader.close();
//            } catch (FileNotFoundException exception) {
//                exception.printStackTrace();
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//    }
//}