package com.xiajr.masterblog.web.controller.admin;

import com.xiajr.masterblog.modules.data.PermissionTree;
import com.xiajr.masterblog.modules.service.PermissionService;
import com.xiajr.masterblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiajr
 *
 */
@RestController
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/tree")
    public List<PermissionTree> tree() {
        return permissionService.tree();
    }
}
