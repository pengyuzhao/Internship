function checkValid(){
  

    var source= document.getElementById("source").value;
    var vedio= document.getElementById("vedio").value;
      if (source==""||vedio=="")
  {   
         alert("��ѡ���ļ���");
         return false;
    }
  else{
  			
  			   	var str=document.getElementById("source").value;
 				var pos = str.lastIndexOf(".");
 				var lastname = str.substring(pos,str.length)
 				var str1=document.getElementById("vedio").value;
 				var pos1 = str1.lastIndexOf(".");
 				var lastname1 = str1.substring(pos1,str1.length)
 				if (!(lastname.toLowerCase()==".rar" || lastname.toLowerCase()==".zip" ))
			 {
  			   alert("���ϴ����ļ�����Ϊ"+lastname+"���ļ�����Ϊ.rar�� .zip����");
   				  return false;
 			}
 				if(!(lastname1.toLowerCase()==".wmv" || lastname1.toLowerCase()==".avi" ))
 				{
 					alert("���ϴ����ļ�����Ϊ"+lastname1+"���ļ�����Ϊ.wmv�� .avi����");
   				    return false;
 				}
  				return true;
		}
	
    
}