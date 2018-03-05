var top;
// XSI JS
$(function () {
    new XSIAdmin();
    new XSIEnterprise();
    new LeftSideBar();
});

function LeftSideBar() {
    this.sideBar();
}

LeftSideBar.prototype = {
    sideBar: function () {
        var navbar = $('#navbar').height();
        var menu = $('li[class="active"]').height();
        var str = $("#mainMenu").find('li.active').offset();
        if (str != undefined) {
            var str1 = str.top - navbar - menu;// 获取当前被选中的元素距离浏览器的高度-顶部和父元素的高度
            var stra = $("#click").scrollTop(str1);// 将取好的高度赋值给滚动条
        }
    }
};

function XSIAdmin() {
    this.activeMenus();
}

XSIAdmin.prototype = {
    activeMenus: function () {
        var href = location.pathname;

        if (href.indexOf("/admin/enterprise/list") > 0
            || href.indexOf("/enterpriseAdmin/enterprise/list") > 0) {
            $("#mainMenu li#adminInviteListMenu").addClass("active");
        } else if (href.indexOf("/NoVerify/false") > 0) {
            $("#mainMenu li#adminCompanyNoVerifyMenu").addClass("active");
        } else if (href.indexOf("/company/NoVerify/verify/") > 0) {
            $("#mainMenu li#adminCompanyNoVerifyMenu").addClass("active");
        } else if (href.indexOf("company/passVerify/") > 0) {
            $("#mainMenu li#adminCompanyPassVerifyMenu").addClass("active");
        } else if (href.indexOf("/enterpriseAdmin/company/create/pass") > 0) {
            $("#mainMenu li#adminCompanyPassVerifyMenu").addClass("active");
        } else if (href.indexOf("/enterpriseAdmin/company/create/verify") > 0) {
            $("#mainMenu li#adminCompanyNoVerifyMenu").addClass("active");
        } else if (href.indexOf("/admin/company/create/verify") > 0) {
            $("#mainMenu li#adminCompanyNoVerifyMenu").addClass("active");
        } else if (href.indexOf("/admin/company/create") > 0) {
            $("#mainMenu li#adminCompanyPassVerifyMenu").addClass("active");
        } else if (href.indexOf("/admin/memberExcep/list") > 0) {
            $("#mainMenu li#excepMemberMenu").addClass("active");
        } else if (href.indexOf("/admin/member") > 0) {
            $("#mainMenu li#adminMemberListMenu").addClass("active");
        } else if (href.indexOf("/credit/memberlevel/") > 0) {
            $("#mainMenu li#adminMemberLevelListMenu").addClass("active");
        } else if (href.indexOf("/credit/creditrule/") > 0) {
            $("#mainMenu li#adminCreditruleListMenu").addClass("active");
        } else if (href.indexOf("/credit/handwork") > 0) {
            $("#mainMenu li#adminHandworkListMenu").addClass("active");
        } else if (href.indexOf("/credit/stream") > 0) {
            $("#mainMenu li#adminStreamListMenu").addClass("active");
        } else if (href.indexOf("/admin/list") > 0) {
            $("#mainMenu li#adminListMenu").addClass("active");
        } else if (href.indexOf("/admin/add") > 0) {
            $("#mainMenu li#adminListMenu").addClass("active");
        } else if (href.indexOf("/admin/password/modify") > 0) {
            $("#mainMenu li#adminModifyPasswordMenu").addClass("active");
        } else if (href.indexOf("/admin/news") > 0) {
            $("#mainMenu li#adminNewsListMenu").addClass("active");
        } else if (href.indexOf("/admin/wx/merchant") > 0) {
            $("#mainMenu li#adminWxMerchantMenu").addClass("active");
        } else if (href.indexOf("/admin/product/") > 0) {
            $("#mainMenu li#adminProductMenu").addClass("active");
        } else if (href.indexOf("/admin/credit/record/") > 0) {
            $("#mainMenu li#adminCreditRecordMenu").addClass("active");
        } else if (href.indexOf("/admin/credit/record/") > 0) {
            $("#mainMenu li#enterpriseMemberMenu").addClass("active");
        } else if (href.indexOf("/admin/audit/") > 0) {
            $("#mainMenu li#adminAuditMenu").addClass("active");
        } else if (href.indexOf("/admin/position/") > 0) {
            $("#mainMenu li#adminPositionListMenu").addClass("active");
        } else if (href.indexOf("/admin/company/deleteList") > 0) {
            $("#mainMenu li#adminCompanyDelete").addClass("active");
        } else if (href.indexOf("/admin/tag/tree") > 0) {
            $("#mainMenu li#adminTagMenu").addClass("active");
        } else if (href.indexOf("/admin/question/list") > 0) {
            $("#mainMenu li#adminQuestionMenu").addClass("active");
        } else if (href.indexOf("/admin/question/form/create") > 0) {
            $("#mainMenu li#adminQuestionMenu").addClass("active");
        } else if (href.indexOf("/admin/question/form/edit_") > 0) {
            $("#mainMenu li#adminQuestionMenu").addClass("active");
        }
    }

};

function XSIEnterprise() {
    this.activeMenus();
}

XSIEnterprise.prototype = {

    activeMenus: function () {
        var href = location.pathname;
        if (href.indexOf("/enterprise/company/detail") > 0) {
            $("#mainMenu li#enterpriseCompanyDetailMenu").addClass("active");
        } else if (href.indexOf("/enterprise/member/query_list") > 0) {
            $("#mainMenu li#enterpriseMemberQueryMenu").addClass("active");
        } else if (href.indexOf("/enterprise/member/query") > 0) {
            $("#mainMenu li#enterpriseMemberQueryHistoryMenu").addClass(
                "active");
        } else if (href.indexOf("/enterprise/member/wide_detail") > 0) {
            $("#mainMenu li#enterpriseMemberQueryMenu").addClass("active");
        } else if (href.indexOf("/enterprise/member/tel_detail") > 0) {
            $("#mainMenu li#enterpriseMemberQueryMenu").addClass("active");
        } else if (href.indexOf("/enterprise/member/interview_detail") > 0) {
            $("#mainMenu li#enterpriseMemberQueryMenu").addClass("active");
        } else if (href.indexOf("/enterprise/member/entry_detail") > 0) {
            $("#mainMenu li#enterpriseMemberQueryMenu").addClass("active");
        } else if (href.indexOf("/enterprise/member/") > 0) {
            $("#mainMenu li#enterpriseMemberMenu").addClass("active");
        } else if (href.indexOf("/enterprise/company/stream") > 0) {
            $("#mainMenu li#enterpriseCompanyStreamMenu").addClass("active");
        } else if (href.indexOf("/company/modify_password") > 0) {
            $("#mainMenu li#enterpriseCompanyModifyPasswordMenu").addClass(
                "active");
        } else if (href.indexOf("/enterprise/invite/List") > 0) {
            $("#mainMenu li#enterpriseInviteListMenu").addClass("active");
        } else if (href.indexOf("/enterprise/list") > 0) {
            $("#mainMenu li#adminInviteListMenu").addClass("active");
        } else if (href.indexOf("/enterprise/company/creditbuy") > 0) {
            $("#mainMenu li#enterpriseBuyCreditMenu").addClass("active");
        } else if (href.indexOf("/enterprise/company/buy/") > 0) {
            $("#mainMenu li#enterpriseBuyCreditMenu").addClass("active");
        } else if (href.indexOf("/enterprise/company/credit/query") > 0) {
            $("#mainMenu li#enterpriseCreditQueryMenu").addClass("active");
        } else if (href.indexOf("/enterprise/audit/") > 0) {
            $("#mainMenu li#enterpriseAuditMenu").addClass("active");
        } else if (href.indexOf("/enterprise/exam/") > 0) {
            $("#mainMenu li#enterpriseMemberMenu").addClass("active");
        }
    },
    sideBar: function () {
        var navbar = $('#navbar').height();
        var menu = $('li[class="active"]').height();
        var str = $("#mainMenu").find('li.active').offset();
        var str1 = str.top - navbar - menu;// 获取当前被选中的元素距离浏览器的高度-顶部和父元素的高度
        var stra = $("#click").scrollTop(str1);// 将取好的高度赋值给滚动条
    }

};

