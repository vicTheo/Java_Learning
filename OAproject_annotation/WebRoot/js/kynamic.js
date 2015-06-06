var kynamicTree={
	tree:{
		pNode:'',
		zTree:'',
		setting:{
			 isSimpleData: true,
        treeNodeKey: "kid",
        treeNodeParentKey: "pid",
        showLine: true,
        root: {
            isRoot: true,
            nodes: []
        },
		callback:{
			rightClick:function(event, treeId, treeNode){
				//点击右键时，将当前节点赋给pNode
				kynamicTree.tree.pNode=treeNode;
				if(treeNode.isParent){//文件夹节点
					kynamicTree.controlRMenu({
						x:event.clientX,
						y:event.clientY,
						addFile:true,
						addFolder:true,
						deleteNode:true,
						updateNode:true
					});
				}else{//文件节点
					kynamicTree.controlRMenu({
						x:event.clientX,
						y:event.clientY,
						addFile:false,
						addFolder:false,
						deleteNode:true,
						updateNode:true
					});
				}
			}
		}
		},
		loadTree:function(){
			$.post("kynamicAction_showKynamicList.action",null,function(data){
				kynamicTree.tree.zTree=$("#kynamicTree").zTree(kynamicTree.tree.setting,data.kynamicList);
			});
		}
	},
	controlRMenu:function(rMenuJson){
		/*
		 * 右键菜单项的显示控制
		 */
		//$("#rMenu").show();
		$("#rMenu").css({"top":rMenuJson.y+"px","left":rMenuJson.x+"px", "display":"block"});
		/*
		 * 增加文件的菜单的显示控制
		 */
		if(rMenuJson.addFile){
			$("#addFile").show();
		}else{
			$("#addFile").hide();
		}
		
		if(rMenuJson.deleteNode){
			$("#deleteNode").show();
		}else{
			$("#deleteNode").hide();
		}
		
		if(rMenuJson.addFolder){
			$("#addFolder").show();
		}else{
			$("#addFolder").hide();
		}
		
		if(rMenuJson.updateNode){
			$("#updateNode").show();
		}else{
			$("#updateNode").hide();
		}
		},
		//添加节点
		addNode: function(nodeJson){
			var filename = window.prompt(nodeJson.message);
			if (filename != null) {
				if (filename !="") {
					$.post("kynamicAction_getByName.action",{name:filename},function(data){
						if(data.message=="1"){
							alert("名称重复，请重新输入");
						}else{
							var params = {
						name: filename,
						isParent: nodeJson.isParent,
						pid: kynamicTree.tree.pNode.kid
					};
					$.post("kynamicAction_saveKynamic.action", params, function(data2){
						var kid = data2.kid;
						var newNode = {
							kid: kid,
							pid: kynamicTree.tree.pNode.kid,
							name: filename,
							isParent: nodeJson.isParent
						};
						kynamicTree.tree.zTree.addNodes(kynamicTree.tree.pNode, newNode, true);
					});
						}
					});
					
				}
				else {
					alert(nodeJson.errmessage);
				}
			}
		},
		//增加文件
		addFile:function(){
			kynamicTree.addNode({
				message:"请输入文件夹名称",
				errmessage:"文件夹名不能为空",
				isParent:false
			});
		},
		
		//增加文件夹
		addFolder:function(){
			kynamicTree.addNode({
				message:"请输入文夹名称",
				errmessage:"文件名不能为空",
				isParent:true
			});
		},
		//删除节点
		deleteNode:function(){},
		//修改节点
		updateNode:function(){},
	
	/*
	 * 版本维护
	 */
	version:{
		
	}
};
$().ready(
function(){
	kynamicTree.tree.loadTree();
	/**
	 * hover在这里仅仅是用于声明事件，事件函数中的内容到底是否执行，根据触发的时候判断
	 */
	$("#rMenu").hover(function(){
		/**
		 * 声明增、删、改的事件
		 */
		$("#addFile").unbind("click");
		$("#addFile").bind("click",function(){
			kynamicTree.addFile();
		});
		
		$("#addFolder").unbind("click");
		$("#addFolder").bind("click",function(){
			kynamicTree.addFolder();
		});
		
		$("#deleteNode").unbind("click");
		$("#deleteNode").bind("click",function(){});
		
		$("#updateNode").unbind("click");
		$("#updateNode").bind("click",function(){});
	},function(){
		$("#rMenu").hide();
	});
	
});
