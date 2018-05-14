package com.vsubhuman.portofoli

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

fun ByteArray.serializedBlake2b(): ByteArray {
    return this.cborEnc().blake2b().cborEnc()
}

fun newXPrv(seed: ByteArray, pass: ByteArray) {
    if (seed.size < 32)
        error("Wallet.generate: seed need to be >= 32 bytes, got : ${seed.size}")
    for (i in 1..1000) {

    }
    error("internal error: Wallet.generate looping forever")
}

fun hmac(ent: ByteArray, key: ByteArray):Pair<ByteArray, ByteArray> {
    val algo = "HmacSHA512"
    val hmac = Mac.getInstance(algo)
    hmac.init(SecretKeySpec(ent, algo))
    hmac.update(key)
    return hmac.doFinal().splitAt(32)
}