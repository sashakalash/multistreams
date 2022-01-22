import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static final int ITERATION_LIMIT_VALUE = 5;
    public static final int THREADS_QUANTITY_VALUE = 3;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        threads();
        callable();
    }

    public static void threads() throws InterruptedException {
        ThreadGroup myGroup = new ThreadGroup("myGroup");

        MyThread thread1 = new MyThread();
        thread1.setName("Поток 1");
        MyThread thread2 = new MyThread();
        thread2.setName("Поток 2");
        MyThread thread3 = new MyThread();
        thread3.setName("Поток 3");
        MyThread thread4 = new MyThread();
        thread4.setName("Поток 4");

        Thread commonThread1 = new Thread(myGroup, thread1);
        Thread commonThread2 = new Thread(myGroup, thread2);
        Thread commonThread3 = new Thread(myGroup, thread3);
        Thread commonThread4 = new Thread(myGroup, thread4);

        commonThread1.start();
        commonThread2.start();
        commonThread3.start();
        commonThread4.start();

        thread1.sleep(15000);

        myGroup.interrupt();
    }

    public static void callable() throws ExecutionException, InterruptedException {
        double frequency = 0.5;
        final MyCallable myCallable1 = new MyCallable(ITERATION_LIMIT_VALUE, frequency);
        frequency = 1;
        final MyCallable myCallable2 = new MyCallable(ITERATION_LIMIT_VALUE, frequency);
        frequency = 1.5;
        final MyCallable myCallable3 = new MyCallable(ITERATION_LIMIT_VALUE, frequency);

        final ExecutorService executorService = Executors.newFixedThreadPool(THREADS_QUANTITY_VALUE);

        List<Callable<Integer>> callablesList = List.of(
                myCallable1,
                myCallable2,
                myCallable3
        );

        List<Future<Integer>> callables = null;
        // Раскоментить для теста одного потока
        Integer firsrtCallable = null;

        try {
            callables = executorService.invokeAll(callablesList);
        // Раскоментить для теста одного потока
        // firsrtCallable = executorService.invokeAny(callablesList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        System.out.println("\n========Printing the results======");

       //Закоментить для теста одного потока
        int res = 0;
        for (Future<Integer> task : callables) {
            try {
                res += task.get();
            } catch (ExecutionException | InterruptedException e) {
                throw e;
            }
        }
        System.out.println(String.format("Потоки завершились за %d итераций", res));

        /** Раскоментить для теста одного потока
        System.out.println(String.format("Поток завершились за %d итераций", firsrtCallable));
        */

    }
}