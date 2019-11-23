package lectures.basic

import scala.annotation.tailrec

object DefaultArgs extends App {
  @tailrec
  def trFact(n: Int, acc: Int = 1): Int = {
    if (n < 1) acc
    else trFact(n - 1, n * acc)
  }

  val fact10 = trFact(10)
  println(fact10)
}
