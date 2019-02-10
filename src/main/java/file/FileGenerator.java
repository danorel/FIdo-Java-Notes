package file;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileGenerator implements FileManager {
    private String position = "";

    public void createFile(String src) {
        List<String> path = Arrays.asList(src.split("/"));
        path.stream()
                .forEach(part -> {
                    try {
                        if(part.equals(path.get(path.size() - 1))) {
                            File element = new File(position + "/" +part);
                            element.createNewFile();
                        } else {
                            position += part + "/";
                            File element = new File(position.substring(0, position.length() - 1));
                            element.mkdir();
                        }
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                });
    }

    public String readFile(String src) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(src));
            String line;
            while((line = reader.readLine()) != null){
                content += line + "\n";
            }
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return content;
    }

    public void writeFile(String src, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(src));
            writer.write(content);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
