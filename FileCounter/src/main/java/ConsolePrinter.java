public class ConsolePrinter {
    public void print(CounterState data){
        System.out.printf("%-5d %-10d %s\n",
                data.getId(),
                data.getFiles(),
                data.getDecodedSource());
    }
}
