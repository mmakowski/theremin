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

  test("can be built from an io source") {
    val source = io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("dictionary.txt"))
    val dict = new Dictionary(source)
    for (word <- source.mkString.split("\r\n") if word.length > 0) 
      assert(dict.isWord(word), word + " is in dictionary")
    assert(!dict.isWord("notaword"))
  }
}
