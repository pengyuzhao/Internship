function checkValid(){
  

    var source= document.getElementById("source").value;
    var vedio= document.getElementById("vedio").value;
      if (source==""||vedio=="")
  {   
         alert("请选择文件！");
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
  			   alert("您上传的文件类型为"+lastname+"，文件必须为.rar或 .zip类型");
   				  return false;
 			}
 				if(!(lastname1.toLowerCase()==".wmv" || lastname1.toLowerCase()==".avi" ))
 				{
 					alert("您上传的文件类型为"+lastname1+"，文件必须为.wmv或 .avi类型");
   				    return false;
 				}
  				return true;
		}
	
    
}