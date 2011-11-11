package com.mmakowski.theremin

import scala.util.Random

object ThereminApp {
  val dict = loadDictionary
  val player = new Player(dict, 2)

  def main(args: Array[String]) {
    Console.write("first letter: ")
    val str = Console.readLine().toLowerCase
    playComputer(str)
  }

  private def playComputer(str: String): Unit = player.play(str) match {
    case Prepend(c) => playHuman(c.toUpper + str)
    case Append(c) => playHuman(str + c.toUpper)
    case CompleteWordChallenge => Console.write(str + " is a complete word -- you've lost!\n")
    case NoCompletionChallenge => Console.write("there are no completions for " + str + " -- you've lost!\n")
  }

  private def playHuman(str: String): Unit = {
    Console.write("_" + str + "_\n")
    getHumanAction() match {
      case Prepend(c) => playComputer((c + str).toLowerCase)
      case Append(c) => playComputer((str + c).toLowerCase)
      case CompleteWordChallenge => if (dict.isWord(str)) Console.write(str.toLowerCase + " is a word -- you've won!\n")
                                    else Console.write(str.toLowerCase + " is not a word -- you've lost!\n")
      case NoCompletionChallenge => {
        val completions = dict.wordsContaining(str)
        if (completions isEmpty) Console.write("there are no completions for " + str.toLowerCase + " -- you've won!\n")
        else Console.write("sample completion: " + completions(Random.nextInt(completions.length)) + " -- you've lost!\n")
      }
    }
  }

  private def getHumanAction(): Response = {
    Console.write("[p]repend(+l), [a]ppend(+l), challenge [n]o completion or challenge complete [w]ord: ")
    val input: Seq[Char] = Console.readLine()
    input match {
      case Seq('n') => NoCompletionChallenge
      case Seq('w') => CompleteWordChallenge
      case Seq('p', c) => Prepend(c)
      case Seq('a', c) => Append(c)
      case _ => getHumanAction()
    }
  }

  private def loadDictionary() = {
    Console.write("choose the dictionary: ")
    val dictName = Console.readLine()
    val source = io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(dictName + ".txt"))
    new Dictionary(source)
  }

  object Console {
    import java.io._

    val in = new BufferedReader(new InputStreamReader(System.in))
    val out = new OutputStreamWriter(System.out)

    def write(msg: String) = {
      out.write(msg)
      out.flush()
    }

    def readLine() = in.readLine().trim
  }
}

