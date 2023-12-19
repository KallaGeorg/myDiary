package com.myDiary.myDiary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiaryController {

  @Autowired
  private DiaryRepository diaryRepository;

   @GetMapping("/")
   public String getIndex(Model model){
   model.addAttribute("notes", diaryRepository.findAll());
    return "index";
   } 
   @GetMapping("/diary")
   public String showDiary(Model model){
      model.addAttribute("notes", diaryRepository.findAll());
      return"diary";
   }
 
    @PostMapping("/new-note")
    public String addHeadline(@RequestParam("headline")String headlineContent,
                              @RequestParam("diarynote")String noteContent,
                              @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime date){
     DiaryNote diarynote = new DiaryNote();
     diarynote.setHeadline(headlineContent);
     diarynote.setNote(noteContent);
     diarynote.setDate(date);
     diaryRepository.save(diarynote);
     return "redirect:/diary";
   }
 
   @GetMapping("/delete")
   public String deleteNote(@RequestParam int id){
    diaryRepository.deleteById(id);
    return"redirect:/diary";
    } 

   //   @GetMapping("/search")
   //  public String showSearch(){
   //    return"search";
   //  }

  
    
   }

