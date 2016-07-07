<#import "/macro/header.ftl" as h>
<#import "/macro/menu_shop.ftl" as m>
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
		            <input type="text" name="id" value="${id!}" placeholder="请输入图书ID" style="width:200px">
		            <input type="text" name="name" value="${name!}" placeholder="请输入图书名称" style="width:200px">
		            <select name="type">

		            	<#list enums["com.train.bookshop.enums.BookType"]?values as e> 
		            		<#if type==e.value>
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
		    			<th style="width:10%">图书名称</th>
		    			<th>图书类型</th>
		    			<th>图书简介</th>
		    			<th>图书价格</th>
		    			<th>剩余数量</th>
		    		</tr>
		    		<#if list??>
		    			<#list list as book>
				    		<tr>
				    			<td>
				    				<input type="checkbox" class="cb_single" id="cb_id_${book.id?c}" value="${book.id?c}"  action-add='true' action-del='true' />
				    			</td>
				    			<td>${book.name!}</td>
				    			<td>
				    				<#list enums["com.train.bookshop.enums.BookType"]?values as e> 
										<#if e.value==book.type>
											${e.getName()}
										</#if>
									</#list>
				    			</td>
				    			<td>${book.summary!}</td>
				    			<td>${book.price/100!}</td>
				    			<td>${book.count!}</td>				    			
				    		</tr>
			    		</#list>
		    		</#if>
		    	</table>
		    	<div class="tools-bar">
		    		<a id="btn-buy" can-do="false"><span class="tool-disable">快速购买</span></a>
		    		<a id="btn-add" can-do="false"><span class="tool-disable">加入购物车</span></a>
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
	// 全选
	$("#cb_all").click(function() {
		$(".cb_single").each(function() {
			$(this).prop("checked",!!$("#cb_all").prop("checked"));
		});
		showTools('action-add', $('#btn-add'));
	});
	$(".cb_single").click(function() {
		if(!$(this).prop("checked")) {
			$("#cb_all").prop("checked", false);
		}
		showTools('action-add', $('#btn-add'));
	});
	
	// 批量加入购物车
	$("#btn-add").click(function() {
		if($(this).attr("can-do")=='true') {
			if(confirm("确定要加入购物车吗？")) {
				$.ajax({
					url: 'book/add?ids=' + getCheckedValue(),
					type: 'POST',
					async: false,
					success: function(result) {
						if(result == 'ok') {
							$("#form-search").submit();
						} else {
							alert("失败："+result);
						}
					}
				});
			}
		}
	});
	
	// 批量直接购买
	$("#btn-buy").click(function() {
		if($(this).attr("can-do")=='true') {
			if(confirm("确定要直接购买吗？")) {
				$.ajax({
					url: 'book/buy?ids=' + getCheckedValue(),
					type: 'GET',
					async: false,
					success: function(result) {
						if(result == 'ok') {
							$("#form-search").submit();
						} else {
							alert("失败："+result);
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