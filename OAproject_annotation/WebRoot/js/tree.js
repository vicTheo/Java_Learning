var tree={
		setting:{
		isSimpleData: true,
		treeNodeKey: "mid",
		treeNodeParentKey: "pid",
		showLine: true,
		root:{ 
			isRoot:true,
			nodes:[]
		}
	},
	/**
	 * 1���ص��������ɷ������˴����ģ�ʲôʱ��ִ���ɷ���������
	 * 2���ص���������jQuery�ں˵��õ�
	 * 3���ͻ��˴��������߳�
	 * 4�������js�����У���һЩ����Ҫ�õ��ص������е����ݣ���ô��Щ���������ڻص�������
	 */
	loadtree:function(){
		$.post("menuitemAction_getAllMenuitems.action",null,function(data){
			$("#tree").zTree(tree.setting,data.menuitemList);
			
		})
	}
};
$().ready(function(){
	 tree.loadtree();
	
});
