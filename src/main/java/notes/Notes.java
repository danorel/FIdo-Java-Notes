package notes;

import file.FileManager;
import notes.frame.NotesFrame;

public class Notes {
    private NotesFrame frame;
    private FileManager fileManager;

    public Notes(){
        frame = new NotesFrame();
        frame.visualize();
    }
}
