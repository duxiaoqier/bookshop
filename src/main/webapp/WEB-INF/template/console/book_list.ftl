<#import "/macro/header.ftl" as h>
<#import "/macro/menu.ftl" as m>
<#import "/macro/page.ftl" as p>
<#import "/macro/staticImport.ftl" as s>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<@s.staticImport />
</head>

<body>
<@h.header current="book" />
<div class="main-panel">
	<@m.menu current="1-1" />
    <div class="right-panel">
    	<div class="nav">
	    	<ul class="clr">
	    		<li class="nav_current">书籍查询</li>
			</ul>
		</div>
		<div class="detail">
	    	<fieldset class="filter">
	    		<form id="form-search" action="book" method="get">
		            <input type="text" name="bookId" value="${bookId!}" placeholder="请输入任务ID" style="width:200px">
		            <input type="text" name="sourceId" value="${sourceId!}" placeholder="请输入任务源ID" style="width:200px">
		            <select name="type">
		            	<#if type == ''>
		            		<option value="" selected="selected">请选择任务类型</option>
		            	<#else>
		            		<option value="">请选择任务类型</option>
		            	</#if>
		            	<#list enums["com.bimface.book.enums.bookType"]?values as e> 
		            		<#if type==e.value>
			            		<option value="${e.value}" selected="selected">${e.value}</option>
			            	<#else>
			            		<option value="${e.value}">${e.value}</option>
			            	</#if>
		            	</#list>
		            </select>
		            <select name="status">
		            	<#if status == -99>
		            		<option value="" selected="selected">请选择任务状态</option>
		            	<#else>
		            		<option value="">请选择任务状态</option>
		            	</#if>
		            	<#list enums["com.bimface.book.enums.bookStatus"]?values as e> 
		            		<#if status==e.value>
			            		<option value="${e.value}" selected="selected">${e.getName()}</option>
			            	<#else>
			            		<option value="${e.value}">${e.getName()}</option>
			            	</#if>
		            	</#list>
		            </select>
		            <br/>
		            <input type="hidden" id="pageNo" name="page" value="${pageNo!}">
		            <div id="btn-search" class="button button-primary">查询</div>
		            <a href="book" class="button">清空</a>
	            </form>
	    	</fieldset>
			<#if (list??) && (list?size > 0)>
				<@p.page display="${page.htmlDisplay!}" />
		    	<table class="table-list">
		    		<tr>
		    			<th><input id="cb_all" type="checkbox" value="all" /></th>
		    			<th>任务ID</th>
		    			<th style="width:30%">任务源ID</th>
		    			<th>任务类型</th>
		    			<th>任务状态</th>
		    			<th>执行机器 IP</th>
		    			<th>总耗时（秒）</th>
		    			<th>创建时间</th>
		    			<th>Callback</th>
		    		</tr>
		    		<#if list??>
		    			<#list list as book>
				    		<tr>
				    			<td>
				    				<#if book.status==enums["com.bimface.book.enums.bookStatus"].READY.value || book.status==enums["com.bimface.book.enums.bookStatus"].FAILED.value || book.status==enums["com.bimface.book.enums.bookStatus"].COMPLETE.value>
				    					<#assign bookCanRestart="true">
			    					<#else>
			    						<#assign bookCanRestart="false">
				    				</#if>
				    				<#if book.status==enums["com.bimface.book.enums.bookStatus"].QUEUE.value>
				    					<#assign bookCanDequeue="true">
			    					<#else>
			    						<#assign bookCanDequeue="false">
				    				</#if>
				    				<#if book.status==enums["com.bimface.book.enums.bookStatus"].DEQUEUE.value>
				    					<#assign bookCanenqueue="true">
			    					<#else>
			    						<#assign bookCanenqueue="false">
				    				</#if>
				    				<#if book.status==enums["com.bimface.book.enums.bookStatus"].READY.value || book.status==enums["com.bimface.book.enums.bookStatus"].FAILED.value || book.status==enums["com.bimface.book.enums.bookStatus"].COMPLETE.value>
				    					<#assign bookCanDel="true">
			    					<#else>
			    						<#assign bookCanDel="false">
				    				</#if>
				    				<input type="checkbox" class="cb_single" id="cb_id_${book.id?c}" value="${book.id?c}" action-restart='${bookCanRestart!}' action-dequeue='${bookCanDequeue!}' action-enqueue='${bookCanenqueue!}' action-del='${bookCanDel!}' />
				    			</td>
				    			<td><a href="book/${book.id?c}" target="_blank">${book.id?c}</a></td>
				    			<td>
				    				${book.sourceId!}
				    				<br />名称：${book.sourceName!}
				    			</td>
				    			<td>
				    				<#list enums["com.bimface.book.enums.bookType"]?values as e> 
										<#if e.value==book.type>
											${e.value}
										</#if>
									</#list>
				    			</td>
				    			<td>
				    				<#list enums["com.bimface.book.enums.bookStatus"]?values as e> 
										<#if e.value==book.status>
											<#if book.status==enums["com.bimface.book.enums.bookStatus"].FAILED.value>
												<span style="color:red"">${e.getName()}</span>
												<br />${book.code!}
											<#elseif book.status==enums["com.bimface.book.enums.bookStatus"].COMPLETE.value>
												<span class="font-success">${e.getName()}</span>
											<#else>
												${e.getName()}
											</#if>
										</#if>
									</#list>
				    			</td>
				    			<td>${book.executeHost!}</td>
				    			<td>
				    				${book.executeCost!}
				    				<#if book.executeCost??>
										<br />
										<a class="pop_btn" data="cost_${book.id?c}">明细</a>
										<div class="remodal" data-remodal-id="cost_${book.id?c}">
											<button data-remodal-action="close" class="remodal-close"></button>
											<table class="table-list" id="table_cost_${book.id?c}"></table>
											<button data-remodal-action="confirm" class="remodal-confirm">我知道了</button>
										</div>
									</#if>
				    			</td>
				    			<td>
				    				<#if book.createTime??>
				    					${classMap.relativeDateFormat.format(book.createTime)}
				    				</#if>
				    			</td>
				    			<td>
				    				<#if book.callbackStatus??>
				    					<#if book.callbackStatus==200>
											<span class="font-success">${book.callbackStatus!}</span>
										</#if>
										<#if book.callbackStatus!=200>
											<span class="font-failed">${book.callbackStatus!} / ${book.callbackTimes!}</span>
										</#if>
				    				</#if>
				    			</td>
				    		</tr>
			    		</#list>
		    		</#if>
		    	</table>
		    	<div class="tools-bar">
		    		<a id="btn-add" can-do="false"><span class="tool-disable">增加</span></a>
		    		<a id="btn-del" can-do="false"><span class="tool-disable">删除</span></a>
		    		<span class="error-msg"></span>
		    	</div>
		    	<div class="remodal" data-remodal-id="tool-confirm">
					<button data-remodal-action="close" class="remodal-close"></button>
					<div>是否确定？</div>
					<button data-remodal-action="cancel" class="remodal-cancel">取消</button>
					<button data-remodal-action="confirm" class="remodal-confirm">确定</button>
				</div>
	    	</#if>
    	</div>
    </div>
