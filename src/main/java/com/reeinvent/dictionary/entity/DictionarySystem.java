package com.reeinvent.dictionary.entity;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DictionarySystem {
    private Map<String, Set<String>> synonymsMap;
    public DictionarySystem() {
        synonymsMap = new HashMap<>();
    }
    public void addSynonym(String word, String synonym)  {
        // add to map if word doesn't exist
        synonymsMap.putIfAbsent(word, new HashSet<>());
        synonymsMap.putIfAbsent(synonym, new HashSet<>());

        // add to map in both directions
        synonymsMap.get(word).add(synonym);
        synonymsMap.get(synonym).add(word);

        // if we have transitive synonyms put it in all word
        addToTransitiveSynonyms(word, synonym);
        addToTransitiveSynonyms(synonym, word);

    }
    private void addToTransitiveSynonyms(String source, String newSynonym) {
        Set<String> synonyms = synonymsMap.get(source);
        for (String synonym : synonyms) {
            if (!synonym.equals(newSynonym)) {
                synonymsMap.get(synonym).add(newSynonym);
                synonymsMap.get(newSynonym).add(synonym);
            }
        }
    }

    public Set<String> getSynonyms(String word) {
        return synonymsMap.getOrDefault(word, Collections.emptySet());
    }

    public Set<String> getAllWords() {
        return  synonymsMap.keySet();
    }

    public void removeWord(String word) {
        Set<String> synonyms = synonymsMap.getOrDefault(word, Collections.emptySet());

        // Remove the word and its synonyms from the map
        for (String synonym : synonyms) {
            synonymsMap.get(synonym).remove(word);
        }
        synonymsMap.remove(word);

        // Remove word from transitive synonyms
        removeFromTransitiveSynonyms(word);
    }

    private void removeFromTransitiveSynonyms(String removedWord) {
        for (Map.Entry<String, Set<String>> entry : synonymsMap.entrySet()) {
            Set<String> synonyms = entry.getValue();
            synonyms.remove(removedWord);
        }
    }
}

