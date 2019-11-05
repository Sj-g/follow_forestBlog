package com.it.controller;

import cn.hutool.json.JSONObject;
import com.it.entity.User;
import com.it.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     *                      待测试
     */
    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject  uploadFile(@RequestParam(value = "editormd-image-file") MultipartFile multipartFile, HttpServletRequest request, Integer userId) {
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
        //上传路径
        String path = url + "/" + name + "." + fileName;
        try {
            multipartFile.transferTo(new File(path));
        } catch (IOException e) {
            log.error("文件上传失败，case{},path{}", e, path);
        }
        //保存路径到数据库
        User user = userMapper.getUserById(userId);
        user.setUserImg(path);
        userMapper.update(user);
        JSONObject res = new JSONObject();
        res.put("url",path);
        res.put("success", 1);
        res.put("message", "upload success!");
        return res;
    }
    /**
     * 下载软件
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request) throws IOException {
        //指定要下载的文件所在目录
        String url = request.getSession().getServletContext().getRealPath("/upload/");
        File file=new File(url);
        HttpHeaders httpHeaders=new HttpHeaders();
        //浏览器以下载的方式打开文件
        httpHeaders.setContentDispositionFormData("attachment",url);
        //定义以流的形式返回文件数据
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //使用SpringMvc框架ResponseEntity对象封装返回数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.OK);
    }

}
