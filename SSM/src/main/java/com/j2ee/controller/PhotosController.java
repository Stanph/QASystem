package com.j2ee.controller;

import com.auth0.jwt.interfaces.Claim;
import com.j2ee.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/img")
@CrossOrigin
public class PhotosController {
    @RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
    @ResponseBody
    private Map addPhoto(@RequestParam("file") MultipartFile file, @RequestParam("token") String token, HttpServletRequest request ) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(token);
        Map<String, Claim> a = JwtUtil.verifyToken(token);
        String userID = JwtUtil.getAppUID(token);
        if (userID == null) {
            map.put("code", -1);
        } else {
            //获得物理路径webapp所在路径
            String pathRoot = request.getSession().getServletContext().getRealPath("")+"images/";
//            String pathRoot = "D:\\photo\\";
            System.out.println(pathRoot);
            String path = "";
            if (!file.isEmpty()) {
                //生成uuid作为文件名称
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                System.out.println(uuid);
                //获得文件类型（可以判断如果不是图片，禁止上传）
                String contentType = file.getContentType();
                System.out.println(contentType);
                //获得文件后缀名称
                String imageName = contentType.substring(contentType.indexOf("/") + 1);
                System.out.println(imageName);
                if ("jpg".equals(imageName) || "jpeg".equals(imageName) || "gif".equals(imageName) || "png".equals(imageName)
                        || "bmp".equals(imageName)) {
                    path = uuid + "." + imageName;
                    System.out.println(path);
                    File dir = new File(pathRoot);
                    if (!dir.exists()){
                        dir.mkdirs();
                    }
                    file.transferTo(new File(pathRoot + path));
                    map.put("code", 0);
                    map.put("url", path);
                } else {
                    map.put("code", -2);
                }
            } else {
                map.put("code", -2);
            }
        }
        System.out.println(map);
        return map;
    }
}