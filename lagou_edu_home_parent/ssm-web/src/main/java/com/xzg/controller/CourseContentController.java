package com.xzg.controller;

import com.xzg.domain.Course;
import com.xzg.domain.CourseLesson;
import com.xzg.domain.CourseSection;
import com.xzg.domain.ResponseResult;
import com.xzg.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /*查询课程内容 */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId( Integer courseid){
        List<CourseSection> courseSections =
        courseContentService.findSectionAndLessonByCourseId(courseid);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseSections);
        return  result;
    }

    //回显章节对应的课程信息
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam int  courseId){
        Course courseByCourseId = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseByCourseId);
        return result;

    }
    //保存或者修改章节
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        //保存章节（没有id）
        if (courseSection.getId() == null){
            //保存章节
            courseContentService.saveSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "保存成功", null);
            return result;
        }else {

            courseContentService.updateSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }
    }
    //修改章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id ,@RequestParam int status){
        courseContentService.updateSectionStatus(id,status);
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "状态修改成功", map);
        return  result;
    }

    //新建课程
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){
        courseContentService.saveLesson(courseLesson);
        ResponseResult result = new ResponseResult(true, 200, "新建课程成功", null);
        return  result;
    }

}
