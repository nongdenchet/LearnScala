package exercies

import scala.annotation.tailrec

trait MyList[+T] {
  def head: T

  def tail: MyList[T]

  def isEmpty: Boolean

  def add[E >: T](element: E): MyList[E]

  def filter(f: T => Boolean): MyList[T]

  def map[E](m: T => E): MyList[E]

  def flatMap[E](m: T => MyList[E]): MyList[E]

  def forEach(f: T => Unit)

  def fold[E](init: E, f: (E, T) => E): E

  def zipWith[E, R](list: MyList[E], f: (E, T) => R): MyList[R]

  override def toString: String
}

object MyList {
  @tailrec
  def serialize[T](list: MyList[T], acc: String = ""): String = {
    if (list.isEmpty) acc
    else {
      val result = if (acc.isEmpty)
        list.head.toString
      else
        acc + ", " + list.head

      serialize(list.tail, result)
    }
  }

  def merge[T](head: MyList[T], tail: MyList[T]): MyList[T] = {
    @tailrec
    def helper(head: MyList[T], tail: MyList[T]): MyList[T] = {
      if (head.isEmpty) tail
      else helper(head.tail, Cons(head.head, tail))
    }

    helper(reverse(head), tail)
  }

  def reverse[T](list: MyList[T]): MyList[T] = {
    @tailrec
    def helper(curr: MyList[T], rest: MyList[T]): MyList[T] = {
      if (rest.isEmpty) curr
      else helper(Cons(rest.head, curr), rest.tail)
    }

    if (list.isEmpty) list
    else helper(Cons(list.head), list.tail)
  }
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def filter(f: Nothing => Boolean): MyList[Nothing] = this

  override def map[E](m: Nothing => E): MyList[E] = this

  override def flatMap[E](m: Nothing => MyList[E]): MyList[E] = this

  override def isEmpty: Boolean = true

  override def add[E >: Nothing](element: E): MyList[E] = Cons(element, this)

  override def toString: String = "[]"

  override def forEach(f: Nothing => Unit): Unit = ()

  override def fold[E](init: E, f: (E, Nothing) => E): E = init

  override def zipWith[E, R](list: MyList[E], f: (E, Nothing) => R): MyList[R] = {
    if (list.isEmpty) this
    else throw new IllegalArgumentException("Cannot zip list with different size")
  }
}

final case class Cons[T](h: T, t: MyList[T] = Empty) extends MyList[T] {
  override def head: T = h

  override def tail: MyList[T] = t

  override def filter(f: T => Boolean): MyList[T] = {
    if (f(head)) Cons(h, tail.filter(f))
    else tail.filter(f)
  }

  override def map[E](m: T => E): MyList[E] = {
    Cons(m(h), tail.map(m))
  }

  override def flatMap[E](m: T => MyList[E]): MyList[E] = {
    MyList.merge(m(h), tail.flatMap(m))
  }

  override def isEmpty: Boolean = false

  override def add[E >: T](element: E): MyList[E] = Cons(element, this)

  override def toString: String = s"[${MyList.serialize(this)}]"

  override def forEach(f: T => Unit): Unit = {
    if (!isEmpty) {
      f(head)
      t.forEach(f)
    }
  }

  override def fold[E](init: E, f: (E, T) => E): E = t.fold(f(init, h), f)

  override def zipWith[E, R](list: MyList[E], f: (E, T) => R): MyList[R] = {
    @tailrec
    def helper(list1: MyList[E], list2: MyList[T], result: MyList[R]): MyList[R] = {
      if (list1.isEmpty && list2.isEmpty)
        result
      else if (!list1.isEmpty && !list2.isEmpty)
        helper(list1.tail, list2.tail, result.add(f(list1.head, list2.head)))
      else
        throw new IllegalArgumentException("Cannot zip list with different size")
    }

    MyList.reverse(helper(list, this, Empty))
  }
}

object ListTest extends App {
  println(Empty)

  // Int
  val list = Cons(1, Cons(2, Cons(4)))
  println(list)
  println(list == Cons(1, Cons(2, Cons(4))))

  val newList = list.add(0)
  println(newList)

  // String
  val listString = Cons("a", Cons("b"))
  println(listString)

  val newListString = listString.add("c")
  println(newListString)

  // Mix
  val listMix = Cons("a", Cons(100))
  println(listMix)

  val newListMix = listMix.add(12.24)
  println(newListMix)

  // filter
  println(list.filter(_ % 2 == 0))

  // map
  println(list.map(_ * 3))

  // flatMap
  println(list.flatMap(element => Cons(element, Cons(element + 1))))

  // forEach
  list.forEach(x => print(s"$x, "))
  println()

  // fold
  println(list.fold("", (a: String, b: Int) => a + b))

  // zipWith
  println(Cons(1, Cons(2, Cons(3))).zipWith(Cons(4, Cons(5, Cons(6))), (a: Int, b: Int) => a + b))

  // for comprehensive
  println(for {
    n <- list
    string <- listString
  } yield n + " " + string)
}
