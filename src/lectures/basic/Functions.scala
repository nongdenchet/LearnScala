package lectures.basic

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def repeatFunction(a: String, b: Int): String = {
    if (b <= 1) return a

    a + repeatFunction(a, b - 1)
  }

  println(repeatFunction("a", 100))

  // pass value
  def callByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  // defer value, factory
  def callByName(x: => Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())
}
