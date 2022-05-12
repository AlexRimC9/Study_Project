package com.example.study_project.Controller;


import com.example.study_project.Annotation.Timer;
import com.example.study_project.Service.Currencies.CurrenciesService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/currencies")
public class CurrenciesController {
   private final CurrenciesService currenciesService;



   public CurrenciesController(CurrenciesService currenciesService){
       this.currenciesService = currenciesService;
   }



    @Timer
    @PostMapping("/add")
    public ResponseEntity<?>gettingCourseData() throws IOException{
        currenciesService.gettingInformation();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/conversion")
        public String conversionCurrencies(@RequestParam String entranceCurrency,@RequestParam String outputCurrency ,@RequestParam double total){
        return currenciesService.conversion(entranceCurrency,outputCurrency,total);
    }

    @GetMapping("/course")
    public List<?> getCourse(){
       return currenciesService.course();
    }

}
