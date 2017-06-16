import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by pavel on 16.06.17.
 *
 * Необходимо разработать программу, которая получает
 * на вход список ресурсов, содержащих набор чисел и
 * считает сумму всех положительных четных. Каждый ресурс
 * должен быть обработан в отдельном потоке, набор должен
 * содержать лишь числа, унарный оператор "-" и пробелы.
 * Общая сумма должна отображаться на экране и изменяться
 * в режиме реального времени.
 * Запуск потоков реализовать через ссылки на методы,
 * итоговый подсчет суммы через stream API
 */
public class CounterWithFileResources {

    private FileResource[] resources;
    private int sum;

    public CounterWithFileResources(FileResource... resources) {
        this.resources = resources;
    }

    public void countSum() {
        Arrays.stream(resources).forEach(this::processResource);
    }

    private void processResource(FileResource resource) {
        Thread thread = new Thread(() -> resource.getStream().filter(v -> v > 0).forEach(
                this::increaseSumAndPrint));
        thread.start();
    }

    private synchronized void increaseSumAndPrint(int value) {
        sum += value;
        System.out.println(sum);
    }
}

