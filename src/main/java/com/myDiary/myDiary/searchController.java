package com.myDiary.myDiary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class searchController {

     @Autowired
  private DiaryRepository diaryRepository;

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
