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

  test("for strings shorter than 3 player adds a random valid letter") {
    val player = new Player(new Dictionary(List("all", "ball", "alternate")), 2)
    // if the implementation is correct the probability that this test fails is 1/2^999
    val responsesFromMultipleTries: Set[Response] = Set.empty ++ (for (i <- 1 to 1000) yield player.play("al"))
    assert(responsesFromMultipleTries === Set(Prepend('b'), Append('t')))
  }

}
