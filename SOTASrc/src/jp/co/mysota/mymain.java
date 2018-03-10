//このソースは、VstoneMagicによって自動生成されました。
//ソースの内容を書き換えた場合、VstoneMagicで開けなくなる場合があります。
package	jp.co.mysota;
import	main.main.GlobalVariable;
import	jp.vstone.RobotLib.*;
import	jp.vstone.sotatalk.*;
import	jp.vstone.sotatalk.SpeechRecog.*;
import	jp.vstone.network.*;
import	java.net.Socket;
import	java.net.ServerSocket;
import	java.io.BufferedReader;
import	java.io.InputStreamReader;
import	java.io.OutputStreamWriter;
import	java.io.BufferedWriter;
import	java.io.PrintWriter;
import	java.io.IOException;
import	java.awt.Color;

public class mymain
{

	public int ECHO_PORT;
	public String ECHO_IP;
	public int intSpchSeq;
	public CRobotPose pose;
	public mymain()																										//@<BlockInfo>jp.vstone.block.func.constructor,32,32,432,32,False,4,@</BlockInfo>
	{
																														//@<OutputChild>
		ECHO_PORT=10007;																								//@<BlockInfo>jp.vstone.block.variable,96,32,96,32,False,3,break@</BlockInfo>
																														//@<EndOfBlock/>
		ECHO_IP="192.168.179.5";																						//@<BlockInfo>jp.vstone.block.variable,160,32,160,32,False,19,break@</BlockInfo>
																														//@<EndOfBlock/>
		intSpchSeq=0;																									//@<BlockInfo>jp.vstone.block.variable,224,32,224,32,False,2,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*CRobotPose pose*/;																							//@<BlockInfo>jp.vstone.block.variable,288,32,288,32,False,1,break@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void main()																									//@<BlockInfo>jp.vstone.block.func,64,256,576,688,False,18,コメント@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		String sendMsg="Test";																							//@<BlockInfo>jp.vstone.block.variable,192,256,192,256,False,17,break@</BlockInfo>
																														//@<EndOfBlock/>
		Socket socket = null;																							//@<BlockInfo>jp.vstone.block.freeproc,256,256,256,256,False,16,@</BlockInfo>
		BufferedReader rcv = null;
		PrintWriter snd = null;
																														//@<EndOfBlock/>
		int presenEndFlg=0;																								//@<BlockInfo>jp.vstone.block.variable,320,256,320,256,False,15,break@</BlockInfo>
																														//@<EndOfBlock/>
		while(GlobalVariable.TRUE)																						//@<BlockInfo>jp.vstone.block.while.endless,400,256,64,688,False,14,Endless@</BlockInfo>
		{

																														//@<OutputChild>
			intSpchSeq=(int)intSpchSeq + 1;																				//@<BlockInfo>jp.vstone.block.calculater,64,352,64,352,False,13,@</BlockInfo>
																														//@<EndOfBlock/>
			String resultMsg="失敗";																						//@<BlockInfo>jp.vstone.block.variable,128,352,128,352,False,12,break@</BlockInfo>
																														//@<EndOfBlock/>
			try {																										//@<BlockInfo>jp.vstone.block.freeproc,192,352,192,352,False,11,@</BlockInfo>
				System.out.println("000");
				if(socket == null){
					System.out.println("001");
					socket = new Socket(ECHO_IP, ECHO_PORT);
					rcv = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					snd = new PrintWriter(socket.getOutputStream());
				}else{

				}

				if(socket  != null){

					System.out.println("003");

					snd.println(intSpchSeq);
					System.out.println("intSpchSeq:" + intSpchSeq);
					snd.flush();
					System.out.println("004");
					String rcvMsg = rcv.readLine();

					if (rcvMsg != null) {
						resultMsg = new String(rcvMsg.getBytes("UTF-8"), "UTF-8");
						if("end".equals(resultMsg)){
							presenEndFlg = 1;
						}
			                                          else if("wait".equals(resultMsg)){
			                                                        presenEndFlg = 9;
			                                          }
			                                          else{
			                                                        presenEndFlg  = 0;
			                                          }
					} else {

					}
				}
				System.out.println("005");


			} catch (IOException e) {
				e.printStackTrace();

			} finally {



			}
																														//@<EndOfBlock/>
			if(presenEndFlg==0)																							//@<BlockInfo>jp.vstone.block.if,272,384,560,384,False,10,コメント@</BlockInfo>
			{
																														//@<OutputChild>
				pose = new CRobotPose();																				//@<BlockInfo>jp.vstone.block.pose,336,384,336,384,False,6,コメント@</BlockInfo>
				pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{0,-900,0,900,0,0,0,0}
								);
				pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{100,100,100,100,100,100,100,100}
								);
				pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
								new Short[]{0,-255,0,180,80,0,180,80,0}
								);
				GlobalVariable.motion.play(pose,1000);
				CRobotUtil.wait(1000);																					//@<EndOfBlock/>
				GlobalVariable.sotawish.Say((String)resultMsg,MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,400,384,400,384,False,5,@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
			}
			else if(presenEndFlg==9)
			{
																														//@<OutputChild>
				intSpchSeq=(int)0;																						//@<BlockInfo>jp.vstone.block.calculater,336,480,336,480,False,8,@</BlockInfo>
																														//@<EndOfBlock/>
				pose = new CRobotPose();																				//@<BlockInfo>jp.vstone.block.pose,400,480,400,480,False,7,コメント@</BlockInfo>
				pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{1,-906,-10,-685,3,-7,-14,9}
								);
				pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{100,100,100,100,100,100,100,100}
								);
				pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
								new Short[]{0,-255,0,0,0,255,0,0,255}
								);
				GlobalVariable.motion.play(pose,1000);
				CRobotUtil.wait(1000);																					//@<EndOfBlock/>
																														//@</OutputChild>
			}
			else
			{
																														//@<OutputChild>
				break;																									//@<BlockInfo>jp.vstone.block.break,336,576,336,576,False,9,break@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>
		}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

}
