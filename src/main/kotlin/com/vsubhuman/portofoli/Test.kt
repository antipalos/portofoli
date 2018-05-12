package com.vsubhuman.portofoli

fun main(args: Array<String>) {
//    val words = "result ice tilt harbor trade service drink tuition mail half like tribe"
    val words = "air air air air air air air air air air air air"
    println(fromMnemonic(words.split(" ")).map { it + 128 })
}