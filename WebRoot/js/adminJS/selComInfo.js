function goSubmitOrder() {

		window.location.href = "/Internship/SelComInfoCl?type=selAll";
	}
	
	 function check(){

  		if(window.confirm("提示！此操作会删除这个公司的所有数据，确定要删除吗？")){
   			 return true;
  		}else{
  			  return false;
 		 }
	}