function checkPwd(){
	 
  	 
  	var pre= document.getElementById("pre").value;
  	var now= document.getElementById("now").value;
  	var comfirm= document.getElementById("comfirm").value;
  	  if(pre==""||now==""||comfirm=="")
  	  {
  	  	alert("����Ϊ��");
  	  	return false;
  	  }
  	    if(now!=comfirm)
  	    {
  	    	alert("�������벻��ͬ");
  	  		return false;
  	    }
	
}