package com.FoodAid.Group35.repository;
import com.FoodAid.Group35.entity.Notes;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long > {
}





