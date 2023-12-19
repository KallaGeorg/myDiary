package com.myDiary.myDiary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends CrudRepository<DiaryNote, Integer> {

   @Query("SELECT e FROM DiaryNote e WHERE e.date BETWEEN : firstDate AND : secondDate")
   List<DiaryNote> dateSearcher(@Param("firstDate")LocalDateTime firstDate,@Param("secondDate")LocalDateTime secondDate);
}
