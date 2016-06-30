<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>出错啦-BIMFACE</title>
<link rel="shortcut icon" href="//static.bimface.com/favicon.ico" type="image/x-icon" />
<link rel="icon" href="//static.bimface.com/favicon.ico" type="image/x-icon" />
<style type="text/css">
html {color: #000;background: #fff;}
body {margin:0;padding:0;font:14px/1.5 'Microsoft Yahei', 'sans-serif', 'Arial', 'sans-serif';}
</style>
</head>

<body>
<div style="width:800px; margin:0 auto; padding:120px 0">
	<div style="font-size: 48px; margin:20px 0">500 ERROR</div>
	<div><span style="color:#CCC">code: </span>${code!}</div>
	<div><span style="color:#CCC">message: </span>${message!}</div>
	<div style="margin:20px 0">
		<a href="javascript:history.go(-1)">返回上一页</a>
		&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
		<a href="${rc.getContextPath()}">返回首页</a>
	</div>
</div>
</body>
</html>