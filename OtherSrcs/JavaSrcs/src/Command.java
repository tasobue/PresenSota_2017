import java.lang.Runtime;
import java.io.IOException;
import java.lang.InterruptedException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Command {
    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec("cd /d dir");
        	
        	
	        	int ret = p.waitFor();
	        	
	        	InputStream is = p.getInputStream();
				InputStream es = p.getErrorStream();	//ïWèÄÉGÉâÅ[
					
				System.out.println("printInputStream is start");
				printInputStream(is);
				System.out.println("printInputStream is end");
					
				System.out.println("printInputStream es start");
				printInputStream(es);
				System.out.println("printInputStream es end");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	
	public static void printInputStream(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			for (;;) {
				String line = br.readLine();
				if (line == null) break;
				System.out.println(line);
			}
		} finally {
			br.close();
		}
	}
}