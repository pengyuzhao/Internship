function goSubmitOrder() {

		window.location.href = "/Internship/SelStuInfoCl?type=selAll";
	}
	
	function gotoPageNow(){
		
		var pageNow=document.getElementById('pageNow');
		window.open('/Internship/SelStuInfoCl?type=<%=request.getAttribute("type") %>&pageNow='+pageNow.value,'_self');
	}
	
	  function check(){

  		if(window.confirm("��ʾ���˲�����ɾ����ѧ�����������ݣ�ȷ��Ҫɾ����")){
   			 return true;
  		}else{
  			  return false;
 		 }
	}