/**
 * displaytag paginated use it. Don't change it
 *
 * @param formId
 * @param data
 */
function displaytagform(formId, data) {
    var $form = $("#" + formId);
    var action = $form.attr("action");
    var params = action.indexOf('?') == -1 ? '?' : '&';
    $.map(data, function (d) {
        params += (d.f + "=" + d.v + "&");
    });

    var url = action + params;
    var $targetDiv = $("div.displayTarget");
    if ($targetDiv.length > 0) {
        // if exist, load the content to the div
        $targetDiv.load(url);
    } else {
        location.href = url;
    }
}

/**
 * member_three.jsp
 *
 * @constructor
 */
function MemberThree() {
    this.datepicker();
    this.showOrHidden();
    this.select2();
    this.enterExam();
}

MemberThree.prototype = {
    enterExam: function () {
        $('#enter_exam').click(function () {
            var position = $('select[name="telIntentionPosition"]').find('option:selected').attr('id');
            var memberUuid = $('#memberUuid').val();
            window.location.href = contextPath + "/enterprise/exam/load/" + position + "/general_examination_setting_" + memberUuid;
        });
    },
    select2: function () {
        $("#telSelect").select2();
    },
    showOrHidden: function () {
        $("input[name='telIntention']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('ENABLED' == value) {
                $("div#intention").removeClass("hidden");
                $("div#noIntention").addClass("hidden");
            } else {
                $("div#intention").addClass("hidden");
                $("div#noIntention").removeClass("hidden");
                $('#entryDate').val('');
            }
        });

        $("input[name='agreeEntry']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('ENABLED' == value) {
                $("div#agreeEntry").removeClass("hidden");
                $("div#disagreeEntry").addClass("hidden");
            } else {
                $("div#agreeEntry").addClass("hidden");
                $("div#disagreeEntry").removeClass("hidden");
                $('#entryDate').val('');
            }
        });

        $("input[name='personalOrCompany']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('true' == value) {
                $("div#personalNotEntry").removeClass("hidden");
                $("div#companyNotEntry").addClass("hidden");
            } else {
                $("div#personalNotEntry").addClass("hidden");
                $("div#companyNotEntry").removeClass("hidden");
            }
        });

        $("input[name='entrySuccess']").click(function () {
            var value = $(this).val();
            if ('ENABLED' == value) {
                $("div#entrySuccess").removeClass("hidden");
            } else {
                $("div#entrySuccess").addClass("hidden");
                $('#entryDate').val('');
            }
        });

        $("input[name='joinInterview']").click(function () {
            var value = $(this).val();
            if ('true' == value) {
                $("div#joinInterview").removeClass("hidden");

            } else {
                $("div#joinInterview").addClass("hidden");
                $('#entryDate').val('');

            }

        });

    },
    datepicker: function () {
        $('input.datepicker').datepicker({
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true
        });
    }
};

/**
 * member_four.jsp
 *
 * @constructor
 */
function MemberFour(uuid) {
    this.datepicker();
    this.showOrHidden();
    this.checkTurnoverDate(uuid);
    this.keydown();
    this.select2();
    this.addAttr();

}

