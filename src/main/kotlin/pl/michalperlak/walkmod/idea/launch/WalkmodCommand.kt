package pl.michalperlak.walkmod.idea.launch

enum class WalkmodCommand(
    val id: String
) {
    APPLY("apply"), CHECK("check"), INSTALL("install");

    override fun toString(): String = id
}