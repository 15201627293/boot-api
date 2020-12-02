package com.boot.excel;

import com.boot.entity.User;
import com.boot.service.UserService;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Classname UserImportListener
 * @Description TODO
 * @Date 2020/9/14 2:28 下午
 */
public class UserImportListener extends ExcelImportListener<UserModel> {


    private UserService userService;


    public UserImportListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void saveData(List<UserModel> dataList) {
        logger.info("SaveData size: {}", dataList.size());
        dataList.forEach(userModel -> {
            User user = new User();
            BeanUtils.copyProperties(userModel, user);
            user.setPassWord("123456");
            userService.insert(user);
        });
    }
}
