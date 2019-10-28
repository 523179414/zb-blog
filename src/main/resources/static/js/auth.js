var Auth = {
    vars: {
        lowin: document.querySelector('.lowin'),
        lowin_brand: document.querySelector('.lowin-brand'),
        lowin_wrapper: document.querySelector('.lowin-wrapper'),
        lowin_login: document.querySelector('.lowin-login'),
        lowin_wrapper_height: 0,
        login_back_link: document.querySelector('.login-back-link'),
        forgot_link: document.querySelector('.forgot-link'),
        login_link: document.querySelector('.login-link'),
        login_btn: document.querySelector('.login-btn'),
        register_btn: document.querySelector('.register-btn'),
        register_link: document.querySelector('.register-link'),
        password_group: document.querySelector('.password-group'),
        password_group_height: 0,
        lowin_register: document.querySelector('.lowin-register'),
        lowin_footer: document.querySelector('.lowin-footer'),
        box: document.getElementsByClassName('lowin-box'),
        option: {}
    },
    register(e) {
        Auth.vars.lowin_login.className += ' lowin-animated';
        setTimeout(()=>{
            Auth.vars.lowin_login.style.display = 'none';
        }
        , 300);
        Auth.vars.lowin_register.style.display = 'block';
        Auth.vars.lowin_register.className += ' lowin-animated-flip';
        e.preventDefault();
    },
    login(e) {
        Auth.vars.lowin_register.classList.remove('lowin-animated-flip');
        Auth.vars.lowin_register.className += ' lowin-animated-flipback';
        Auth.vars.lowin_login.style.display = 'block';
        Auth.vars.lowin_login.classList.remove('lowin-animated');
        Auth.vars.lowin_login.className += ' lowin-animatedback';
        setTimeout(()=>{
            Auth.vars.lowin_register.style.display = 'none';
        }
        , 300);
        setTimeout(()=>{
            Auth.vars.lowin_register.classList.remove('lowin-animated-flipback');
            Auth.vars.lowin_login.classList.remove('lowin-animatedback');
        }
        , 600);
        e.preventDefault();
    },
    forgot(e) {
        Auth.vars.password_group.classList += ' lowin-animated';
        Auth.vars.login_back_link.style.display = 'block';
        setTimeout(()=>{
            Auth.vars.login_back_link.style.opacity = 1;
            Auth.vars.password_group.style.height = 0;
            Auth.vars.password_group.style.margin = 0;
        }
        , 100);
        setTimeout(()=>{
            Auth.vars.password_group.style.display = 'none';
        }
        , 600);
        Auth.vars.login_btn.innerText = 'Forgot Password';
        Auth.vars.login_btn.setAttribute('act', 'forgot');
        e.preventDefault();
    },
    loginback(e) {
        Auth.vars.password_group.classList.remove('lowin-animated');
        Auth.vars.password_group.classList += ' lowin-animated-back';
        Auth.vars.password_group.style.display = 'block';
        setTimeout(()=>{
            Auth.vars.login_back_link.style.opacity = 0;
            Auth.vars.password_group.style.height = Auth.vars.password_group_height + 'px';
            Auth.vars.password_group.style.marginBottom = 30 + 'px';
        }
        , 100);
        setTimeout(()=>{
            Auth.vars.login_back_link.style.display = 'none';
            Auth.vars.password_group.classList.remove('lowin-animated-back');
        }
        , 600);
        Auth.vars.login_btn.innerText = 'Sign In';
        Auth.vars.login_btn.setAttribute('act', 'login');
        e.preventDefault();
    },
    setHeight(height) {
        Auth.vars.lowin_wrapper.style.minHeight = height + 'px';
    },
    brand() {
        Auth.vars.lowin_brand.classList += ' lowin-animated';
        setTimeout(()=>{
            Auth.vars.lowin_brand.classList.remove('lowin-animated');
        }
        , 600);
    },
    init(option) {
        Auth.vars.password_group.style.height = Auth.vars.password_group.offsetHeight + 'px';
        Auth.vars.password_group_height = Auth.vars.password_group.offsetHeight;
        Auth.vars.lowin_wrapper_height = Auth.vars.lowin_wrapper.offsetHeight;
        Auth.vars.option = option;
        Auth.vars.login_btn.setAttribute('act', 'login');
        var len = Auth.vars.box.length - 1;
        for (var i = 0; i <= len; i++) {
            if (i !== 0) {
                Auth.vars.box[i].className += ' lowin-flip';
            }
        }
        Auth.vars.forgot_link.addEventListener("click", (e)=>{
            Auth.forgot(e);
        });
        Auth.vars.register_link.addEventListener("click", (e)=>{
            Auth.brand();
            Auth.register(e);
        });
        Auth.vars.login_link.addEventListener("click", (e)=>{
            Auth.brand();
            Auth.login(e);
        });
        Auth.vars.login_back_link.addEventListener("click", (e)=>{
            Auth.loginback(e);
        });
        Auth.vars.login_btn.addEventListener("click", (e)=>{
        	var act = Auth.vars.login_btn.getAttribute("act");
        	if(act==="login"){
        		Auth.submitLogin(e);
        	}else if(act==="forgot"){
        		Auth.submitForgot(e);
        	}
        });
        Auth.vars.register_btn.addEventListener("click", (e)=>{
            Auth.submitRegister(e);
        });
    },
    submitLogin(e){
        Core.mask(".login-btn");
        Core.postAjax(Auth.vars.option.login_url, $("#login-form").serialize(),function (data) {
            if(data.status===200){
                var access_token=data.data;
                var payload = JSON.parse(window.atob(access_token.split(".")[1]));
                Core.setCookieLong("access_token",access_token,payload.exp*1000);
                Core.msg(data.msg, function(){
                    Core.unmask(".login-btn");
                    window.location.href="/"
                })
            }else{
                Core.unmask(".login-btn");
                Core.msg(data.msg, 2);
            }
        });
    },
    submitRegister(e){
        Core.mask(".register-btn");
        Core.postAjax(Auth.vars.option.register_url, $("#register-form").serialize(),function (data) {
            if(data.status===200){
                var access_token=data.data;
                var payload = JSON.parse(window.atob(access_token.split(".")[1]));
                Core.setCookieLong("access_token",access_token,payload.exp*1000);
                Core.msg(data.msg, function(){
                    Core.unmask(".login-btn");
                    window.location.href="/"
                })
            }else{
                Core.unmask(".register-btn");
                Core.msg(data.msg, 2);
            }
        });
    },
    submitForgot(e){
        Core.msg("忘记密码暂未开放",2)
    }
}
