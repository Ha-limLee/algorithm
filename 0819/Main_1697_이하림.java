import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main { // BOJ 1697 숨바꼭질
int solve(int subin, int bro) {
	if (subin == bro)
		return 0;
	
	int max = Math.max(subin, bro) * 2 + 1;
	int[] dp = new int[max];
	
	Deque<Integer> q = new ArrayDeque<>();
	
	dp[subin] = 0;
	q.add(subin);
	int count = 0;
	while (!q.isEmpty()) {
		count += 1;
		int size = q.size();
		while (size != 0) {
			size -= 1;
			int curr = q.pop();
			int[] next = {curr * 2, curr + 1, curr - 1};
			for (int n : next) {
				if (valid(n, dp)) {
					dp[n] = count;
					q.offer(n);
				}
			}
		}
	}
	return dp[bro];
}

boolean valid(int idx, int[] arr) {
	if (inbound(idx, arr) && arr[idx] == 0)
		return true;
	return false;
}

boolean inbound(int idx, int[] arr) {
	int N = arr.length;
	if (0 <= idx && idx < N)
		return true;
	return false;
}

public static void main(String[] args) {
	Main m = new Main();
	Scanner scan = new Scanner(System.in);
	int subin = scan.nextInt(), bro = scan.nextInt();
	int ret = m.solve(subin, bro);
	System.out.print(ret);
}}
