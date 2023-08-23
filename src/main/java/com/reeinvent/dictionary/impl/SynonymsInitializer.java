package com.reeinvent.dictionary.impl;

import com.reeinvent.dictionary.exceptions.SameWordAsSynonymException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SynonymsInitializer implements CommandLineRunner {
    private final DictionaryServiceImpl dictionarySystem;

    @Autowired
    public SynonymsInitializer(DictionaryServiceImpl dictionarySystem) {
        this.dictionarySystem = dictionarySystem;
    }

    @Override
    public void run(String... args) throws SameWordAsSynonymException {
        // Pre-filling some synonyms for development purposes
        dictionarySystem.addSynonym("home", "firm");
        dictionarySystem.addSynonym("firm", "house");
        dictionarySystem.addSynonym("house", "company");
        dictionarySystem.addSynonym("school", "university");
        dictionarySystem.addSynonym("applause", "ovation");
        dictionarySystem.addSynonym("health", "energy");
        dictionarySystem.addSynonym("form", "strength");
        dictionarySystem.addSynonym("strength", "hardiness");
        dictionarySystem.addSynonym("hardiness", "fettle");
        dictionarySystem.addSynonym("fettle", "stamina");
        dictionarySystem.addSynonym("stamina", "prime");
        dictionarySystem.addSynonym("prime", "tone");
        dictionarySystem.addSynonym("leave", "go");
        dictionarySystem.addSynonym("happy", "joyful");
        dictionarySystem.addSynonym("sad", "unhappy");
        dictionarySystem.addSynonym("big", "large");
        dictionarySystem.addSynonym("small", "little");
        dictionarySystem.addSynonym("fast", "quick");
        dictionarySystem.addSynonym("slow", "sluggish");
        dictionarySystem.addSynonym("beautiful", "gorgeous");
        dictionarySystem.addSynonym("ugly", "unattractive");
        dictionarySystem.addSynonym("crave ", "courageous");
        dictionarySystem.addSynonym("cowardly ", "timid");
        dictionarySystem.addSynonym("smart ", "intelligent");
        dictionarySystem.addSynonym("dumb", "stupid");
        dictionarySystem.addSynonym("funny", "humorous");
        dictionarySystem.addSynonym("serious", "grave");
        dictionarySystem.addSynonym("kind", "benevolent");
        dictionarySystem.addSynonym("mean", "cruel");
        dictionarySystem.addSynonym("strong", "powerful");
        dictionarySystem.addSynonym("weak", "frail");
        dictionarySystem.addSynonym("rich", "wealthy");
        dictionarySystem.addSynonym("poor", "impoverished");
        dictionarySystem.addSynonym("tall", "high");
        dictionarySystem.addSynonym("short", "petite");
        dictionarySystem.addSynonym("old", "elderly");
        dictionarySystem.addSynonym("young", "youthful");
        dictionarySystem.addSynonym("warm", "hot");
        dictionarySystem.addSynonym("cold", "chilly");
        dictionarySystem.addSynonym("calm ", "peaceful");
        dictionarySystem.addSynonym("furious ", "angry");
    }
}

