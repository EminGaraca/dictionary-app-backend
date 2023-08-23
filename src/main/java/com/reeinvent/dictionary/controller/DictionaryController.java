package com.reeinvent.dictionary.controller;

import com.reeinvent.dictionary.constants.DictionaryConstants;
import com.reeinvent.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(DictionaryConstants.ENDPOINT_BASE)
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @PostMapping(DictionaryConstants.SLASH_ADD)
    public ResponseEntity<String> addSynonym(@RequestParam String word, @RequestParam String synonym) {
        dictionaryService.addSynonym(word, synonym);
        return ResponseEntity.ok("Synonym added successfully.");
    }

    @GetMapping(DictionaryConstants.SLASH_SEARCH)
    public ResponseEntity<Set<String>> getSynonyms(@RequestParam String word)  {
        return ResponseEntity.ok(dictionaryService.getSynonyms(word));
    }

    @GetMapping(DictionaryConstants.SLASH_LIST)
    public ResponseEntity<Map<String, Object>> getSortedSynonyms(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Map<String, Object> sortedSynonyms = dictionaryService.getAllWords(page, pageSize);
        return ResponseEntity.ok(sortedSynonyms);
    }

    @DeleteMapping(DictionaryConstants.SLASH_DELETE)
    public ResponseEntity<String> deleteWordWithAllSynonyms(@RequestParam String word)  {
        dictionaryService.deleteWord(word);
        return ResponseEntity.ok("Word has been successfully deleted");
    }
}
