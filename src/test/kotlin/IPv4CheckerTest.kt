import org.example.IPv4Checker
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class IPv4CheckerTest {

    private val iPv4Checker = IPv4Checker()

    @Test
    fun `empty string is invalid`() {
        assertFalse(iPv4Checker.isValidIPv4(""))
    }

    @Test
    fun `null input is invalid`() {
        assertFalse(iPv4Checker.isValidIPv4(null))
    }

    @Test
    fun `valid simple IPv4 address`() {
        assertTrue(iPv4Checker.isValidIPv4("192.168.0.1"))
    }

    @Test
    fun `zero is a valid segment`() {
        assertTrue(iPv4Checker.isValidIPv4("0.0.0.0"))
    }

    @Test
    fun `255 is a valid segment`() {
        assertTrue(iPv4Checker.isValidIPv4("255.255.255.255"))
    }

    @Test
    fun `invalid when more than 3 dots`() {
        assertFalse(iPv4Checker.isValidIPv4("192.168.0.1.2"))
    }

    @Test
    fun `invalid when fewer than 3 dots`() {
        assertFalse(iPv4Checker.isValidIPv4("192.168.0"))
    }

    @Test
    fun `invalid when segments are out of range`() {
        assertFalse(iPv4Checker.isValidIPv4("256.0.0.1"))
        assertFalse(iPv4Checker.isValidIPv4("192.168.0.256"))
        assertFalse(iPv4Checker.isValidIPv4("-1.0.0.1"))
    }

    @Test
    fun `invalid with leading zeros`() {
        assertFalse(iPv4Checker.isValidIPv4("01.02.03.04"))
    }

    @Test
    fun `invalid with non-numeric characters`() {
        assertFalse(iPv4Checker.isValidIPv4("192.168.0.a"))
        assertFalse(iPv4Checker.isValidIPv4("192.168.0."))
    }

    @Test
    fun `invalid with spaces`() {
        //assertFalse(iPv4Checker.isValidIPv4(" 192.168.0.1 "))
        assertFalse(iPv4Checker.isValidIPv4("192. 168.0.1"))
    }
}