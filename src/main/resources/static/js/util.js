utils = {
    //公共弹窗成功
    success:{
        show:(function (title,parm,func) {
            var diaLog = '<div class="confirm-box ctb"><div class="cf-b-in"><p class="title okicon"></p><p class="parm"></p><div class="btn"><a href="javascript:;" class="true" role="public-close">确认</a></div></div></div>'
            var jqdiaLog = $(diaLog);
            $("body").append(jqdiaLog);
            $("body").append('<div id="shade" class="addShade"></div>');
            $(".cf-b-in .title").html(title);
            $(".cf-b-in .parm").html(parm);
            if (typeof(func) == 'function') 
                jqdiaLog.find("a.true").click(func);
            $("a[role='public-close']").click(function () {
                $(".confirm-box").remove();
                $(".addShade").remove();
            })
        })
    },
    //公共弹窗失败
    errors:{
        show:(function (title,parm,func) {
            var diaLog = '<div class="confirm-box ctb"><div class="cf-b-in"><p class="title erroricon"></p><p class="parm"></p><div class="btn"><a href="javascript:;" class="true" role="public-close">确认</a></div></div></div>'
            var jqdiaLog = $(diaLog);
            $("body").append(jqdiaLog);
            $("body").append('<div id="shade" class="addShade"></div>');
            $(".cf-b-in .title").html(title);
            $(".cf-b-in .parm").html(parm);
            if (typeof(func) == 'function') 
                jqdiaLog.find("a.true").click(func);
            $("a[role='public-close']").click(function () {
                $(".confirm-box").remove();
                $(".addShade").remove();
            })
        })
    },
    //取消确定
    confirms:{
        show:(function (title,parm,func) {
            var diaLog = '<div class="confirm-box ctb"><div class="cf-b-in"><p class="title erroricon">激活失败</p><p class="parm">请核对激活信息</p><div class="btn twobtn clearfix"><a href="javascript:;" class="true" role="public-close">确认</a> <a href="javascript:;" class="cancel" role="public-close">取消</a></div></div></div>'
            var jqdiaLog = $(diaLog);
            $("body").append(jqdiaLog);
            $("body").append('<div id="shade" class="addShade"></div>');
            $(".cf-b-in .title").html(title);
            $(".cf-b-in .parm").html(parm);
            if (typeof(func) == 'function') 
                jqdiaLog.find("a.true").click(func);
            $("a[role='public-close']").click(function () {
                $(".confirm-box").remove();
                $(".addShade").remove();
            })
        })
    },
    //公共加载状态显示
    openLoading:{
        show:(function (parm) {
            var ldhtml = '<div class="loaderAni"><div class="la-line-scale la-2x"><div></div><div></div><div></div><div></div><div></div></div><p class="text">'+ parm +'</p></div>';
            $("body").append(ldhtml);
            $("body").append('<div class="loadershade"></div>')
        }),
        hide:(function () {
            $(".loaderAni").remove();
            $(".loadershade").remove();
        })
    },
}