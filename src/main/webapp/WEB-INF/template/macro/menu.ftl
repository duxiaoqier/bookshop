<#macro menu current>
	<div class="left-panel">
		<div class="menu-bar">图书管理</div>
		<ul class="menu-list">
			<li class='<#if current="1-1">menu-list-current</#if>'>- <a href="${app_path}/protect/console/book">图书管理</a></li>
		</ul>
		<div class="menu-bar">用户管理</div>
		<ul class="menu-list">
			<li class='<#if current="2-1">menu-list-current</#if>'>- <a href="${app_path}/protect/console/user">用户管理</a></li>
		</ul>
    </div>
</#macro>