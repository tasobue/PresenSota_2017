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
	*�����@String fileNm�@�ǂݍ��ތ��eCSV
	*���e��CSV�t�@�C����ǂݍ��݂P�s�P�ʂ�ArrayList�N���X�Ɋi�[���܂��B
	*CSV�͂��̃N���X�Ɠ����f�B���N�g���ɂ���t�@�C����ǂݍ��݂܂��B
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
        		//wait�t�@�C��������ꍇ
				isServerWaitFlg =  true;
			
			}else{
        		//wait�t�@�C�����Ȃ��ꍇ
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
	*	CSV�̎w�肵���s����e�L�X�g��ǂݍ��݂܂��B
	*	@param seq�FCSV�̉��s�ڂ�ǂݍ��ނ����������l
	*	@return String ���e�e�L�X�g
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
	*	�����@File file�F�ǂݍ��ݑΏۂ̃t�@�C��
	*	�t�@�C�����ǂݍ��݉\���Ԃ��܂��B
	*	�߂�l�@true:�ǂݍ��݉\�@False�F�ǂݍ��ݕs��
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