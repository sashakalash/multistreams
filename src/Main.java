public class Main {

    public static void main(String[] args) throws InterruptedException {
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
}