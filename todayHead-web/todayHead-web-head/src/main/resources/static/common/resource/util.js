/* 根路径 */
var rootPath = "/todayHead-web-back";

/* 刷新验证码
${}这叫el表达式,它只解析jsp文件,其他文件不解析
*/
function refreshCode(imgCode) {
    var imgPath = rootPath + "/common/imgCode?now=" + new Date();
    $("#" + imgCode).attr("src", imgPath);
    return false;
}

/**
 * 解决此问题:当首页,上一页,页面跳转GO的情况;每一个链接都要添加搜索条件;
 * 解决方案:
 *    点击首页和上一页等,统一和点击Go的效果一样,只不过在点击之后要把currentPage和pageSize修改掉
 * @param    formId    表单的id
 * @param    currentPageId    当前页的文本框的id
 * @param    currentPage    当前页的值
 * @param    pageSizeId    每页条数文本框的id
 * @param    pageSize    每页多少条的值
 */
function pageFormSubmit(formId, currentPageId, currentPage, pageSizeId, pageSize) {
    /* 为当前页文本框赋值 */
    $("#" + currentPageId).val(currentPage);
    /* 为每页多少条文本框赋值 */
    $("#" + pageSizeId).val(pageSize);
    /* 表单提交 */
    $("#" + formId).submit();
    /* 在a上添加的链接,只要绑定的onclick事情return false,事件阻止 */
    return false;
}

/**
 * 全选
 */
function checkAllSelect(selectAllId, selectedName) {
    var selectAll = $("#" + selectAllId).prop("checked");
    $("input[name=" + selectedName + "]").prop("checked", selectAll);
    return true;
}

/**
 * 提交验证必须选择一个
 */
function vailForm(formId, selectedName) {
    var selectedArr = $("input[name=" + selectedName + "]:checked")
    var flag = selectedArr.size() > 0;
    if (!flag) {
        alert("==必须选择一个==")
    }
    return flag;
}