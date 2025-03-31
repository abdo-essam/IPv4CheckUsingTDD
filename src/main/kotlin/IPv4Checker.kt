package org.example

class IPv4Checker{
    fun isValidIPv4(ip: String?): Boolean {

        // Null or empty check
        if (ip.isNullOrBlank()) return false

        // Trim whitespace
        val trimmedIp = ip.trim()

        // Check for exactly 3 dots
        val segments = trimmedIp.split(".")
        if (segments.size != 4) return false

        // Validate each segment
        for (segment in segments) {
            // Check if segment is empty or contains non-numeric characters
            if (segment.isEmpty() || !segment.all { it.isDigit() }) return false

            // Check for leading zeros
            if (segment.length > 1 && segment.startsWith('0')) return false

            // Convert to integer and check range
            val value = segment.toIntOrNull() ?: return false
            if (value < 0 || value > 255) return false
        }

        return true
    }
}