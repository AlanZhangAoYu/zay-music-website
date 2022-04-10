package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;
import service.UserService;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ZAY
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping(value = "/selectAllUser",produces = "application/json; charset=utf-8")
    public String selectAllUserController(@RequestParam("page") int page){
        ArrayList<User> users = userService.selectAllUser(page);
        return JSON.toJSONString(users);
    }
    @PostMapping(value = "/userRegister",produces = "application/json; charset=utf-8")
    public String userRegisterController(User user){
        HashMap<String,Integer> map=new HashMap<>(1);
        int result = userService.userRegisterService(user);
        if(result == 1){
            map.put("code",0);
            return JSON.toJSONString(map);
        }
        map.put("code",-1);
        return JSON.toJSONString(map);
    }
    @GetMapping(value = "/selectAllUserCount",produces = "application/json; charset=utf-8")
    public String selectAllUserCountController(){
        HashMap<String,Integer> map=new HashMap<>(1);
        int result = userService.selectAllUserCount();
        map.put("allUserCount",result);
        return JSON.toJSONString(map);
    }
    @PostMapping(value = "/userLogin",produces = "application/json; charset=utf-8")
    public String userLoginController(@RequestParam("userAccount") String userAccount,
                                      @RequestParam("userPassword") String userPassword){
        HashMap<String,String> map=userService.userLoginService(userAccount,userPassword);
        return JSON.toJSONString(map);
    }
}