MemberFour.prototype = {
    select2: function () {
        $("#telSelect").select2();
    },
    showOrHidden: function () {

        $('#outOtherRemark').change(function () {
            var value = $(this).val();
            if (value == '无') {
                $('div#outOtherFileOption').addClass('hidden');
            } else {
                $('div#outOtherFileOption').removeClass('hidden');
            }
        });

        $('#goOutReasonItems1').change(function () {

            if ($(this).is(':checked')) {
                $('div#revealSecrets').removeClass('hidden');
            } else {
                $('div#revealSecrets').addClass('hidden');
            }

        });
        $('#goOutReasonItems2').change(function () {

            if ($(this).is(':checked')) {
                $('div#bribery').removeClass('hidden');
            } else {
                $('div#bribery').addClass('hidden');
            }

        });
        $('#goOutReasonItems3').change(function () {

            if ($(this).is(':checked')) {
                $('div#rude').removeClass('hidden');
            } else {
                $('div#rude').addClass('hidden');
            }

        });
        $('#goOutReasonItems4').change(function () {

            if ($(this).is(':checked')) {
                $('div#destroyImportantAssets').removeClass('hidden');
            } else {
                $('div#destroyImportantAssets').addClass('hidden');
            }

        });
        $('#goOutReasonItems5').change(function () {

            if ($(this).is(':checked')) {
                $('div#stealing').removeClass('hidden');
            } else {
                $('div#stealing').addClass('hidden');
            }

        });
        $('#goOutReasonItems6').change(function () {

            if ($(this).is(':checked')) {
                $('div#defamation').removeClass('hidden');
            } else {
                $('div#defamation').addClass('hidden');
            }

        });
        $('#goOutReasonItems7').change(function () {

            if ($(this).is(':checked')) {
                $('div#misuseResources').removeClass('hidden');
            } else {
                $('div#misuseResources').addClass('hidden');
            }

        });

        $("select[name='turnoverReason']").change(function () {
            var value = $(this).val();
            // console.log(value);
            if ('LABOR_DISPUTE' == value) {
                $("div#laborAttachment").removeClass("hidden");
                $("div#goOutOptions").addClass("hidden");
                $("div#otherOutRemark").addClass("hidden");
            } else if ('GO_OUT' == value) {
                $("div#goOutOptions").removeClass("hidden");
                $("div#laborAttachment").addClass("hidden");
                $("div#otherOutRemark").addClass("hidden");
            } else {
                $("div#goOutOptions").addClass("hidden");
                $("div#laborAttachment").addClass("hidden");
                $("div#otherOutRemark").addClass("hidden");
            }
        });

        $("select[name='turnoverProcess']").change(function () {
            var value = $(this).val();
            // console.log(value);
            if ('TAKE_RESOURCES' == value) {
                $("div#takeCompanyRs").removeClass("hidden");
                $("div#otherOutReason").addClass("hidden");
            } else if ('OTHER' == value) {
                $("div#takeCompanyRs").addClass("hidden");
                $("div#otherOutReason").removeClass("hidden");
            } else {
                $("div#takeCompanyRs").addClass("hidden");
                $("div#otherOutReason").addClass("hidden");
            }
        });

        $("input[name='breachTrainingAgreement']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('true' == value) {
                $("div#brTrainAgree").removeClass("hidden");
            } else {
                $("div#brTrainAgree").addClass("hidden");
            }
        });

        $("input[name='outStopPeriod']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('true' == value) {
                $("div#outStopPeriFile").removeClass("hidden");
            } else {
                $("div#outStopPeriFile").addClass("hidden");
            }
        });

        $("input[name='illegalDestroyCompanyFace']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('true' == value) {
                $("div#illegalDestoryCFile").removeClass("hidden");
            } else {
                $("div#illegalDestoryCFile").addClass("hidden");
            }
        });

        $("input[name='legalDisputes']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('true' == value) {
                $("div#legalDispuFile").removeClass("hidden");
            } else {
                $("div#legalDispuFile").addClass("hidden");
            }
        });

        $("input[name='reasonStatus']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('false' == value) {
                $("div#reasonStatus").removeClass("hidden");
            } else {
                $("div#reasonStatus").addClass("hidden");
            }
        });

        $("input[name='processStatus']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('false' == value) {
                $("div#processStatus").removeClass("hidden");
            } else {
                $("div#processStatus").addClass("hidden");
            }
        });

        $("input[name='processAfterStatus']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('false' == value) {
                $("div#processAfterStatus").removeClass("hidden");
            } else {
                $("div#processAfterStatus").addClass("hidden");
            }
        });

        $("input[name='workChange']").click(function () {
            var value = $(this).val();
            // console.log(value);
            if ('true' == value) {
                $("div#workChange").removeClass("hidden");
            } else {
                $("div#workChange").addClass("hidden");
            }
        });

    },
    datepicker: function () {
        $('input.datepicker').datepicker({
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true
        });
    },
    checkTurnoverDate: function (uuid) {

        $.ajaxSetup({
            async: false
        });

        var href = "../form_4/isValid";
        $('#submitNext').click(function (event) {
            var turnoverDate = $('#turnoverDate').val();
            $.get(href, {
                turnoverDate: turnoverDate,
                memberUuid: uuid
            }, function (data) {
                var result = JSON.stringify(data);
                if (result == 'true') {

                } else {
                    $('#turnError').css('display', '');
                    event.preventDefault();
                }
            }, 'json');

        });

        $('#submitPrev').click(function (event) {
            var turnoverDate = $('#turnoverDate').val();
            $.get(href, {
                turnoverDate: turnoverDate,
                memberUuid: uuid
            }, function (data) {
                var result = JSON.stringify(data);
                if (result == 'true') {

                } else {
                    $('#turnError').css('display', '');
                    event.preventDefault();
                }
            }, 'json');
        });
    },
    keydown: function () {
        $('#turnoverDate').keydown(function () {
            $('#turnError').css('display', 'none');
        });

        $('#turnoverDate').change(function () {
            $('#turnError').css('display', 'none');
        });
    },
    addAttr: function () {
        $("#hireAgain1").click(function () {
            $(this).attr("value", true);
        });
        $("#hireAgain2").click(function () {
            $(this).attr("value", false);
        });
    }
};

/**
 * company_creditStream_list.jsp
 *
 * @returns {CompanyCreditStream}
 */

function CompanyCreditStream() {
    this.datepicker();

}

CompanyCreditStream.prototype = {
    datepicker: function () {
        $("input[name='start'].datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true
        });

        $("input[name='end'].datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true
        }).on(
            'click',
            function () {
                $("input[name='end'].datepicker").datepicker(
                    "setStartDate", $("input[name='start']").val());
            });
    }
};

/**
 * sysAdmin creditRule_list.jsp
 *
 * @returns {CreditRule}
 */
function CreditRule() {
    this.deleteAction();
}

CreditRule.prototype = {
    deleteAction: function () {
        $("#deleteBtn").click(function () {
            var ids = [];
            $("input[name='deleteIds']:checked").each(function () {
                ids.push($(this).val());
            });

            if (ids.length == 0) {
                alert("您未选择任何数据！");
                return;
            }

            if (confirm("确定删除所选数据？") == false) {
                return;
            }

            document.deleteForm.submit();

        });

    }
};

function CompanyCredit() {
    this.batchAction();
}

CompanyCredit.prototype = {
    batchAction: function () {
        $("#batchBtn").click(function () {
            var ids = [];
            $("input[name='companyCheckIds']:checked").each(function () {
                ids.push($(this).val());
            });

            if (ids.length == 0) {
                alert("您未选择任何数据！");
                return;
            }

            document.batchForm.submit();

        });
    }

};

function News() {
    this.datepicker();
    this.showOrHidden();
}

News.prototype = {
    datepicker: function () {
        $("input.datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true
        });
    },
    showOrHidden: function () {
        $('#platform').change(function () {
            var value = $(this).val();
            if ('PLATNOTICE' == value) {
                $('div#fileBlock').addClass('hidden');
            } else {
                $('div#fileBlock').removeClass('hidden');
            }
        });

    }
};

/**
 * sysAdmin company_Noverifydetail.jsp
 *
 * @returns
 */
function Noverify() {
    $(".VERIFYFALSE").bind("change", function () {
        var check = this;
        if (check.checked == false) {
            $("#verifyCheck input[name='verifyReason']").each(function () {
                var check = this;
                if ($(check).attr("class") != "VERIFYFALSE") {
                    check.disabled = false;
                    check.checked = false;
                }
            });
        } else if (check.checked == true) {
            $("#verifyCheck input[name='verifyReason']").each(function () {
                var check = this;
                if ($(check).attr("class") != "VERIFYFALSE") {
                    check.disabled = true;
                    check.checked = false;
                }
            });
        }
    });
    $(".VERIFYFALSE").trigger("change");
}

function submitReason() {
    var checks = document.getElementsByName("verifyReason");
    n = 0;
    for (i = 0; i < checks.length; i++) {
        if (checks[i].checked)
            n++;
    }
    if (n == 0) {
        alert("请选择原因");
        return false;
    }
}

/**
 * company_credit_query.jsp
 *
 * @returns {CreditQuery}
 */
function CreditQuery() {
    this.datepicker();
}

CreditQuery.prototype = {
    datepicker: function () {
        $("input.datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true
        });
    }

};

function City() {
    this.getCity();
}

