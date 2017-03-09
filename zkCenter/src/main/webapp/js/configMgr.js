// 配置管理页面js

function queryConfig(systemName, configName, targetTagId){
	var tableData = "";
	$.ajax({
		url: "../configCtrl/queryConfig",
		data: {"systemName": systemName, "configName": configName},
		dataType: "json",
		success: function(data){
			if (data["resultCode"] == 0){
				var htmlData = "";
				$.each(data["resultData"], function(index, config){
					htmlData += "<tr class='even pointer'><td class='a-center'><input type='checkbox' class='flat'></td>";
					htmlData += ("<td>" + config["systemName"] + "</td>");
					htmlData += ("<td>" + config["configName"] + "</td>");
					htmlData += ("<td>" + config["configValue"] + "</td>");
					htmlData += ("<td>" + config["remark"] + "</td>");
					htmlData += ("<td class='a-right a-right'>");
					htmlData += "<button name='updateConfig' type='button' class='btn btn-warning btn-xs'>修改</button>";
					htmlData += "<button name='deleteConfig' type='button' class='btn btn-danger btn-xs'>删除</button>";
					htmlData += "</td>";
					htmlData += "</tr>";
				});
				$("#" + targetTagId).html(htmlData);
			} else {
				alterTips("error", "alterDivId", ("查询数据失败，" + data["resultMessage"]));
			}
		},
		error: function(){
			alterTips("error", "alterDivId", "配置项查询失败，请稍后再试！");
		}
	});
}

function queryAccessSystem(englishName, targetTagId){
	var tableData = "";
	$.ajax({
		url: "../newSystemCtrl/queryAccessSystem",
		data: {"englishName": englishName},
		dataType: "json",
		typs: "POST",
		success: function(data){
			if (data["resultCode"] == 0){
				var htmlData = "";
				$.each(data["resultData"], function(index, config){
					htmlData += "<tr class='even pointer'><td class='a-center'><input type='checkbox' class='flat'></td>";
					htmlData += ("<td>" + config["chineseName"] + "</td>");
					htmlData += ("<td>" + config["englishName"] + "</td>");
					htmlData += ("<td>" + config["remark"] + "</td>");
					htmlData += ("<td class='a-right a-right'>");
					htmlData += "<button name='deleteAccessSystem' type='button' class='btn btn-danger btn-xs'>删除</button>";
					htmlData += "</td>";
					htmlData += "</tr>";
				});
				$("#" + targetTagId).html(htmlData);
			} else {
				alterTips("error", "alterDivId", ("查询数据失败，" + data["resultMessage"]));
			}
		},
		error: function(){
			alterTips("error", "alterDivId", "配置项查询失败，请稍后再试！");
		}
	});
}

// 清空modal下面所有的input及textarea中的值
function cleanModalData(modalId){
	$("#" + modalId + " input").val("");
	$("#" + modalId + " textarea").val("");
}

function alterTips(alterId, types, tipContent){
	switch (types){
		case "success":
			$("#" + alterId).attr("class", "alert alert-success");
			break;
		case "warning":
			$("#" + alterId).attr("class", "alert alert-warning");
			break;
		case "error":
			$("#" + alterId).attr("class", "alert alert-danger");
			break;
		default:
			break;
	}
	$("#" + alterId).html(tipContent);
	$("#" + alterId).fadeIn().delay(5000).fadeOut();
}