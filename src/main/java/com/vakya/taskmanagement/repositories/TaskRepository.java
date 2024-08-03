package com.vakya.taskmanagement.repositories;

import com.vakya.taskmanagement.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:priority IS NULL OR t.priority = :priority) AND " +
            "(:dueDate IS NULL OR t.dueDate = :dueDate) AND " +
            "(:search IS NULL OR t.title LIKE %:search% OR t.description LIKE %:search%)")
    List<Task> findByFilters(String status, String priority, Date dueDate, String search);

    Page<Task> findAll(Pageable pageable);
}
