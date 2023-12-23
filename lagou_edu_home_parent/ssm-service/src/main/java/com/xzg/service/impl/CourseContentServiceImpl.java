package com.xzg.service.impl;

import com.xzg.dao.CourseContentMapper;
import com.xzg.domain.Course;
import com.xzg.domain.CourseLesson;
import com.xzg.domain.CourseSection;
import com.xzg.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    //根据课程ID查询章节与课时信息
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseid) {

        List<CourseSection> sectionAndLessonByCourseId = courseContentMapper.findSectionAndLessonByCourseId(courseid);
        return sectionAndLessonByCourseId;
    }
    //回显章节对应的课程信息
    @Override
    public Course findCourseByCourseId(int courseId) {
        Course courseByCourseId = courseContentMapper.findCourseByCourseId(courseId);
        return courseByCourseId;
    }
    //保存章节
    @Override
    public void saveSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }
    //修改章节信息
    @Override
    public void updateSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseContentMapper.updateSection(courseSection);
    }
    //修改章节状态
    @Override
    public void updateSectionStatus(int id,int status) {
        //补全信息
        Date date = new Date();
        CourseSection courseSection = new CourseSection();
        courseSection.setUpdateTime(date);
        courseSection.setStatus(status);
        courseSection.setId(id);
        courseContentMapper.updateSectionStatus(courseSection);
    }
    //新建课程
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        courseContentMapper.saveLesson(courseLesson);
    }


}
