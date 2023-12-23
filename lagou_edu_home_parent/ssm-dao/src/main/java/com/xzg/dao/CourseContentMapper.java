package com.xzg.dao;

import com.xzg.domain.Course;
import com.xzg.domain.CourseLesson;
import com.xzg.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    //根据课程ID查询章节信息以及章节对应的课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseid);

    //回显章节对应的课程信息
    public Course findCourseByCourseId(int courseId);

    //保存章节信息
    public void saveSection(CourseSection courseSection);

    //修改章节信息
    public void updateSection(CourseSection courseSection);

    //修改章节状态
    public void updateSectionStatus(CourseSection courseSection);

    //新建课程
    public void saveLesson(CourseLesson courseLesson);
}
