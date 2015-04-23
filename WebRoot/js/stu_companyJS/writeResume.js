 function previewImage(file)
        {
          var MAXWIDTH  = 260; 
          var MAXHEIGHT = 180;
          var div = document.getElementById('preview');
          if (file.files && file.files[0])
          {
              div.innerHTML ='<img id=imghead>';
              var img = document.getElementById('imghead');
              img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top+'px';
              }
              var reader = new FileReader();
              reader.onload = function(evt){img.src = evt.target.result;}
              reader.readAsDataURL(file.files[0]);
          }
          else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
          }
        }
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                
                if( rateWidth > rateHeight )
                {
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else
                {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
            
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }

function checkData()
     {
	
     	var name=document.form1.name.value;
     	var sex=document.form1.sex.value;
     	var nation=document.form1.nation.value;
     	var nativePlace=document.form1.nativePlace.value;
     	var birthday=document.form1.birthday.value;
     	var graduationYear=document.form1.graduationYear.value;
     	var education=document.form1.education.value;
     	var hightweight=document.form1.hightweight.value;
     	var graduationschool=document.form1.graduationschool.value;
     	var hopePlace=document.form1.hopePlace.value;
     	var professional=document.form1.professional.value;
     	var healthsituation=document.form1.healthsituation.value;
     	var qq=document.form1.qq.value;
     	var poscalcode=document.form1.poscalcode.value;
     	var picture=document.form1.picture.value;
     	var direction=document.form1.direction.value;
     	var hopeJob=document.form1.hopeJob.value;
     	var major=document.form1.major.value;
     	var punishsituation=document.form1.punishsituation.value;
     	var experiences=document.form1.experiences.value;
     	var evaluation=document.form1.evaluation.value;
 
     	if(name==""||sex==""||nation==""||nativePlace==""||birthday==""||graduationYear==""||education==""||hightweight==""||graduationschool==""||hopePlace==""
     	||professional==""||healthsituation==""||qq==""||poscalcode==""||picture==""||direction==""||hopeJob==""||major==""||punishsituation==""||experiences==""
     	||evaluation=="")
     	{  		
     		alert("尚有信息未填");
     		return false;	
     	}
    if(!(document.form1.sex[0].checked)&&!(document.form1.sex[1].checked))
   	{
     		alert("没有选择性别");
     		return false;				
    }
    
   var str="";
   for(i=0;i<document.form1.direction.length;i++)
   {
   		if(document.form1.direction[i].checked){
   			str+=document.form1.direction[i].value;	
   		}
   		
   }
	if(str=="")
	{
		alert("请至少选择一个精通方向");
		return false;
	}
		
	var strDate=document.form1.birthday.value;
	var  re =/^(\d{4})-(\d{2})-(\d{2})$/;		
	 if(!(re.test(strDate)))//判断日期格式符合YYYY-MM-DD标准
	   { 			
	   		alert("日期格式需要符合YYYY-MM-DD标准");
	   		return false;
	   
	   }else{
	   
	   	var  dateElement=new  Date(RegExp.$1,parseInt(RegExp.$2,10)-1,RegExp.$3);
	    if(!((dateElement.getFullYear()==parseInt(RegExp.$1))&&((dateElement.getMonth()+1)==parseInt(RegExp.$2,10))&&(dateElement.getDate()==parseInt(RegExp.$3))))//判断日期逻辑
	    {
	      	
	     	alert("日期不合逻辑");
	      	return false;
	  	 }
      
	   }  
	 var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	 if(!(filter.test(poscalcode)))
		 {
		 	alert("邮箱格式不正确");
	   		return false;
		 }
	 
     			
 }
 