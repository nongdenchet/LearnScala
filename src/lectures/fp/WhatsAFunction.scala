package lectures.fp

object WhatsAFunction extends App {
  val concat: (String, String) => String = (a, b) => a + b
  println(concat("a", "b"))

  val inner: Int => Int => Int = a => x => x * a
  println(inner(10)(20))

  val adder: (Int, Int) => Int = _ + _
  println(adder(10, 1))

  def toCurry(f: (Int, Int) => Int): Int => Int => Int = a => b => f(a, b)
  val curry = toCurry((a, b) => a + b)
  println(curry(10)(20))

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int = (a, b) => f(a)(b)
  val test = fromCurry(a => b => a + b)
  println(test(100, 20))

  def andThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))
  def compose[A, B, C](f: B => C, g: A => B): A => C = x => f(g(x))
}
