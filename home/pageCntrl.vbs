'===========================================================
'TCP/IP�ŒʐM����T���v��(VBS/VBA)
'�ʐM�I�u�W�F�N�g����
'===========================================================
'�y�N�����@�z
' cscript.exe pptCntrl.vbs  [�y�[�WNo]
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
' �������擾�i�y�[�WNo�j
'----------
pgNo = WScript.Arguments(0)

'----------
' ����
'----------

'�p���|�I�u�W�F�N�g�����E�����ݒ�
Set oApp = CreateObject("PowerPoint.Application")

Call goToPptSlideNo(Int(pgNo), oApp)


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

'===========================================================
' PPT�t�@�C���̎w��X���C�h�ԍ��ɑJ�ڂ���
'===========================================================
Sub goToPptSlideNo(ByVal pNo, ByRef pPptObj)
	Dim wMaxPageNo
	
	wMaxPageNo = pPptObj.ActivePresentation.Slides.Count
	
	'�L���ȃy�[�W���łȂ��ꍇ�́A�ŏI�y�[�W��\��
	If Not(IsNumeric(pNo)) Then
		pNo = wMaxPageNo
	End If

	'�ő�y�[�W���𒴂������̏ꍇ�́A�ŏI�y�[�W��\��
	If pNo > wMaxPageNo Then
		pNo = wMaxPageNo
	End If
	
	'�P�y�[�W��菬�����ꍇ�́A�P�y�[�W�ڂ�\��
	If pNo < 1 Then
		pNo = 1
	End If
	
	'�w��X���C�h�ֈړ�
	Call oApp.SlideShowWindows(1).View.GotoSlide(Int(pNo))
	
End Sub