City.prototype = {
    getCity: function () {
        // demo04
        $("#city_4").citySelect({
            prov: "北京",
            nodata: "none"
        });
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
function TScAcnt(uuid) {
    this.getTScAcntNumber(uuid);
}

TScAcnt.prototype = {
    getTScAcntNumber: function (uuid) {
        $.ajax({
            // async: false,
            url: '../tscAcnt/' + uuid,
            type: 'GET',
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                alert("服务器错误:" + XmlHttpRequest.status + " "
                + XmlHttpRequest.statusText + '!');
            },
            dataType: 'json',
            success: function (data) {
                if (!data)
                    return;
                console.log(data)

                if (null != data) {
                    $("#showSpan").html(data + "%");
                    $("#showBar").css("width", data * 2);
                    $("#showPercent").html(data);
                } else if (data.success == "false") {
                    $("#infodegree").html(data.desc);
                    return false;
                }
            }
        });
    }
};

function AdminMember() {
    this.importExcelForm();
    sortIcon();
    this.chNameSort();
    this.enNameSort();
    this.idNumberSort();
    this.genderSort();
    this.birthdaySort();
    this.workPositionSort();
    this.mobileSort();
    this.emailSort();
    this.companNameSort();

}

AdminMember.prototype = {
    genderSort: function () {
        propertySort('gender');
    },
    chNameSort: function () {
        propertySort('chName');
    },
    enNameSort: function () {
        propertySort('enName');
    },
    idNumberSort: function () {
        propertySort('idNumber');
    },
    birthdaySort: function () {
        propertySort('birthday');
    },
    workPositionSort: function () {
        propertySort('workPosition');
    },
    mobileSort: function () {
        propertySort('mobile');
    },
    emailSort: function () {
        propertySort('email');
    },
    companNameSort: function () {
        propertySort("companyName");
    },
    importExcelForm: function () {

        $('#import_form').click(function () {
            $('#excelFile').val('');
            $("#myModal").modal({
                "backdrop": "static",
                "keyboard": true,
                "show": true
            });

        });

        $('#import').click(function () {
            document.excelForm.submit();
        });

        $('#exportModel').click(function () {
            var href = location.pathname;
            if (href.indexOf("/form/import_excel") > 0) {
                location.href = "../exportModel";
            } else {
                location.href = "exportModel";
            }
        });
    }
};

function propertySort(id) {

    $('#' + id).click(function () {
        var propertyName = $('#propertyName').val();
        var direction = $('#direction').val();
        // 属性为空时
        if (propertyName == '' || propertyName != id) {
            $('#direction').val('desc');
        }

        if (propertyName == id && direction == 'asc') {
            $('#direction').val('desc');
        }
        if (propertyName == id && direction == 'desc') {
            $('#direction').val('asc');
        }

        $('#propertyName').val($(this).attr('name'));

        submit();
    });

}

function sortIcon() {

    var propertyName = $('#propertyName').val();
    var direction = $('#direction').val();

    if (propertyName != '') {
        $('#' + propertyName).children('i').remove();
    }

    if (direction == 'asc') {
        $('#' + propertyName).append(
            '<i class="glyphicon glyphicon-chevron-up"></i>');
    }

    if (direction == 'desc') {
        $('#' + propertyName).append(
            '<i class="glyphicon glyphicon-chevron-down"></i>');
    }
}

function submit() {
    document.QueryForm.submit();
}

/**
 * member/query_history.jsp
 *
 * @returns {QueryHistory}
 */
function QueryHistory() {
    this.datepicker();
}

QueryHistory.prototype = {
    datepicker: function () {
        $("input.datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true
        });
    }
};

function Audit() {
    this.exportExcel();
}

Audit.prototype = {
    exportExcel: function () {
        $('#export_excel').click(function () {

            var operator = $("input[name='operator']").val();
            var operateDetail = $("input[name='operateDetail']").val();
            if (operator == "") {
                operator = null;
            }
            if (operateDetail == "") {
                operateDetail = null;
            }
            var href = "excel/export/" + operator + "/" + operateDetail;

            location.href = href;

        });
    }

};

/**
 * member/query_member_list.jsp
 */
function QueryMember(userRole) {

    this.getDataAndHandle(userRole);

}

QueryMember.prototype = {
    getDataAndHandle: function (userRole) {

        $('#btnSearch').click(function (event) {
            event.preventDefault();
            var href = 'query_list/getData';
            $.get(href, {
                chNameQuery: $('#chNameQuery').val(),
                idNumberQuery: $('#idNumberQuery').val(),
                mobileQuery: $('#mobileQuery').val(),
                emailQuery: $('#emailQuery').val()
            }, function (data) {
                handle(data, userRole);

            }, 'json');
        });

        function handle(data, userRole) {
            var chNameEmpty = JSON.stringify(data.chNameEmpty);
            var optionEmpty = JSON.stringify(data.optionEmpty);
            if (chNameEmpty == 'false') {
                $('#chNameError').removeClass('hidden');
            } else {
                $('#chNameError').addClass('hidden');
            }

            if (optionEmpty == 'false') {
                $('#optionError').removeClass('hidden');
            } else {
                $('#optionError').addClass('hidden');
            }

            if (chNameEmpty == 'true' && optionEmpty == 'true') {

                if (userRole != 'admin') {
                    if (confirm('全站搜索会扣除积分,确定操作？') == false) {
                        return;
                    } else {
                        document.QueryForm.submit();
                    }
                } else {
                    document.QueryForm.submit();
                }

            }
        }

    }
};

function CompanyVerify(guid) {
    this.img();
    this.btnDisabled(guid);
}

CompanyVerify.prototype = {
    img: function () {
        var x = 10;
        var y = 20;
        $("a.imghover").mouseover(
            function () {
                var $thissrc = $(this).attr("name");
                var tooltip = "<div id='tooltip'><img src='" + $thissrc
                    + "'alt='产品预览图' /></div>";
                $("body").append(tooltip);
                $("#tooltip").css({
                    "top": (e.pageY + y) + "px",
                    "left": (e.pageX + x) + "px"
                }).show("fast");
            }).mouseout(function () {
                $("#tooltip").remove();
            }).mousemove(function (e) {
                $("#tooltip").css({
                    "top": (e.pageY + y) + "px",
                    "left": (e.pageX + x) + "px"
                });
            })
    },
    btnDisabled: function (guid) {
        $('#passBtn').click(function () {
            var disabled = $(this).attr('disabled');
            if (disabled == 'disabled') {
                return;
            }
            if (confirm('确定审核通过吗?') == false) {
                return;
            }

            $('#noPassBtn').removeAttr('href');
            $('#noPassBtn').attr('disabled', 'disabled');
            window.location.href = '../pass/' + guid + '/true';
        });
    }
};


/**
 * question_tag_tree.jsp
 * @constructor
 */
function QuestionTagTree() {
    this.initTree();
}

