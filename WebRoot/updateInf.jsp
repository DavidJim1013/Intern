<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
Map<String,Object> map = (Map<String,Object>)request.getAttribute("clientMap");  
  
%>   
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>修改客户信息</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <!--  
    <link rel="stylesheet" type="text/css" href="styles.css">  
    -->  
<script type="text/javascript">  
function dosubmit(){  
	if (form1.name.value == ""){
		alert("姓名不能为空，请输入姓名！");
		form1.name.focus(); 
		return false;
	}
	if (form1.Phone.value.length != 11 && form1.Phone.value != ""){
		alert("请检查手机号");
		form1.Phone.focus(); 
		return false;
	}
	if (form1.year.value != "" || form1.day.value != "" || form1.month.value != ""){
		if (form1.year.value <= 1900 || form1.year.value > 2021){
			alert("请检查生日");
			form1.year.focus(); 
			return false;
		}
		if (form1.month.value == ""){
			alert("请检查生日");
			form1.month.forcus();
			return false;
		}
		if (form1.day.value < 1 || form1.day.value > 31){
			alert("请检查生日");
			form1.day.focus(); 
			return false;
		}
	}
	
    var th = document.form1;  
    th.action="<%= path%>/servlet/ClientAction?action_flag=update";  
    th.submit();    
}

  
</script>  
  </head>  
    
  <body>  
    <div align="center">  
      
        <table width=70% style="margin:auto;">  
            <tr><td align="center" height=20 valign="bottom">客户信息修改</td></tr>  
            <tr>  
                <td>  
                    <form id="form1" name="form1" action="" method="post" enctype="multipart/form-data">  
                    <table border=1 style="margin:auto">  
                    	
                        <tr >  
                            <td>姓名</td>  
                            <td><input type="text" name="name" id="name" value="<%=map.get("name") %>"/></td>  
                            <td>性别</td>
                            <td><input type="radio" value="男" checked name="sex">男
								<input type="radio" name="sex" value="女">女<br>
							</td>  

                        </tr>  
                        <tr>  
                            <td>生日</td>
								<td colspan="3" ><input name="year" size="4" maxlength=4>年
								<select name="month">
									<option value="" selected>请选择</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="08">07</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
								</select>月
								<input name="day" size="2" maxlength=2>日
								<br></td>  
                        </tr>  
                        <tr> 
                            <td>手机</td>
							<td><input type="text" name="Phone" id="Phone" value="<%=map.get("Phone") %>" maxlength=11/></td>
							<td>职业</td>
							<td><input type="text" name="Job" id="Job" value="<%=map.get("Job") %>" /></td> 
                        </tr>  
                        <tr>  
                            <td>备注</td>
							<td colspan="4"><textarea rows="10" cols="30" name="remark" id="remark"></textarea></td>
                        </tr>
                        <tr style="display:none">  
                            <td>客户ID</td>  
                            <td colspan="3"><input type="text" name="clientid" id="clientid" value="<%=map.get("clientid") %>"/></td>  
                        </tr>
                    </table>   
                    </form>                     
                  
                </td>  
            </tr>  
            <tr>  
                <td colspan="4" align="center">  
                    <button type="button" onclick="javascript:dosubmit();">确定</button>  
                    <button type="button" onclick="javascript:location.href='main.jsp'">返回</button>  
                  
                </td>  
            </tr>  
              
          
        </table>  
          
    </div>  
  </body>  
  <script>
  	var a = "<%=map.get("Other")%>";
  	document.getElementById("remark").value = a;
  </script>
</html>  