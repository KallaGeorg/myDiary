package com.myDiary.myDiary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class editController {
    @Autowired
    private DiaryRepository diaryRepository;

    @GetMapping("/editForm")
     public String editPage(Model model){
        model.addAttribute("allNotes",diaryRepository.findAll());
        return"redirect:/";

    }
    @GetMapping("/editForm/{id}")
    public String editForm(@PathVariable int id, Model model){
        DiaryNote diarynote = diaryRepository.findById(id).orElse(null);
        model.addAttribute("diarynote", diarynote);
        return"editForm";
    }
    
    @PostMapping("/editForm/{id}")
    public String editNote(@PathVariable int id, DiaryNote changedNote){
        DiaryNote currentNote = diaryRepository.findById(id).orElse(null);
        if(currentNote != null){
            currentNote.setDate(changedNote.getDate());
            currentNote.setHeadline(changedNote.getHeadline());
            currentNote.setNote(changedNote.getNote());
            diaryRepository.save(changedNote);
        }
        return"redirect:/editForm";
    }

 
    
}