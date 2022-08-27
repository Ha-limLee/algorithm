import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// SWEA 1238 Contact
class Solution {
	static Util ut = new Util();
	static class Util {
		int toInt(String s) {
			return Integer.parseInt(s);
		}
		int solve(int begin, Node[] people) {
			boolean[] visited = new boolean[people.length];
			Deque<Integer> q = new ArrayDeque<>();
			q.offer(begin);
			visited[begin] = true;
			int ret = 0;
			while (!q.isEmpty()) {
				int size = q.size();
				int maxVal = -1;
				while (size != 0) {
					int curr = q.poll();
					maxVal = Math.max(maxVal, curr);
					for (Node t = people[curr]; t != null; t = t.next) {
						if (!visited[t.val]) {
							q.offer(t.val);
							visited[t.val] = true;
						}
					}
					size -= 1;
				}
				ret = maxVal;
			}
			return ret;
		}
	}
	static class Node {
		int val;
		Node next;
		Node(int val, Node n) {
			this.val = val;
			next = n;
		}
	}
	public static void main(String[] args) throws IOException {
		StringBuilder out = new StringBuilder();
		final int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			Node[] people = new Node[101];
			st = new StringTokenizer(br.readLine());
			int N = ut.toInt(st.nextToken());
			int begin = ut.toInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int from = ut.toInt(st.nextToken());
				int to = ut.toInt(st.nextToken());
				people[from] = new Node(to, people[from]);
			}
			int answer = ut.solve(begin, people);
			out.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.print(out.toString());
	}
}
