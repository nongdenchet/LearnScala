package projects.commands

import projects.files.DirEntry
import projects.filesystem.State

class ListCommand extends Command {
  override def apply(state: State): State =
    state.setMessage(createNiceOutput(state.wd.content))

  private def createNiceOutput(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      s"${entry.name}[${entry.getType}]\n${createNiceOutput(contents.tail)}"
    }
  }
}
