package lectures.oop

import scala.annotation.tailrec

object OOBasics extends App {
  val person = new Person("Jim", 24)
  println(person)
  println(person.age)
  person.greet("Daniel")
  person.greet()

  val person1 = new Person("Test")
  println(person1.age)

  val c = new Counter()
  c.inc.inc.inc.print()

  val point1 = new Point(1, 2)
  val point2 = new Point(3, 4)
  val point = point1 + point2
  println(point)
  println(-point)
  println(point())
  println(Person.SIZE)
}

object Person {
  val SIZE = 100
}

class Person(val name: String, val age: Int = 0) {
  def greet(name: String): Unit = {
    println(s"${this.name} says: Hi $name")
  }

  def greet(): Unit = {
    println(s"$name says hi")
  }
}

final class Point(val x: Double, val y: Double) {
  def +(point: Point): Point = {
    new Point(x + point.x, y + point.y)
  }

  def +(value: String): Point = {
    val values = value.split(";")

    new Point(x + values(0).toDouble, y + values(1).toDouble)
  }

  def unary_- = new Point(-x, -y)

  def apply(): String = s"$x, $y"

  override def toString: String = s"$x, $y"
}

final class Counter(value: Int = 0) {
  def count(): Int = value

  def inc = new Counter(value + 1)

  def dec = new Counter(value - 1)

  @tailrec
  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }

  @tailrec
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print(): Unit = println(s"Counter: $value")
}
