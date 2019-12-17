import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * This class decode string to UTF-8 for work with cyrillic dirs
 */
public class StringDecoder {

    /**
     * Decode string to UTF-8
     * @param input - String with path
     * @return - Decoded string
     * @throws IOException
     */
    String decodeString(String input) throws IOException {
        return
        new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(input.getBytes()),
                        StandardCharsets.UTF_8))
                .readLine();
    }
}
