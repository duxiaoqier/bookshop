<#macro menu current>
	<div class="left-panel">
		<div class="menu-bar">图书选购</div>
		<ul class="menu-list">
			<li class='<#if current="1-1">menu-list-current</#if>'>- <a href="${app_path}/shop/book">图书选购</a></li>
		</ul>
		<div class="menu-bar">购物车管理</div>
		<ul class="menu-list">
			<li class='<#if current="2-1">menu-list-current</#if>'>- <a href="${app_path}/shop/cart">购物车</a></li>
		</ul>
    </div>
</#macro>