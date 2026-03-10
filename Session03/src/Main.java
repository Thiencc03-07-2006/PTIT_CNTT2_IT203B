import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Integer> list = Stream.generate(() -> new Random().nextInt(400) - 200).limit(1000).toList();
        list.stream().filter(e -> e > 0).forEach(e -> System.out.print(e + " "));
        System.out.println();
        list.stream().distinct().forEach(e -> System.out.print(e + " "));
        System.out.println();
        list.stream().sorted(Comparator.reverseOrder()).forEach(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println(list.stream().max(Comparator.comparingInt(e -> e)).get());
        System.out.println(list.stream().min(Comparator.comparingInt(e -> e)).get());
        System.out.println(list.stream().reduce(0, Integer::sum));
        System.out.println(list.contains(100));
        list.stream().map(e -> e < 0 ? e * -1 : e).forEach(e -> System.out.print(e + " "));
    }
}