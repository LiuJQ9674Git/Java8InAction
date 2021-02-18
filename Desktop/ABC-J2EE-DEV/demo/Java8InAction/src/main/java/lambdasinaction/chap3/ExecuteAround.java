package lambdasinaction.chap3;

import java.io.*;
import java.net.URL;

public class ExecuteAround {

	public static void main(String ...args) throws IOException{

        // method we want to refactor to make more flexible
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        //函数式编程
		String oneLine = processFile((BufferedReader b) -> b.readLine());
		System.out.println(oneLine);

		String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
		System.out.println(twoLines);

	}

    public static String processFileLimited() throws IOException {
		URL path=ExecuteAround.class.getResource("/lambdasinaction/chap3/data.txt");
		String name=path.getFile();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(name))) {
            return br.readLine();
        }
    }

	//----------------------------函数式编程--------------------------
	public static String processFile(BufferedReaderProcessor p) throws IOException {
		String name=ExecuteAround.class.getResource(
				"/lambdasinaction/chap3/data.txt").getFile();
		try(BufferedReader br =
					new BufferedReader(new FileReader(name))){
			return p.process(br);
		}

	}

	public interface BufferedReaderProcessor{
		public String process(BufferedReader b) throws IOException;

	}
}
