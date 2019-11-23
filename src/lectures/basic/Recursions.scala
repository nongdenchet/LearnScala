package lectures.basic

import scala.annotation.tailrec

object Recursions extends App {
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  def factorial2(n: BigInt): BigInt = {
    def facHelper(x: BigInt, acc: BigInt): BigInt = {
      if (x <= 1) acc
      else facHelper(x - 1, x * acc)
    }

    facHelper(n, 1)
  }

  println(factorial2(5000))

  // 1, 1, 2, 3, 5
  def fibonacci(n: BigInt): BigInt = {
    @tailrec
    def help(prev: BigInt, curr: BigInt, index: BigInt): BigInt = {
      if (index >= n) curr
      else help(curr, prev + curr, index + 1)
    }

    if (n <= 2) 1
    else help(1, 2, 3)
  }

  println(fibonacci(1))
  println(fibonacci(2))
  println(fibonacci(3))
  println(fibonacci(4))
  println(fibonacci(1000))
}
