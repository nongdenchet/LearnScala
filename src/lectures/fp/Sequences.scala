package lectures.fp

object Sequences extends App {
  // Seq
  val seq = Seq(1, 2, 3, 4)
  println(seq)
  println(seq.reverse)
  println(seq(2))
  println(seq ++ Seq(5, 6, 7))
  println(Seq(1, 3, 2, 4).sorted)

  // Range
  val ranges: Seq[Int] = 1 to 10
  ranges.foreach(println)

  // List
  val list = List(1, 2, 3)
  println(42 :: list)
  println(42 +: list :+ 90)
  println(List.fill(5)("hello"))

  // Array
  val numbers = Array(1, 2, 3)
  numbers.foreach(println)
  Array.fill(5)("test").foreach(println)
  Array.ofDim[Int](3).foreach(println)
  val test: Seq[Int] = numbers
  println(test)
}