QuestionTagTree.prototype = {
    initTree: function () {
        var setting = {
            view: {
                addHoverDom: addHoverDom,//增加节点
                removeHoverDom: removeHoverDom,
                selectedMulti: false
            },
            edit: {
                enable: true,
                showRemoveBtn: showRemoveBtn
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeRename: beforeRename,
                beforeRemove: beforeRemove
            }
        };

        var csrf = $('input[name="_csrf"]').val();

        $.get('tree_data', {"_csrf": csrf}, function (data) {
            if (!data.empty) {
                $.fn.zTree.init($("#treeDemo"), setting, data.data);
                setEdit();
            }
        }, 'json');

        var newCount = 1;

        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='添加' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_" + treeNode.tId);
            if (btn) btn.bind("click", function () {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                if (treeNode.level <= 3) {
                    zTree.addNodes(treeNode, {id: (100 + newCount), pId: treeNode.id, name: "标签" + (newCount++)});
                    //TODO 保存操作
                } else {
                    alert('标签至多添加四级');
                }
                return false;
            });
        }

        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.tId).unbind().remove();
        }

        var log, className = "dark";

        function beforeRename(treeId, treeNode, newName, isCancel) {
            className = (className === "dark" ? "" : "dark");
            if (newName.length == 0) {
                alert("标签名称不能为空.");
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                setTimeout(function () {
                    zTree.editName(treeNode)
                }, 10);
                return false;
            }
            return true;
        }

        function setEdit() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                remove = $("#remove").attr("checked"),
                rename = $("#rename").attr("checked"),
                removeTitle = $.trim($("#removeTitle").get(0).value),
                renameTitle = $.trim($("#renameTitle").get(0).value);
            //zTree.setting.edit.showRemoveBtn = remove;
            zTree.setting.edit.showRenameBtn = rename;
            zTree.setting.edit.removeTitle = removeTitle;
            zTree.setting.edit.renameTitle = renameTitle;
        }

        function beforeRemove(treeId, treeNode) {
            className = (className === "dark" ? "" : "dark");
            //showLog("[ " + getTime() + " beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.selectNode(treeNode);
            return confirm("确认删除 标签 -- " + treeNode.name + " 吗？");
        }

        function showRemoveBtn(treeId, treeNode) {
            //获取节点所配置的noRemoveBtn属性值
            if (treeNode.noR) {
                return false;
            } else {
                return true;
            }
        }
    }
};


/**
 * admin_question_form.jsp
 * @constructor
 */
function AdminQuestionForm() {
    this.suitableTypeChange();
    this.questionTypeChange();
    this.addOrRemoveOption();
    this.select2();
    this.showTagBox();//综合标签选择
    this.hideTagBox();
    this.saveTag();
    this.submitForm();
}

AdminQuestionForm.prototype = {
    suitableTypeChange: function () {
        $('select[name="suitableType"]').change(function () {
            var $this = $(this);
            var val = $this.val();
            if (val == 'GENERAL') {
                $('#professionalDiv').addClass('hidden');
                $('select[name="positionUuid"]').attr('disabled', true);
                $('#generalDiv').removeClass('hidden');
                $('input[name="tags"]').attr('disabled', false);

            } else {
                $('#generalDiv').addClass('hidden');
                $('input[name="tags"]').attr('disabled', true);
                $('#professionalDiv').removeClass('hidden');
                $('select[name="positionUuid"]').attr('disabled', false);
            }

        });
    },
    submitForm: function () {
        $('#submitBtn').click(function () {
            $('.errors').remove();
            $(this.form).ajaxSubmit({
                url: "create",
                dataType: "json",
                type: "post",
                success: function (data) {
                    if (!data.success) {
                        $.each(data.errors, function (key, value) {
                            if (key != 'answer') {
                                $('#' + key + 'Div').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                            } else {
                                if (data.standard) {
                                    $('#options1Div').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                                } else {
                                    $('#' + key + 'Div').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                                }
                            }
                        });
                    } else {
                        window.location.href = "../list";
                    }
                }
            });
        });
    },
    saveTag: function () {
        $('#submitTagBtn').click(function () {
            $('#tagDiv a').remove();
            $('input[name="tags"]').remove();
            if ($('.svae_box').length > 0) {
                $('.svae_box').each(function () {
                    var name = $(this).data("name");
                    var code = $(this).data("code");
                    $('#tag-plus-btn').before('<a class="tags-selected"  value="' + code + '" title="' + name + '" href="javascript:void(0);"><span>' + name + '</span></a>');
                    $('#tagDiv').append('<input type="hidden" name="tags" value="' + code + '"/>');
                })
            }

            $('.aui_state_box').hide();
            $('.data-tabs ul li').not(":first").remove();
        });
    },
    hideTagBox: function () {
        $('.close-btn').click(function () {
            $('.data-tabs ul li').not(":first").remove();
            $('#znhy-table').children().remove();
            $('.aui_state_box').hide();
        });
    },
    showTagBox: function () {
        $('button#tag-plus-btn').click(function () {
            var url = contextPath + "/admin/question/load/first_level_tag";
            $('div#tag-content').load(url);
            $('.aui_state_box').show();
        });
    },
    select2: function () {
        $('select[name="positionUuid"]').select2({
            theme: "bootstrap",
            language: "zh-CN"
        });
    },
    questionTypeChange: function () {
        $("select[name='type']").change(function () {
            var $this = $(this);
            var $radioDiv = $('div#optionsDiv');
            var $multiDiv = $('div#optionsDiv');
            var $nonStandardDiv = $('div#non-standard-answer');
            var val = $this.val();
            if (val == 'SINGLE_CHOICE') {
                $radioDiv.removeClass('hidden');
                $radioDiv.find('input').attr("disabled", false);
                $nonStandardDiv.addClass('hidden');
                $nonStandardDiv.find('textarea').attr('disabled', true);
                $('span.input-group-addon').each(function () {
                    $(this).find('input').attr('type', 'radio');
                });
            } else if (val == 'MULTIPLE_CHOICE') {
                $radioDiv.removeClass('hidden');
                $radioDiv.find('input').attr('disabled', false);
                $nonStandardDiv.addClass('hidden');
                $nonStandardDiv.find('textarea').attr('disabled', true);
                $('span.input-group-addon').each(function () {
                    $(this).find('input').attr('type', 'checkbox');
                });
            } else if (val == 'SHORT_ANSWER') {
                $radioDiv.addClass('hidden');
                $radioDiv.find('input').attr('disabled', true);
                $nonStandardDiv.removeClass('hidden');
                $nonStandardDiv.find('textarea').attr('disabled', false);
            }
        });
    },
    addOrRemoveOption: function () {
        $("body").on("click", ".addButton", function () {
            var index = $(this).data("index");
            if (!index) {
                index = 1;
                $(this).data("index", 1)
            }
            index++;
            $(this).data("index", index);
            var template = $(this).attr("name"),
                $templateEle = $("#" + template + 'Template'),
                $row = $templateEle.clone().removeAttr("id").insertBefore($templateEle).removeClass("hide"),
                $el = $row.find("input").eq(0).attr("name", "options");

            var length = $('input[name="options"]').length;
            var code = String.fromCharCode(65 + length - 1);

            var type = $('select[name="type"]').val();
            if (type == 'SINGLE_CHOICE') {
                $row.find("span").eq(0).html('<input name="answer" type="radio" value="' + code + '">' + code);
            } else {
                $row.find("span").eq(0).html('<input name="answer" type="checkbox" value="' + code + '">' + code);
            }

            $row.on("click", ".removeButton", function () {
                var index = $(".removeButton").index(this);
                var length2 = $('button.removeButton').length - 2;
                if (index == length2) {
                    $row.remove();
                } else {
                    alert('您只能删除最后添加的选项');
                }
            })
        });
    }
};

