import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Predicate<E> {
	boolean test(E e);

	default void foo() {

	}

	static void goo() {

	}
}

public class Sample {
	static List<Integer> filter(List<Integer> data, Predicate<Integer> predicate) {
		List<Integer> result = new ArrayList<>();
		for (Integer e : data) {
			if (predicate.test(e)) {
				result.add(e);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> result = filter(list, (Integer integer) -> {
            return integer % 2 == 1;
        });
        for (Integer e : result) {
            System.out.println(e);
        }

        result = filter(list, (integer) -> integer % 2 == 1);

        for (Integer e : result) {
            System.out.println(e);
        }

	}
}
