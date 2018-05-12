package com.vsubhuman.portofoli

fun main(args: Array<String>) {
    val words = listOf(
        "result", "ice", "tilt", "harbor", "trade", "service", "drink", "tuition", "mail", "half", "like", "tribe")
    val ent = toHashSeed(words)
    println(ent.toList())
}