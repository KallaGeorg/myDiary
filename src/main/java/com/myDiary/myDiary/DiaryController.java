package com.myDiary.myDiary;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiaryController {

  @Autowired
  private DiaryRepository diaryRepository;

   @GetMapping
   public String getIndex(Model model){
    model.addAttribute("notes", diaryRepository.findAll());
    model.addAttribute("localDateTime", LocalDateTime.now());
    return "index";
   } 
   @GetMapping("/time-date")
   public String timeDateShow(){
    DiaryNote diarynote = new DiaryNote();
    diarynote.setDate(LocalDateTime.now());
    diaryRepository.save(diarynote);
    return "redirect:/";
   }
   
   @PostMapping("/new-note")
   public String addHeadline(@RequestParam("headline")String headlineContent,@RequestParam("diarynote")String noteContent){
    DiaryNote diarynote = new DiaryNote();
   diarynote.setHeadline(headlineContent);
    diarynote.setNote(noteContent);
    diarynote.setDate(LocalDateTime.now());
    diaryRepository.save(diarynote);


    return "redirect:/";
   }
//     @PostMapping("/new-note")
//    public String addNote(@RequestParam("diarynote")String noteContent){
//    DiaryNote diarynote = new DiaryNote();
//     // diarynote.setHeadline("How late is it????");
//      diarynote.setNote(noteContent);
//     // diarynote.setDate(LocalDateTime.now());
//    diaryRepository.save(diarynote);
 
//  System.out.println("New note: "+noteContent);
//     return "redirect:/";
//    }
   @GetMapping("/delete")
   public String deleteNote(@RequestParam int id){
    diaryRepository.deleteById(id);

    return"redirect:/";
   }
}
