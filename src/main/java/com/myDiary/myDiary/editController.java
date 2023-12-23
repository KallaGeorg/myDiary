package com.myDiary.myDiary;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class editController {
    @Autowired
    private DiaryRepository diaryRepository;

    @GetMapping("/editForm")
     public String editPage(Model model){
        model.addAttribute("allNotes",diaryRepository.findAll());
        return"redirect:/diary";

    }
    @GetMapping("/editForm/{id}")
    public String editForm(@PathVariable int id, Model model){
        DiaryNote diarynote = diaryRepository.findById(id).orElse(null);
        model.addAttribute("diarynote", diarynote);
        return"editForm";
    }
  
    @PostMapping("/editForm/{id}")
    public String editNote(@PathVariable int id,
                            @RequestParam("date") String editDatetime,  
                            @RequestParam("headline")String editHeadline,
                            @RequestParam("note")String editNote){
    DiaryNote currentDiaryNote = diaryRepository.findById(id).orElse(null);
    if(currentDiaryNote != null){
    currentDiaryNote.setDate(LocalDateTime.parse(editDatetime));
    currentDiaryNote.setHeadline(editHeadline);
    currentDiaryNote.setNote(editNote);
                  diaryRepository.save(currentDiaryNote);     
    }
    return"redirect:/editForm";
     }
 
    
}
