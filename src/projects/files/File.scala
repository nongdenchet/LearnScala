package projects.files

final class File(override val parentPath: String, override val name: String, val content: String)
  extends DirEntry(parentPath, name) {
  override def asDirectory: Directory = throw new IllegalStateException("File cannot convert to directory")

  override def getType: String = "File"

  override def isFile: Boolean = true
}
