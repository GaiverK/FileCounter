import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FWriter {
    private Path destinationPath;
    private boolean canWrite = true;

    public FWriter(Path destinationPath) {
        this.destinationPath = destinationPath;
    }

    /**
     * Clear the file before recording if exist, otherwise create empty file
     */
    public void prepareFile(){
        String path = destinationPath.toString();
        if (path.indexOf(".") > 0) {
            path = path.substring(0, path.lastIndexOf(".")) + ".csv";
        }
        try {
            new FileWriter(path).close();
        } catch (IOException e) {
            canWrite = false;
            System.err.println("File cleaning error");
            System.err.println("Please close all programs using the output file");
            e.printStackTrace();
        }
        destinationPath = Paths.get(path);
    }

    /**
     * Write data to csv file
     * @param data - Current state of FCounter
     */
    public void write(CounterState data){
        if(canWrite){
            String[] csvData = new String[]{data.getDecodedSource().toString(), String.valueOf(data.getFiles())  };
            try (FileWriter fw = new FileWriter(destinationPath.toString(), true)) {
                fw.write(String.join(";", csvData));
                fw.write("\r\n");
            } catch (IOException e) {
                System.err.println("Error when writing a file");
                e.printStackTrace();
            }
        }
    }

}
