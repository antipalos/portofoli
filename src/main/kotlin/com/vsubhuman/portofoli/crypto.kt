package com.vsubhuman.portofoli

fun ByteArray.serializedBlake2b(): ByteArray {
    return this.cborEnc().blake2b().cborEnc()
}