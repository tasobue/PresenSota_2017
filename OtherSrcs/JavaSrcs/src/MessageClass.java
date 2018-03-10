import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MessageClass {
    private int _speechSeq;
    private String _speechTxt;
    private ArrayList<String> _genkoList;
	private String _filePath;
	
    public MessageClass(){
        _speechSeq = -1;
        _speechTxt = "";
    	_genkoList = new ArrayList<String>();
    	_filePath = System.getProperty("user.dir");
    }
	
	/**
	*readGenkoFile
	*引数　String fileNm　読み込む原稿CSV
	*原稿のCSVファイルを読み込み１行単位でArrayListクラスに格納します。
	*CSVはこのクラスと同じディレクトリにあるファイルを読み込みます。
	*/
	public void readGenkoFile(String fileNm){
		try{
			File file = new File(_filePath + "\\" + fileNm);
			
			if (checkBeforeReadfile(file)){
        		BufferedReader br = new BufferedReader(new FileReader(file));

        		String str;
        		while((str = br.readLine()) != null){
        			//System.out.println("readGenkoFile Start");
        			//System.out.println(str);
        			//System.out.println("readGenkoFile End");
        			_genkoList.add(str);
        		}

        		br.close();
				
			
			}else{
        		
      		}

		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}finally {
			
		}
	}
	
	public boolean isServerWait(String fileNm){
		boolean isServerWaitFlg = true;
		try{
			File file = new File(_filePath + "\\" + fileNm);
			
			
			if (checkBeforeReadfile(file)){
        		//waitファイルがある場合
				isServerWaitFlg =  true;
			
			}else{
        		//waitファイルがない場合
				isServerWaitFlg =  false;
      		}

		}  catch(Exception e){
			e.printStackTrace();
		}finally {
			return isServerWaitFlg;
		}
		
	}
    
	/**
	*	getMsgFrmCsv	
	*	CSVの指定した行からテキストを読み込みます。
	*	@param seq：CSVの何行目を読み込むかを示す数値
	*	@return String 原稿テキスト
	*
	*/
    public String getMsgFrmCsv(int seq){
    	System.out.println("getMsgFrmCsv:" + seq);
    	
    	if(_genkoList.size() < seq ){
    		_speechTxt = "end";
    	}else{
    		_speechTxt = _genkoList.get(seq -1);
    	}
        System.out.println("_speechTxt:" + _speechTxt);
       return _speechTxt;
    }
	
	/**
	*	checkBeforeReadfile
	*	引数　File file：読み込み対象のファイル
	*	ファイルが読み込み可能か返します。
	*	戻り値　true:読み込み可能　False：読み込み不可
	*/
	
	private static boolean checkBeforeReadfile(File file){
    if (file.exists()){
      if (file.isFile() && file.canRead()){
        return true;
      }
    }

    return false;
  }
}