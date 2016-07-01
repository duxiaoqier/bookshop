<#import "/macro/staticImport.ftl" as s>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<@s.staticImport />
</head>

<body>
<div class="main-panel">
	<#if transferDetail??>
	<div class="page-detail">
		<h3>book信息</h3>
		<table class="table-list">
			
		</table>
	</div>
	</#if>
</div>
</body>
</html>