function goSubmitOrder() {

		window.location.href = "/Internship/SelStuInfoCl?type=selAll";
	}
	
	function gotoPageNow(){
		
		var pageNow=document.getElementById('pageNow');
		window.open('/Internship/SelStuInfoCl?type=<%=request.getAttribute("type") %>&pageNow='+pageNow.value,'_self');
	}
	
	  function check(){

  		if(window.confirm("提示！此操作会删除此学生的所有数据，确定要删除吗？")){
   			 return true;
  		}else{
  			  return false;
 		 }
	}