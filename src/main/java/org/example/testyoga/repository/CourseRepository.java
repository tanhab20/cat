package org.example.testyoga.repository;

import org.example.testyoga.beans.Course;
import org.example.testyoga.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
