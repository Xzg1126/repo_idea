package com.xzg.service.impl;

import com.xzg.dao.CourseMapper;
import com.xzg.domain.Course;
import com.xzg.domain.CourseSection;
import com.xzg.domain.CourseVO;
import com.xzg.domain.Teacher;
import com.xzg.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    //查询课程
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> courseList = courseMapper.findCourseByCondition(courseVO);
        return courseList;
    }


    //添加课程老师信息
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);

        //补全课程信息
        Date date = new Date();
        course.setUpdateTime(date);
        course.setCreateTime(date);

        //保存课程
        courseMapper.saveCourse(course);

        //获取新插入数据的id
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全讲师信息
        teacher.setUpdateTime(date);
        teacher.setCreateTime(date);
        teacher.setCourseId(id);

        //保存老师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(Integer id) {
        CourseVO courseById = courseMapper.findCourseById(id);
        return courseById;
    }


    //更新课程老师信息
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        //把courseVO封装到course实体中
        BeanUtils.copyProperties(course,courseVO);
        //补全信息
        Date date = new Date();
        course.setUpdateTime(date);
        //更新课程
        courseMapper.updateCourse(course);

        //封装老师信息
        Teacher teacher = new Teacher();
        teacher.setCourseId(course.getId());
        BeanUtils.copyProperties(teacher,courseVO);
        teacher.setUpdateTime(date);
        //更新老师信息
        courseMapper.updateTeacher(teacher);
    }
    //修改状态
    @Override
    public void updateCourseStatus(int id, int status) {
        //封装课程信息
        Course course = new Course();
        //补全信息
        Date date = new Date();
        course.setUpdateTime(date);
        course.setId(id);
        course.setStatus(status);
        courseMapper.updateCourseStatus(course);
    }





}

