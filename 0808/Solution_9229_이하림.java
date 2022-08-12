import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// SWEA 9229
class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder(); // 출력버퍼
	final int T = toInt(br.readLine()); // 테스트케이스 수
	
	{ // 테스트케이스 진행
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = toInt(st.nextToken()); // 과자 수
			int M = toInt(st.nextToken()); // 무게 제한
			
			// cookies[i] := 과자 무게
			int[] cookies = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
			
			int maxWeight = -1;
			for (int i = 0; i < N - 1; i++) {
				int a = cookies[i];
				if (a > M)
					continue;
				for (int j = i + 1; j < N; j++) {
					int weight = a + cookies[j];
					if (weight > M)
						continue;
					maxWeight = Math.max(maxWeight, weight);
				}
			}
			
			// 결과 저장
			out.append("#" + t + " " + maxWeight + "\n");
		}
		// 결과 출력
		System.out.print(out.toString());
	}
	
	int toInt(String s) {
		return Integer.parseInt(s);
	}
	
	Solution() throws IOException { }
	
	public static void main(String[] args) throws IOException {
		new Solution();
	}
}