</div>
<script>
$(function () {
	$('.pop_btn').click(function(){
		
		// 如果是明细
		var dataId = $(this).attr('data');
		var bookId = dataId.substring(dataId.indexOf('_')+1, dataId.lenth);
		
		var tableEle = $('#table_cost_' + bookId);
		tableEle.html('<tr><th>阶段</th><th>耗时（秒）</th></tr>');
		
		// Ajax请求
		$.get('book/step?bookId=' + bookId, function(result) {
			if(result.code == 'success') {
				$.each(result.data, function(key, value) {
					tableEle.append('<tr><td>'+key+'</td><td>'+value+'</td></tr>');
				});
			}
		});
		
		// pop up
		var inst = $('[data-remodal-id='+dataId+']').remodal();
		inst.open();
	});
	
	// 全选
	$("#cb_all").click(function() {
		$(".cb_single").each(function() {
			$(this).prop("checked",!!$("#cb_all").prop("checked"));
		});
		showTools('action-restart', $('#btn-restart'));
		showTools('action-dequeue', $('#btn-dequeue'));
		showTools('action-enqueue', $('#btn-enqueue'));
		showTools('action-del', $('#btn-del'));
	});
	$(".cb_single").click(function() {
		if(!$(this).prop("checked")) {
			$("#cb_all").prop("checked", false);
		}
		showTools('action-restart', $('#btn-restart'));
		showTools('action-dequeue', $('#btn-dequeue'));
		showTools('action-enqueue', $('#btn-enqueue'));
		showTools('action-del', $('#btn-del'));
	});
	
	// 批量删除
	$("#btn-del").click(function() {
		if($(this).attr("can-do")=='true') {
			if(confirm("确定要删除吗？")) {
				$.ajax({
					url: '?ids=' + getCheckedValue(),
					type: 'DELETE',
					async: false,
					success: function(result) {
						if(result.code == 'success') {
							$("#form-search").submit();
						} else {
							alert("失败："+result.code);
						}
					}
				});
			}
		}
	});
	
	// 是否显示操作按钮
	function showTools(action, btn) {
		var countChecked = 0;
		var countAction = 0;
		$(".cb_single").each(function() {
			if($(this).prop("checked")) {
				countChecked++;
				if($(this).attr(action) == 'true') {
					countAction++;
				}
			}
		})
		if(countChecked > 0 && (countChecked == countAction)) {
			btn.attr('can-do', 'true');
			btn.find('span').removeClass("tool-disable");
		} else {
			btn.attr('can-do', 'false');
			btn.find('span').addClass("tool-disable");
		}
	}
	
	// 获取所有选中的元素
	function getCheckedValue() {
		var str="";
		$(".cb_single").each(function(){
			if($(this).prop("checked")){
				str+=$(this).val()+",";  
			} 
		})
		if(str.endsWith(',')) {
			str = str.substring(0, str.length-1);
		}
		return str;
	}
});
</script>
</body>
</html>