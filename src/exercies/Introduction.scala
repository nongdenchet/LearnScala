package exercies

object Introduction extends App {
  // Compose function
  def compose[A, B, C](g: B => C, f: A => B): A => C = x => g(f(x))

  def twice: Int => Int = x => x * 2

  def increase: Int => Int = x => x + 1

  println(compose(twice, increase)(10))

  // Fuse function
  def fuse[A, B](a: Option[A], b: Option[B]): Option[(A, B)] = for (x <- a; y <- b) yield (x, y)

  println(fuse(Some(1), Some(2)))
  println(fuse(Some(1), None))
  println(fuse(None, Some(2)))

  // Check function
  def check[T](xs: Seq[T])(pred: T => Boolean): Boolean = {
    def test(x: T) = {
      try {
        pred(x)
      } catch {
        case _: Exception => false
      }
    }

    xs.map(test).forall(y => y)
  }

  println(check(0 until 40)(40 / _ > 0))
  println(check(1 until 40)(40 / _ > 0))

  // Pair class for pattern matching
  case class Pair[P, Q](first: P, second: Q)

  Pair(10, 2) match {
    case Pair(1, b) =>
      println(b)
    case Pair(a, b) =>
      println(a + " " + b)
    case _ =>
      println("Nothing")
  }
}
