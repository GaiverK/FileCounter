import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        int taskCounter = 0;
        File source;
        File destination;
        StringDecoder decoder = new StringDecoder();

        if( args != null && args.length == 2 ){

            source = new File(args[0]);
            destination = new File(args[1]);
            FWriter fWriter = new FWriter(destination.toPath());
            fWriter.prepareFile();
            ConsolePrinter consolePrinter = new ConsolePrinter();

            if( source != null && source.exists() && destination != null && destination.exists() ){
                KeyboardEscHook keyboardEscHook = new KeyboardEscHook();
                keyboardEscHook.setEscHook();

                Collection<String> lines = new FReader(source).scanFile();
                Collection<FCounter> tasks = new ArrayList<>(lines.size());

                lines.forEach(line->{
                    tasks.add(new FCounter(line, new CounterState(decoder)));
                });

                ExecutorService executor = Executors.newFixedThreadPool(10);
                ExecutorCompletionService<CounterState> completionService = new ExecutorCompletionService<>(executor);

                tasks
                    .forEach(completionService::submit);

                taskCounter = tasks.size();

                while(taskCounter-- > 0 && !KeyboardEscHook.escCommandPressed){
                    try{
                        Future<CounterState> future = completionService.take();
                        CounterState res = future.get();
                        consolePrinter.print(res);
                        fWriter.write(res);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                executor.shutdown();
                keyboardEscHook.shutDownHook();
                System.out.println(KeyboardEscHook.escCommandPressed ? "Process was interrupted" : "Process is complete");
            }else{
                throw new IllegalArgumentException("Invalid arguments!");
            }
        }else{
            throw new IllegalArgumentException("Please restart app and pass two arguments [to the source] and [the result file]");
        }
    }
}
