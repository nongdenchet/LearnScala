package projects.commands

import projects.filesystem.State

trait Command {
  def apply(state: State): State
}

object Command {
  private val MKDIR = "mkdir"
  private val LS = "ls"
  private val TOUCH = "touch"

  def emptyCommand: Command = (state: State) => state

  def incompleteCommand(name: String): Command = (state: State) =>
    state.setMessage(name + " incomplete command")

  def from(input: String): Command = {
    val tokens: Array[String] = input.split(" ")

    if (input.isEmpty || tokens.isEmpty) emptyCommand
    else if (tokens(0) == LS) new ListCommand()
    else if (tokens(0) == TOUCH) {
      if (tokens.length < 2) incompleteCommand(MKDIR)
      else new TouchCommand(tokens(1))
    }
    else if (tokens(0) == MKDIR) {
      if (tokens.length < 2) incompleteCommand(MKDIR)
      else new MkdirCommand(tokens(1))
    }
    else new UnknownCommand()
  }
}
