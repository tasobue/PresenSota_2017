'===========================================================
'TCP/IPで通信するサンプル(VBS/VBA)
'通信オブジェクト生成
'===========================================================
'【起動方法】
' cscript.exe pptCntrl.vbs  [ページNo]
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
' 引数を取得（ページNo）
'----------
pgNo = WScript.Arguments(0)

'----------
' 処理
'----------

'パワポオブジェクト生成・初期設定
Set oApp = CreateObject("PowerPoint.Application")

Call goToPptSlideNo(Int(pgNo), oApp)


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

'===========================================================
' PPTファイルの指定スライド番号に遷移する
'===========================================================
Sub goToPptSlideNo(ByVal pNo, ByRef pPptObj)
	Dim wMaxPageNo
	
	wMaxPageNo = pPptObj.ActivePresentation.Slides.Count
	
	'有効なページ数でない場合は、最終ページを表示
	If Not(IsNumeric(pNo)) Then
		pNo = wMaxPageNo
	End If

	'最大ページ数を超えた数の場合は、最終ページを表示
	If pNo > wMaxPageNo Then
		pNo = wMaxPageNo
	End If
	
	'１ページより小さい場合は、１ページ目を表示
	If pNo < 1 Then
		pNo = 1
	End If
	
	'指定スライドへ移動
	Call oApp.SlideShowWindows(1).View.GotoSlide(Int(pNo))
	
End Sub