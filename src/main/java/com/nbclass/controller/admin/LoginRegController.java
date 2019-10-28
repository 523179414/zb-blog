package com.nbclass.controller.admin;

import com.nbclass.model.BlogUser;
import com.nbclass.service.UserService;
import com.nbclass.framework.util.ResponseUtil;
import com.nbclass.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * blog rest接口控制器
 *
 * @version V1.0
 * @date 2019/10/11
 * @author nbclass
 */
@RestController
public class LoginRegController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseVo login(BlogUser user){
        return userService.login(user);
    }

    @PostMapping("logout")
    public ResponseVo logout(){
        /*如有需求退出后token失效，jwtId可以做进redis*/
        return ResponseUtil.success("退出成功");
    }

}
