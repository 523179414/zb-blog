/*Core*/
var Core = (function () {
    var core = {};
    var coreOptions;
    /*Core的参数对象*/
    coreOptions = {
        tableOptions: {
            id: "",
            url: "",
            columns: [],
            uniqueId: "id",//每一行的唯一标识，一般为主键列
            method: "post",//请求方式（*）
            undefinedText: "-", /*为undefiend时显示的字*/
            striped: false, //是否显示行间隔色
            queryParams: queryInitParams,
            responseHandler: responseHandler,
            toolbar: '',        //工具按钮用哪个容器
            pageNumber: 1,
            pageSize: 10,
            pageList: [10, 20, 50, 999],
            contentType: "application/x-www-form-urlencoded",//用post请求，这个是必须条件，必须加上，get可以不用，亲测
            dataType: "json",
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: false, //是否启用排序
            sortOrder: "asc", //排序方式
            sortName: "", //排序字段
            queryParamsType: "limit",
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
            showColumns: false, //是否显示所有的列
            showRefresh: false, //是否显示刷新按钮
            minimumCountColumns: 2, //最少允许的列数
            clickToSelect: true, //是否启用点击选中行
            strictSearch: true,
            showToggle: false, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表
            showExport: false, //是否显示导出
            exportDataType: "basic", //basic', 'all', 'selected'.
            escape: true,//html转意
            onLoadSuccess: tableLoadSuccess
        }
    };
    /*ajax请求*/
    core.postAjax = function (url, dataToPost, d, type, contentType, async) {
        $.ajax({
            url: url,
            cache: false,
            async: async == undefined ? true : async,
            data: dataToPost,
            type: type == undefined ? "POST" : type,
            contentType: contentType == undefined ? 'application/x-www-form-urlencoded; charset=UTF-8' : contentType,
            success: function (data) {
                if (typeof d == "function") {
                    d(data);
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                if(XMLHttpRequest.status==403){
                    Core.msg("您没有权限访问，请联系管理员！",2)
                }else if(XMLHttpRequest.status==500){
                    Core.msg("服务器内部错误！",2)
                }else if(XMLHttpRequest.status==404){
                    Core.msg("您访问的内容不存在！",2)
                }else{
                    Core.msg("服务器未知错误！",2)
                }
            }
        });
    };
    /*load()*/
    core.load = function (id,url,d,t) {
        $(id).html("");
        $(id).load(url,function(response,status,XMLHttpRequest){
            if (typeof d == "function" && status=="success") {
                d();
            }
            if(status=="error"){
                if(t==undefined||t==1){
                    $("#content").html(response);
                }else if(t=2){
                    if(XMLHttpRequest.status==403){
                        Core.msg("您没有权限访问！",2)
                    }else if(XMLHttpRequest.status==500){
                        Core.msg("服务器内部错误！",2)
                    }else{
                        Core.msg("服务器未知错误！",2)
                    }
                }
            }
        })
    };

    /*消息提示*/
    core.msg = function(msg,d,type) {
        if(typeof d === "number"){
            type=d;
        };
        var alertId = Core.generateMixed(6);
        var svgContent= (type===undefined||type===1) ? '<svg t="1571189863238" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3745" width="24" height="24"><path d="M837.461333 535.466667a19.072 19.072 0 0 0-22.314666 15.573333 326.314667 326.314667 0 0 1-321.792 269.226667c-180.266667 0-326.912-146.645333-326.912-326.912a326.826667 326.826667 0 0 1 597.930666-182.784 323.157333 323.157333 0 0 1 50.773334 125.056 19.242667 19.242667 0 0 0 37.888-6.698667 360.704 360.704 0 0 0-56.789334-139.946667A365.098667 365.098667 0 0 0 493.312 128 365.226667 365.226667 0 0 0 128 493.354667c0 201.472 163.882667 365.354667 365.354667 365.354666a364.672 364.672 0 0 0 359.68-300.885333 19.2 19.2 0 0 0-15.573334-22.314667" p-id="3746" fill="#2bc80f"></path><path d="M650.538667 389.504l-199.978667 200.021333-119.765333-119.765333a18.816 18.816 0 0 0-26.624 26.581333l132.224 132.181334c0.128 0.170667 0.341333 0.213333 0.469333 0.341333 0.170667 0.170667 0.213333 0.426667 0.426667 0.597333a18.773333 18.773333 0 0 0 26.581333 0l213.290667-213.333333a18.816 18.816 0 0 0-26.624-26.624" p-id="3747" fill="#2bc80f"></path></svg>' : '<svg t="1571190723226" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4874" width="24" height="24"><path d="M872.746667 573.866667a18.858667 18.858667 0 0 0-22.058667 15.445333 322.901333 322.901333 0 0 1-318.464 266.368c-178.389333 0-323.498667-145.066667-323.498667-323.498667a323.413333 323.413333 0 0 1 591.701334-180.906666 319.829333 319.829333 0 0 1 50.261333 123.818666 19.072 19.072 0 0 0 37.461333-6.656A356.949333 356.949333 0 0 0 832 329.984 361.301333 361.301333 0 0 0 532.224 170.666667 361.429333 361.429333 0 0 0 170.666667 532.181333c0 199.381333 162.218667 361.557333 361.557333 361.557334a360.874667 360.874667 0 0 0 355.925333-297.770667 19.029333 19.029333 0 0 0-15.36-22.058667" p-id="4875" fill="#a94442"></path><path d="M532.224 411.306667a20.138667 20.138667 0 0 0-20.138667 20.181333v322.133333a20.138667 20.138667 0 0 0 40.277334 0v-322.133333a20.138667 20.138667 0 0 0-20.138667-20.181333M552.362667 310.613333a20.138667 20.138667 0 0 0-40.277334 0v40.277334a20.138667 20.138667 0 0 0 40.277334 0v-40.32z" p-id="4876" fill="#a94442"></path></svg>';
        var html='<div id="'+alertId+'" class="alert '+((type===undefined||type===1)? 'alert-success':'alert-danger')+'">'+svgContent+'<span class="alert-text">'+msg+'</span></div>';
        $("body").append(html);
        $("#"+alertId).css("display","flex");
        setTimeout(function () {
            $("#"+alertId).remove();
            if (typeof d === "function") {
                d();
            }
        }, 2500);
    };

    core.generateMixed = function (n) {
        var res = "";
        var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
        for(var i = 0; i < n ; i ++) {
            var id = Math.ceil(Math.random()*35);
            res += chars[id];
        }
        return res;
    };

    /*bootstrap-table表格*/
    core.initTable = function (options, success) {
        var tableOptions = $.extend({}, coreOptions.tableOptions, options);
        $(tableOptions.id).bootstrapTable({
            url: tableOptions.url, //请求后台的URL（*）
            contentType: tableOptions.contentType, //用post请求，这个是必须条件，必须加上，get可以不用，亲测
            dataType: tableOptions.dataType,
            method: tableOptions.method, //请求方式（*）
            //            toolbar: '#toolbar',        //工具按钮用哪个容器
            undefinedText: tableOptions.undefinedText, /*为undefiend时显示的字*/
            striped: tableOptions.striped, //是否显示行间隔色
            cache: tableOptions.cache, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: tableOptions.pagination, //是否显示分页（*）
            sortable: tableOptions.sortable, //是否启用排序
            sortOrder: tableOptions.sortOrder, //排序方式
            sortName: tableOptions.sortName, //排序方式
            toolbar: tableOptions.toolbar,
            //            search: true,             //是否使用客户端搜索
            queryParams: tableOptions.queryParams,//传递参数（*）
            responseHandler: tableOptions.responseHandler,
            queryParamsType: tableOptions.queryParamsType,
            sidePagination: tableOptions.sidePagination, //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: tableOptions.pageNumber, //初始化加载第一页，默认第一页
            pageSize: tableOptions.pageSize, //每页的记录行数（*）
            pageList: tableOptions.pageList, //可供选择的每页的行数（*）
            showColumns: tableOptions.showColumns, //是否显示所有的列
            showRefresh: tableOptions.showRefresh, //是否显示刷新按钮
            minimumCountColumns: tableOptions.minimumCountColumns, //最少允许的列数
            clickToSelect: tableOptions.clickToSelect, //是否启用点击选中行
            strictSearch: tableOptions.strictSearch,
            //            height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            showToggle: tableOptions.showToggle, //是否显示详细视图和列表视图的切换按钮
            uniqueId: tableOptions.uniqueId, //每一行的唯一标识，一般为主键列
            cardView: tableOptions.cardView, //是否显示详细视图
            detailView: tableOptions.detailView, //是否显示父子表
            showExport: tableOptions.showExport, //是否显示导出
            exportDataType: tableOptions.exportDataType, //basic', 'all', 'selected'.
            escape: tableOptions.escape,//html转意
            //            align: "center",
            columns: tableOptions.columns,//表格列
            onLoadSuccess: tableOptions.onLoadSuccess,
            onPostBody : function () {
                //改变复选框样式
                $(tableOptions.id).find("input:checkbox").each(function (i) {
                    var $check = $(this);
                    if ($check.attr("id") && $check.next("label")) {
                        return;
                    }
                    var name = $check.attr("name");
                    var id = name + "-" + i;
                    var $label = (i==0?$('<label for="'+ id +'"></label>'):$('<label></label>'));
                    $check.attr("id", id).parent().addClass("zb-checkbox").append($label);
                });
                $(tableOptions.id).find("input:radio").each(function (i) {
                    var $check = $(this);
                    if ($check.attr("id") && $check.next("label")) {
                        return;
                    }
                    var name = $check.attr("name");
                    var id = name + "-" + i;
                    var $label = (i==0?$('<label for="'+ id +'"></label>'):(tableOptions.clickToSelect==true?$('<label></label>'):$('<label for="'+ id +'"></label>')));
                    $check.attr("id", id).parent().addClass("zb-radio").append($label);
                });
                if ($.isFunction(options.onPostBody)) {
                    options.onPostBody();
                }
            }
        });
    };

    function queryInitParams(params) {
        var temp = { //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
            limit: params.limit, //页面大小
            offset: params.offset //页码
        };
        return temp;
    }

    function responseHandler(data) {
        return data;
    }

    function tableLoadSuccess(data) {
    }

    /*刷新表格 ：flag-是否跳转到当前页。默认首页*/
    core.refreshTable = function (id, flag) {
        if (flag) {
            $(id).bootstrapTable("refresh");
        } else {
            $(id).bootstrapTable("refresh", {"pageNumber": 1});
        }
    };

    /*根据data选中数据*/
    core.checkTableBy=function (id,data) {
        $(id).bootstrapTable("checkBy", data)
    };

    /*根据uniqueId获取所选列*/
    core.getRowByUniqueId = function (id, val) {
        return $(id).bootstrapTable("getRowByUniqueId", val);
    };
    core.selectSingleData = function (id){
        var selectContent = $(id).bootstrapTable('getSelections');
        if(typeof(selectContent) == 'undefined' || selectContent == "") {
            Core.msg("请先选择一条数据!",2);
            return false;
        }else if(selectContent.length > 1){
            Core.msg("只能选择一条数据!",2);
            return false;
        }else{
            var selectData = selectContent[0];
            return selectData;
        }
    };

    core.selectMutiData = function (id){
        var checkedRows= $(id).bootstrapTable('getSelections');
        if(checkedRows.length==0){
            Core.msg("请先选择一条数据！",2);
            return false;
        }else{
            return checkedRows;
        }
    };


    /*更新某一列的值 index-行索引，field-字段名，value-值*/
    core.updateCell = function (id, index, field, value) {
        var updateCellOptions = {
            index: index,
            field: field,
            value: value
        };
        return $(id).bootstrapTable("updateCell", updateCellOptions);
    };

    /*禁用button*/
    core.mask = function (e) {
        $(e).attr('disabled', "true");//添加disabled属性
    }
    /*启用button*/
    core.unmask = function (e) {
        $(e).removeAttr('disabled');//添加disabled属性
    };

    //date类型到字符串
    core.formatterDateTime = function (date) {
        var datetime = date.getFullYear()
            + "-"// "年"
            + ((date.getMonth() + 1) >= 10 ? (date.getMonth() + 1)
                : "0" + (date.getMonth() + 1))
            + "-"// "月"
            + (date.getDate() < 10 ? "0" + date.getDate() : date
                .getDate())
            + " "
            + (date.getHours() < 10 ? "0" + date.getHours() : date
                .getHours())
            + ":"
            + (date.getMinutes() < 10 ? "0" + date.getMinutes()
                : date.getMinutes())
            + ":"
            + (date.getSeconds() < 10 ? "0" + date.getSeconds()
                : date.getSeconds());
        return datetime;
    };

    //long类型转时间字符串
    core.longMsTimeConvertToDateTime = function (time) {
        if(time==null) return "-";
        var myDate = new Date(time);
        return this.formatterDateTime(myDate);
    };

    /*日期+*/
    core.addDate = function (date, days) {
        if (days == undefined || days == '') {
            days = 1;
        }
        var date = new Date(date);
        date.setDate(date.getDate() + days);
        var month = date.getMonth() + 1;
        var day = date.getDate();
        return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day);
    };
    function getFormatDate(arg) {
        if (arg == undefined || arg == '') {
            return '';
        }
        var re = arg + '';
        if (re.length < 2) {
            re = '0' + re;
        }
        return re;
    }

    /*是否是数组*/
    core.isArray = function (s) {
        return s instanceof Array;
    };

    core.clearForm = function (id) {

        var objId = document.getElementById(id);
        if (objId == undefined) {
            return;
        }
        for (var i = 0; i < objId.elements.length; i++) {
            if (objId.elements[i].type == "text") {
                objId.elements[i].value = "";
            }
            else if (objId.elements[i].type == "password") {
                objId.elements[i].value = "";
            }
            else if (objId.elements[i].type == "radio") {
                objId.elements[i].checked = false;
            }
            else if (objId.elements[i].type == "checkbox") {
                objId.elements[i].checked = false;
            }
            else if (objId.elements[i].type == "select-one") {
                objId.elements[i].options[0].selected = true;
            }
            else if (objId.elements[i].type == "select-multiple") {
                for (var j = 0; j < objId.elements[i].options.length; j++) {
                    objId.elements[i].options[j].selected = false;
                }
            }
            else if (objId.elements[i].type == "textarea") {
                objId.elements[i].value = "";
            }
        }
    };

    /*清除表单错误提示*/
    core.clearError = function (id) {
        $(id).find(".warning,.valid,.promimg").remove();
        $(id).find(".error").removeClass("error");
        $(id).find(".prombtn").removeClass("prombtn");
        $(id).find(".prominput").removeClass("prominput");
    };
    /*保留两位小数*/
    core.numberTwo = function (num) {
        if (isNaN(num) || num == "") {
            return num;
        } else {
            if (isNaN(parseFloat(num).toFixed(2))) {
                return num;
            } else {
                return parseFloat(num).toFixed(2);
            }
        }
    };
    /*数字千分话并保留两位小数*/
    core.numToTwo = function (num) {
        try {
            num = this.numberTwo(num).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
        } finally {
            return num;
        }
    };


    // 判断是否为json对象
    core.isJsonObject = function (obj) {
        var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
        return isjson;
    };

    core.setCookie = function (cname,cvalue,exdays){
        var d = new Date();
        d.setTime(d.getTime()+(exdays*24*60*60*1000));
        var expires = "expires="+d.toGMTString();
        document.cookie = cname+"="+cvalue+"; "+expires+";path=/";
    };
    core.setCookieLong = function (cname,cvalue,longtime){
        var d = new Date();
        d.setTime(longtime);
        var expires = "expires="+d.toGMTString();
        document.cookie = cname+"="+cvalue+"; "+expires+";path=/";
    };
    core.getCookie =  function (cname){
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i].trim();
            if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
        }
        return "";
    };
    core.removeCookie = function (name){
        var cval=this.getCookie(name);
        if(cval!=null){
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            document.cookie= name + "="+cval+";expires="+exp.toGMTString()+";path=/";
        }
    };
    core.getCurrentUser =  function (){
       var access_token = this.getCookie("access_token");
       if (access_token===""){
           return null;
       }else{
           return JSON.parse(window.atob(access_token.split(".")[1]));
       }
    };

    core.getDateDiff = function(dateTimeStamp) {
        var minute = 1000 * 60;
        var hour = minute * 60;
        var day = hour * 24;
        var halfamonth = day * 15;
        var month = day * 30;
        var year = day * 365;
        var now = new Date().getTime();
        var diffValue = now - dateTimeStamp;
        if (diffValue < 0) {
            //若日期不符则弹出窗口告之
            return "结束日期不能小于开始日期！";
        }
        var yearC = diffValue / year;
        var monthC = diffValue / month;
        var weekC = diffValue / (7 * day);
        var dayC = diffValue / day;
        var hourC = diffValue / hour;
        var minC = diffValue / minute;
        if(yearC>=1){
            result = parseInt(yearC) + "年前";
        }else if (monthC >= 1) {
            result = parseInt(monthC) + "个月前";
        }else if (weekC >= 1) {
            result = parseInt(weekC) + "周前";
        } else if (dayC >= 1) {
            result = parseInt(dayC) + "天前";
        }else if (hourC >= 1) {
            result = parseInt(hourC) + "小时前";
        }else if (minC >= 1) {
            result = parseInt(minC) + "分钟前";
        }else{
            result = "刚刚";
        }
        return result;
    };

    return core;
})(Core, window);
Date.prototype.Format = function(fmt) {
    var o = {
        "M+" : this.getMonth() + 1, // 月份
        "d+" : this.getDate(), // 日
        "h+" : this.getHours(), // 小时
        "m+" : this.getMinutes(), // 分
        "s+" : this.getSeconds(), // 秒
        "q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
        "S" : this.getMilliseconds()
// 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for ( var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;

};