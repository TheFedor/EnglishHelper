package com.example.englishhelper.Controller;

import com.example.englishhelper.WordClass.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PageController {

    //wordsMap - карта для словаря
    private final Map<String, List<Word>> wordsMap;
    private final ResourceLoader resourceLoader;

    @Autowired
    public PageController(ResourceLoader resourceLoader){
        wordsMap = new HashMap<>();
        this.resourceLoader = resourceLoader;
        //заполняем wordsMap словами из словаря
        //каждому ключу - букве ставится в соответствие список - слова на эту букву из словаря
        try {
            Resource resource = resourceLoader.getResource("classpath:static/dictionary.txt");
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            String letter = "A"; //ключами выступают String, а не Character, так как не уверен в своих данных, а так проще контролировать
            List<Word> wordList = new ArrayList<>();
            reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                if (line.length() < 5)
                {
                    wordsMap.put(letter, wordList);
                    wordList = new ArrayList<>();
                    letter = line.trim();
                }
                else {
                    String[] lineArray = line.trim().split(" - ");
                    wordList.add(new Word(lineArray[0], lineArray[1], lineArray[2]));
                }
            }
            reader.close();
            inputStream.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    @GetMapping
    public String start(Model model) {
        model.addAttribute("letters", wordsMap.keySet());
        return "index";
    }

    //при соответствующем переходе будем выводить соответствующие слова словаря
    @GetMapping("/words/{letter}")
    public String getWordsForLatter(Model model, @PathVariable String letter) {
        List<Word> words = wordsMap.get(letter);
        model.addAttribute("words", words);
        model.addAttribute("letters", wordsMap.keySet());
        return "index";
    }
}
