import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class keeps the count status of the files, source path and id
 */
public class CounterState {
    private int id;
    private Path source;
    private Path decodedSource;
    private long files;
    private static int idCounter = 1;
    private StringDecoder decoder;

    /**
     * Setting id when the class is initialized
     * @param decoder - Decode to UTF-8
     */
    public CounterState(StringDecoder decoder) {
        this.id = idCounter++;
        this.decoder = decoder;
    }

    /**
     * Set source and decode it for program
     * @param source - Path string for counting files
     */
    public void setSources(String source){
        this.source = Paths.get(source);
        try {
            this.decodedSource = Paths.get(decoder.decodeString(source));
        } catch (IOException ignore) {
            this.decodedSource = this.source;
        }
    }

    public int getId() {
        return id;
    }

    public Path getSource() {
        return source;
    }

    public Path getDecodedSource() {
        return decodedSource;
    }

    public long getFiles() {
        return files;
    }

    public void incrementFiles() {
        this.files++;
    }
}
