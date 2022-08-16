import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 정올 1828 냉장고
class Main {{
	StringTokenizer st;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N = toInt(br.readLine());
	// chemicals[i] := (최저보관온도, 최고보관온도)
	int[][] chemicals = new int[N][];
	for (int i = 0; i < N; i++) {
		st = new StringTokenizer(br.readLine());
		chemicals[i] = new int[] {
				toInt(st.nextToken()),
				toInt(st.nextToken())
		};
	}
	Arrays.sort(chemicals, (a, b) -> a[0] - b[0]);
	// upper bound
	int prevUB = chemicals[0][1];
	int count = 1;
	for (int i = 1; i < N; i++) {
		int lb = chemicals[i][0], ub = chemicals[i][1];
		// 기존 냉장고에 담을 수 있다.
		if (lb <= prevUB) {
			if (prevUB > ub)
				prevUB = ub;
		} else { // 새로 시작
			count += 1;
			prevUB = ub;
		}
	}
	System.out.print(count);
}
	
int toInt(String s) {
	return Integer.parseInt(s);
}

Main() throws IOException { }

public static void main(String[] args) throws IOException {
	new Main();
}}
