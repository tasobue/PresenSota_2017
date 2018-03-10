import java.io.IOException;
import java.lang.ProcessBuilder;
import java.lang.InterruptedException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class PptControll {

	public void openPptFile(String pptFlNm) {
		try {
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "C:\\Windows\\SysWow64\\cscript.exe", "pptCntrl.vbs", pptFlNm);
			Process process = pb.start();
			
			
			try{
				int ret = process.waitFor();
				System.out.println("戻り値：" + ret);
				InputStream is = process.getInputStream();	//標準出力
				InputStream es = process.getErrorStream();	//標準エラー
				
				System.out.println("printInputStream is start");
				printInputStream(is);
				System.out.println("printInputStream is end");
				
				System.out.println("printInputStream es start");
				printInputStream(es);
				System.out.println("printInputStream es end");
			
			}catch(InterruptedException e){
				e.printStackTrace();
			} finally {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			
		}
		
	}
	
	public void slidePage(String pgNo){
		try {
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "C:\\Windows\\SysWow64\\cscript.exe", "pageCntrl.vbs", pgNo);
			Process process = pb.start();
			
			
			try{
				int ret = process.waitFor();
				System.out.println("戻り値：" + ret);
				
			}catch(InterruptedException e){
				e.printStackTrace();
			} finally {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			
		}
	}
	
	private static void printInputStream(InputStream is) throws IOException {
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