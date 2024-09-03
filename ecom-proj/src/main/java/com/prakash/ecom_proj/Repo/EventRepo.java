package com.prakash.ecom_proj.Repo;

import com.prakash.ecom_proj.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    //Fire JPQL for custom searching of Events
    @Query("SELECT e FROM Event e WHERE " +
            "LOWER(e.name) LIKE LOWER(CONCAT('%', :keyStroke, '%')) OR " +
            "LOWER(e.brand) LIKE LOWER(CONCAT('%', :keyStroke, '%')) OR " +
            "LOWER(e.description) LIKE LOWER(CONCAT('%', :keyStroke, '%')) OR " +
            "LOWER(e.category) LIKE LOWER(CONCAT('%', :keyStroke, '%'))")
    List<Event> searchList(String keyStroke);

}

