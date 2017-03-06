package com.greenfox.lesson.junit;

import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.model.TestTimedOutException;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Created by kond on 2017. 03. 03..
 */
public class TestKond {
  private ArrayList<WordToolbox> wSources = new ArrayList<>();
  private String[] testStrings = {
          "Tarzan's toenails",
          "Me Tarzan, You Jane",
          "folate",
          "aloft",
          "",
          "alma"
  };
  private WordToolbox wordToolbox;

  @Rule
  public Timeout globalTimeout = Timeout.seconds(1);

  @BeforeClass
  public static void onceExecutedBeforeAll() {
    System.out.println("@BeforeClass: onceExecutedBeforeAll");
  }

  @Before
  public void setUp() throws Exception {
    for(String s : testStrings) {
      wSources.add(new WordToolbox(s));
    }
  }

  @Before
  public void executedBeforeEach() {
    wordToolbox = new WordToolbox("Muppets");
    System.out.println("@Before: executedBeforeEach");
  }

  @AfterClass
  public static void onceExecutedAfterAll() {
    System.out.println("@AfterClass: onceAfterAll");
  }

  @After
  public void executedAfterEach() {
    wordToolbox = null;
    System.out.println("@After: executedAfterEach");
  }

  @Test
  public void testGetS() throws Exception {
    int i = 0;
    for(WordToolbox w : wSources) {
      assertEquals(testStrings[i], w.getS());
      i++;
    }
  }

  @Test
  public void testSetS() throws Exception {
    wSources.get(0).setS("alma");
    assertEquals("alma", wSources.get(0).getS());
  }

  @Test
  public void testCountHowMany() throws Exception {
    assertEquals(3, wSources.get(0).countHowMany('a'));
    assertEquals(0, wSources.get(0).countHowMany('x'));
    assertEquals(1, wSources.get(0).countHowMany(' '));
    assertEquals(3, wSources.get(1).countHowMany('a'));
    assertEquals(0, wSources.get(1).countHowMany('x'));
    assertEquals(3, wSources.get(1).countHowMany(' '));
  }

  @Test
  public void testCountHowManyCaseInsensitveness() throws Exception {
    assertEquals(2, wSources.get(0).countHowMany('T'));
    assertEquals(2, wSources.get(0).countHowMany('t'));
    assertEquals(1, wSources.get(1).countHowMany('T'));
    assertEquals(1, wSources.get(1).countHowMany('t'));
  }

  @Test
  public void testIsAnAnagram() throws Exception {
    assertFalse(wSources.get(2).isAnAnagram("falsetto"));
    assertFalse(wSources.get(2).isAnAnagram("latest"));
    assertTrue(wSources.get(2).isAnAnagram("foetal"));
    assertFalse(wSources.get(2).isAnAnagram("aloft"));
    assertFalse(wSources.get(2).isAnAnagram("float"));
    assertFalse(wSources.get(2).isAnAnagram("flota"));

    assertFalse(wSources.get(3).isAnAnagram("falsetto"));
    assertFalse(wSources.get(3).isAnAnagram("latest"));
    assertFalse(wSources.get(3).isAnAnagram("foetal"));
    assertTrue(wSources.get(3).isAnAnagram("aloft"));
    assertTrue(wSources.get(3).isAnAnagram("float"));
    assertTrue(wSources.get(3).isAnAnagram("flota"));
  }

  @Test
  public void testIsAnAnagramCaseInsensitiveness() throws Exception {
    assertTrue(wSources.get(2).isAnAnagram("FoLaTe"));
    assertTrue(wSources.get(3).isAnAnagram("FLOAT"));
  }

  @Test
  public void testIsAnAnagramWithWhitespaces() throws Exception {
    assertTrue(wSources.get(2).isAnAnagram("   Fo   LaT e"));
    assertTrue(wSources.get(3).isAnAnagram("F  LO   AT  "));
  }

  @Test
  public void testIsAnAnagramWithNonLetters() throws Exception {
    assertTrue(wSources.get(2).isAnAnagram("Fo, La!T'e123"));
    assertTrue(wSources.get(3).isAnAnagram("1F = LO +  A#T  "));
  }

  @Test
  public void testIsAnAnagramIfBlank() throws Exception {
    assertFalse(wSources.get(3).isAnAnagram(""));
    assertFalse(wSources.get(4).isAnAnagram("not blank"));
    assertTrue(wSources.get(4).isAnAnagram(""));
  }

  @Test
  public void testIsAnagramSameLettersDifferentCounts() throws Exception {
    assertFalse(wSources.get(5).isAnAnagram("alama"));
  }

  @Test (expected = NullPointerException.class)
  public void testIsAnAnagramIfNull() throws Exception {
    System.out.printf("is %s an anagram of %s = %s\n", null, wSources.get(3).getS(),
            wSources.get(3).isAnAnagram(null));
  }

  @Test //(expected = TestTimedOutException.class)
  public void testWaitingItOut() throws Exception {
    try {
      //wSources.get(3).WaitingItOut();
    } catch (Throwable t) {
      System.out.println("WaitingItOut timed out");
    }
  }

}