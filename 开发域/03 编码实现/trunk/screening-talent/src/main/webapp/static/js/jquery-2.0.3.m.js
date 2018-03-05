function CompanyForm(hasErrors) {
	this.checkForm();
	this.protocol(hasErrors);
	this.keydown();
}

CompanyForm.prototype = {
	protocol : function(hasErrors) {

		if (hasErrors != "Y") {
			$("#myModal").modal({
				"backdrop" : "static",
				"keyboard" : true,
				"show" : true
			});
		}

		$('#readProtocol').click(function() {
			$("#myModal").modal({
				"backdrop" : "static",
				"keyboard" : true,
				"show" : true
			});
		});

		$('#agree').click(function() {
			$("#viewprotocol").prop("checked", true);
			$("#submit").removeAttr("disabled");
			$("#myModal").modal("hide");// 弹出框隐藏
		});

		$("#viewprotocol").change(function() {
			if ($(this).is(":checked")) {
				$("#submit").removeAttr("disabled");
			} else {
				$("#viewprotocol").prop("checked", false);
				$("#submit").attr("disabled", "disabled");
			}
		});
	},
	checkForm : function() {
		$.ajaxSetup({
			async : false
		});

		var usernameFlag = true;
		var passFlag = true;
		var repasswordFlag = true;
		var rePasswordFlag = true;
		var companyNameFlag = true;
		var companyEmailFlag = true;
		var contactsFlag = true;
		var contactsPhoneFlag = true;
		var file1Flag = true;
		var file2Flag = true;

		var usernameExist = false;
		var companyNameExist = false;
		var companyEmailExist = false;
		// isExistName();
		// isExistEmail();
		submit();
		function submit() {

			$('#submit').click(
					function(event) {

						usernameFlag = checkUsername();
						passFlag = checkPass();
						repasswordFlag = checkRepassword();
						rePasswordFlag = checkRepassword2();
						companyNameFlag = checkCompanyName();
						companyEmailFlag = checkEmail();
						contactsFlag = checkContacts();
						contactsPhoneFlag = checkPhone();
						file1Flag = checkFile1();
						file2Flag = checkFile2();

						var href = "../is_exist_username";
						var value = $('#username').val();

						$.get(href, {
							username : value
						}, function(data) {
							var result = JSON.stringify(data);
							if (result == 'true') {
								$("#usernameExistError").css('display', '');
								usernameExist = true;
							} else {
								usernameExist = false;
							}

						}, 'json');

						// 公司名查是否重复
						href = "../is_exist_companyEmail";
						value = $('#companyEmail').val().trim();

						$.get(href, {
							companyEmail : value
						},
								function(data) {
									var result = JSON.stringify(data);
									if (result == 'true') {
										$("#companyEmailExistError").css(
												'display', '');
										companyEmailExist = true;
									} else {
										companyEmailExist = false;
									}
								}, 'json');

						// 公司名查是否重复
						href = "../is_exist_companyName";
						value = $('#companyName').val().trim();
						$.get(href, {
							companyName : value
						}, function(data) {
							var result = JSON.stringify(data);
							if (result == 'true') {
								$("#companyNameExistError").css('display', '');
								companyNameExist = true;
							} else {
								companyNameExist = false;
							}
						}, 'json');

						if (!usernameFlag || !passFlag || !repasswordFlag
								|| !rePasswordFlag || !companyNameFlag
								|| !companyEmailFlag || !contactsFlag
								|| !contactsPhoneFlag || !file1Flag
								|| !file2Flag || companyNameExist
								|| companyEmailExist || usernameExist) {
							event.preventDefault()
						}

						// document.forms['CompanyForm'].submit();

					});
		}

		function checkFile1() {
			var file1 = $('#fileuploade').val();
			if (file1 == "") {
				$('#urlbusiImg_null_error').css('display', '');
				return false;
			} else {
				if (file1.indexOf(".jpg") < 0) {
					$('#urlbusiImg_format_error').css('display', '');
					return false;

				}
				return true;
			}
		}

		function checkFile2() {
			var file2 = $('#fileuploade2').val();
			if (file2 == "") {
				$('#urlbusiImg2_null_error').css('display', '');
				return false;
			} else {
				if (file2.indexOf(".jpg") < 0) {
					$('#urlbusiImg2_format_error').css('display', '');
					return false;

				}
				return true;
			}
		}

		// 电话
		function checkPhone() {
			var phone = $("#contactsPhone").val().trim();
			var isPhone = /^0(\d{10}|\d{2}-\d{8}|\d{3}-\d{7})$/;
			var isMob = /^((\+?86)|(\(\+86\)))?(13[0123456789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
			if (!isPhone.test(phone) && !isMob.test(phone)) {
				$('#contactsPhoneError').css('display', '');
				return false;
			} else {
				return true;
			}
		}

		// 联系人
		function checkContacts() {
			var username = $('#contacts').val().trim();
			var length = username.length;
			if (length < 2 || length > 15) {
				$('#contactsError').css('display', '');
				return false;
			} else {
				return true;
			}

		}

		// 邮箱格式
		function checkEmail() {
			var email = $("#companyEmail").val().trim();
			var myreg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
			if (!myreg.test(email)) {
				$('#companyEmailError').css('display', '');
				return false;
			} else {
				return true;
			}
		}

		// 公司名称
		function checkCompanyName() {
			var username = $('#companyName').val().trim();
			var length = username.length;
			if (length < 2 || length > 25) {
				$('#companyNameError').css('display', '');
				return false;
			} else {
				return true;
			}

		}

		function checkRepassword2() {
			var password = $('#pass').val();
			var repassword = $('#repassword').val();
			var rePassword = $('#rePassword').val();
			if (repassword == undefined) {
				if (password != rePassword) {
					$('#repasswordError').css('display', '');
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}

		}

		// 检查确认密码
		function checkRepassword() {
			var password = $('#pass').val();
			var repassword = $('#repassword').val();
			var rePassword = $('#rePassword').val();
			if (rePassword == undefined) {
				if (password != repassword) {
					$('#repasswordError').css('display', '');
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		}

		// 检查username
		function checkUsername() {
			var username = $('#username').val().trim();
			var length = username.length;
			if (length < 2 || length > 16) {
				$('#usernameError').css('display', '');
				return false;
			} else {
				return true;
			}

		}
		// 检查密码
		function checkPass() {
			var username = $('#pass').val();
			var length = username.length;
			var reg = /^.*[a-zA-Z]+.*$/;
			if (!reg.test(username)) {
				$('#passwordStrError').css('display', '');
				return false;
			} else {
				if (length < 2 || length > 16) {
					$('#passwordError').css('display', '');
					return false;
				}
				return true;
			}

		}

	},
	keydown : function() {

		propkeydown('username', 'usernameError');
		propkeydown('pass', 'passwordError');
		propkeydown('pass', 'passwordStrError');
		propkeydown('repassword', 'repasswordError');
		propkeydown('rePassword', 'repasswordError');
		propkeydown('companyName', 'companyNameError');
		propkeydown('companyEmail', 'companyEmailError');
		propkeydown('contacts', 'contactsError');
		propkeydown('contactsPhone', 'contactsPhoneError');

		function propkeydown(propertyId, errorId) {
			$('#' + propertyId).keydown(function() {
				$('#' + errorId).css('display', 'none');
				$('#' + propertyId + "ExistError").css('display', 'none');
				if (propertyId == 'pass') {
					$('#repasswordError').css('display', 'none');
				}
			});

			$('#' + propertyId).change(function() {
				$('#' + errorId).css('display', 'none');
				$('#' + propertyId + "ExistError").css('display', 'none');
				if (propertyId == 'pass') {
					$('#repasswordError').css('display', 'none');
				}
			});
		}
	}
};

function getRootPath() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}
// username
/*
 * $("#username").bind("blur", function() { var username = this.value; if
 * (username != "") { // 用户名 if (username.length < 2 || username.length > 16) {
 * $("#username_error").show(); // $("#submit").attr("disabled","disabled");
 * return false; } else { // $("#submit").removeAttr("disabled");
 * $("#username_error").hide(); return true; } } }); // password
 * $("#pass").bind("blur", function() { var password = this.value; if (password !=
 * "") { if (password.length < 2 || password.length > 16) {
 * $("#password_error").show(); // $("#submit").attr("disabled","disabled");
 * return false; } else { // $("#submit").removeAttr("disabled");
 * $("#password_error").hide(); return true; } } }); // repassword
 * $("#repassword").bind("blur", function() { var password = this.value; if
 * (password != "") { if (password.length < 2 || password.length > 16) {
 * $("#repassword_error").show(); // $("#submit").attr("disabled","disabled");
 * return false; } else { // $("#submit").removeAttr("disabled");
 * $("#repassword_error").hide(); } } var pass = $("#pass").val(); if (password !=
 * pass) { $("#repassword_two_error").show(); return false; } else {
 * $("#repassword_two_error").hide(); return true; }
 * 
 * }); // companyName $("#companyName").bind("blur", function() { var
 * companyName = this.value; if (companyName != "") { if (companyName.length < 2 ||
 * companyName.length > 16) { $("#company_error").show(); //
 * $("#submit").attr("disabled","disabled"); return false; } else { //
 * $("#submit").removeAttr("disabled"); $("#company_error").hide(); return true; } }
 * }); // email $("#companyEmail") .bind( "blur", function() { var comEmail =
 * this.value; if (comEmail != "") { // 对电子邮件的验证 var myreg =
 * /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
 * if (!myreg.test($("#companyEmail").val().trim())) { $("#emailLable").show(); //
 * $("#submit").attr("disabled","disabled"); return false; } else { //
 * $("#submit").removeAttr("disabled"); $("#emailLable").hide(); return true; } }
 * }); // contacts $("#contacts").bind("blur", function() { var contacts =
 * this.value; if (contacts != "") { if (contacts.length < 2 || contacts.length >
 * 15) { $("#contacts_error").show(); //
 * $("#submit").attr("disabled","disabled"); return false; } else { //
 * $("#submit").removeAttr("disabled"); $("#contacts_error").hide(); return
 * true; } } });
 */
// phone
/*
 * $("#contactsPhone") .bind( "blur", function() { var contactsPhone =
 * this.value;
 * 
 * var isPhone = /^([0-9]{3,4}-|[0-9]{3,4})?[0-9]{7,8}$/; var isMob =
 * /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
 * if (isMob.test(contactsPhone) || isPhone.test(contactsPhone)) { //
 * $("#submit").removeAttr("disabled"); $("#phone").hide(); return true; } else {
 * $("#phone").show(); // $("#submit").attr("disabled","disabled"); return
 * false; } });
 */
// check image
function checkFileSize() {
	$('#urlbusiImg_format_error').css('display', 'none');
	var maxsize = 2 * 1024 * 1024;// 2M
	var errMsg = "上传的附件文件不能超过2M！！！";
	var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。";
	var browserCfg = {};
	var ua = window.navigator.userAgent;
	if (ua.indexOf("MSIE") >= 1) {
		browserCfg.ie = true;
	} else if (ua.indexOf("Firefox") >= 1) {
		browserCfg.firefox = true;
	} else if (ua.indexOf("Chrome") >= 1) {
		browserCfg.chrome = true;
	}
	try {
		var obj_file = document.getElementById("fileuploade");
		if (obj_file.value == "") {
			$("#urlbusiImg_null_error").show();
			return false;
		} else {
			$("#urlbusiImg_null_error").hide();
		}

		var filesize = 0;
		if (browserCfg.firefox || browserCfg.chrome) {
			filesize = obj_file.files[0].size;
		} else if (browserCfg.ie) {
			var obj_img = document.getElementById('tempimg');
			obj_img.dynsrc = obj_file.value;
			filesize = obj_img.fileSize;
		} else {
			alert(tipMsg);
			return;
		}
		if (filesize == -1) {
			alert(tipMsg);
			return;
		} else if (filesize > maxsize) {
			$("#urlbusiImg_error").show();
			return;
		} else {
			$("#urlbusiImg_error").hide();
			return;
		}
	} catch (e) {
		// alert(e);
	}
}
// check image
function checkFileSize2() {
	$('#urlbusiImg2_format_error').css('display', 'none');
	var maxsize = 2 * 1024 * 1024;// 2M
	var errMsg = "上传的附件文件不能超过2M！！！";
	var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。";
	var browserCfg = {};
	var ua = window.navigator.userAgent;
	if (ua.indexOf("MSIE") >= 1) {
		browserCfg.ie = true;
	} else if (ua.indexOf("Firefox") >= 1) {
		browserCfg.firefox = true;
	} else if (ua.indexOf("Chrome") >= 1) {
		browserCfg.chrome = true;
	}
	try {
		var obj_file = document.getElementById("fileuploade2");
		if (obj_file.value == "") {
			$("#urlbusiImg2_null_error").show();
			return;
		} else {
			$("#urlbusiImg2_null_error").hide();
		}
		var filesize = 0;
		if (browserCfg.firefox || browserCfg.chrome) {
			filesize = obj_file.files[0].size;
		} else if (browserCfg.ie) {
			var obj_img = document.getElementById('tempimg2');
			obj_img.dynsrc = obj_file.value;
			filesize = obj_img.fileSize;
		} else {
			alert(tipMsg);
			return;
		}
		if (filesize == -1) {
			return;
		} else if (filesize > maxsize) {
			$("#urlbusiImg2_error").show();
			return;
		} else {
			$("#urlbusiImg2_error").hide();
			return;
		}
	} catch (e) {
		// alert(e);
	}
}

/*
 * $("#agree").bind("click",function(){
 * window.open(getRootPath()+"/public/company/agreeAlert",'newwindow',
 * 'height=600,width=800,top=100,left=300'); });
 */

function checkSize() {
	var obj_file = document.getElementById("fileuploade2");
	if (obj_file.value == "") {
		$("#urlbusiImg2_null_error").show();
		return false;
	}

	var one = document.getElementById("fileuploade");
	if (one.value == "") {
		$("#urlbusiImg_null_error").show();
		return false;
	}
}

function PasswordStrength(passwordID, strengthID) {
	this.init(strengthID);
	var _this = this;
	document.getElementById(passwordID).onkeyup = function() {
		_this.checkStrength(this.value);
	};
};
PasswordStrength.prototype.init = function(strengthID) {
	var id = document.getElementById(strengthID);
	var div = document.createElement('div');
	var strong = document.createElement('strong');
	this.oStrength = id.appendChild(div);
	this.oStrengthTxt = id.parentNode.appendChild(strong);
};
PasswordStrength.prototype.checkStrength = function(val) {
	var aLvTxt = [ '', '低', '中', '高' ];
	var lv = 0;
	if (val.match(/[a-z]/g)) {
		lv++;
	}
	if (val.match(/[0-9]/g)) {
		lv++;
	}
	if (val.match(/(.[^a-z0-9])/g)) {
		lv++;
	}
	if (val.length < 3) {
		lv = 0;
	}
	if (lv > 3) {
		lv = 3;
	}
	this.oStrength.className = 'strengthLv' + lv;
	this.oStrengthTxt.innerHTML = aLvTxt[lv];
};
function allinfo() {
	var ua = navigator.userAgent;
	ua = ua.toLowerCase();
	var match = /(webkit)[ \/]([\w.]+)/.exec(ua)
			|| /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua)
			|| /(msie) ([\w.]+)/.exec(ua) || !/compatible/.test(ua)
			&& /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) || [];

	// 如果需要获取浏览器版本号：match[2]

	switch (match[1]) {
	case "msie": // ie
		if (parseInt(match[2]) === 6) { // ie6
			//alert("暂时不支持IE7.0及以下版本浏览器，请升级您的浏览器版本！");
			// document.getElementById("hid").style.display = "none";
			// document.getElementById("show").style.display = "block";
			// document.getElementById("nosee_b").style.display = "none";
		} else if (parseInt(match[2]) === 7) { // ie7
			// alert("ie7");
			// document.getElementById("hid").style.display = "none";
			// document.getElementById("show").style.display = "block";
		} else if (parseInt(match[2]) === 8) { // ie8
			// this.protocol("Y");
			//alert("暂时不支持IE7.0及以下版本浏览器，请升级您的浏览器版本！");
		} else if (parseInt(match[2]) === 9) {
			// alert("ie9");
			// document.getElementById("hid").style.display = "none";
		}
		break;
	case "webkit": // safari or chrome
		// alert("safari or chrome");
		// document.getElementById("middle").style.display = "none";
		break;
	case "opera": // opera
		// alert("opera");
		break;
	case "mozilla": // Firefox
		// alert("Firefox");
		// document.getElementById("hid").style.display = "none";
		break;
	default:
		break;
	}
}