package com.azhuo.usercrud.controller;

import com.azhuo.usercrud.pojo.UserInfo;
import com.azhuo.usercrud.service.UserService;
import com.azhuo.usercrud.utils.Result;
import com.azhuo.usercrud.utils.StatuCode;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // 相当于：@ResponseBody、@Controller两个注解
@CrossOrigin // 解决跨域访问
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     *
     * @return
     */
    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public Result findAll() {

        List<UserInfo> userInfos = userService.findAll();

        return new Result("查询成功", StatuCode.OK, userInfos);
    }


    /**
     * 根据id查询用户
     *
     * @param uid
     * @return
     */
    // @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    @GetMapping(value = "/{uid:\\d+}") // ":"后为正则表达式校验
    public Result findUserById(@PathVariable Long uid) {

        // System.out.println("uid = " + uid);

        UserInfo userInfo = userService.findUserById(uid);

        return new Result("查询成功", StatuCode.OK, userInfo);
    }

    /**
     * 添加用户
     *
     * @return
     */
    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    // @Valid启动实体类的NotNull非空注解
    // BindingResult: 绑定错误信息
    public Result addUser(@RequestBody @Valid UserInfo userInfo, BindingResult error) {

        // 是否存在错误
        if (error.hasErrors()) {
            // 流式编程，java8箭头函数
            error.getAllErrors().stream().forEach(err -> {
                System.out.println(ReflectionToStringBuilder.toString(err, ToStringStyle.MULTI_LINE_STYLE));
            });
            return new Result("参数值非法异常", StatuCode.INVALID_PARAMETER);
        }

        System.out.println("userInfo = " + userInfo);

        // 添加用户
        userService.addUser(userInfo);

        return new Result("添加成功", StatuCode.OK);
    }

    /**
     * 更新用户
     *
     * @return
     */
    // @RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public Result updateUser(@RequestBody UserInfo userInfo) {

        System.out.println("userInfo = " + userInfo);

        // 添加用户
        userService.updateUser(userInfo);

        return new Result("修改成功", StatuCode.OK);
    }

    /**
     * 根据id删除用户
     *
     * @param uid
     * @return
     */
    // @RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "/{uid}")
    public Result deleteUserById(@PathVariable Long uid) {

        System.out.println("uid = " + uid);

        // 根据id删除
        userService.deleteUserById(uid);

        return new Result("删除成功", StatuCode.OK);
    }

    /**
     * 用户注册
     */
    @PostMapping("/regist")
    public Result regist(@RequestBody UserInfo formUserInfo) {

        System.out.println("formUserInfo = " + formUserInfo);

        userService.regist(formUserInfo);

        return new Result("注册成功", StatuCode.OK);
    }

    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserInfo formUserInfo) {

        System.out.println("formUserInfo = " + formUserInfo);

        if (StringUtils.isBlank(formUserInfo.getUsername()) || StringUtils.isBlank(formUserInfo.getPassword())) {
            return new Result("用户名或密码错误", StatuCode.USER_ERROR);
        }

        // 用户名密码校验
        UserInfo userInfo = userService.login(formUserInfo);
        if (userInfo == null) {
            return new Result("用户名或密码错误", StatuCode.USER_ERROR);
        }

        return new Result("用户成功", StatuCode.OK);
    }
}
