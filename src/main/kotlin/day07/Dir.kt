package day07

data class Dir(
    val name: String,
) {
    val childDirs: MutableMap<String, Dir> = mutableMapOf()
    val childFiles: MutableList<File> = mutableListOf()

    fun getSize(): Int = childFiles.sumOf { it.size } + childDirs.values.sumOf { it.getSize() }

    fun getAllChildDirs() = childDirs.values
}

class File(
    val size: Int,
) {
    override fun toString(): String {
        return size.toString()
    }
}