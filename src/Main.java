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
public class Main {
    public static void main(String[] args) {
//        Counter counter = new Counter(
//                                        IntStream.of(1, 1, -3, 1, -5),
//                                        IntStream.of(1, -7, 1, -9),
//                                        IntStream.of(10, -11, -12)
//        );
//        counter.countSum();
        CounterWithFileResources counter = new CounterWithFileResources(
                new FileResource("file1.txt"),
                new FileResource("file2.txt")
        );
        counter.countSum();
    }
}
