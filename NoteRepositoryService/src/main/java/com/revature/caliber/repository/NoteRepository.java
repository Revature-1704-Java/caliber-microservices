package com.revature.caliber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}
