package projects.files

import scala.annotation.tailrec

final class Directory(override val parentPath: String, override val name: String, val content: List[DirEntry])
  extends DirEntry(parentPath, name) {
  def replaceEntry(entryName: String, directory: Directory): Directory = {
    new Directory(parentPath, name, content.map(d =>
      if (!d.name.equals(entryName)) d
      else directory
    ))
  }

  def findEntry(name: String): DirEntry = {
    @tailrec
    def helper(name: String, list: List[DirEntry]): DirEntry = {
      if (list.isEmpty) null
      else if (list.head.name.equals(name)) content.head
      else helper(name, list.tail)
    }

    helper(name, content)
  }

  def addEntry(newDir: DirEntry): Directory = new Directory(parentPath, name, content :+ newDir)

  @tailrec
  def findDescendant(paths: List[String]): Directory =
    if (paths.isEmpty) this
    else findEntry(paths.head).asDirectory.findDescendant(paths.tail)

  def getAllFoldersInPath: List[String] =
    path.substring(1)
      .split(Directory.SEPARATOR)
      .filter(x => !x.isEmpty)
      .toList

  def hasEntry(name: String): Boolean = findEntry(name) != null

  override def asDirectory: Directory = this

  override def getType: String = "Directory"

  override def isFile: Boolean = ???
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory =
    new Directory(parentPath, name, List())
}
