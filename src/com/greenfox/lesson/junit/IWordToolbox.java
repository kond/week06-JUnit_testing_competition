package com.greenfox.lesson.junit;

/**
 * Created by kond on 2017. 03. 03..
 */
interface IWordToolbox {
  public int countHowMany(char c);
  public void setS(String s);
  public String getS();
  public boolean isAnAnagram(String stringToCheck) throws Exception;
  public void WaitingItOut();
}
