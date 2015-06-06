var tree = {
    zTree: '',
    pNode: '',
    setting: {
        isSimpleData: true,
        treeNodeKey: "mid",
        treeNodeParentKey: "pid",
        showLine: true,
        root: {
            isRoot: true,
            nodes: []
        },
        callback: {
            /**
             * @param {Object} event
             *     ����¼�
             * @param {Object} treeId
             *     ��������ID
             * @param {Object} treeNode
             *     ��ǰ����Ľڵ�
             */
            expand: function(event, treeId, treeNode){
                tree.pNode = treeNode;
                tree.loadNodeByPNODE();
            }
        }
    },
    /**
     * 1���ص��������ɷ������˴����ģ�ʲôʱ��ִ���ɷ���������
     * 2���ص���������jQuery�ں˵��õ�
     * 3���ͻ��˴��������߳�
     * 4�������js�����У���һЩ����Ҫ�õ��ص������е����ݣ���ô��Щ���������ڻص�������
     */
    loadTree: function(){
        $.post("menuitemAction_getAllMenuitem.action", null, function(data){
            $("#tree").zTree(tree.setting, data.menuitemList);
        });
    },
    /**
     * һ������£����һ�δ�����Ҫ�õ�һ�������������������ֵ���ڻص������и�ֵ�ģ����ʱ��һ��Ҫȷ��ʹ�øú���ʱ���ص������Ѿ�ִ����
     */
    loadRootNode: function(){
        var parameter = {
            pid: 0
        };
        $.post("menuitemAction_showMenuitemsByPid.action", parameter, function(data){
            tree.zTree = $("#tree").zTree(tree.setting, data.menuitemList);
        });
    },
    /**
     * �÷������ڵ�����ڵ��+�ŵ�ʱ��ִ�еģ������ζ����ִ�и÷�����ʱ�������Ѿ�������,���Բ�����tree.zTree;
     */
    loadNodeByPNODE: function(){
        var parameter = {
            pid: tree.pNode.mid
        };
        if (!tree.zTree.getNodeByParam("pid", tree.pNode.mid)) {
            $.post("menuitemAction_showMenuitemsByPid.action", parameter, function(data){
                /**
                 * �Ѳ�ѯ�������ӽڵ�׷�ӵ����ڵ���
                 */
                tree.zTree.addNodes(tree.pNode, data.menuitemList, true);
            });
        }
    }
};
$().ready(function(){
    //tree.loadTree();
    tree.loadRootNode();
	//�÷��������ʲô��ȷ������Ϊ��ִ�иô����ʱ�򣬻ص�����ִ�в�ȷ��������tree.zTree�ĸ�ֵҲ��ȷ��
    //alert(tree.zTree);
});

/**

 * ����¼�����

 *   *  ���ظ��ڵ�

 *   *  ����ýڵ��+�ţ������¼������ظýڵ���ӽڵ�

 */

