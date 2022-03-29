package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author ZAY
 */
@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @PostMapping(value = "/adminLogin",produces = "application/json; charset=utf-8")
    public String adminLoginController(@RequestBody HashMap<String,String> para){
        HashMap<String,String> map=new HashMap<>(1);
        map.put("adminName",adminService.adminLoginService(para.get("adminAccount"), para.get("adminPassword")));
        return JSON.toJSONString(map);
    }
}
