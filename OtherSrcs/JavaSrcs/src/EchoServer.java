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
    	
    	//���e�t�@�C����ǂݍ���
    	msgCls.readGenkoFile(GENKO_NAME);
	    	
	    try {
	        serverSocket = new ServerSocket(ECHO_PORT);
	        System.out.println("EchoServer���N�����܂���(port=" + serverSocket.getLocalPort() + ")");
	        socket = serverSocket.accept();
	        System.out.println("�ڑ�����܂��� " + socket.getRemoteSocketAddress() );
	    	
	        BufferedReader rcv = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        PrintWriter snd = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF8"), true);
	    	
	        String rcvSeq;
	    	String strMsgs;			//���b�Z�[�W�N���X����̖߂�l
	    	String[] strMsgsArr;	//���b�Z�[�W�N���X����̖߂�l���J���}��؂�ŕ���
	    	String spchText;		//Sota���b�����e
	        String sendMsgUtf8;		//Sota���b�����e��UTF-8�ŃG���R�[�h
	    	String strMotionCd;		//Sota�̓�����w�肷��ԍ�
	    	String strPgNo;			//PPT�̃y�[�W�ԍ�
	    	
	        while ( (rcvSeq = rcv.readLine()) != null ) {
	            System.out.println("000");
	            System.out.println(rcvSeq);
	        	
	        	if(msgCls.isServerWait(WAIT_FILE_NAME)){
	        		//�T�[�o�ҋ@��Ԃ̏ꍇ
	        		snd.println(WAIT_MESSAGE);
	        	}else{
	        	
		        	//���b�Z�[�W�̎擾
		            strMsgs = msgCls.getMsgFrmCsv(Integer.parseInt(rcvSeq));
		        	
		        	if(END_MESSAGE.equals(strMsgs)){
		        		//Sota�ɏI�������̍��}�𑗂�
		        		spchText = strMsgs;
		        		snd.println(spchText);
		        	}else{
		        	
			        	strMsgsArr = strMsgs.split(",", 0);
			        	strPgNo = strMsgsArr[1]; //�y�[�W�ԍ�
			        	spchText = strMsgsArr[2];//Sota���b�����e
			        	strMotionCd = strMsgsArr[3];//Sota�̓�����w�肷��ԍ�
			        	
			        	//ppt�𑀍�
			        	if("1".equals(rcvSeq)){
			        		System.out.println("ppt.openPptFile");
			        		ppt.openPptFile(PPT_NAME);
			        	}else{
			        		System.out.println("ppt.slidePage");
			        		ppt.slidePage(strPgNo);
			        	}
			        	
			        	//�N���C�A���g�Ƀ��b�Z�[�W���M
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