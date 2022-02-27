import java.util.LinkedList;
import java.util.List;

public class AlgorithmEngineerTests {

	public static boolean test1() {
		try {
			HashTableSortedSets<Integer, String> map1 = new HashTableSortedSets<Integer, String>();

			map1.add(1, "Tiger");
			map1.add(2, "Lion");
			map1.add(3, "Dog");
			List<String> map1value = map1.get(1);
			if (map1value.get(0).equals("Tiger")) {
				map1value = map1.get(2);
				if (map1value.get(0).equals("Lion")) {
					map1value = map1.get(3);
					if (map1value.get(0).equals("Dog")) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean test2() {
		try {
			HashTableSortedSets<String, String> map1 = new HashTableSortedSets<String, String>();

			map1.add("1", "Tiger");
			map1.add("2", "Lion");
			map1.add("3", "Dog");
			List<String> map1value = map1.get("1");
			if (map1value.get(0).equals("Tiger")) {
				map1value = map1.get("2");
				if (map1value.get(0).equals("Lion")) {
					map1value = map1.get("3");
					if (map1value.get(0).equals("Dog")) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean test3() {
		try {
			HashTableSortedSets<Integer, Integer> map1 = new HashTableSortedSets<Integer, Integer>();

			map1.add(1, 5);
			map1.add(2, 6);
			map1.add(3, 7);
			List<Integer> map1value = map1.get(1);
			if (map1value.get(0).equals(5)) {
				map1value = map1.get(2);
				if (map1value.get(0).equals(6)) {
					map1value = map1.get(3);
					if (map1value.get(0).equals(7)) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean test4() {
		try {
			HashTableSortedSets<Integer, Double> map1 = new HashTableSortedSets<Integer, Double>();

			map1.add(1, 1.0);
			map1.add(2, 2.0);
			map1.add(3, 3.0);
			List<Double> map1value = map1.get(1);
			if (map1value.get(0).equals(1.0)) {
				map1value = map1.get(2);
				if (map1value.get(0).equals(2.0)) {
					map1value = map1.get(3);
					if (map1value.get(0).equals(3.0)) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean test5() {
		try {
			HashTableSortedSets<Double, String> map1 = new HashTableSortedSets<Double, String>();

			map1.add(1.0, "Tiger");
			map1.add(2.0, "Lion");
			map1.add(3.0, "Dog");
			List<String> map1value = map1.get(1.0);
			if (map1value.get(0).equals("Tiger")) {
				map1value = map1.get(2.0);
				if (map1value.get(0).equals("Lion")) {
					map1value = map1.get(3.0);
					if (map1value.get(0).equals("Dog")) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static void main(String[] args) {
		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
		System.out.println(test4());
		System.out.println(test5());
	}

}
