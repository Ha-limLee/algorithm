import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// SWEA 7465 창용 마을 무리의 수
class Solution {
	static Util ut = new Util();
	static class Util {
		int toInt(String s) {
			return Integer.parseInt(s);
		}
		int length = 0;
		private int _find(int a, int[] people) {
			this.length += 1;
			int parent = people[a];
			if (parent == a) {
				return parent;
			}
			
			return people[a] = _find(parent, people);
		}
		// return {root, length}
		int[] find(int a, int[] people) {
			this.length = 0;
			return new int[] {_find(a, people), length};
		}
		
		void union(int a, int b, int[] people) {
			// {root, length}
			int[] setA = find(a, people);
			int[] setB = find(b, people);
			// 집합 A에 집합 B를 붙인다.
			if (setA[1] > setB[1]) {
				people[setB[0]] = setA[0];
			} else {
				people[setA[0]] = setB[0]; 
			}
		}
	}
	public static void main(String[] args) throws IOException {
		StringBuilder out = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = ut.toInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = ut.toInt(st.nextToken());
			int M = ut.toInt(st.nextToken());
			int[] people = new int[N + 1];
			for (int i = 1; i <= N; i++)
				people[i] = i;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = ut.toInt(st.nextToken());
				int b = ut.toInt(st.nextToken());
				ut.union(a, b, people);
			}
			Set<Integer> s = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				int[] root = ut.find(i, people);
				s.add(root[0]);
			}
			out.append("#").append(t).append(" ").append(s.size()).append("\n");
		}
		System.out.print(out.toString());
	}
}
