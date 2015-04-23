function checkPwd(){
	 
  	 
  	var pre= document.getElementById("pre").value;
  	var now= document.getElementById("now").value;
  	var comfirm= document.getElementById("comfirm").value;
  	  if(pre==""||now==""||comfirm=="")
  	  {
  	  	alert("不能为空");
  	  	return false;
  	  }
  	    if(now!=comfirm)
  	    {
  	    	alert("两次密码不相同");
  	  		return false;
  	    }
	
}