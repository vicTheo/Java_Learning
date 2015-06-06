var tree={
	setting: {
		isSimpleData: true,
		treeNodeKey: "mid",
		treeNodeParentKey: "pid",
		showLine: true,
		root: {
			isRoot: true,
			nodes: []
		}
	},
	loadTree:function(){
		$.post("menuitemAction_getUserMenuitem.action",null,function(data){
			$("#menuTree").zTree(tree.setting,data.menuitemList);
		});

	}
};
$().ready(
function(){
	tree.loadTree();
});
