var Core = (function () {
    var core = {};

    //ajax请求
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
                Core.msg("错误："+XMLHttpRequest.status,2)
            }
        });
    };

    //消息提示
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

    //禁用button
    core.mask = function (e) {
        $(e).attr('disabled', "true");//添加disabled属性
    }

    //启用button
    core.unmask = function (e) {
        $(e).removeAttr('disabled');//添加disabled属性
    };

    //设置cookie
    core.setCookie = function (cname,cvalue,exdays){
        var d = new Date();
        d.setTime(d.getTime()+(exdays*24*60*60*1000));
        var expires = "expires="+d.toGMTString();
        document.cookie = cname+"="+cvalue+"; "+expires+";path=/";
    };

    //获取cookie
    core.getCookie =  function (cname){
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i].trim();
            if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
        }
        return "";
    };

    //删除cookie
    core.removeCookie = function (name){
        var cval=this.getCookie(name);
        if(cval!=null){
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            document.cookie= name + "="+cval+";expires="+exp.toGMTString()+";path=/";
        }
    };

    //格式化时间差
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

    //文字滚动
    core.textSlider = function (k,settings) {
        settings = jQuery.extend({
            speed: 300,
            line: 1,
            timer: 5000
        }, settings);
        return $(k).each(function () {
            Core.textSliderScroll($(k), settings);
        });
    };

    core.textSliderScroll = function ($this, settings) {
        var ul = $("ul:eq(0)", $this);
        var timerID;
        var li = ul.children();
        var liHight = $(li[0]).height();
        var upHeight = 0 - settings.line * liHight;//滚动的高度；
        var scrollUp = function () {
            ul.animate({marginTop: upHeight}, settings.speed, function () {
                for (i = 0; i < settings.line; i++) {
                    ul.find("li:first", $this).appendTo(ul);
                }
                ul.css({marginTop: 0});
            });
        };
        var autoPlay = function () {
            timerID = window.setInterval(scrollUp, settings.timer);
        };
        var autoStop = function () {
            window.clearInterval(timerID);
        };
        //事件绑定
        ul.hover(autoStop, autoPlay).mouseout();
    };

    core.slider = function(k, settings){
        settings = jQuery.extend({
            speed:500,
            spaceBetween: 30,
            centeredSlides: true,
            loop : true,
            effect : 'fade',
            autoplay: {
                delay: 4000,
                disableOnInteraction: false,
            },
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        }, settings);
        if($(k).length){
            var swiper = new Swiper($(k), settings);
            swiper.el.onmouseover = function(){
                swiper.autoplay.stop();
            };
            swiper.el.onmouseout = function(){
                swiper.autoplay.start();
            };
        }
    };
    return core;
})(Core, window);

//回到顶部
$(window).scroll(function() {
    var scroTop = $(window).scrollTop();
    if (scroTop > 100) {
        $('.return_top').css("opacity","0.4");
    } else {
        $('.return_top').css("opacity","0");
    }
});

$(function () {
    $('.return_top').click(function(){
        $("html,body").animate({scrollTop:0},"fast");
    });
});

