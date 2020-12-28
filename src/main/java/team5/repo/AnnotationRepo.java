package team5.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.model.Annotation;

public interface AnnotationRepo extends JpaRepository<Annotation, Long> {

}
