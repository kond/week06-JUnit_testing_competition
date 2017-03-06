package com.greenfox.lesson.junit;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by kond on 2017. 03. 03..
 */
public class WordToolbox implements IWordToolbox {
  private String stringHeld;

  public WordToolbox(String stringHeld) {
    this.stringHeld = stringHeld;
  }

  @Override
  public String getS() {
    return stringHeld;
  }

  @Override
  public void setS(String s) {
    this.stringHeld = s;
  }

  private HashMap<Character, Integer> countAllLetters() {
    // Here create the HashMap and go through the word letters, count them if the letter is not already counted
    HashMap<Character, Integer> wordLetterNumbers = new HashMap<>();
    String s = this.getS().replaceAll("[^a-zA-Z]+","").toUpperCase();
    for(int i=0; i<s.length(); i++) {
      if(!wordLetterNumbers.containsKey(s.charAt(i))) {
        wordLetterNumbers.put(s.charAt(i), countHowMany(s.charAt(i)));
      }
    }
    return wordLetterNumbers;
  }

  public int countHowMany(char charToFind) {
    int result = 0;
    for(int i=0; i<this.stringHeld.length(); i++) {
      result += (Character.toUpperCase(charToFind) == Character.toUpperCase(this.stringHeld.charAt(i))) ? 1 : 0;
    }
    return result;
  }

  @Override
  public boolean isAnAnagram(String stringToCheck) {
    WordToolbox otherString = new WordToolbox(stringToCheck);
    HashMap<Character, Integer> hashMap1 = this.countAllLetters();
    HashMap<Character, Integer> hashMap2 = otherString.countAllLetters();
    boolean isAnagram = (hashMap1.keySet().equals(hashMap2.keySet())) ? true : false;
    if(isAnagram) {
      for(char key : hashMap1.keySet()) {
        if(hashMap1.get(key) != hashMap2.get(key)) {
          isAnagram = false;
        }
      }
    }
    return isAnagram;
  }

  @Override
  public void WaitingItOut() {
    for (int i = 0; i == 0; ) {};
  }
}
