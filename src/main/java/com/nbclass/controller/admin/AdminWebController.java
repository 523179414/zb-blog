package com.nbclass.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * blog rest接口控制器
 *
 * @version V1.0
 * @date 2019/10/11
 * @author nbclass
 */
@Controller
public class AdminWebController {

    private static final String pathSuffix="admin/";


    @GetMapping("/login")
    public String login() {
        return  pathSuffix + "login";
    }

}
