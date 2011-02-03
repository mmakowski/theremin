package com.mmakowski.theremin

import scala.util.Random

sealed class Response
case class Append(letter: Char) extends Response
case class Prepend(letter: Char) extends Response
case object CompleteWordChallenge extends Response
case object NoCompletionChallenge extends Response

class Player(val dict: Dictionary, val noOfPlayers: Int) {
  def play(str: String) =
    if (dict.isWord(str)) CompleteWordChallenge
    else {
      val completions = dict.wordsContaining(str)
      if (completions isEmpty) NoCompletionChallenge
      else extend(str, completions)
    }

  private def extend(str: String, completions: Seq[String]) = 
    if (str.length < 3) extendRandomly(str, completions)
    else Append('u')

  private def extendRandomly(str: String, completions: Seq[String]) = {
    val letters = 'a' to 'z' // TODO: i18
    def genResponses(combined: (Char, String) => String, resp: (Char) => Response) = 
      for (l <- letters 
	   if !(completions contains combined(l, str)) && 
	      ((completions find ((s: String) => s.contains(combined(l, str)))) != None)) yield resp(l)
    val prepends = genResponses((l, s) => l + s, Prepend(_))
    val appends = genResponses((l, s) => s + l, Append(_))
    val responses = prepends ++ appends
    responses(Random.nextInt(responses length))
  }
}
