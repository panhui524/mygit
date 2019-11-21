//验证码
function getyzm(){	
	var val=parseInt(Math.random()*10000);
	var str=val<1000?val+"0":(val<100?val+"00":(val<10?val+"000":val));
	document.getElementById("getyzm").innerHTML="<b>"+str+"</b>";
}

//验证输入验证码是否正确
function ckyzm(){
	
	var yzm=document.getElementById("getyzm").innerText;
	var txtyzm=document.myform.veryCode.value;
	var sobj=document.getElementById("yzmid");
	if(txtyzm==yzm){
		sobj.innerHTML="验证码正确";
		sobj.style.color="darkgreen";
		return true;
	}else{
		sobj.innerHTML="验证码错误";
		sobj.style.color="red";
		getyzm();//输入不正确，需要重新获得验证码
		return false;
	}	
}

//整理验证方法
function cksub(){
	return ckyzm();	
}