package com.xzg.controller;

import com.xzg.domain.*;
import com.xzg.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {


    //分情况查询课程
    @Autowired
    private CourseService courseService;
    //@RequestBody 获得请求体里的类容进行封装
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseList);
        return result;
    }



    //图片上传
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file , HttpServletRequest request) throws IOException {

         //1.判断file是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        //2.获取项目路径
        //D:\apache-tocat-8.5.56\webapp\ssm-web
        String realPath = request.getServletContext().getRealPath("/");
        //D:\apache-tocat-8.5.56\webapp\
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3.获取文件名
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
     String newFileName =  System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传
        //D:\apache-tocat-8.5.56\webapp\ upload
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath,newFileName);
        //判断目录存不存在，没有就创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
            System.out.println("创建目录: "+filePath);
        }
        //图片进行上传
        file.transferTo(filePath);

        //6.将文件名和文件路径返回，进行响应
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        //responseResult 响应前台会转换为json格式   前台解析json串获取map中的值 实现回显
        return responseResult;

    }

    /*新增课程信息以及老师信息
    * 新增课程信息和老师信息写在一个方法上
    * */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        if (courseVO.getId() == null){
            //添加操作
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "新增成功", null);
            return result;
        }else {
            //更新操作
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }

    }

    /*根据id查询课程信息*/
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam int id){
        CourseVO courseById = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseById);
        return result;
    }
    //修改课程状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id ,@RequestParam int status) {
        courseService.updateCourseStatus(id,status);
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }




}
