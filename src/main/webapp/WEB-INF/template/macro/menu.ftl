<#macro menu current>
	<div class="left-panel">
		<div class="menu-bar">转换管理</div>
		<ul class="menu-list">
			<li class='<#if current="1-1">menu-list-current</#if>'>- <a href="${app_path}/protect/console/transfer">转换管理</a></li>
		</ul>
		<div class="menu-bar">文件管理</div>
		<ul class="menu-list">
			<li class='<#if current="2-1">menu-list-current</#if>'>- <a href="${app_path}/protect/console/file">文件管理</a></li>
		</ul>
    </div>
</#macro>