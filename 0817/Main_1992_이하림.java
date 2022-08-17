import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ 1992 쿼드트리
class Main {
	StringBuilder out = new StringBuilder();
{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N = toInt(br.readLine());
	char[][] image = new char[N][];
	for (int i = 0; i < N; i++)
		image[i] = br.readLine().toCharArray();
	zip(0, 0, N, image);
	System.out.print(out.toString());
}

void zip(int beginR, int beginC, int width, char[][] image) {
	int first = image[beginR][beginC];
	boolean complete = true;
	for (int i = 0; i < width; i++) {
		for (int j = 0; j < width; j++) {
			if (image[beginR + i][beginC + j] != first) {
				complete = false;
				break;
			}
		}
	}
	if (complete) {
		out.append(first - 48);
	} else {
		out.append("(");
		int nextWidth = width / 2;
		zip(beginR, beginC, nextWidth, image);
		zip(beginR, beginC + nextWidth, nextWidth, image);
		zip(beginR + nextWidth, beginC, nextWidth, image);
		zip(beginR + nextWidth, beginC + nextWidth, nextWidth, image);
		out.append(")");
	}
}

int toInt(String s) {
	return Integer.parseInt(s);
}

Main() throws IOException { }

public static void main(String[] args) throws IOException {
	new Main();
}}
