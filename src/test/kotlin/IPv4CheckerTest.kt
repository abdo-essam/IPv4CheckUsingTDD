import org.example.isValidIPv4
import org.junit.jupiter.api.Test

import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IPv4CheckerTest {
    @Test
    fun `valid IPv4 addresses should return true`() {
        assertTrue(isValidIPv4("192.168.1.1"))
        assertTrue(isValidIPv4("0.0.0.0"))
        assertTrue(isValidIPv4("255.255.255.255"))
        assertTrue(isValidIPv4("1.2.3.4"))
    }

    @Test
    fun `invalid IPv4 addresses should return false`() {
        // Invalid format
        assertFalse(isValidIPv4("192.168.1"))
        assertFalse(isValidIPv4("192.168.1.1.1"))
        assertFalse(isValidIPv4("192.168..1"))

        // Invalid numbers
        assertFalse(isValidIPv4("256.1.2.3"))
        assertFalse(isValidIPv4("1.256.2.3"))
        assertFalse(isValidIPv4("1.2.256.3"))
        assertFalse(isValidIPv4("1.2.3.256"))

        // Leading zeros
        assertFalse(isValidIPv4("01.1.1.1"))
        assertFalse(isValidIPv4("1.01.1.1"))

        // Invalid characters
        assertFalse(isValidIPv4("a.b.c.d"))
        assertFalse(isValidIPv4("192.168.1.1a"))
        assertFalse(isValidIPv4(" 192.168.1.1"))
        assertFalse(isValidIPv4("192.168.1.1 "))

        // Empty or null
        assertFalse(isValidIPv4(""))
        assertFalse(isValidIPv4("..."))
    }
}