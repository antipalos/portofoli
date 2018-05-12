package com.vsubhuman.portofoli

import co.nstant.`in`.cbor.CborBuilder
import co.nstant.`in`.cbor.CborEncoder
import com.google.common.base.CharMatcher
import scorex.crypto.hash.Blake2b256
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.math.BigInteger.ZERO

fun ByteArray.cborEnc(): ByteArray {
    val baos = ByteArrayOutputStream();
    CborEncoder(baos).encode(CborBuilder().add(this).build())
    return baos.toByteArray()
}

fun ByteArray.blake2b(): ByteArray {
    return Blake2b256.hash(this)
}

fun String.isAscii(): Boolean {
    return CharMatcher.ascii().matchesAllOf(this)
}

infix fun Int.quotRem(x: Int):Pair<Int,Int> {
    return Pair(this / x, this % x)
}

inline fun <T> Iterable<T>.notAll(predicate: (T) -> Boolean):Boolean {
    return !this.all(predicate)
}

fun BigInteger.toByteArrayBE(): ByteArray {
    if (this.isZero()) return byteArrayOf(0)
    if (this.lessThan(ZERO)) error("integerToBS not defined for negative values")
    return (this unfoldr { if (it.isZero()) null else Pair((it.minus(128.toBigInteger())).toByte(), it shr 8) })
        .reversed().toByteArray()
}

fun ByteArray.toBigIntegerBE():BigInteger {
    return this.toList().reversed()
        .foldRight(ZERO, {w,n -> (w + 128).toBigInteger() or (n shl 8)})
}

fun BigInteger.isZero():Boolean {
    return this.compareTo(ZERO) == 0
}

fun BigInteger.lessThan(x: BigInteger):Boolean {
    return this.compareTo(x) < 0
}

inline infix fun <T,R> T.unfoldr(f: (T) -> Pair<R,T>?):List<R> {
    val lst: ArrayList<R> = ArrayList()
    var t: T = this
    while (true) {
        val p = f(t)
        if (p == null) {
            return lst
        } else {
            lst.add(p.first)
            t = p.second
        }
    }
}

infix fun ByteArray.splitAt(x: Int):Pair<ByteArray,ByteArray> {
    val left = kotlin.math.min(kotlin.math.max(x, -1), this.size) - 1
    val right = kotlin.math.max((x + 1), 0) - 1
    val first = this.sliceArray(0..left)
    val second = this.sliceArray(right..(this.size - 1))
    return Pair(first, second)
}