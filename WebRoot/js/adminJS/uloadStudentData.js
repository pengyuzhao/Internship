function checkFile() {
		//�ڱ������һ������Ԫ�أ�Ŀ���ǲ����Ƿ��ö�̬���û������Ա���Servlet�н��ղ����ݵ����ݿ���  
		var f = document.uploadForm.upload.value;
		//��Ԫ�ص�id��ø�Ԫ�ص�ֵ���Ӷ������ж�ѡ����ļ��Ƿ�Ϸ�  
		var file = document.uploadForm.upload.value;
		if (file == null || file == "") {
			alert("�㻹û��ѡ���κ��ļ��������ϴ�!");
			return;
		}
		if (file.lastIndexOf(".") == -1) {
			alert("·������ȷ!");
			return;
		}
		var allImgExt = ".xls";
		var extName = file.substring(file.lastIndexOf("."));
		if (allImgExt.indexOf(extName) == -1) {

			errMsg = "���ļ����Ͳ������ϴ������ϴ� " + allImgExt + " ���͵��ļ�����ǰ�ļ�����Ϊ" + extName;
			alert(errMsg);
			return;
		}
		document.uploadForm.submit();
	}

	function confirmOper() {
		return window.confirm('ȷ���Ѵ�ѧ����Ϣ�������ݿ���');
	}