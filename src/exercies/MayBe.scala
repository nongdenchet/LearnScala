package exercies

abstract class MayBe[+T] {
  def map[E](m: T => E): MayBe[E]

  def flatMap[E](m: T => MayBe[E]): MayBe[E]

  def filter(f: T => Boolean): MayBe[T]
}

case object MayBeNot extends MayBe[Nothing] {
  override def map[E](m: Nothing => E): MayBe[E] = MayBeNot

  override def flatMap[E](m: Nothing => MayBe[E]): MayBe[E] = MayBeNot

  override def filter(f: Nothing => Boolean): MayBe[Nothing] = MayBeNot
}

case class Just[+T](value: T) extends MayBe[T] {
  override def map[E](m: T => E): MayBe[E] = Just(m(value))

  override def flatMap[E](m: T => MayBe[E]): MayBe[E] = m(value)

  override def filter(f: T => Boolean): MayBe[T] = {
    if (f(value)) Just(value)
    else MayBeNot
  }
}

// test
object MayBeTest extends App {
  val just = Just(3)
  println(just)
  println(just.map(_ * 2))
  println(just.flatMap(x => Just(x * 3)))
  println(just.filter(_ == 3))
  println(just.filter(_ == 2))
}