//点击标签
function tagClick(tag, flag) {
    var id = $(tag).data('code');
    var name = $(tag).data('name');
    var html = "";
    if (flag) {
        var url = contextPath + "/admin/question/load/other_level_tag_" + id;
        $.get(url, {"_csrf": $('input[name="_csrf"]').val()}, function (data) {
            $('.data-tabs ul').append('<li onclick="removenode(this,' + data.pId + ')" data-code="' + data.pId + '" class="active"><a href="javascript:;"><span>' + data.pName + '</span><em></em></a></li>');
            $('#tagPName').html(data.pName);
            var ul = $(tag).parents('#znhy-table').html('');
            for (var j in  data.tags) {
                html += '<li><a href="javascript:;" data-ditem="' + data.tags[j].id + '" data-code="' + data.tags[j].id + '"data-name="' + data.tags[j].name + '" class="d-cate" onclick="tagClick(this,' + data.tags[j].parentTag + ')">' + data.tags[j].name + '</a> </li>';
            }
            $('#znhy-table').children().remove();
            $('ul#znhy-table').html(html);
            if ($('.data-result span').length > 0) {
                $('.data-result span').each(function (index) {
                    $('#znhy-table a[data-code=' + $(this).data("code") + ']').addClass('d-item-active');
                });
            }
        }, 'json');


    } else {
        if ($(tag).attr("class").indexOf("d-item-active") > 0) {
            $(tag).removeClass("d-item-active");
            $('.data-result span[data-code=' + $(tag).data("code") + ']').remove();
        } else {
            if ($('.data-result span').length > 0) {
                $('.data-error').slideDown();
                setTimeout("$('.data-error').hide()", 1000);
                return false;
            } else {
                $('.data-result').append("<span class=\"svae_box aui-titlespan\" data-code=\"" + $(tag).data("code") + "\"  data-name=\"" + $(tag).data("name") + "\" onclick=\"removespan(this)\">" + $(tag).data("name") + "<i>×<\/i><\/span>");
                $(tag).addClass('d-item-active');
            }
        }
    }
}
//已选标签删除
function removespan(spanthis) {
    $('#znhy-table a[data-ditem=' + $(spanthis).data("code") + ']').removeClass('d-item-active');
    $(spanthis).remove();
}

//点击tab
function removenode(lithis, id) {
    var html = "";
    $(lithis).nextAll('li').remove();
    var url = contextPath + "/admin/question/load/other_level_tag_" + id;
    $.get(url, {"_csrf": $('input[name="_csrf"]').val()}, function (data) {
        $('#tagPName').html(data.pName);
        for (var j in  data.tags) {
            html += '<li><a href="javascript:;" data-ditem="' + data.tags[j].id + '" data-code="' + data.tags[j].id + '"data-name="' + data.tags[j].name + '" class="d-cate" onclick="tagClick(this,' + data.tags[j].parentTag + ')">' + data.tags[j].name + '</a> </li>';
        }
        $('#znhy-table').children().remove();
        $('ul#znhy-table').html(html);
        if ($('.data-result span').length > 0) {
            $('.data-result span').each(function (index) {
                $('#znhy-table a[data-code=' + $(this).data("code") + ']').addClass('d-item-active');
            });
        }
    }, 'json');
}


/**
 * admin_question_edit_form.jsp
 * @constructor
 */
function AdminQuestionEditForm() {
    this.hideTagBox();
    this.showTagBox();
    this.select2();
    this.saveTag();
    this.questionTypeChange();
    this.addOrRemoveOption();
    this.removeOption();
    this.submitForm();
    this.suitableTypeChange();
}

AdminQuestionEditForm.prototype = {
    suitableTypeChange: function () {
        $('select[name="suitableType"]').change(function () {
            var $this = $(this);
            var val = $this.val();
            if (val == 'GENERAL') {
                $('#professionalDiv').addClass('hidden');
                $('select[name="positionUuid"]').attr('disabled', true);
                $('#generalDiv').removeClass('hidden');
                $('input[name="tags"]').attr('disabled', false);

            } else {
                $('#generalDiv').addClass('hidden');
                $('input[name="tags"]').attr('disabled', true);
                $('#professionalDiv').removeClass('hidden');
                $('select[name="positionUuid"]').attr('disabled', false);
            }

        });
    },
    submitForm: function () {
        $('#submitBtn').click(function () {
            $('.errors').remove();
            $(this.form).ajaxSubmit({
                url: "edit_" + $("#uuid").val(),
                dataType: "json",
                type: "post",
                success: function (data) {
                    if (!data.success) {
                        $.each(data.errors, function (key, value) {
                            if (key != 'answer') {
                                $('#' + key + 'Div').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                            } else {
                                if (data.standard) {
                                    $('#options1Div').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                                } else {
                                    $('#' + key + 'Div').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                                }
                            }
                        });
                    } else {
                        window.location.href = "../list";
                    }
                }
            });
        });
    },
    removeOption: function () {
        $("body").on("click", ".removeButton", function () {
            var index = $(".removeButton").index(this);
            var length2 = $('button.removeButton').length - 2;
            if (index == length2) {
                $(this).parent().parent().remove();
            } else {
                alert('您只能删除最后添加的选项');
            }
        })
    },
    addOrRemoveOption: function () {
        $("body").on("click", ".addButton", function () {
            var index = $(this).data("index");
            if (!index) {
                index = 1;
                $(this).data("index", 1)
            }
            index++;
            $(this).data("index", index);
            var template = $(this).attr("name"),
                $templateEle = $("#" + template + 'Template'),
                $row = $templateEle.clone().removeAttr("id").insertBefore($templateEle).removeClass("hide"),
                $el = $row.find("input").eq(0).attr("name", "options");

            var length = $('input[name="options"]').length;
            var code = String.fromCharCode(65 + length - 1);

            var type = $('select[name="type"]').val();
            if (type == 'SINGLE_CHOICE') {
                $row.find("span").eq(0).html('<input name="answer" type="radio" value="' + code + '">' + code);
            } else {
                $row.find("span").eq(0).html('<input name="answer" type="checkbox" value="' + code + '">' + code);
            }
        });
    },
    questionTypeChange: function () {
        $("select[name='type']").change(function () {
            var $this = $(this);
            var $radioDiv = $('div#optionsDiv');
            var $multiDiv = $('div#optionsDiv');
            var $nonStandardDiv = $('div#non-standard-answer');
            var val = $this.val();
            if (val == 'SINGLE_CHOICE') {
                $radioDiv.removeClass('hidden');
                $radioDiv.find('input').attr("disabled", false);
                $nonStandardDiv.addClass('hidden');
                $nonStandardDiv.find('textarea').attr('disabled', true);
                $('span.input-group-addon').each(function () {
                    $(this).find('input').attr('type', 'radio');
                });
            } else if (val == 'MULTIPLE_CHOICE') {
                $radioDiv.removeClass('hidden');
                $radioDiv.find('input').attr('disabled', false);
                $nonStandardDiv.addClass('hidden');
                $nonStandardDiv.find('textarea').attr('disabled', true);
                $('span.input-group-addon').each(function () {
                    $(this).find('input').attr('type', 'checkbox');
                });
            } else if (val == 'SHORT_ANSWER') {
                $radioDiv.addClass('hidden');
                $radioDiv.find('input').attr('disabled', true);
                $nonStandardDiv.removeClass('hidden');
                $nonStandardDiv.find('textarea').attr('disabled', false);
            }
        });
    },
    saveTag: function () {
        $('#submitTagBtn').click(function () {
            $('#tagDiv a').remove();
            $('#tagDiv input').remove();
            $('input[name="tags"]').remove();
            if ($('.svae_box').length > 0) {
                $('.svae_box').each(function () {
                    var name = $(this).data("name");
                    var code = $(this).data("code");
                    $('#tag-plus-btn').before('<a class="tags-selected"  value="' + code + '" title="' + name + '" href="javascript:void(0);"><span>' + name + '</span></a>');
                    $('#tagDiv').append('<input type="hidden" name="tags" value="' + code + '"/>');
                })
            }

            $('.aui_state_box').hide();
            $('.data-tabs ul li').not(":first").remove();
        });
    },
    hideTagBox: function () {
        $('.close-btn').click(function () {
            $('.data-tabs ul li').not(":first").remove();
            $('#znhy-table').children().remove();
            $('.aui_state_box').hide();
        });
    },
    showTagBox: function () {
        $('button#tag-plus-btn').click(function () {
            var url = contextPath + "/admin/question/load/first_level_tag";
            $('div#tag-content').load(url);
            $('.aui_state_box').show();
        });
    },
    select2: function () {
        $('select[name="positionUuid"]').select2({
            theme: "bootstrap",
            language: "zh-CN"
        });
    }
};

