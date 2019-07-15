package com.xiajr.masterblog.shiro;

import com.xiajr.masterblog.common.lang.Consts;
import com.xiajr.masterblog.modules.data.AccountProfile;
import com.xiajr.masterblog.modules.data.UserVO;
import com.xiajr.masterblog.modules.entity.Role;
import com.xiajr.masterblog.modules.service.UserRoleService;
import com.xiajr.masterblog.modules.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xiajr
 */
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;


    public AccountRealm() {
        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从shiro中获取账户信息
       AccountProfile profile = (AccountProfile)SecurityUtils.getSubject().getPrincipal();
        if (profile != null) {
            UserVO user = userService.get(profile.getId());
            if (user != null) {
                //角色权限信息
                SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
                List<Role> roles = userRoleService.listRoles(user.getId());
                roles.forEach(role -> {
                   simpleAuthorizationInfo.addRole(role.getName());
                   role.getPermissions().forEach(permission -> simpleAuthorizationInfo.addStringPermission(permission.getName()));
                });
                return simpleAuthorizationInfo;
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        AccountProfile profile = userService.login(upToken.getUsername(), String.valueOf(upToken.getPassword()));

        //不存在的用户
        if (null == profile) {
            throw new UnknownAccountException(upToken.getUsername());
        }

        //用户被禁用
        if (profile.getStatus() == Consts.STATUS_CLOSED) {
            throw new LockedAccountException(profile.getName());
        }

        //获取用户信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(profile,authenticationToken.getCredentials(),getName());

        Session session = SecurityUtils.getSubject().getSession();
        //将用户信息放入session
        session.setAttribute("profile", profile);

        return simpleAuthenticationInfo;
    }
}
