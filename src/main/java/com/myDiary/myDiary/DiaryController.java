package com.myDiary.myDiary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DiaryController {

  @Autowired
  private DiaryRepository diaryRepository;

   @GetMapping("/")
   public String getIndex(){
    return "index";
   } 
    @GetMapping("/diary")
    public String showDiary(Model model){
      LocalDateTime currentDate = LocalDateTime.now();
      List<DiaryNote> actualEntities = diaryRepository.showActualEntity(currentDate);
       model.addAttribute("notes", actualEntities);
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
     
  @GetMapping("/search")
   public String searchForm(){
    return"search";
}

    @GetMapping("/runSearch")
   public String showNotesBetween(Model model,@RequestParam("startDateTime") LocalDateTime startDateTime,
                                             @RequestParam("endDateTime")LocalDateTime endDateTime,
                                             RedirectAttributes redirectAttributes){
     List<DiaryNote> notesBetween1 = diaryRepository.notesBetween(startDateTime,endDateTime);
     redirectAttributes.addFlashAttribute("notesBetween1", notesBetween1);
     return"redirect:/search";
    }
      

   }

