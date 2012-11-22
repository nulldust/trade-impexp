package com.oilchem.trade.view.controller;

import com.oilchem.trade.util.ConfigUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import static com.oilchem.trade.util.ConfigUtil.ConfigBean;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-22
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/manage")
public class ConfigController {

    /**
     * 显示ConfigUtil map 中各种元素列表信息
     * @param model   model
     * @return  config的列表页
     */
    @RequestMapping(value = "/configlist")
    public String configList(Model model){
        Map<String ,ConfigBean> map = ConfigUtil.getConfigMap();
        model.addAttribute("configmaps",map);
        return "/manage/config/list";
    }



    /**
     * 跳转到修改页面
     * @param key  要编辑对象key
     * @param model   model
     * @return  修改页面
     */
    @RequestMapping(value = "/toeditcfg/{key}", method = RequestMethod.GET)
    public String toedit( @PathVariable String key, Model model) {
        Map<String ,ConfigBean> map = ConfigUtil.getConfigMap();
        ConfigBean configBean = map.get(key);
        model.addAttribute("configBean", configBean);
        return "/manage/config/edit";

    }

    /**
     * 重新修改 配置对象
     * @param configBean   配置对象
     * @param model   model
     * @return   新的列表页
     */
    @RequestMapping(value = "/updatecfg")
    //@RequiresPermissions("menu:update")
    public String update(ConfigBean configBean, Model model) {
        Map<String ,ConfigBean> map = ConfigUtil.getConfigMap();
        if(null != configBean && StringUtils.isNotBlank(configBean.getKey())){
            map.put(configBean.getKey(),configBean);
        }
        ConfigUtil.setConfigMap(map);
        model.addAttribute("configmaps",ConfigUtil.getConfigMap());
        return "/manage/config/list";
    }
}