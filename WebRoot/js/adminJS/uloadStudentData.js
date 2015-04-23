function checkFile() {
		//在表单中添加一个隐藏元素，目的是测试是否获得动态的用户名，以便在Servlet中接收并传递到数据库中  
		var f = document.uploadForm.upload.value;
		//用元素的id获得该元素的值，从而进行判断选择的文件是否合法  
		var file = document.uploadForm.upload.value;
		if (file == null || file == "") {
			alert("你还没有选择任何文件，不能上传!");
			return;
		}
		if (file.lastIndexOf(".") == -1) {
			alert("路径不正确!");
			return;
		}
		var allImgExt = ".xls";
		var extName = file.substring(file.lastIndexOf("."));
		if (allImgExt.indexOf(extName) == -1) {

			errMsg = "该文件类型不允许上传。请上传 " + allImgExt + " 类型的文件，当前文件类型为" + extName;
			alert(errMsg);
			return;
		}
		document.uploadForm.submit();
	}

	function confirmOper() {
		return window.confirm('确定把此学生信息导入数据库吗？');
	}