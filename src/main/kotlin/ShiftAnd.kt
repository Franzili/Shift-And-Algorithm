package main.kotlin

import kotlin.collections.HashMap

typealias BitMaskTable = HashMap<Char, Long>

/**
 * Creates a bit mask for the pattern.
 */
fun createBitMaskTable(pattern: String): BitMaskTable {
    val m = pattern.length
    val p = pattern.toCharArray()
    val table: BitMaskTable = HashMap()
    for (char: Char in pattern) {
        table[char] = 0
    }
    for (i in 0 until m) {
        val t = table.getOrDefault(p[i], 0)
        table[p[i]] = t or (1L shl(i))
    }
    return table
}

/**
 * Input: @param{pattern} to search in a given @param{text}
 * Returns an index of where the match begins in the text, or -1 if there was no match.
 */
fun shiftAnd(pattern: String, text: String): Int {
    val bTable = createBitMaskTable(pattern)
    val m = pattern.length
    val l = text.length
    val t = text.toCharArray()
    val matchMask: Long = 1L shl(m-1)
    var matched: Long
    var d: Long = 0
    for (i in 0 until l) {
        d = (d shl(1) or 1) and (bTable.getOrDefault(t[i], 0) or 0)
        matched = d and matchMask
        if (matched != 0L) {
            return i - m + 1
        }
    }
    return -1
}

fun main(args: Array<String>) {
    print(shiftAnd("nana", "banana"))
}