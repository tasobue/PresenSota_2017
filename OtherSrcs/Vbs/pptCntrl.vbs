'===========================================================
'TCP/IPで通信するサンプル(VBS/VBA)
'通信オブジェクト生成
'===========================================================
'【起動方法】
' cscript.exe pptCntrl.vbs [pptファイル名] 
'===========================================================

'===========================================================
'メイン処理
'===========================================================
'----------
' 初期処理／設定
'----------
Dim commandFileName
Dim pptFileName
Dim pgNo

'----------
' 引数を取得（pptファイル名、ページNo）
'----------
pptFileName = WScript.Arguments(0)

'----------
' 処理
'----------

'パワポオブジェクト生成・初期設定
Set oApp = CreateObject("PowerPoint.Application")
oApp.Visible = True '可視にする
oApp.Presentations.Open(getCurPath & "\" & pptFileName)
oApp.ActivePresentation.SlideShowSettings.Run 'PPT起動

'----------
' 終了処理
'----------
Set oApp = Nothing
Set Winsock1 = Nothing

WSCript.Quit

'===========================================================
' カレントパスを取得
'===========================================================
Function getCurPath()
	Dim wFileObj
	
	Set wFileObj = createObject("Scripting.FileSystemObject")
	getCurPath = wFileObj.GetParentFolderName(WScript.ScriptFullName)
	
End Function