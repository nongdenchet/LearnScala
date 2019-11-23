package lectures.oop

object Generics extends App {
  class MyList[+T] {
    def add[B >: T](element: B): MyList[B] = ???
  }

  object MyList {
    def empty[T]: MyList[T] = ???
  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Covariance
  class CovarianceList[+A]
  val animalList: CovarianceList[Animal] = new CovarianceList[Dog]

  // Invariance
  class InvarianceList[A]
  val animalList2: InvarianceList[Animal] = new InvarianceList[Animal]

  // Contravariance
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // Bounded
  class Cage[A <: Animal](animal: A)
  val cage = new Cage[Dog](new Dog)
}
