package com.boot.excel.util;

import com.boot.entity.User;
import com.boot.excel.model.UserModel;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname UserUtil
 * @Description TODO
 * @Date 2020/9/14 2:11 下午
 */
public class UserModelUtil {
    public static List<UserModel> convertToExcelModel(List<User> eventRecordList) {
        List<UserModel> userMedalModelList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(eventRecordList)) {
            eventRecordList.stream().forEach(user -> {
                UserModel userModel = new UserModel();
                userModel.setUserName(user.getUserName());
                userModel.setName(user.getName());
                userModel.setSex(user.getSex() == null ? "" : user.getSex() == 1 ? "男" : "女");
                userModel.setCreateTime(DateUtils.localDateToString(DateUtils.asLocalDateTime(user.getCreateTime())));
                userMedalModelList.add(userModel);
            });
        }
        return userMedalModelList;
    }
}
