package com.reeinvent.dictionary.service;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DictionaryService {

    void addSynonym(String word, String synonym);

    Set<String> getSynonyms(String word);

    Map<String, Object> getAllWords(int page, int pageSize);

    void deleteWord(String word);

}
