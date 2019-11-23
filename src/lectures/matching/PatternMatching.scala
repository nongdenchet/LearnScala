package lectures.matching

import scala.util.Random

object PatternMatching extends App {
  val random = new Random(System.nanoTime())
  val x = random.nextInt(10)
  val des = x match {
    case 1 => "One"
    case 2 => "Two"
    case 3 => "Three"
    case _ => "Blah"
  }
  println(s"$x $des")

  case class Person(name: String, age: Int)
  val pop = Person("Bob", 24)
  val greeting = pop match {
    case Person(n, a) if a < 21 => s"Hi my name is $n and $a years old and I can't drink"
    case Person(n, a) => s"Hi my name is $n and $a years old"
    case _ => s"Blah"
  }
  println(greeting)

  sealed class Animal
  case class Dog(name: String) extends Animal
  case class Cat(breed: String) extends Animal
  val animal: Animal = Cat("dog")
  animal match {
    case Dog(_) => println("Dog")
    case Cat(_) => println("Cat")
    case _ => println("lol")
  }

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(expr1: Expr, expr2: Expr) extends Expr
  case class Prod(expr1: Expr, expr2: Expr) extends Expr
  def show(expr: Expr, isMultiply: Boolean = false): String = {
    expr match {
      case Number(n) => n.toString
      case Sum(a, b) =>
        if (isMultiply) s"(${show(a)} + ${show(b)})"
        else s"${show(a)} + ${show(b)}"
      case Prod(a, b) => s"${show(a, isMultiply = true)} * ${show(b, isMultiply = true)}"
      case _ => ""
    }
  }
  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Number(2), Sum(Number(3), Number(4)))))
  println(show(Prod(Number(2), Sum(Number(3), Number(4)))))
  println(show(Sum(Number(2), Prod(Number(3), Number(4)))))
  println(show(Prod(Number(2), Prod(Number(3), Number(4)))))

  val tuple = (1, 2, 3)
  val (t, y, z) = tuple
  println(s"$t $y $z")

  val list = List(1, 2 ,3)
  val head :: tail = list
  println(head)
  println(tail)
}
