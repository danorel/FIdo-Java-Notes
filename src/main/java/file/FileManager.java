package file;

public interface FileManager {
    void createFile(String src);
    String readFile(String src);
    void writeFile(String src, String content);
}
