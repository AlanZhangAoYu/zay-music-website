package controller;

import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import javax.annotation.Resource;

/**
 * @author ZAY
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;
}
