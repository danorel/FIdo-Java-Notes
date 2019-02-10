//package notes.listeners.action;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.util.ArrayList;
//
//public class DeleteMenuItemActionListener implements ActionListener {
//    public void actionPerformed(ActionEvent event) {
//        ArrayList<File> files = getDirectoryFiles();
//        for(File file : files){
//            if((file.getName().substring(0, 1).toUpperCase() + file.getName().substring(1, file.getName().length() - 4)).equals(pane.getTitleAt(pane.getSelectedIndex()))){
//                file.delete();
//            }
//        }
//        pane.remove(pane.getComponentAt(pane.getSelectedIndex()));
//    }
//}