package org.example.testyoga.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateTime;
    @ManyToOne
    private Cat cat;
    private String courseDirectorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String getCourseDirectorName() {
        return courseDirectorName;
    }

    public void setCourseDirectorName(String courseDirectorName) {
        this.courseDirectorName = courseDirectorName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", cat=" + cat +
                ", courseDirectorName='" + courseDirectorName + '\'' +
                '}';
    }
}
