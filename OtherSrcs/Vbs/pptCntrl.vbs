'===========================================================
'TCP/IP�ŒʐM����T���v��(VBS/VBA)
'�ʐM�I�u�W�F�N�g����
'===========================================================
'�y�N�����@�z
' cscript.exe pptCntrl.vbs [ppt�t�@�C����] 
'===========================================================

'===========================================================
'���C������
'===========================================================
'----------
' ���������^�ݒ�
'----------
Dim commandFileName
Dim pptFileName
Dim pgNo

'----------
' �������擾�ippt�t�@�C�����A�y�[�WNo�j
'----------
pptFileName = WScript.Arguments(0)

'----------
' ����
'----------

'�p���|�I�u�W�F�N�g�����E�����ݒ�
Set oApp = CreateObject("PowerPoint.Application")
oApp.Visible = True '���ɂ���
oApp.Presentations.Open(getCurPath & "\" & pptFileName)
oApp.ActivePresentation.SlideShowSettings.Run 'PPT�N��

'----------
' �I������
'----------
Set oApp = Nothing
Set Winsock1 = Nothing

WSCript.Quit

'===========================================================
' �J�����g�p�X���擾
'===========================================================
Function getCurPath()
	Dim wFileObj
	
	Set wFileObj = createObject("Scripting.FileSystemObject")
	getCurPath = wFileObj.GetParentFolderName(WScript.ScriptFullName)
	
End Function