/**
 * general_examination_setting_form.jsp
 * @constructor
 */
function GeneralExaminationSettingForm() {
    this.hideTagBox();
    this.showTagBox();
    this.saveTag();
    this.submitForm();
    this.ignoreForm();
    this.degreeChange();
}

GeneralExaminationSettingForm.prototype = {
    degreeChange: function () {
        $('input[name="degree"]').change(function () {
            $('.question-tag-degree').remove();
            var code = $(this).val();
            $('#tagForm').append('<input class="question-tag-degree" type="hidden" name="degree" value="' + code + '"/>');
            amountAjax("", "", true, false);
        });
    },
    ignoreForm: function () {
        $('#ignoreBtn').click(function () {
            window.location.href = contextPath + "/enterprise/exam/load/" + $('#positionUuid').val() + "/professional_examination_setting_" + $("#memberUuid").val();
        });
    },
    submitForm: function () {
        $('#submitBtn').click(function () {
            //此职位存在考试成绩
            var existed = $('#existed').val();
            if (existed == 'true') {
                if (confirm('该被面试者已经存在考试成绩，更改试卷设置会删除考试成绩，确认操作？') == false) {
                    return;
                }
            }
            $('.errors').remove();
            $(this.form).ajaxSubmit({
                url: contextPath + "/enterprise/exam/load/" + $('#positionUuid').val() + "/general_examination_setting_" + $("#memberUuid").val(),
                dataType: "json",
                type: "post",
                success: function (data) {
                    if (!data.success) {
                        $.each(data.errors, function (key, value) {
                            if (key == 'singleNum') {
                                $('#selectedTypesDiv').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                            } else {
                                $('#' + key + 'Div').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                            }
                        });
                    } else {
                        window.location.href = contextPath + "/enterprise/exam/load/" + $('#positionUuid').val() + "/professional_examination_setting_" + $("#memberUuid").val();
                    }
                }
            });
        });
    },
    saveTag: function () {
        $('#submitTagBtn').click(function () {
            $('#tagDiv a').remove();
            $('.general-tag').remove();
            if ($('.svae_box').length > 0) {
                $('.svae_box').each(function () {
                    var name = $(this).data("name");
                    var code = $(this).data("code");
                    $('#tag-plus-btn').before('<a class="tags-selected"  value="' + code + '" title="' + name + '" href="javascript:void(0);"><span>' + name + '</span></a>');
                    $('#tagForm').append('<input class="general-tag" type="hidden" name="tags" value="' + code + '"/>');
                    $('#tagDiv').append('<input type="hidden" class="general-tag" name="generalTags" value="' + code + '"/>');
                })
            }
            amountAjax("", "", true, false);
            $('.aui_state_box').hide();
            $('.data-tabs ul li').not(":first").remove();
        });
    },
    hideTagBox: function () {
        $('.close-btn').click(function () {
            $('.data-tabs ul li').not(":first").remove();
            $('#znhy-table').children().remove();
            $('.aui_state_box').hide();
        });
    },
    showTagBox: function () {
        $('button#tag-plus-btn').click(function () {
            var url = contextPath + "/enterprise/exam/load_general_tag";
            $('div#tag-content').load(url);
            $('.aui_state_box').show();
        });
    }
};

function enterpriseTagClick(tag, flag) {
    var id = $(tag).data('code');
    var name = $(tag).data('name');
    var html = "";
    if (flag) {
        var url = contextPath + "/enterprise/exam/load_general_other_tag_" + id;
        $.get(url, {"_csrf": $('input[name="_csrf"]').val()}, function (data) {
            $('.data-tabs ul').append('<li onclick="removenode1(this,' + data.pId + ')" data-code="' + data.pId + '" class="active"><a href="javascript:;"><span>' + data.pName + '</span><em></em></a></li>');
            $('#tagPName').html(data.pName);
            var ul = $(tag).parents('#znhy-table').html('');
            for (var j in  data.tags) {
                html += '<li><a href="javascript:;" data-ditem="' + data.tags[j].id + '" data-code="' + data.tags[j].id + '"data-name="' + data.tags[j].name + '" class="d-cate" onclick="enterpriseTagClick(this,' + data.tags[j].parentTag + ')">' + data.tags[j].name + '</a> </li>';
            }
            $('#znhy-table').children().remove();
            $('ul#znhy-table').html(html);
            if ($('.data-result span').length > 0) {
                $('.data-result span').each(function (index) {
                    $('#znhy-table a[data-code=' + $(this).data("code") + ']').addClass('d-item-active');
                });
            }
        }, 'json');


    } else {
        if ($(tag).attr("class").indexOf("d-item-active") > 0) {
            $(tag).removeClass("d-item-active");
            $('.data-result span[data-code=' + $(tag).data("code") + ']').remove();
        } else {
            /*if ($('.data-result span').length > 4) {
             */
            /*$('.data-error').slideDown();
             setTimeout("$('.data-error').hide()", 1000);
             return false;*/
            /*
             } else {
             }*/
            $('.data-result').append("<span class=\"svae_box aui-titlespan\" data-code=\"" + $(tag).data("code") + "\"  data-name=\"" + $(tag).data("name") + "\" onclick=\"removespan(this)\">" + $(tag).data("name") + "<i>×<\/i><\/span>");
            $(tag).addClass('d-item-active');
        }
    }
}

