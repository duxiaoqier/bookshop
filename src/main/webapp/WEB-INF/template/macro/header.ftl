<#macro header current>
<div class="header clr">
    <div class="header-logo"><img src="${app_static_path}/images/bops-logo.jpg" /></div>
    <div class="header-title">运营管理后台</div>
    <div class="header-nav">
        <ul class="clr">
            <li class='<#if current="bops">header-nav-current</#if>'><a href="${app_path}">首页</a></li>
            <li class='<#if current="job">header-nav-current</#if>'><a href="${app_path}/job">任务调度</a></li>
        </ul>
    </div>
</div>
</#macro>