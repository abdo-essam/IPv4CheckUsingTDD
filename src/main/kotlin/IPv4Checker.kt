package org.example

fun isValidIPv4(ip: String): Boolean {
    // Check if string is empty
    if (ip.isEmpty()) return false

    // Split the string by dots
    val segments = ip.split(".")

    // Check if we have exactly 4 segments
    if (segments.size != 4) return false

    // Check each segment
    for (segment in segments) {
        // Check if segment is empty
        if (segment.isEmpty()) return false

        // Check for invalid characters
        if (!segment.all { it.isDigit() }) return false

        // Check for leading zeros
        if (segment.length > 1 && segment[0] == '0') return false

        // Convert to integer and check range
        try {
            val value = segment.toInt()
            if (value < 0 || value > 255) return false
        } catch (e: NumberFormatException) {
            return false
        }
    }

    return true
}