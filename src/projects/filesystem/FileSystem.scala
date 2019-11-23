package projects.filesystem

import java.util.Scanner

import projects.commands.Command
import projects.files.Directory

import scala.annotation.tailrec

object FileSystem extends App {
  val root = Directory.ROOT
  val scanner = new Scanner(System.in)

  @tailrec
  def run(state: State): Unit = {
    state.show()
    val input = scanner.nextLine()
    run(Command.from(input).apply(state))
  }

  run(State(root, root))
}
