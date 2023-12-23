package com.myDiary.myDiary;


import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiaryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String headline;
    private String note;
    private LocalDateTime date;
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getHeadline() {
        return headline;
    }
    public void setHeadline(String headline) {
        this.headline = headline;
    }
     public String getNote() {
         return note;
     }
     public void setNote(String note) {
         this.note = note;
     }
     public LocalDateTime getDate() {
        return date;
     }
     public void setDate(LocalDateTime date) {
         this.date = date;
    }
} 
    
    

