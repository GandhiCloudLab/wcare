package com.pro.wealth.service;

import com.pro.wealth.common.CommonConstants;
import com.pro.wealth.common.LogUtil;
import com.pro.wealth.common.LoginUtil;
import com.pro.wealth.entity.WcBusinessManagerEntity;
import com.pro.wealth.entity.WcCustomerEntity;
import com.pro.wealth.entity.WcUsersEntity;
import com.pro.wealth.entity.WcWealthManagerEntity;
import com.pro.wealth.model.LoginInfo;
import com.pro.wealth.model.WcBusinessManager;
import com.pro.wealth.model.WcUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Configuration
@Service
public class LoginService {

    @Value("${prop.api.server.url.user}")
    private String url_user;


    @Autowired
    WcUsersService usersService;

    @Autowired
    WcBusinessManagerService businessManagerService;

    @Autowired
    WcCustomerService customerService;

    @Autowired
    WcWealthManagerService wealthManagerService;
    @Autowired
    RestUtilService restUtilService;

    public LoginInfo processLogin(LoginInfo loginInfo) {
        LogUtil.log("LoginService  processLogin Started ");

        loginInfo.clear();

        WcUsersEntity wcUsers = usersService.fetchUserByLoginId(loginInfo.getLoginId());

        if (wcUsers == null) {
            LogUtil.log("LoginService : login failed : User doesn't exists");
            populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_400, CommonConstants.LOGIN_RETURN_MSG_Invalid_LoginId);
        } else {
            if (LoginUtil.match(loginInfo.getPassword(), wcUsers.getPassword())) {
                LogUtil.log("LoginService : login success");
                populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_200, CommonConstants.LOGIN_RETURN_MSG_Sucess);

                //Process
                processLoginSuccess(loginInfo, wcUsers);
            } else {
                LogUtil.log("LoginService : login failed : Invalid password");
                populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_401, CommonConstants.LOGIN_RETURN_MSG_Invalid_Password);
            }
        }

        return loginInfo;
    }

    private void processLoginSuccess(LoginInfo loginInfo, WcUsersEntity wcUsers) {
        LoginUtil.populateRole(wcUsers.getRole(), loginInfo);

        loginInfo.setUserLoginEmailId(wcUsers.getEmailId());
        loginInfo.setUserLoginName(wcUsers.getUserName());
        loginInfo.setUserRole(wcUsers.getRole());

        if (loginInfo.isUserRoleCustomer()) {
            populateCustomer(loginInfo, wcUsers);
        } else if (loginInfo.isUserRoleBuisnessManager()) {
            populateBusinessManager(loginInfo, wcUsers);
        } else if (loginInfo.isUserRoleWealthManager()) {
            populateWealthManager(loginInfo, wcUsers);
        }
    }

    private void populateCustomer(LoginInfo loginInfo, WcUsersEntity wcUsers) {
        WcCustomerEntity entity = customerService.findOneByWcUserId(wcUsers.getId());
        if (entity == null) {
            LogUtil.log("LoginService : login failed : User/Customer doesn't exists");
            populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_400, CommonConstants.LOGIN_RETURN_MSG_Invalid_LoginId);
        } else {
            loginInfo.setUserDisplayId(String.valueOf(entity.getId()));
            loginInfo.setUserDisplayName(entity.getFirstName() + " " + entity.getLastName());

            loginInfo.setValidLogin(true);
        }
    }

    private void populateWealthManager(LoginInfo loginInfo, WcUsersEntity wcUsers) {
        WcWealthManagerEntity entity = wealthManagerService.findOneByWcUserId(wcUsers.getId());
        if (entity == null) {
            LogUtil.log("LoginService : login failed : User/WealthManager doesn't exists");
            populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_400, CommonConstants.LOGIN_RETURN_MSG_Invalid_LoginId);
        } else {
            loginInfo.setUserDisplayId(String.valueOf(entity.getId()));
            loginInfo.setUserDisplayName(entity.getFirstName() + " " + entity.getLastName());

            loginInfo.setValidLogin(true);
        }
    }

    private void populateBusinessManager(LoginInfo loginInfo, WcUsersEntity wcUsers) {
        WcBusinessManagerEntity entity = businessManagerService.findOneByWcUserId(wcUsers.getId());
        if (entity == null) {
            LogUtil.log("LoginService : login failed : User/BusinessManager doesn't exists");
            populateReturnCode (loginInfo, CommonConstants.LOGIN_RETURN_CODE_400, CommonConstants.LOGIN_RETURN_MSG_Invalid_LoginId);
        } else {
            loginInfo.setUserDisplayId(String.valueOf(entity.getId()));
            loginInfo.setUserDisplayName(entity.getFirstName() + " " + entity.getLastName());

            loginInfo.setValidLogin(true);
        }
    }

    private void populateReturnCode(LoginInfo loginInfo, String returnCode, String returnMessage) {
        loginInfo.setReturnCode(returnCode);
        loginInfo.setReturnMessage(returnMessage);
    }


}
