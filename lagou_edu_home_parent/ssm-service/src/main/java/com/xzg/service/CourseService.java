package com.xzg.service;

import com.xzg.domain.Course;
import com.xzg.domain.CourseSection;
import com.xzg.domain.CourseVO;
import com.xzg.domain.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    //多条件课程列表查询
    public List<Course> findCourseByCondition(CourseVO courseVO);

    //新增课程和老师信息
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    //根据id查询课程信息
    public CourseVO findCourseById(Integer id);

    // 修改课程信息
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    //修改课程状态信息
    public void updateCourseStatus(int id,int status);




}
