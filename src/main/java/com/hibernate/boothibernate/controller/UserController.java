package com.hibernate.boothibernate.controller;

import com.hibernate.boothibernate.model.Catering;
import com.hibernate.boothibernate.model.User;
import com.hibernate.boothibernate.service.UserService;
import com.hibernate.boothibernate.utils.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lushun
 * @date: 2018/12/27 15:48
 * Title: Controller
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 保存
     */
    @RequestMapping(value = "/save")
    public void saveUser() {

        User user = new User();
        user.setId(1);
        user.setJob("1");
        user.setAge("3");
//        userService.saveUser(user);

//       List<User> list = userService.getUser(user);
//       List<User> list = userService.findByName(user);
//        userService.updateNameById(user);
        userService.saveUser(user);

    }

    /**
     * 上传excel
     *
     * @return
     */
    @RequestMapping(value = "/toUpload")
    public ModelAndView toUpload() {
        return new ModelAndView("upload");
    }

    /**
     * excel解析
     *
     * @param file
     * @throws Exception
     */
    @RequestMapping(value = "/upload")
    public void upload(MultipartFile file) throws Exception {

        PoiUtil poiUtil = new PoiUtil();
        List<Catering> list = poiUtil.importExcel(poiUtil.getWorkBook(file), Catering.class);
        System.out.println(list.size());

    }
}
