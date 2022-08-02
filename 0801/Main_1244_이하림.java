import java.util.Optional;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

class Solve {
	static final BufferedReader br;
	static final BufferedWriter bw;
	static {
		br = Optional.of(System.in)
					.map(InputStreamReader::new)
					.map(BufferedReader::new)
					.get();
		bw = Optional.of(System.out)
					.map(OutputStreamWriter::new)
					.map(BufferedWriter::new)
					.get();
	}
	static int[] toIntArr(String s) {
		return Optional.of(s)
				.map(x -> x.split(" "))
				.map(Arrays::stream)
				.get()
				.mapToInt(Integer::parseInt)
				.toArray();
	}
	static void toggle(int[] swtch, int pos) {
		if (swtch[pos] == 0)
			swtch[pos] = 1;
		else
			swtch[pos] = 0;
	}
	static int[] parallel(int[] swtch, int pos) {
		int i = 1;
		for (i = 1; (pos - i >= 0 && pos + i < swtch.length) ; i++) {
			if (swtch[pos - i] != swtch[pos + i]) {
				break;
			}
		}
		i -= 1;
		int lbound = pos - i, rbound = pos + i;
		return new int[] {lbound, rbound};
	}
	static String group20(int[] swtch) {
		int i = 0;
		StringBuilder ret = new StringBuilder();
		while(i < swtch.length) {
			StringBuilder sb = new StringBuilder();
			int k;
			for (k = 0; i + k < swtch.length && k < 20; k++) {
				sb.append(swtch[i + k] + " ");
			}
			i += k;
			String s = sb.toString().trim();
			ret.append(s)
				.append("\n");
		}
		return ret.toString().trim();
	}
	public static void main(String[] args) throws Exception {
		final int MAN = 1, WOMAN = 2;
		br.readLine(); // drop first input
		int[] swtch = Optional.of(br.readLine())
							.map(Solve::toIntArr)
							.get();
		// the number of students
		int M = Optional.of(br.readLine())
						.map(Integer::parseInt)
						.get();
		for (int m = 0; m < M; m++) {
			int[] student = Optional.of(br.readLine())
									.map(Solve::toIntArr)
									.get();
			int sex = student[0], pos = student[1];
			if (sex == MAN) {
				int k = pos;
				for (int i = k - 1; i < swtch.length; i += k)
					toggle(swtch, i);
			} else {
				int[] bound = parallel(swtch, pos - 1);
				int lbound = bound[0], rbound = bound[1];
				for (int i = lbound; i <= rbound; i++) {
					toggle(swtch, i);
				}	
			}
		}

		bw.write(group20(swtch));
		bw.close();
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		Solve.main(args);
	}
}
