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
public class Counter {

    private IntStream[] streams;
    private int sum;

    public Counter(IntStream... streams) {
        this.streams = streams;
    }

    public void countSum() {
        Arrays.stream(streams).forEach(this::processIntStream);
    }

    private void processIntStream(IntStream intStream) {
        Thread thread = new Thread(() -> intStream.filter(v -> v > 0).forEach(
                this::increaseSumAndPrint));
        thread.start();
    }

    private synchronized void increaseSumAndPrint(int value) {
        sum += value;
        System.out.println(sum);
    }
}
