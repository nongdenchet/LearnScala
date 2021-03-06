package projects.commands

import projects.files.Directory
import projects.files.File
import projects.filesystem.State

class TouchCommand(name: String) extends Command {
  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage(s"Entry $name already exist")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage("Name must not contain separator")
    } else if (checkIllegalName(name)) {
      state.setMessage("Illegal entry name")
    } else {
      createFile(state, name)
    }
  }

  private def checkIllegalName(name: String): Boolean = {
    name.contains(".")
  }

  private def updateStructure(curr: Directory, path: List[String], file: File): Directory = {
    if (path.isEmpty) {
      curr.addEntry(file)
    } else {
      val oldEntry = curr.findEntry(path.head).asDirectory
      curr.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, file))
    }
  }

  private def createFile(state: State, name: String): State = {
    val wd = state.wd
    val allDirInPath = wd.getAllFoldersInPath
    val newDir = new File(wd.path, name, "")
    val newRoot = updateStructure(state.root, allDirInPath, newDir)
    val newWd = newRoot.findDescendant(allDirInPath)

    State(newRoot, newWd)
  }
}
