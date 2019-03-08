/*****表格*****/
//通过分页方式，选择加载数据的方式
//通过
var TableUtil = {
	init : function (queryUrl) {
        var table = $('#ArbetTable').bootstrapTable({
            url: queryUrl,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            contentType: "application/x-www-form-urlencoded",
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //height: $(window).height()*0.79,    //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [
                {
                    field: 'id',
                    title: '模版名称'
                }, {
                    field: 'name',
                    title: '类型'
                }, {
                    field: 'sex',
                    title: '描述'
                },
                {
                    field: 'operate',
                    title: '操作',
                    width: 260,
                    align: 'center',
                    valign: 'middle',
                    formatter: operateFormatter //自定义方法，添加操作按钮
                },
            ],
            rowStyle: function (row, index) {
                //这里有5个取值颜色['active', 'success', 'info', 'warning', 'danger'];
                var strclass = "";
                // if (index == 0) {
                //     strclass = "warning";
                // }
                return { classes: strclass }
            },//隔行变色
            queryParams : function (params) {//传递参数（*）
                var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    limit: params.limit,   //页面大小
                    offset: params.offset
                    // sort: params.sort,      //排序列名
                    // sortOrder: params.order //排位命令（desc，asc）
                };
                return temp;
            }
        });
        return table;
	}
}
function operateFormatter(value, row, index) {//赋予的参数
    // return [
    //     '<a class="btn active disabled" href="#">编辑</a>',
    //     '<a class="btn active" href="#">档案</a>',
    //     '<a class="btn active" href="#">记录</a>',
    //     '<a class="btn active" href="#">准入</a>'
    // ].join('');
}