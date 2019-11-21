<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基于RSA的人才一体化系统</title>
<meta name="viewport" content="width=600">
<script type="text/javascript" src="js/jsencrypt.js"></script>
<link rel="stylesheet" href="css/main.css" media="screen" type="text/css" />
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
<!-- 标题盒子 -->
<div id="header">
    <p>
        <span data-text="基">基</span>
        <span data-text="于">于</span>
        <span data-text="RSA">RSA</span>
        <span data-text="的">的</span>
        <span data-text="人">人</span>
        <span data-text="才">才</span>
        <span data-text="一">一</span>
        <span data-text="体">体</span>
        <span data-text="化">化</span>
        <span data-text="系">系</span>
        <span data-text="统">统</span>
    </p>
</div>
<!-- 登录盒子 -->
<div id="login">
    <div class="tit01">
        用户登录
    </div>
    <form name="user" action="login.action" onsubmit="fun();" autocomplete='off'>
        <!-- 用户名 -->
        <div class=" input-group input-group-md" style="width: 280px;margin: auto;">
            <span class="input-group-addon" id="sizing-addon1">
        <i class="glyphicon glyphicon-user" aria-hidden="true"></i>
            </span>
            <input type="text" name="ruUserName" class="form-control" placeholder="用户名" aria-describedby="sizing-addon1" style="height:35px;" />
        </div>
        <br />
        <!-- 密码 -->
        <div class="input-group input-group-md" style="width: 280px;margin: auto;">
            <span class="input-group-addon" id="sizing-addon1">
        <i class="glyphicon glyphicon-lock"></i>
            </span>
            <input type="password" name="ruPassword" class="form-control" placeholder="密码" aria-describedby="sizing-addon1" style="height:35px;" />
        </div>
        <br />
        <!-- 验证码 -->
        <div class="input-group" style="width: 280px;margin: auto;">
            <input class="form-control " name="verifyCode"  placeholder="验证码" style="height:40px;">
            <div class="input-group-addon">
                <img alt="点击更换验证码" src="verifyCode.action" onclick="this.src='verifyCode.action?'+ Math.random()">
            </div>
        </div>
        <div class="input-group" style="width: 280px;margin: auto;"><span style="color:red;">${message} ${info}</span></div>
        <br>
        <!-- 登录按钮 -->
        <button type="submit" class="btn btn-success btn-block" style="width: 282px;margin: -8px auto 0;">登录</button>
        <a class="btn btn-sm btn-white btn-block" style="text-align: right;" href="beforeRegister.action"><h6>还没有账户？前往注册</h6></a>
    </form>

</div>
<!-- 底部 -->
<div id="footer">
    <div class="box">
        版权所有：潘卉 技术支持：SSM 最佳使用分辨率：1920*1280
    </div>
</div>
</body>
<script type="text/javascript">
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