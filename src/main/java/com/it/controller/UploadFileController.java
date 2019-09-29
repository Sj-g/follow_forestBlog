package com.it.controller;

import com.it.dto.JsonResult;
import com.it.dto.ResponseVo;
import com.it.entity.User;
import com.it.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 简单的文件上传
 */
@Controller
@Slf4j
public class UploadFileController {
    private final UserMapper userMapper;

    @Autowired
    public UploadFileController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 文件上传
     *
     * @param multipartFile 上传文件
     * @return 上传结果
     * 待测试
     */
    @RequestMapping("/upload")
    @ResponseBody
    public JsonResult<User> uploadFile(MultipartFile multipartFile, HttpServletRequest request, Integer userId) {
        //创建文件名
        String name = UUID.randomUUID().toString().replace("-", "");
        //获取后缀名
        String fileName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        //获取上传路径
        String url = request.getSession().getServletContext().getRealPath("/upload/");
        File file = new File(url);
        //判断文件是否存在
        if (!file.exists()) {
            //创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录。 请注意，如果此操作失败，它可能已成功创建一些必需的父目录。
            file.mkdir();
        }
        //返回的结果集
        JsonResult<User> jsonResult = new JsonResult<User>();
        //上传路径
        String path = url + "/" + name + "." + fileName;
        try {
            multipartFile.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传失败，case{},path{}", e, path);
            return jsonResult.fail();
        }
        //保存路径到数据库
        User user = userMapper.getUserById(userId);
        user.setUserImg(path);
        userMapper.update(user);
        return jsonResult.success(user);
    }
}
