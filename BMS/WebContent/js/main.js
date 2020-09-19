
function del(){
	if(!isSelectOne()){
		alert("请至少选择一条数据");
		return;
	}
	
	if (confirm("您确定要删除选中项吗？")){
		location = "delete?id="+getSelect();
	}
}

function selectAll(){
	$("input[name='box']").each(function(index, domEle){
		$(domEle).attr("checked",true);
	});
}

function unSelectAll(){
	$("input[name='box']").each(function(index, domEle){
		$(domEle).attr("checked",false);
	});
}

function inverse(){
	$("input[name='box']").each(function(index, domEle){
		if($(domEle).attr("checked")){
			$(domEle).attr("checked",false);
		}else{
			$(domEle).attr("checked",true);
		}
	});
}

function isSelectOne(){
	var bool;
	$("input[name='box']").each(function(index, domEle){
		if($(domEle).attr("checked")){
			bool = true;
		}
	});
	return bool;
}

function getSelect(){
	var str="-1";
	$("input[name='box']").each(function(index, domEle){
		if($(domEle).attr("checked")){
			str+=","+$(domEle).attr("id_attr");
		}
	});
	return str;
}

function exportAllTxt(){
	location="exportFile.action?content=all";
}

function exportTxt(search,limit0,limit1){
	if(limit0==null || limit1==null){
		location="exportFile.action?content=search&search="+search;
		return;
	}
	location="exportFile.action?content=limit&search="+search+"&limit0="+limit0+"&limit1="+limit1;
}