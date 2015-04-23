	function checkPwd(){ 
	var pwd= document.getElementById("pwd").value;
  		var newPwd= document.getElementById("newPwd").value;
  		var newPwd1= document.getElementById("newPwd1").value;
  	  if(pwd==""||newPwd==""||newPwd1=="")
  	  {
  	  	alert("不能为空");
  	  	return false;
  	  }
  	    if(newPwd!=newPwd1)
  	    {
  	    	alert("两次密码不相同");
  	  		return false;
  	    }
	
		}