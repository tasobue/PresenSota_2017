import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class EchoServer {
	
    public static final int ECHO_PORT = 10007;
	public static final String PPT_NAME = "presen.pptx";
	public static final String GENKO_NAME = "genko.csv";
	public static final String WAIT_FILE_NAME = "wait.txt";
	public static final String WAIT_MESSAGE = "wait";
	public static final String END_MESSAGE = "end";
	
    public static void main(String args[]) {
    	
	    ServerSocket serverSocket = null;
	    Socket socket = null;
	    MessageClass msgCls = new MessageClass();
	    PptControll ppt = new PptControll();
    	
    	//原稿ファイルを読み込む
    	msgCls.readGenkoFile(GENKO_NAME);
	    	
	    try {
	        serverSocket = new ServerSocket(ECHO_PORT);
	        System.out.println("EchoServerが起動しました(port=" + serverSocket.getLocalPort() + ")");
	        socket = serverSocket.accept();
	        System.out.println("接続されました " + socket.getRemoteSocketAddress() );
	    	
	        BufferedReader rcv = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        PrintWriter snd = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF8"), true);
	    	
	        String rcvSeq;
	    	String strMsgs;			//メッセージクラスからの戻り値
	    	String[] strMsgsArr;	//メッセージクラスからの戻り値をカンマ区切りで分割
	    	String spchText;		//Sotaが話す内容
	        String sendMsgUtf8;		//Sotaが話す内容をUTF-8でエンコード
	    	String strMotionCd;		//Sotaの動作を指定する番号
	    	String strPgNo;			//PPTのページ番号
	    	
	        while ( (rcvSeq = rcv.readLine()) != null ) {
	            System.out.println("000");
	            System.out.println(rcvSeq);
	        	
	        	if(msgCls.isServerWait(WAIT_FILE_NAME)){
	        		//サーバ待機状態の場合
	        		snd.println(WAIT_MESSAGE);
	        	}else{
	        	
		        	//メッセージの取得
		            strMsgs = msgCls.getMsgFrmCsv(Integer.parseInt(rcvSeq));
		        	
		        	if(END_MESSAGE.equals(strMsgs)){
		        		//Sotaに終了処理の合図を送る
		        		spchText = strMsgs;
		        		snd.println(spchText);
		        	}else{
		        	
			        	strMsgsArr = strMsgs.split(",", 0);
			        	strPgNo = strMsgsArr[1]; //ページ番号
			        	spchText = strMsgsArr[2];//Sotaが話す内容
			        	strMotionCd = strMsgsArr[3];//Sotaの動作を指定する番号
			        	
			        	//pptを操作
			        	if("1".equals(rcvSeq)){
			        		System.out.println("ppt.openPptFile");
			        		ppt.openPptFile(PPT_NAME);
			        	}else{
			        		System.out.println("ppt.slidePage");
			        		ppt.slidePage(strPgNo);
			        	}
			        	
			        	//クライアントにメッセージ送信
			            snd.println(spchText);
			            System.out.println("001");
		        	
		        	}
	        	}
	        }
	    	
	    	
	        System.out.println("002");
	    } catch (Exception e) {
	        e.printStackTrace();
	    
	    } finally {
	        try {
	            if (socket != null) {
	            System.out.println("009");
	                socket.close();
	            }
	        } catch (IOException e) {
	        }
	        try {
	            if (serverSocket != null) {
	                serverSocket.close();
	            }
	        } catch (IOException e) {
	        }
	    }
	}
}