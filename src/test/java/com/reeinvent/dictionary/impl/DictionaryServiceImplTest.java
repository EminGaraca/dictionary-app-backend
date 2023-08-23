package com.reeinvent.dictionary.impl;

import com.reeinvent.dictionary.entity.DictionarySystem;
import com.reeinvent.dictionary.exceptions.SynonymsNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DictionaryServiceImplTest {

    private DictionaryServiceImpl dictionaryService;
    private DictionarySystem dictionarySystemMock;

    @BeforeEach
    public void setUp() {
        dictionarySystemMock = mock(DictionarySystem.class);
        dictionaryService = new DictionaryServiceImpl(dictionarySystemMock);
    }

    @Test
    public void testAddSynonym() {
        String word = "home";
        String synonym = "house";
        doNothing().when(dictionarySystemMock).addSynonym(word, synonym);
        assertDoesNotThrow(() -> dictionaryService.addSynonym(word, synonym));
        verify(dictionarySystemMock, times(1)).addSynonym(word, synonym);
    }
    @Test
    public void testGetSynonyms() {
        String word = "home";
        Set<String> synonyms = new HashSet<>(Collections.singletonList("house"));
        when(dictionarySystemMock.getSynonyms(word)).thenReturn(synonyms);
        Set<String> result = dictionaryService.getSynonyms(word);
        assertEquals(synonyms, result);
        verify(dictionarySystemMock, times(1)).getSynonyms(word);
    }

    @Test
    public void testGetSynonymsNotFound() {
        String word = "nonexistent";
        when(dictionarySystemMock.getSynonyms(word)).thenReturn(Collections.emptySet());
        assertThrows(SynonymsNotFoundException.class, () -> dictionaryService.getSynonyms(word));
        verify(dictionarySystemMock, times(1)).getSynonyms(word);
    }

    @Test
    public void testGetAllWords() {
        Set<String> allWords = new HashSet<>(Arrays.asList("apple", "banana", "cherry"));
        int page = 1;
        int pageSize = 2;

        when(dictionarySystemMock.getAllWords()).thenReturn(allWords);
        Map<String, Object> result = dictionaryService.getAllWords(page, pageSize);
        List<String> expectedWordsOnPage = Arrays.asList("apple", "banana");
        int expectedTotalPages = 2;

        assertEquals(expectedWordsOnPage, result.get("words"));
        assertEquals(allWords, result.get("allWords"));
        assertEquals(page, result.get("page"));
        assertEquals(pageSize, result.get("pageSize"));
        assertEquals(expectedTotalPages, result.get("totalPages"));
        verify(dictionarySystemMock, times(1)).getAllWords();
    }

    @Test
    public void testDeleteWord() {
        String word = "apple";
        doNothing().when(dictionarySystemMock).removeWord(word);
        assertDoesNotThrow(() -> dictionaryService.deleteWord(word));
        verify(dictionarySystemMock, times(1)).removeWord(word);
    }
}
