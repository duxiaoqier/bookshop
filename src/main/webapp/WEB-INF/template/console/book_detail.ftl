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
		<h3>文件/转换</h3>
		<table class="table-list">
			<tr>
				<th style="width:20%">转换ID</th>
				<td style="width:30%">${transferDetail.transferEntity.id!}</td>
				<th style="width:20%">创建时间</th>
				<td>
					${transferDetail.transferEntity.createTime?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
			<tr>
				<th style="width:20%">数据包ID</th>
				<td style="width:30%">
					${transferDetail.transferEntity.databagId!}
				</td>
				<th style="width:20%">所属APP</th>
				<td style="width:30%">${transferDetail.transferEntity.appKey!}</td>
			</tr>
			<tr>
				<th style="width:20%">引用文件</th>
				<td style="width:30%">
				<#if transferDetail.transferEntity.referenceFileId??>
					${transferDetail.transferEntity.referenceFileId!}
				<#else>
				 	否
				</#if>
				</td>
			</tr>
			<tr>
				<th style="width:20%">转换类型</th>
				<td>
					${transferDetail.transferEntity.type!}
				</td>
				<th style="width:20%">Worker版本</th>
				<td style="width:30%">
					${transferDetail.transferEntity.workerVersion!}
				</td>
			</tr>
			<tr>
				<th style="width:20%">转换状态</th>
				<td>
					<#if (transferDetail.transferEntity.status)??>
			        <#switch transferDetail.transferEntity.status>
			        	<#case 0> 准备中 <#break>
			        	<#case 1> 转换中 <#break>
			        	<#case 99> 成功<#break>
			        	<#case -1> 失败 <#break>
			        	<#default> 异常状态 
			        </#switch>
			   		</#if>
				</td>
				<th style="width:20%">优先级</th>
				<td style="width:30%">
					${transferDetail.transferEntity.priority!}
				</td>
			</tr>
			<tr>
				<th style="width:20%">任务ID</th>
				<td style="width:30%">
				    <#if (transferDetail.transferEntity.jobId)??>
						${transferDetail.transferEntity.jobId?c}
					</#if>
				</td>
				<th style="width:20%">任务返回码</th>
				<td>
					${transferDetail.transferEntity.resultCode!}
				</td>
			</tr>
			<tr>
				<th style="width:20%">回调</th>
				<td style="width:30%">
    				<#if transferDetail.transferEntity.callbackStatus??>
    					<#if transferDetail.transferEntity.callbackStatus==200>
							<span style="color:green">${transferDetail.transferEntity.callbackStatus!} / ${transferDetail.transferEntity.callbackTimes!}</span>
						</#if>
						<#if transferDetail.transferEntity.callbackStatus!=200>
							<span style="color:red">${transferDetail.transferEntity.callbackStatus!} / ${transferDetail.transferEntity.callbackTimes!}</span>
						</#if>
    				</#if>
				</td>
				<th style="width:20%">任务返回消息</th>
				<td style="width:30%">
					${transferDetail.transferEntity.resultMessage!}
				</td>
			</tr>
			<tr>
				<th style="width:20%">回调URL</th>
				<td colspan=3>
					${transferDetail.transferEntity.callbackUrl!}
				</td>
			</tr>
		</table>
		
		<h3>分享链接</h3>
		<table class="table-list">
		    <tr>
			<th style="width:20%">链接地址</th>
			<td style="width:30%">
			    <#if (transferDetail.shareLinkEntity.token)??>
					<a href="https://api.bimface.com/preview/${(transferDetail.shareLinkEntity.token)!}" target="_blank">
						https://api.bimface.com/preview/${(transferDetail.shareLinkEntity.token)!}
					</a>
				<#else>
					未生成预览
				</#if>
			</td>
			<th style="width:20%">失效时间</th>
			<td>
			    <#if (transferDetail.shareLinkEntity.expireTime)??>
				${(transferDetail.shareLinkEntity.expireTime)?string("yyyy-MM-dd HH:mm:ss")}
				</#if>
			</td>
			</tr>
		</table>
		
		<h3>主文件信息</h3>
		<table class="table-list">
		    <tr>
				<th style="width:20%">文件ID</th>
				<td style="width:30%">
				  <#if (transferDetail.fileEntity.length)??>
					${(transferDetail.fileEntity.id)?c}
				  </#if>
				</td>
				<th style="width:20%">文件名</th>
				<td>
				    ${(transferDetail.fileEntity.name)!}
				</td>
				</tr>
				<tr>
				<th style="width:20%">文件长度</th>
				<td style="width:30%">
					<#if (transferDetail.fileEntity.length)??>
					${(transferDetail.fileEntity.length)/(1024*1024)}M
					</#if>
				</td>
				<th style="width:20%">上传状态</th>
				<td>
				   <#if (transferDetail.fileEntity.status)??>
				        <#switch transferDetail.fileEntity.status>
				        	<#case 0> 上传中 <#break>
				        	<#case 99> 成功 <#break>
				        	<#case -1> 失败 <#break>
				        	<#default> 异常状态 
				        </#switch>
				   </#if>
				</td>
				</tr>
				<tr>
				<th style="width:20%">上传模式</th>
				<td style="width:30%">
					${(transferDetail.fileEntity.uploadMode)!}
				</td>
				<th style="width:20%">下载地址</th>
				<td>
					${(transferDetail.fileEntity.sourceUrl)!}
				</td>
				</tr>
				<tr>
				<th style="width:20%">Bucket地址</th>
				<td style="width:30%">
					${(transferDetail.fileEntity.sourceBucket)!}
				</td>
				<th style="width:20%">Bucket存储Key</th>
				<td>
					${(transferDetail.fileEntity.sourceObjectKey)!}
				</td>
				</tr>
				<tr>
				<th style="width:20%">存储ID</th>
				<td style="width:30%">
					${(transferDetail.fileEntity.storeId)!}
				</td>
				<th style="width:20%">存储ETAG</th>
				<td>
					${(transferDetail.fileEntity.etag)!}
				</td>
				</tr>
				<tr>
				<th style="width:20%">创建时间</th>
				<td style="width:30%">
				 <#if (transferDetail.fileEntity.createTime)??>
					${(transferDetail.fileEntity.createTime)?string("yyyy-MM-dd HH:mm:ss")}
				 </#if>
				</td>
				<th style="width:20%">更新时间</th>
				<td>
				  <#if (transferDetail.fileEntity.updateTime)??>
					${(transferDetail.fileEntity.updateTime)?string("yyyy-MM-dd HH:mm:ss")}
				  </#if>
				</td>
			</tr>
		</table>
	</div>
	</#if>
</div>
</body>
</html>