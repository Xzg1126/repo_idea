package com.xzg.dao;

import com.xzg.domain.Course;
import com.xzg.domain.CourseSection;
import com.xzg.domain.CourseVO;
import com.xzg.domain.Teacher;

import java.util.List;

public interface CourseMapper {


    //多条件课程列表查询
    public List<Course> findCourseByCondition(CourseVO courseVO);

    //新增课程信息
    public void saveCourse(Course course);

    //新增讲师信息
    public void saveTeacher(Teacher teacher);

    //回显课程信息（根据id查对应课程信息及关联的讲师信息）
    public CourseVO findCourseById(Integer id);

    //更新课程信息
    public void updateCourse(Course course);

    //更新老师信息
    public void updateTeacher(Teacher teacher);

    //修改课程状态
    public void updateCourseStatus(Course course);

    //根据课程ID查询章节与课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseid);

}
