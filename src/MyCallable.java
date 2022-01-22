import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    private final int MILLISECONDS_VALUE = 1000;
    private int limit;
    private double frequency;

    public MyCallable(int limit, double frequency) {
        this.limit = limit;
        this.frequency = frequency;
    }

    @Override
    public Object call() throws Exception {
        int count = 0;
        while(this.limit != 0) {
            Thread.sleep((long)frequency * MILLISECONDS_VALUE);
            System.out.println(String.format("Я %s! Всем привет!", getName()));
            this.limit--;
            count++;
        }
        return count;
    }

    public String getName() {
        return Thread.currentThread().getName();
    }
}