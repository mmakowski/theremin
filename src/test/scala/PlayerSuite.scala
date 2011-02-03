package com.mmakowski.theremin

import org.scalatest.FunSuite

class PlayerSuite extends FunSuite {
  test("player challenges complete word") {
    val player = new Player(new Dictionary(List("complete")), 2)
    assert(player.play("complete") === CompleteWordChallenge)
  }

  test("player challenges string without completion") {
    val player = new Player(new Dictionary(List("complete")), 2)
    assert(player.play("nc") === NoCompletionChallenge)
  }

  test("player adds a random valid letter if possible") {
    val player = new Player(new Dictionary(List("all", "ball", "alternate")), 2)
    // if the implementation is correct the probability that this test fails is 1/2^999
    val responsesFromMultipleTries: Set[Response] = Set.empty ++ (for (i <- 1 to 1000) yield player.play("al"))
    assert(responsesFromMultipleTries === Set(Prepend('b'), Append('t')))
  }

  test("player bluffs by adding a random letter if lost") {
    val player = new Player(new Dictionary(List("all")), 2)
    player.play("ll") match {
      case Prepend('a') => fail("player completed a word")
      case Prepend(_) => () // expected
      case Append(_) => () // expected
      case _ => fail("player should have prepended or appended something")
    }
  }
}
