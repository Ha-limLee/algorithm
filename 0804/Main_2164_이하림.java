import java.io.IOException;
import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

// BOJ 2164 카드2
public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		Deque<Integer> d = new ArrayDeque<>();
		
		for (int i = N; i >= 1; i--)
			d.addLast(i);
		
		while (d.size() != 1) {
			d.removeLast();
			d.addFirst(d.removeLast());
		}
		
		System.out.println(d.peek());
	}
}
