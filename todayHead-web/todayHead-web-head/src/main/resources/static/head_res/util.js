/*
 * js的全局变量
 * */
var rootPath = "/todayHead-web-head";

/**
 * 返回值:如果为false,事件阻止; ~获取当前页的dom元素,为当前页的dom元素赋值; ~获取每页多少条的dom元素,为每页多少条的dom元素赋值;
 * ~表单提交;
 */
function pageFormSubmit(formId, currentPageId, currentPageVal, pageSizeId,
		pageSizeVal) {
	// console.log("=====>" + $("#" + currentPageId).length);
	/* 获取当前页的dom元素,为当前页的dom元素赋值; */
	$("#" + currentPageId).prop("value", currentPageVal);
	$("#" + pageSizeId).prop("value", pageSizeVal);
	/*
	 * 通过js让表单提交 通过Jquery里面的id选择器来做
	 */
	$("#" + formId).submit();
	return false;
}

/**
 * 刷新验证码
 * 
 * @return
 */
function refreshCode(imgId) {
	/* 验证码的图片 */
	var imgPath = rootPath + "/common/imgCode?now=" + Math.random();
	$("#" + imgId).attr("src", imgPath)
	return false;
}

/**
 * 全选
 * 
 * @return
 */
function checkAll(currid, tarname) {
	$("input[name=" + tarname + "]").each(function() {
		$(this).prop("checked", $("#" + currid).prop("checked"));
	});
	return false;
}

/**
 * 检查必须选中一个
 * 
 * @return
 */
function checkOneSubmit(tarids) {
	if (!window.confirm('确认执行此操作吗?')) {
		return false;
	}

	// 设置标志位
	var flag = "0";
	$("input[name=" + tarids + "]").each(function() {
		if ($(this).prop("checked") && flag == "0") {
			flag = '1';
		}
	});

	if (flag == '1') {
		return true;
	}
	alert("至少选择一个");
	return false;
}

/**
 * 查询地区
 * parentId 如果为-1;表示不更新
 * @param currId 当前元素的Iid
 * @param tarId 目标元素的Id
 * @param finiId 最终存储值的id;如果id不存在,是不会赋值的;
 * @param rootFlag	查询一级节点(true);查询当前的节点:(false)
 * @returns
 */
function selectRegion(currId,tarId,finiId,rootFlag)
{
	/* 默认是第一级节点 */
	var firstParentId = "0" ; 
	
	var curr = $("#" + currId);
	var parentId = firstParentId ;  
	if(curr != 'undefined')
	{
		parentId = curr.val()
	}
	/* 如果取不到值 */
	if(parentId == '')
	{
		parentId = firstParentId ;
	}
	if(tarId == '')
	{
		return false ; 
	}
	
	if(finiId != '')
	{
		$("#" + finiId).val(parentId);
	}
	
	/* 请求的参数 */
	var queryParma = "json={'parentId':'"+ parentId +"'}&method=regionList";
	
	var tar = $("#" + tarId);
	/* ajax请求服务器,获取json数据 */
	$.post(
		rootPath + "/outer/requestOuter.htm",
		queryParma,
		function (data)
		{
			if(data.code == '0')
			{
				var childrenList = data.data.list ;
				
				/*
				拼装的目标HMTL
				<option value='-1'>请选择</option>
				 */
				var resSb = "<option value='0'>请选择</option>" ; 
				for(var i = 0 ; i < childrenList.length ; i ++ )
				{
					var regionTemp = childrenList[i] ; 
					if('true' == rootFlag)
					{
						resSb += "<option value='"+ regionTemp.id +"'>"+ regionTemp.pinyin + "("+ regionTemp.name +")</option>" ; 
					}else if("false"== rootFlag)
					{
						resSb += "<option value='"+ regionTemp.id +"'>"+ regionTemp.name +"</option>" ; 
					}
				}
				//alert(resSb);
				tar.html(resSb);
			}else
			{
				console.error(data.info);
			}
		},
		'json'
	);
	return false ; 
}