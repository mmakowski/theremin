package com.mmakowski.theremin

/**
 * A dictionary of all allowed words.
 */
class Dictionary(wordList: List[String]) {
  val words = wordList.map(_.toLowerCase)

  def isWord(str: String) = words.contains(str.toLowerCase)

  def wordsContaining(str: String) = words.filter(_.contains(str.toLowerCase))
}
