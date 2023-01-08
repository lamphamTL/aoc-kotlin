package utils

fun String.findSingle(str: String): String = toRegex().find(str)!!.groupValues[1]
fun String.findMultiple(str: String): List<String> = toRegex().find(str)!!.groupValues.drop(1)