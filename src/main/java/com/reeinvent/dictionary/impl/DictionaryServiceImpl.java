package com.reeinvent.dictionary.impl;

import com.reeinvent.dictionary.entity.DictionarySystem;
import com.reeinvent.dictionary.exceptions.NoSpecialCharactersException;
import com.reeinvent.dictionary.exceptions.NumberOfCharactersException;
import com.reeinvent.dictionary.exceptions.SameWordAsSynonymException;
import com.reeinvent.dictionary.exceptions.SynonymsNotFoundException;
import com.reeinvent.dictionary.service.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private DictionarySystem dictionarySystem;
    public DictionaryServiceImpl(DictionarySystem dictionarySystem) {
        this.dictionarySystem = dictionarySystem;
    }

    @Override
    public void addSynonym(String word, String synonym)  {
        if (word.equals(synonym)) {
            throw new SameWordAsSynonymException("Word cannot be the same as synonym.");

        } else if(!(word.length() > 2)) {
            throw new NumberOfCharactersException("You need to provide more than 2 characters");
        } else if (containsSpecialCharacter(word) || containsSpecialCharacter(synonym)) {
            throw new NoSpecialCharactersException("Word contains special characters or numbers.");
        }
        dictionarySystem.addSynonym(word.trim().toLowerCase(), synonym.trim().toLowerCase());
    }

    @Override
    public Set<String> getSynonyms(String word) {
        Set<String> synonyms = dictionarySystem.getSynonyms(word);
        if (synonyms.isEmpty()) {
            throw new SynonymsNotFoundException("No synonyms found for the given word.");
        }
        return synonyms;
    }

    @Override
    public Map<String, Object> getAllWords(int page, int pageSize) {
        Set<String> allWords = dictionarySystem.getAllWords();
        List<String> sortedSynonyms = allWords.stream().sorted().collect(Collectors.toList());
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, sortedSynonyms.size());
        List<String> wordsOnPage = sortedSynonyms.subList(startIndex, endIndex);

        int totalPages = (int) Math.ceil((double) sortedSynonyms.size() / pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("words", wordsOnPage);
        result.put("allWords", allWords);
        result.put("page", page);
        result.put("pageSize", pageSize);
        result.put("totalPages", totalPages);
        return result;
    }

    @Override
    public void deleteWord(String word) {
        dictionarySystem.removeWord(word);
    }

    private boolean containsSpecialCharacter(String word) {
        String specialCharacters = "[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]";
        String numbers = ".*\\d.*";
        return word.matches(".*" + specialCharacters + ".*") || word.matches(numbers);
    }

}
