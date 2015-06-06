var kynamicTree={
	tree:{
		pNode:'',
		zTree:'',
		setting:{
			 isSimpleData: true,
        treeNodeKey: "kid",
        treeNodeParentKey: "pid",
        showLine: true,
		//keepParent:true,
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
			},
			click:function(event, treeId, treeNode){
				//因为单击时不确定是否右键过 所以重新赋值
				kynamicTree.tree.pNode=treeNode;
				var params={
					kid:kynamicTree.tree.pNode.kid
				}
				$.post("kynamicAction_getVersionByKid.action",params,function(data){
					if(data.versionList.length==0){
						kynamicTree.version.controlShowVersion({
					           addVersion:true,
					            versionList:false,
								checkin:true,
								checkout:true
				           });
					}else{
					    kynamicTree.version.controlShowVersion({
					           addVersion:false,
							   	checkin:false,
								checkout:false,
					            versionList:true
				           });	
						   kynamicTree.version.showVersionList(data.versionList);
					 }
				});
				
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
		deleteNode:function(){
			/*
			 * 判断是否为文件节点
			 *    否 判断是否包含子节点
			 *        是 可以删除
			 *        否 不可以删除
			 *    是  可以删除    
			 */
			if(kynamicTree.tree.pNode.isParent){
				if(kynamicTree.tree.zTree.getNodeByParam("pid",kynamicTree.tree.pNode.kid)){
					alert("有子节点，不能删除！");
				}else{
						var params={
					kid:kynamicTree.tree.pNode.kid
				};
				//判断是否有兄弟节点
				$.post("kynamicAction_getSibLingNode.action",params,function(data){
					if(data.kynamicList.length<2){//没有兄弟节点
					 $.post("kynamicAction_getParentNode.action",params,function(data1){
					 	var pNode=kynamicTree.tree.zTree.getNodeByParam("kid",data1.kynamic.kid);
					        	if(window.confirm("确定删除吗？")){
				                 $.post("kynamicAction_deleteNode.action",params,function(data2){
					                   kynamicTree.tree.zTree.removeNode(kynamicTree.tree.pNode);
					                   pNode.isParent=true;//更新子节点属性 相当于seting中keepParent=true
									   kynamicTree.tree.zTree.refresh();  //刷新树
									   alert(data2.message);
				});
				}
					
					 });
					
					}
					else{
						 $.post("kynamicAction_getParentNode.action",params,function(data1){
					 	var pNode=kynamicTree.tree.zTree.getNodeByParam("kid",data1.kynamic.kid);
					        	if(window.confirm("确定删除吗？")){
				                 $.post("kynamicAction_deleteNode.action",params,function(data2){
					                   kynamicTree.tree.zTree.removeNode(kynamicTree.tree.pNode);
					                   
									   kynamicTree.tree.zTree.refresh();
									   alert(data2.message);
				});
				}
					
					 });
					    }
				});
				
				
				}
			}else{
				var params={
					kid:kynamicTree.tree.pNode.kid
				};
				//判断是否有兄弟节点
				$.post("kynamicAction_getSibLingNode.action",params,function(data){
					if(data.kynamicList.length<2){//没有兄弟节点
					 $.post("kynamicAction_getParentNode.action",params,function(data1){
					 	var pNode=kynamicTree.tree.zTree.getNodeByParam("kid",data1.kynamic.kid);
					        	if(window.confirm("确定删除吗？")){
				                 $.post("kynamicAction_deleteNode.action",params,function(data2){
					                   kynamicTree.tree.zTree.removeNode(kynamicTree.tree.pNode);
					                   pNode.isParent=true;//更新子节点属性 相当于seting中keepParent=true
									   kynamicTree.tree.zTree.refresh();  //刷新树
									   alert(data2.message);
				});
				}
					
					 });
					
					}
					else{
						 $.post("kynamicAction_getParentNode.action",params,function(data1){
					 	     var pNode=kynamicTree.tree.zTree.getNodeByParam("kid",data1.kynamic.kid);
					        	if(window.confirm("确定删除吗？")){
				                 $.post("kynamicAction_deleteNode.action",params,function(data2){
					                   kynamicTree.tree.zTree.removeNode(kynamicTree.tree.pNode);
					                   
									   kynamicTree.tree.zTree.refresh();
									   alert(data2.message);
				});
				}
					
					 });
					    }
				});
				
				
			}
		},
		//修改节点
		updateNode:function(){
			var filename=window.prompt("请输入新名称",kynamicTree.tree.pNode.name);
			var params={
				name:filename,
				kid:kynamicTree.tree.pNode.kid
			};
			$.post("kynamicAction_getByName.action",{name:filename},function(data){
				if (data.message == "1") {
					alert("名称重复，请重新输入");
				}else{
					$.post("kynamicAction_updateNode.action",params,function(data1){
						kynamicTree.tree.pNode.name=filename;
						kynamicTree.tree.zTree.refresh();
						alert(data1.message);
						
					});
				}
			});
		},
	
	/*
	 * 版本维护
	 */
	version:{
		//控制versionList的显示
		showVersionList:function(versionList){
//<td width="240" height="26" align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;"><a>1</a></td>
//<td width="232" align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;">2010-5-24 09:56:33</td>
//<td width="231" align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;"><a>删除<a/></td>
		$("#showVersion").empty();
		for(var i=0;i<versionList.length;i++){
			var version=versionList[i].version;
			var updatetime=versionList[i].updateTime;
			var $versionA=$("<a/>");
			$versionA.text(version);
			$versionA.css("cursor","pointer");
			$versionA.attr("vid",versionList[i].vid);
			$versionA.attr("version",version);//将version作为标签对象的属性
			$versionA.unbind("click");
	  /**
             * click事件的函数是在单击的时候触发的，这段代码所在的函数showVersionsByKID，而这个函数在回调函数中
             *    versionList是传递过来的形参，所以回调函数的声明周期结束以后，该参数就不存在了,所以
             *       versinList[i] is undefined
             *       
             * 如果在$.post或者$.ajax中，回调函数中，调用了一个函数，而该函数中又有事件，那么事件中不能直接使用回调函数的形参
             *    因为事件执行的时候，回调函数已经执行完毕了，形参已经不存在了
             *    
             *    可以用 闭包(function(){要执行的内容})() 或是把事件要访问的参数赋为对象的属性
             */
			$versionA.bind("click",function(){
				//显示内容及标题
				kynamicTree.version.showContent($versionA.attr("vid"));
			});
			
			var $versionTd=$("<td/>");
			$versionTd.attr("width","180");
			$versionTd.attr("height","26");
			$versionTd.attr("align","center");
			$versionTd.attr("valign","middle");
			$versionTd.attr("bgcolor","FFFFFF");
			$versionTd.attr("style","border-bottom:1px solid #f3f8fd;");
			$versionTd.append($versionA);
			
			var $updatetimeTd=$("<td/>");
			$updatetimeTd.attr("width","232");
			$updatetimeTd.attr("align","center");
			$updatetimeTd.attr("valign","middle");
			$updatetimeTd.attr("bgcolor","FFFFFF");
			$updatetimeTd.attr("style","border-bottom:1px solid #f3f8fd;");
			$updatetimeTd.text(updatetime);
			
			var $deleteA=$("<a/>");
			$deleteA.text("删除");
			$deleteA.css("cursor","pointer");
			$deleteA.attr("vid",versionList[i].vid);
			$deleteA.unbind("click");
			$deleteA.bind("click",function(){
				//删除一条version记录
				kynamicTree.version.deleteVersion($deleteA.attr("vid"));
			});
			var $deleteTd=$("<td/>");
			$deleteTd.attr("width","231");
			$deleteTd.attr("align","center");
			$deleteTd.attr("valign","middle");
			$deleteTd.attr("bgcolor","FFFFFF");
			$deleteTd.attr("style","border-bottom:1px solid #f3f8fd;");
			$deleteTd.append($deleteA);
			
			var $tr=$("<tr/>");
			$tr.append($versionTd);
			$tr.append($updatetimeTd);
			$tr.append($deleteTd);
		    $("#showVersion").append($tr);
			
			
		}
		
		
		},
		//控制div 和check in的显示
		controlShowVersion:function(versionJson){
			if(versionJson.addVersion){
				$("#addVersion").show();
			}else{
				$("#addVersion").hide();
			}
			
			if(versionJson.versionList){
				$("#versionList").show();
			}else{
				$("#versionList").hide();
			}
			
			if(versionJson.checkin){
				$("#checkin").show();
			}else{
				$("#checkin").hide();
			}
			
			if(versionJson.checkout){
				$("#checkout").show();
			}else{
				$("#checkout").hide();
			}
			
		
		},
		/*
		 * 生成一条新的version记录
		 * 如果没有版本号初始值为1
		 * 否则版本号在原来基础上+1
		 */
		checkIn:function(){
			$("#checkin").unbind("click");
			$("#checkin").bind("click",function(){
				//保存当前输入内容生成一条新version纪录
				var params={
					title:$("#title").val(),
					content:$("#content").val(),
					kid:kynamicTree.tree.pNode.kid
				};
				$.post("versionAction_saveVersion.action",params,function(data){
					alert(data.message);
					var parameter={
					kid:kynamicTree.tree.pNode.kid
				};
				$.post("kynamicAction_getVersionByKid.action",parameter,function(data2){
					if(data2.versionList.length==0){
						kynamicTree.version.controlShowVersion({
					           addVersion:true,
					            versionList:false,
								checkin:true,
								checkout:true
				           });
					}else{
					    kynamicTree.version.controlShowVersion({
					           addVersion:false,
							   	checkin:false,
								checkout:false,
					            versionList:true
				           });	
						   kynamicTree.version.showVersionList(data2.versionList);
					 }
				});
				});
			});
			
		},
		//点击版本号显示title和content
		showContent:function(versionId){
			var params={
				vid:versionId
			};
			$.post("versionAction_getVersionByVid.action",params,function(data){
				$("#title").val(data.version.title);
				$("#content").val(data.version.content);
				       kynamicTree.version.controlShowVersion({
					            addVersion:true,
					            versionList:false,
								checkin:true,
								checkout:true
				              });
				
			});
		},
		//删除一条version记录
		deleteVersion:function(vid){
			var params={
				vid:vid
			};
			$.post("versionAction_deleteVersion.action",params,function(data){
				alert(data.message);
				var parameter={
					kid:kynamicTree.tree.pNode.kid
				};
				$.post("kynamicAction_getVersionByKid.action",parameter,function(data2){
					if(data2.versionList.length==0){
						kynamicTree.version.controlShowVersion({
					           addVersion:true,
					            versionList:false,
								checkin:true,
								checkout:true
				           });
					}else{
					    kynamicTree.version.controlShowVersion({
					           addVersion:false,
							   	checkin:false,
								checkout:false,
					            versionList:true
				           });	
						   kynamicTree.version.showVersionList(data2.versionList);
					 }
				});
				
			});
		}
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
		$("#deleteNode").bind("click",function(){
			kynamicTree.deleteNode();
		});
		
		$("#updateNode").unbind("click");
		$("#updateNode").bind("click",function(){
			kynamicTree.updateNode();
		});
	},function(){
		$("#rMenu").hide();
	});
	
	kynamicTree.version.checkIn();
});
