package com.mmakowski.theremin

import org.scalatest.FunSuite

class DictionarySuite extends FunSuite {
  val dict = new Dictionary(List("Extraordinary", "Pass", "Password", "Word"))

  test("correct word is reported to be a word irrespective of case") { 
    assert(dict.isWord("WOrd")) 
  }

  test("incorrect word is reported to not be a word") {
    assert(!dict.isWord("wor"))
  }

  test("all words containing given string are returned") {
    assert(dict.wordsContaining("ORD") === List("extraordinary", "password", "word"))
  }
}
