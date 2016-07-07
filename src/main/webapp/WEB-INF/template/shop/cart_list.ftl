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
	    		<li class="nav_current">购物车</li>
			</ul>
		</div>
		<div class="detail">
			<#if (list??) && (list?size > 0)>
				<@p.page display="${page.htmlDisplay!}" />
		    	<table class="table-list">
		    		<tr>
		    			<th><input id="cb_all" type="checkbox" value="all" /></th>
		    			<th style="width:10%">图书名称</th>
		    			<th>图书类型</th>
		    			<th>图书简介</th>
		    			<th>图书价格</th>
		    			<th>购买数量</th>
		    		</tr>
		    		<#if list??>
		    			<#list list as book>
				    		<tr>
				    			<td>
				    				<input type="checkbox" class="cb_single" id="cb_id_${book.id?c}" value="${book.id?c}"  action-del='true' action-buy='true' />
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
		    		<tr>总价：${sumPrice/100!}元</tr>
		    	</table>
		    	<div class="tools-bar">
		    		<a id="btn-buy" can-do="false"><span class="tool-disable">确认购买</span></a>
		    		<a id="btn-del" can-do="false"><span class="tool-disable">从购物车移除</span></a>
		    		<span class="error-msg"></span>
		    	</div>
		    	<div class="remodal" data-remodal-id="tool-confirm">
					<button data-remodal-action="close" class="remodal-close"></button>
					<div>是否确定？</div>
					<button data-remodal-action="cancel" class="remodal-cancel">取消</button>
					<button data-remodal-action="confirm" class="remodal-confirm">确定</button>
				</div>
			<#else>
			    <h2>您的购物车是空的，快去选购吧</h2>
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
		showTools('action-del', $('#btn-del'));
		showTools('action-buy', $('#btn-buy'));
	});
	$(".cb_single").click(function() {
		if(!$(this).prop("checked")) {
			$("#cb_all").prop("checked", false);
		}
		showTools('action-del', $('#btn-del'));
		showTools('action-buy', $('#btn-buy'));
	});
	
	// 删除购物车中选中书籍
	$("#btn-del").click(function() {
		if($(this).attr("can-do")=='true') {
			if(confirm("确定要从购物车中移除吗？")) {
				$.ajax({
					url: 'book/delete?ids=' + getCheckedValue(),
					type: 'DELETE',
					async: false,
					success: function(result) {
						if(result == 'ok') {
							location.reload()
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