//点击tab
function removenode1(lithis, id) {
    var html = "";
    $(lithis).nextAll('li').remove();
    var url = contextPath + "/enterprise/exam/load_general_other_tag_" + id;
    $.get(url, {"_csrf": $('input[name="_csrf"]').val()}, function (data) {
        $('#tagPName').html(data.pName);
        for (var j in  data.tags) {
            html += '<li><a href="javascript:;" data-ditem="' + data.tags[j].id + '" data-code="' + data.tags[j].id + '"data-name="' + data.tags[j].name + '" class="d-cate" onclick="enterpriseTagClick(this,' + data.tags[j].parentTag + ')">' + data.tags[j].name + '</a> </li>';
        }
        $('#znhy-table').children().remove();
        $('ul#znhy-table').html(html);
        if ($('.data-result span').length > 0) {
            $('.data-result span').each(function (index) {
                $('#znhy-table a[data-code=' + $(this).data("code") + ']').addClass('d-item-active');
            });
        }
    }, 'json');
}


/**
 * professional_examination_setting_form.jsp
 * @constructor
 */
function ProfessionalExaminationSettingForm() {
    this.hideTagBox();
    this.showTagBox();
    this.saveTag();
    this.submitForm();
    this.prevForm();
    this.degreeChange();

}

ProfessionalExaminationSettingForm.prototype = {
    queryQuestionAmount: function () {
        $('#tagForm').ajaxSubmit({
            url: "../../query_question_by_position",
            dataType: "json",
            type: "post",
            success: function (data) {
                $('#singleQuestion').html('');
                $('#singleQuestion').html('单选题总数：' + data.singleAmount);
                $('input[name="totalSingle"]').val(data.singleAmount);

                $('#multipleQuestion').html('');
                $('#multipleQuestion').html('多选题总数：' + data.multipleAmount);
                $('input[name="totalMultiple"]').val(data.multipleAmount);

                $('#shortQuestion').html('');
                $('#shortQuestion').html('简答题总数：' + data.shortAmount);
                $('input[name="totalShort"]').val(data.shortAmount);
            }
        });
    },
    degreeChange: function () {
        $('input[name="degree"]').change(function () {
            $('.question-tag-degree').remove();
            var code = $(this).val();
            $('#tagForm').append('<input class="question-tag-degree" type="hidden" name="degree" value="' + code + '"/>');
            ProfessionalExaminationSettingForm.prototype.queryQuestionAmount();
        });
    },
    prevForm: function () {
        $('#preBtn').click(function () {
            window.location.href = contextPath + "/enterprise/exam/load/" + $('#positionUuid').val() + "/general_examination_setting_" + $('#memberUuid').val();
        });
    },
    submitForm: function () {
        $('#submitBtn').click(function () {
            $('.errors').remove();
            $(this.form).ajaxSubmit({
                url: contextPath + "/enterprise/exam/load/" + $('#positionUuid').val() + "/professional_examination_setting_" + $("#memberUuid").val(),
                dataType: "json",
                type: "post",
                success: function (data) {
                    if (!data.success) {
                        $.each(data.errors, function (key, value) {
                            if (key == 'singleNum') {
                                $('#selectedTypesDiv').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                            } else {
                                $('#' + key + 'Div').find('p').before('<span class="label label-warning errors">' + value + '</span>');
                            }
                        });
                    } else {
                        window.location.href = contextPath + "/enterprise/exam/load_" + $('#positionUuid').val() + "_general_examination_" + $("#memberUuid").val();
                    }
                }
            });
        });
    },
    saveTag: function () {
        $('#submitTagBtn').click(function () {
            $('#tagDiv a').remove();
            $('.general-tag').remove();
            if ($('.svae_box').length > 0) {
                $('.svae_box').each(function () {
                    var name = $(this).data("name");
                    var code = $(this).data("code");
                    $('#tag-plus-btn').before('<a class="tags-selected"  value="' + code + '" title="' + name + '" href="javascript:void(0);"><span>' + name + '</span></a>');
                    $('#tagForm').append('<input class="general-tag" type="hidden" name="tags" value="' + code + '"/>');
                    $('#tagDiv').append('<input type="hidden" class="general-tag" name="professionalTags" value="' + code + '"/>');
                })
            }
            amountAjax("", "", true, false);
            $('.aui_state_box').hide();
            $('.data-tabs ul li').not(":first").remove();
        });
    },
    hideTagBox: function () {
        $('.close-btn').click(function () {
            $('.data-tabs ul li').not(":first").remove();
            $('#znhy-table').children().remove();
            $('.aui_state_box').hide();
        });
    },
    showTagBox: function () {
        $('button#tag-plus-btn').click(function () {
            var url = contextPath + "/enterprise/exam/load_professional_tag";
            $('div#tag-content').load(url);
            $('.aui_state_box').show();
        });
    }
};


/**
 * general_examination_form.jsp
 * @constructor
 */
function GeneralExaminationForm() {
    this.showOrHideAnswer();
    this.typeAnswer();
    this.radioChange();
    this.checkboxChange();
}

GeneralExaminationForm.prototype = {
    checkboxChange: function () {
        $('.checkbox_answer').change(function () {
            var $this = $(this);
            var name = $this.attr('name');
            var checked = "";
            $('input[name="' + name + '"]:checked').each(function () {
                checked += $(this).val();
            });
            $('#' + name).val(checked);
        });
    },
    radioChange: function () {
        $('.radio_answer').change(function () {
            var $this = $(this);
            var name = $this.attr('name');
            $('#' + name).val($this.val());
        });
    },
    typeAnswer: function () {
        $('#type-answer-btn').click(function () {
            $('.type-answer').removeClass('hidden');
        });
    },
    showOrHideAnswer: function () {
        $('#show_answer_btn').click(function () {
            var $showAnswerBtn = $('#show_answer_btn');
            var show = $showAnswerBtn.hasClass('show');
            if (!show) {
                $showAnswerBtn.addClass('show');
                $showAnswerBtn.html('隐藏答案');
                $('.answer').css('display', 'block');
            } else {
                $showAnswerBtn.removeClass('show');
                $showAnswerBtn.html('显示答案');
                $('.answer').css('display', 'none');
            }
        });
    }
};