import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExampleThreadPool {
    public static void main(String[] args) throws InterruptedException {
        // создание множества потоков
        ExecutorService executorService = Executors.newFixedThreadPool(2); // 2 потока
        // задание потокам
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Work(i));
        }
        // окончание принятия заданий и начало их выполнения
        executorService.shutdown(); // аналог метода start()
        System.out.println("All tasks submitted");

        // ожидание окончания (указание времени ожидания выполнения потоками всех заданий)
        executorService.awaitTermination(1, TimeUnit.DAYS); // 1 день (аналог метода join())
    }
}
class Work implements Runnable {

    // унификация работ
    private int id;

    public Work(int id) {
        this.id = id;
    }

    // образец выполняемой работы
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Work " + id + " was completed");
    }
}
