package lectures.oop

object Traits extends App {
  trait Animal {
    val name: String
    def eat(animal: Animal): Unit
  }

  final class Crocodile extends Animal {
    override def eat(animal: Animal): Unit = println(s"$name eats ${animal.name}")

    override val name: String = "croc"
  }

  final class Dog extends Animal {
    override def eat(animal: Animal): Unit = println(s"$name eats ${animal.name}")

    override val name: String = "dog"
  }
}
