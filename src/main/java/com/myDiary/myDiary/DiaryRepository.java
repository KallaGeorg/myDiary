package com.myDiary.myDiary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends CrudRepository<DiaryNote, Integer> {

   
      @Query("SELECT d FROM DiaryNote d WHERE d.date <= :currentDateTime AND d.headline IS NOT NULL AND d.note IS NOT NULL")
      List<DiaryNote> showActualEntity(LocalDateTime currentDateTime);

      @Query("SELECT d FROM DiaryNote d WHERE d.date BETWEEN :startDateTime AND :endDateTime")
      List<DiaryNote> notesBetween(@Param("startDateTime") LocalDateTime startDateTime,
                                    @Param("endDateTime")LocalDateTime endDateTime);
      
   }
