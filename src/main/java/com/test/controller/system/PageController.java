package com.test.controller.system;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * FileName: SelectDataListController.java
 *
 * @version V1.0
 * Createdate:  2018年12月7日 下午1:38:38
 * @Description: 页面资源请求
 * All rights Reserved, Designed By JS-YFB
 * Copyright:   Copyright(C) 2017-2027
 * Company      JS-YFB LTD.
 * @author: 杨陈
 */
@RequestMapping("/test")
@Controller
public class PageController {

    /**
     * @param @param  request
     * @param @param  model
     * @param @return 参数
     * @return Object    返回类型
     * @Title: userList
     * @Description: TODO(登录页面)
     */
    @RequestMapping("/loginPage")
    public Object login(HttpServletRequest request, Model model) {
        return "login";
    }

    /**
     * 首页
     *
     * @param: @param request
     * @param: @param model
     * @param: @return
     * @return: Object
     */
    @RequestMapping("/indexPage")
    public Object userList(HttpServletRequest request, Model model) {
        return "index";
    }

    /**
     * 纸票开具页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/zpkjPage")
    public String dkpsjHtml() {
        return "applyInvoice";
    }

    /**
     * 纸票待开票数据修改
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/changeOrderPage")
    public String changeOrderHtml() {
        return "changeOrder";
    }

    /**
     * 发票开具成功页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/kjcgPage")
    public String kjcgHtml() {
        return "selectFP";
    }

    /**
     * 原始数据页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/initdataPage")
    public String getInitDataHtml() {
        return "chushi/getInitData";
    }

    /**
     * 通过单据号查询原始数据明细
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/initDJDataListPage")
    public String initDJDataList() {
        return "chushi/initDJDataList";
    }

    /**
     * 纸票已开票数据详情
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/kpSuccessOrderPage")
    public String kpSuccessOrderHtml() {
        return "kpSuccessOrder";
    }

    /**
     * 纸票作废页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/zpzfPage")
    public String zpzfHtml() {
        return "kpCancel";
    }

    /**
     * 纸票作废数据详情
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/kpCancelOrderPage")
    public String kpCancelOrderHtml() {
        return "kpCancelOrder";
    }

    /**
     * 功能树形表格
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/qxGnTreeTablePage")
    public String testTreeTableHtml() {
        return "system/qxMenuTreeTable";
    }

    /**
     * 添加资源页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/addMenuPage")
    public String qxAddMenuHtml() {
        return "system/qxAddMenu";
    }

    /**
     * 编辑资源页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/editMenuPage")
    public String qxEditMenuHtml() {
        return "system/qxEditMenu";
    }

    /**
     * 用户管理页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/qxUserManagePage")
    public String qxUserManageHtml() {
        return "system/qxUserManage";
    }

    /**
     * 用户管理页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/jsglPage")
    public String jsgl() {
        return "system/qxRoleManage";
    }

    /**
     * 返回角色授权资源页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/qxRoleTreeTablePage")
    public String qxRoleTreeTableHtml() {
        return "system/qxRoleTreeTable";
    }

    /**
     * 用户添加页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/qxUserAddHtmlPage")
    public String qxUserAddHtml() {
        return "system/qxUserAdd";
    }

    /**
     * 用户修改页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/qxUserEditHtmlPage")
    public String qxUserEditHtml() {
        return "system/qxUserEdit";
    }

    /**
     * 用户获取角色页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/qxUserRolePage")
    public String qxUserRole() {
        return "system/qxUserRole";
    }

    /**
     * 用户密码修改页面
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/qxUserPwdEdit")
    public String qxUserPwdEdit() {
        return "system/qxPwdEdit";
    }

    // 系统配置页面修改
    @RequestMapping("/xiTongPeiZhiPage")
    public String xitongPeiZhiPage() {
        return "system/xiTongPeiZhi";
    }


    @RequestMapping("/chuShiDataPage")
    public String chuShiDataPage() {
        return "chushi/chuShiDataPage";
    }
    /**
     * 销方信息
     *
     * @param: @return
     * @return: String
     */
    @RequestMapping("/sellerPage")
    public String sellerIndexPage(){
        return  "sellermaintenance/sellerMaintenance";
    }
}