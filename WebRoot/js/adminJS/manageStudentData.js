	function goSubmitOrder() {

		window.location.href = "/Internship/SelStuAuditCl?type=selAllAudit";
	}
	
	function gotoPageNow(){
		
		var pageNow=document.getElementById('pageNow');
		window.open('/Internship/SelStuAuditCl?type=<%=request.getAttribute("type") %>&pageNow='+pageNow.value,'_self');
	}			
	
	 function check(){

  		if(window.confirm("确定批准通过该同学的简历吗？")){
   			 return true;
  		}else{
  			  return false;
 		 }
	}
	