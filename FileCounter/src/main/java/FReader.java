import java.io.*;
import java.util.*;

/**
 * This class reads strings from the passed file
 */
public class FReader {
    private final File inputFile;

    public FReader(File file) {
        this.inputFile = file;
    }

    /**
     * Read file line by line and add unique lines to Set
     * @return SET<FileCounter>
     */
    public Set<String> scanFile(){
        Set<String> fileLines = new HashSet<>();
        if( inputFile.exists() ){
            try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
                String line;
                while ((line = reader.readLine()) != null){
                    fileLines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error when reading a file");
                e.printStackTrace();
            }
        }
        return fileLines;
    }
}
