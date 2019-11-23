package lectures.oop

object CaseClasses extends App {
  case class Person(name: String, age: Int)

  val person = Person("Jim", 21)
  println(person)

  val person2 = Person("Jim", 21)
  println(person == person2)

  val person3 = Person("Jim", 20)
  println(person == person3)
}
