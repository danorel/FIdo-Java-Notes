package notes.listeners.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseMenuItemActionListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.exit(0);
    }
}