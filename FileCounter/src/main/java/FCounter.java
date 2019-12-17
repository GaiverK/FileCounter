import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.concurrent.Callable;

public class FCounter extends SimpleFileVisitor<Path> implements Callable<CounterState> {
    private CounterState state;

    FCounter(String startPath, CounterState state){
        this.state = state;
        state.setSources(startPath);
    }

    /**
     * Count files until the process is completed or cancelled by escape button
     */
    private void countFiles(){
        try {

            Files.walkFileTree(state.getDecodedSource() , EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>(){

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) {
                    state.incrementFiles();
                    if( KeyboardEscHook.escCommandPressed ) {
                        return FileVisitResult.TERMINATE;
                    }else {
                        return FileVisitResult.CONTINUE;
                    }
                }
            });

        } catch (IOException e) {
            System.err.println("Failure to count files");
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }


    @Override
    public CounterState call(){
        countFiles();
        return this.state;
    }

}
