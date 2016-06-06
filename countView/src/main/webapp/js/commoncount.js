/**
 * 
 */
;
// 项目基础URL，如http://localhost:8080/countview/
var baseUrl = "";
function doHttpGet(url)
{
	$.ajax({url: baseUrl + url});
}
function doHttpPost(url, data)
{
	$.ajax({
		url: baseUrl + url,
		type: "POST",
		data: data,
		dataType: "json"
	});
}
// 添加一次PV
function addPV(englishName)
{
	doHttpGet("pvCtrl/addPageEvent?dataType=1&englishName=" + englishName);
}
// 添加一次按钮点击
function addBtnClick(englishName)
{
	doHttpGet("pvCtrl/addPageEvent?dataType=2&englishName=" + englishName);
}
// 添加一次链接访问
function addLinkClick(englishName)
{
	doHttpGet("pvCtrl/addPageEvent?dataType=3&englishName=" + englishName);
}
// 添加一次用户活跃
function addUserActive()
{
	doHttpGet("userCtrl/userActive");
}
// 记录用户IP
function addUserIp()
{
	doHttpGet("userCtrl/addUserIp");
}