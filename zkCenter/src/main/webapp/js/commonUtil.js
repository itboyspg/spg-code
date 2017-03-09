/*
 *
 */

// 英文、数字、下划线、点号
var alphabetNumOutlintPoint = "_A-Za-z0-9.";
var number = "0-9";
var alphabet = "a-zA-Z";

/*
 * 正则校验
 */
function regCheck(pattern, param) {
	return (new RegExp("^["+pattern+"]+$")).test(param);
}

/*
 * 校验是否中文
 */
function checkChinese(param) {
	return /^[\u4e00-\u9fa5]+$/.test(param);
}

/*
 * 校验字母数字，下划线
 */
function checkAlphabet(param) {
	return /^[_A-Za-z0-9]+$/.test(param);
}
/*
 * 校验数字
 */
function checkNum(param) {
	return /^[0-9]+$/.test(param);
}
