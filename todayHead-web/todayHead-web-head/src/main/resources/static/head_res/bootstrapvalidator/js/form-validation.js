/**
 * 表单验证的插件
 * @param $
 * @returns
 */
(function($) {
	/* 全局对象 */
	var instance ;
	
	/**
	 * 这里是私有的方法,由于是闭包,所以外面访问不到
	 */
	var FormValidation = function(elements,options)
	{
		this.settings = options;
		this.elements = elements ;
		/* 调用初始化代码 */
		this.init();
	};
	
	/**
	 * 原型对象
	 */
	FormValidation.prototype = 
	{
		init : function()
		{
			if(this.settings.config == undefined || this.settings.config.toString.length == 0 )
			{
				/* 如果木有传config,就给一个默认的 */
				this.settings.config = $.fn.FormValidation.default;
			}
			
			// 初始化的代码
			/* 为页面上的fields增加显示的元素 */
			/* 找到所有的需要验证的控件 */
			var fields = this.settings.fields; 
			for(var fieldsTemp in fields)
			{
				var fieldsTempChild = this.elements.find(":input[name="+ fieldsTemp +"]");
 				fieldsTempChild.after("<div id='"+ fieldsTemp + this.settings.config.fieldsSuffix +"' class='"+ this.settings.config.validatorUn +"'></div>");
			}
		}
	}
	
	/**
	 * 方便链式调用
	 */
	$.fn.FormValidation = function(options)
	{
		if(this.length != 1)
		{
			return this ; 
		}else
		{
			/* 单例模式 */
			var me = $(this) ; 
			/* 把对象存储到页面上的dom元素上 */
			instance = me.data("FormValidation");
			if(!instance)
			{
				instance = new FormValidation(me,options);
				me.data("FormValidation",instance);
			}
			
			/* 判断options的类型 */
			if($.type(options) == 'string')
			{
				return instance[options];
			}
		}
		
		/* 重新赋值 */
		var form = $(this) ; 
		/* 提交时的一些操作 */
		$(this).submit(function()
		{
			var allFlag = true ; 
			/* 提取配置中的fields */
			var fields = options.fields; 
			/* 循环所有的fields */
			for(var fieldsTemp in fields)
			{
				var fieldsValue = fields[fieldsTemp] ; 
				
				/* 验证结果 */
				var fieldFlag = validatorField(fieldsValue,fieldsTemp,form,options);
				if(!fieldFlag)
				{
					allFlag = false ; 
				}
			}
			//console.info("==allFlag====>" + allFlag)
			/* 验证成功 */
			//form.addClass("was-validated");
			return allFlag ; 
		});
		
		/* 表单所有控件的失去焦点事件 */
		form.find(":input").blur(function(a,b)
		{
			/*
			 * 取到对应字段的值
			 * */
			var fields = options.fields ; 
			for(var fieldsTemp in fields)
			{
				if(fieldsTemp != $(this).attr("name"))
				{
					continue ; 
				}
				var fieldsValue = fields[fieldsTemp] ; 
				
				/* 验证结果 */
				var fieldFlag = validatorField(fieldsValue,fieldsTemp,form,options);
				if(!fieldFlag)
				{
					allFlag = false ; 
				}
			}
		});
		return this ; 
	}
	
	/* 默认的配置参数 */
	$.fn.FormValidation.default = 
	{
		/* dom元素不同状态的显示 */
		"validatorUn":"valid-feedback",
		"validatorErr":"invalid-feedback",
		"validatorEd":"was-validated",
		"invalidUnTooltip":"valid-tooltip",
		"invalidErrTooltip":"invalid-tooltip",
		"invalidEdTooltip":"was-tooltip",
		"fieldsSuffix":"Tip"
	};
	
	/**
	 * 直接验证表单中的某个控件
	 * @param fieldsValue:某一个控件的验证器的值
	 * @param fieldsName:某一个控件的验证器的键
	 * @param form:表单对象
	 * @param option:可选项
	 */
	function validatorField(fieldsValue,fieldsTemp,form,options)
	{
		var fieldsTempEle = form.find(":input[name="+ fieldsTemp +"]");
		var fieldsTempTip = form.find("#"+ fieldsTemp + options.config.fieldsSuffix +"");
		if(fieldsTempEle.length == 0 )
		{
			return true ; 
		}
		/* 获取到一些验证器 */
		var fieldValis = fieldsValue.validators; 
		/* 调用提交的一些方法 */
		var flag = true ; 
		/* 循环一个Fields中的所有验证器 */
		for(var valiTemp in fieldValis)
		{
			var valiValue = fieldValis[valiTemp] ; 
			if(valiTemp == 'notEmpty' && flag)
			{
				/* 调用不为空的方法 */
				flag = notEmpty(fieldsTempEle,fieldsTempTip,valiValue);
			}else if(valiTemp == 'stringLength' && flag)
			{
				/* 调用不为空的方法 */
				flag = stringLength(fieldsTempEle,fieldsTempTip,valiValue);
			}else if(valiTemp == 'regexp' && flag)
			{
				/* 调用不为空的方法 */
				flag = regexp(fieldsTempEle,fieldsTempTip,valiValue);
			}else if(valiTemp == 'equalsTo' && flag)
			{
				/* 调用不为空的方法 */
				flag = equalsTo(fieldsTempEle,fieldsTempTip,valiValue , form);
			}
		}
		/* 所有验证正确,清除错误 */
		if(flag)
		{
			cleanErr(fieldsTempEle,fieldsTempTip);
		}
		/* 存储最终的结果标志 */
		//fieldsValue.valiFlag = flag ; 
		return flag ; 
	}
	
	/**
	 * 验证字符串是否为空
	 * @param ele 验证的元素
	 * @param tip 提示内容的元素
	 * @param defOptions 配置项的内容
	 */
	function notEmpty(ele,tip,defOptions)
	{
		var eleVal = ele.val();
		if(eleVal == '')
		{
			/* 出错了 */
			tip.html(defOptions.message);
			/* 出错的样式 */
			tip.attr("class" , instance.settings.config.invalidErrTooltip);
			tip.show();
			return false ; 
		}
		return true ; 
	}
	
	/**
	 * 验证字符串的长度
	 * @param ele 验证的元素
	 * @param tip 提示内容的元素
	 * @param defOptions 配置项的内容
	 */
	function stringLength(ele,tip,defOptions)
	{
		var eleVal = ele.val();
		if(eleVal.length < defOptions.min || eleVal.length > defOptions.max )
		{
			/* 出错了 */
			tip.html(defOptions.message);
			/* 出错的样式 */
			tip.attr("class" , instance.settings.config.invalidErrTooltip);
			tip.show();
			return false ; 
		}
		return true ; 
	}
	
	/**
	 * 正则表达式是否正确
	 * @param ele 验证的元素
	 * @param tip 提示内容的元素
	 * @param defOptions 配置项的内容
	 */
	function regexp(ele,tip,defOptions)
	{
		/* 获取到元素的值 */
		var eleVal = ele.val();
		var regExp = defOptions.regexp ; 
		var flag = regExp.test(eleVal);
		if(!flag)
		{
			/* 出错了 */
			tip.html(defOptions.message);
			/* 出错的样式 */
			tip.attr("class" , instance.settings.config.invalidErrTooltip);
			tip.show();
			return false ; 
		}
		return flag ; 
	}
	
	/**
	 * 比较密码是否相等
	 * @param ele 验证的元素
	 * @param tip 提示内容的元素
	 * @param defOptions 配置项的内容
	 */
	function equalsTo(ele,tip,defOptions,form)
	{
		var eleVal = ele.val();
		var passVal = form.find(":input[name="+ defOptions.name +"]").val();
		if(eleVal != passVal)
		{
			/* 出错了 */
			tip.html(defOptions.message);
			/* 出错的样式 */
			tip.attr("class" , instance.settings.config.invalidErrTooltip);
			tip.show();
			return false ; 
		}
		return true ; 
	}
	
	/**
	 * 清除错误
	 */
	function cleanErr(ele,tip)
	{
		tip.hide();
	}
}(window.jQuery));