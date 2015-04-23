function checkLogin(){

	var name=document.form1.username.value;
	var passwd=document.form1.passwd.value;
	var userCode=document.form1.userCode.value;
	if(name==""||passwd==""||userCode=="")
	{	
		alert("登陆信息不能为空");
		return false;
	}
	var power;
	if(!(document.form1.power[0].checked)&&!(document.form1.power[1].checked)&&!(document.form1.power[2].checked)){
	
		alert("请选择权限");
		return false;
	}
	
	
}