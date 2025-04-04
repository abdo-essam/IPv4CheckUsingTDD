import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Comprehensive Test Suite for IPv4 Address Validation
 *
 * Tests cover:
 * - Valid IPv4 formats
 * - Invalid formats
 * - Boundary cases
 * - Special cases
 * - Format violations
 * - Invalid characters
 */
class IPv4CheckerTest {

    /**
     * Tests for valid IPv4 addresses
     * Covers standard cases and boundary values
     */
    @Test
    fun `valid IPv4 addresses should return true`() {
        // Standard valid addresses
        assertTrue(isValidIPv4("192.168.1.1"))
        assertTrue(isValidIPv4("10.0.0.0"))
        assertTrue(isValidIPv4("172.16.254.1"))

        // Boundary values
        assertTrue(isValidIPv4("0.0.0.0"))
        assertTrue(isValidIPv4("255.255.255.255"))

        // Single digit values
        assertTrue(isValidIPv4("1.2.3.4"))

        // Mixed length numbers
        assertTrue(isValidIPv4("192.168.1.10"))
        assertTrue(isValidIPv4("172.16.254.12"))
    }

    /**
     * Tests for invalid IPv4 formats
     * Covers structural format violations
     */
    @Test
    fun `invalid IPv4 formats should return false`() {
        // Missing octets
        assertFalse(isValidIPv4("192.168.1"))
        assertFalse(isValidIPv4("192.168"))
        assertFalse(isValidIPv4("192"))

        // Extra octets
        assertFalse(isValidIPv4("192.168.1.1.1"))
        assertFalse(isValidIPv4("192.168.1.1.1.1"))

        // Empty octets
        assertFalse(isValidIPv4("192.168..1"))
        assertFalse(isValidIPv4("192..168.1"))
        assertFalse(isValidIPv4("..168.1"))

        // Multiple dots
        assertFalse(isValidIPv4("192.168.1.."))
        assertFalse(isValidIPv4("192.168...1"))
    }

    /**
     * Tests for invalid numeric values
     * Covers out-of-range values and format violations
     */
    @Test
    fun `invalid numeric values should return false`() {
        // Values > 255
        assertFalse(isValidIPv4("256.1.2.3"))
        assertFalse(isValidIPv4("1.256.2.3"))
        assertFalse(isValidIPv4("1.2.256.3"))
        assertFalse(isValidIPv4("1.2.3.256"))

        // Large invalid values
        assertFalse(isValidIPv4("300.1.2.3"))
        assertFalse(isValidIPv4("1.2.3.999"))

        // Negative values
        assertFalse(isValidIPv4("-1.2.3.4"))
        assertFalse(isValidIPv4("1.-2.3.4"))
    }

    /**
     * Tests for leading zeros
     * IPv4 addresses should not have leading zeros
     */
    @Test
    fun `addresses with leading zeros should return false`() {
        assertFalse(isValidIPv4("01.1.1.1"))
        assertFalse(isValidIPv4("1.01.1.1"))
        assertFalse(isValidIPv4("1.1.01.1"))
        assertFalse(isValidIPv4("1.1.1.01"))
        assertFalse(isValidIPv4("00.1.1.1"))
        assertFalse(isValidIPv4("000.1.1.1"))
    }

    /**
     * Tests for invalid characters
     * Only digits and dots are allowed
     */
    @Test
    fun `addresses with invalid characters should return false`() {
        // Alphabetic characters
        assertFalse(isValidIPv4("a.b.c.d"))
        assertFalse(isValidIPv4("192.168.1.1a"))
        assertFalse(isValidIPv4("x.168.1.1"))

        // Special characters
        assertFalse(isValidIPv4("192.168.1.1#"))
        assertFalse(isValidIPv4("192.168.1,1"))
        assertFalse(isValidIPv4("192:168:1:1"))

        // Spaces
        assertFalse(isValidIPv4(" 192.168.1.1"))
        assertFalse(isValidIPv4("192.168.1.1 "))
        assertFalse(isValidIPv4("192. 168.1.1"))
        assertFalse(isValidIPv4("192.168. 1.1"))
    }

    /**
     * Tests for special cases and edge cases
     */
    @Test
    fun `special cases should return false`() {
        // Empty and null cases
        assertFalse(isValidIPv4(""))
        assertFalse(isValidIPv4(" "))
        assertFalse(isValidIPv4("..."))

        // Malformed inputs
        assertFalse(isValidIPv4("."))
        assertFalse(isValidIPv4(".."))
        assertFalse(isValidIPv4(".192.168.1.1"))
        assertFalse(isValidIPv4("192.168.1.1."))

        // Invalid decimal formats
        assertFalse(isValidIPv4("192.168.1.1."))
        assertFalse(isValidIPv4("192.168.1."))
        assertFalse(isValidIPv4(".192.168.1.1"))
    }

    /**
     * Tests for specific edge cases in each octet
     */
    @Test
    fun `edge cases for each octet should be validated correctly`() {
        // Valid edge cases
        assertTrue(isValidIPv4("0.0.0.0"))
        assertTrue(isValidIPv4("255.255.255.255"))

        // Invalid edge cases
        assertFalse(isValidIPv4("256.255.255.255"))
        assertFalse(isValidIPv4("255.256.255.255"))
        assertFalse(isValidIPv4("255.255.256.255"))
        assertFalse(isValidIPv4("255.255.255.256"))
    }
}