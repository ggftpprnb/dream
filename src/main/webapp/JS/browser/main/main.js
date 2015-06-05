//最先加载
$(function(){
	$("#accordion").accordion({
		onSelect:function(title,index){
			var topMenuId = $("#accordion").accordion('getPanel',index).panel('options').id;
			var topMenu = $("#tree_"+topMenuId);
			if($.trim(topMenu.tree('options').url)==''){
				topMenu.tree({
					url:"main?m=loadMenuTree&menuId="+topMenuId,
					animate:true,
					onDblClick:function(node){
						addContentTab(node.id,node.text);
					}
				});
			}
		}
	}).accordion('select',5);
});

//双点菜单树时，往右边添加tab
function addContentTab(id,name){
	$('#contentTabs').tabs('add',{
	    title:name,
	    /*href:'main?m=toMenu&menuId='+id,*/
	    href:'JSP/main/content.jsp?menuId='+id,
	    closable:true
	});
}
