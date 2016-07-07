<#import "/macro/staticImport.ftl" as s>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<@s.staticImport />
</head>

<body>
<div class="main-panel">
	<div class="page-detail">
		<h3>book信息</h3>
		<table class="table-list">
			<form id="form-add" action="add" method="post">
				  <label class="form-label">
					  <span class="form-name"><span class="required">*</span>图书ID：</span>
					  <input type="text" name="id" placeholder="请输入图书ID" style="width:200px"></br>
				  </label>
				  <label class="form-label">
		              <span class="form-name"><span class="required">*</span>图书名称：</span>
		              <input type="text" name="name"  placeholder="请输入图书名称" style="width:200px"></br>
		          </label>
				  <label class="form-label">
					  	<span class="form-name"><span class="required">*</span>图书类型：</span>
			            <select name="type">
			            	<#list enums["com.train.bookshop.enums.BookType"]?values as e> 
				            	<option value="${e.value}">${e.getName()}</option>
			            	</#list>
			            </select>
		            <br/>
		            </label>
	              <label class="form-label">
				  	  	<span class="form-name">图书简介：</span>
	            	  	<input type="text" name="summary" placeholder="请输入图书简介" style="width:200px"></br>
		          </label>
				  <label class="form-label">
					  	<span class="form-name"><span class="required">*</span>图书价格：</span>
		            	<input type="text" name="price" placeholder="请输入图书价格(单位分)" style="width:200px">
		            	</span>(单位分)：</span></br>
		          </label>
				  <label class="form-label">
					  	<span class="form-name"><span class="required">*</span>图书数量：</span>
		            	<input type="text" name="count" placeholder="请输入图书数量" style="width:200px"></br>
		          </label>
		          
		          <div class="form-footer">
			          <input type="submit" value="确认" class="form-btn">
			      </div>
            </form>	
		</table>
	</div>
</div>
</body>
</html>