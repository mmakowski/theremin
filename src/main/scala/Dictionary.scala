package com.mmakowski.theremin

/**
 * A dictionary of all allowed words.
 */
class Dictionary(wordList: List[String]) {
  def this(source: io.Source) {
    this(source.mkString.split("\r\n").toList)
  }

  val words = wordList.map(_.toLowerCase)

  def isWord(str: String) = words.contains(str.toLowerCase)

  def wordsContaining(str: String) = words.filter(_.contains(str.toLowerCase))
}
