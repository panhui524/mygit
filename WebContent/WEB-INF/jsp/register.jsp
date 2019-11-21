<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="text/javascript" src="js/jsencrypt.js"></script>
</head>
<body>
<h1>注册页面</h1>
<form action="register.action"  name="user" autocomplete='off'>
            申报单位
    <select name="rduName" style="width: 150px;">
	  <option value ="volvo">Volvo</option>
	  <option value ="saab">Saab</option>
	  <option value="opel">Opel</option>
	  <option value="audi">Audi</option>
	</select><br>
	用户名<input type="text" name="ruUserName"><br>
             姓名<input type="text" name="ruName"><br>
            身份证号<input type="text" name="ruIdentityCode"><br>
	密码<input type="password" name="ruPassword"><br>
	手机号<input type="text" name=ruMobile><br>
	邮箱<input type="text" name="ruEmail"><br>
	身份
	<input type="radio" value="0" name="ruStatus">申报人
	<input type="radio" value="1" name="ruStatus">评委
	   <br>
	<input class="form-control " name="verifyCode"  placeholder="验证码" >
	<img alt="点击更换验证码" src="verifyCode.action" onclick="this.src='verifyCode.action?'+ Math.random()">
	<span style="color:red;">${message} ${info}</span>
	<input type="submit" value="注册" onclick="return fun();">
</form>

<div class="col-xs-7">
 <input type="text" class="form-control" placeholder="请选择上报机构" id="reportOrgan" οnblur="setTimeout('hideOrganDiv()',200);" οnfοcus="getOrganList();" οnkeydοwn="clearInput();" οnkeyup="innerKeydown();" autocomplete="off">
</div>

<div id="organ" οnclick="hideOrganDiv();">
       </div>

</body>
<script type="text/javascript">

function getOrganList(){

    var parms = new Object();   
    parms["reportOrgan"]=$("#reportOrgan").val();
    $.ajax({
        cache: true,
        type: "POST",
        url: "",
        data: parms,
        async: false,
        success:function(data){
            var json = $.parseJSON(data);
            if(json.data.length > 15){
                var html = "<table class='altrowstable'>";
            }else{
                var html = "<table class='altrowstables'>";
            }
            for(var i=0;i<json.data.length;i++){
                html += "<tr><td οnclick=\"selectValue(this);\">" +  json.data[i].sysOrganName + "</td></tr>";
            }
            html += "</table>";
            //将获得的数据填充到下拉的数据框里也就是table里
            $("#organ").html(html);
            $("#organ").css("display", "block");
            $("#organ").css("left", $("#reportOrgan").offset().left + "px");
            $("#organ").css("top", $("#reportOrgan").offset().top + $("#reportOrgan").height() + 14 + "px");
        },
        error: function(request) {
            layer.msg("Connection error", {
                icon : 2
            });
        }
    });
}

//选择机构
function selectValue(obj){
    var organName = $(obj).text();
    $("#reportOrgan").val(organName);
}
//输入框中根据用户输入内容动态查询
var clocker = null;
function innerKeydown() {
    if(null == clocker) {
        clocker = setTimeout(getOrganList,700);
        //连续击键，重新开始计时
    } else {
        clearTimeout(clocker);
        clocker = setTimeout(getOrganList,700);
    }
}

function hideOrganDiv(){
    $("#organ").css("display", "none");
}

function clearInput(){
    $("#reportOrgan").val("");
}


function fun() {
	// 获取 name为'user'的from元素下面的 name为'ruPassword'的元素的值
	// 就是获取 form 表单中的 密码
	var password = user.ruPassword.value;

	// 创建 JSEncrypt 对象
	var encrypt = new JSEncrypt();

	// 获取 session中的 publicKey
	encrypt.setPublicKey("${sessionScope.publicKey}");

	// 对密码加密，并且放入 input的value值中
	document.getElementsByName("ruPassword")[0].value = encrypt.encrypt(password);

	return true;
}
</script>
</html>