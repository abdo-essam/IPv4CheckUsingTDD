fun main() {
    val testSuite = IPv4CheckerTest()
    testSuite.runAllTests()
}

class IPv4CheckerTest {
    private var testsPassed = 0
    private var testsFailed = 0

    fun runAllTests() {
        println("Running IPv4Checker tests...\n")

        // Valid IPv4 addresses
        testValidIPv4Addresses()

        // Invalid IPv4 formats
        testInvalidIPv4Formats()

        // Invalid numeric values
        testInvalidNumericValues()

        // Leading zeros
        testAddressesWithLeadingZeros()

        // Invalid characters
        testAddressesWithInvalidCharacters()

        // Special cases
        testSpecialCases()

        // Edge cases for each octet
        testEdgeCasesForEachOctet()

        println("\nTest results:")
        println("Passed: $testsPassed")
        println("Failed: $testsFailed")
    }

    private fun assertTrue(condition: Boolean, message: String) {
        if (condition) {
            println("PASS: $message")
            testsPassed++
        } else {
            println("FAIL: $message")
            testsFailed++
        }
    }

    private fun assertFalse(condition: Boolean, message: String) {
        assertTrue(!condition, message)
    }

    /**
     * Tests for valid IPv4 addresses
     * Covers standard cases and boundary values
     */
    private fun testValidIPv4Addresses() {
        // Standard valid addresses
        assertTrue(isValidIPv4("192.168.1.1"), "192.168.1.1 should be valid")
        assertTrue(isValidIPv4("10.0.0.0"), "10.0.0.0 should be valid")
        assertTrue(isValidIPv4("172.16.254.1"), "172.16.254.1 should be valid")

        // Boundary values
        assertTrue(isValidIPv4("0.0.0.0"), "0.0.0.0 should be valid")
        assertTrue(isValidIPv4("255.255.255.255"), "255.255.255.255 should be valid")

        // Single digit values
        assertTrue(isValidIPv4("1.2.3.4"), "1.2.3.4 should be valid")

        // Mixed length numbers
        assertTrue(isValidIPv4("192.168.1.10"), "192.168.1.10 should be valid")
        assertTrue(isValidIPv4("172.16.254.12"), "172.16.254.12 should be valid")
    }

    /**
     * Tests for invalid IPv4 formats
     * Covers structural format violations
     */
    private fun testInvalidIPv4Formats() {
        // Missing octets
        assertFalse(isValidIPv4("192.168.1"), "192.168.1 should be invalid")
        assertFalse(isValidIPv4("192.168"), "192.168 should be invalid")
        assertFalse(isValidIPv4("192"), "192 should be invalid")

        // Extra octets
        assertFalse(isValidIPv4("192.168.1.1.1"), "192.168.1.1.1 should be invalid")
        assertFalse(isValidIPv4("192.168.1.1.1.1"), "192.168.1.1.1.1 should be invalid")

        // Empty octets
        assertFalse(isValidIPv4("192.168..1"), "192.168..1 should be invalid")
        assertFalse(isValidIPv4("192..168.1"), "192..168.1 should be invalid")
        assertFalse(isValidIPv4("..168.1"), "..168.1 should be invalid")

        // Multiple dots
        assertFalse(isValidIPv4("192.168.1.."), "192.168.1.. should be invalid")
        assertFalse(isValidIPv4("192.168...1"), "192.168...1 should be invalid")
    }

    /**
     * Tests for invalid numeric values
     * Covers out-of-range values and format violations
     */
    private fun testInvalidNumericValues() {
        // Values > 255
        assertFalse(isValidIPv4("256.1.2.3"), "256.1.2.3 should be invalid")
        assertFalse(isValidIPv4("1.256.2.3"), "1.256.2.3 should be invalid")
        assertFalse(isValidIPv4("1.2.256.3"), "1.2.256.3 should be invalid")
        assertFalse(isValidIPv4("1.2.3.256"), "1.2.3.256 should be invalid")

        // Negative values
        assertFalse(isValidIPv4("-1.2.3.4"), "-1.2.3.4 should be invalid")
        assertFalse(isValidIPv4("1.-2.3.4"), "1.-2.3.4 should be invalid")
    }

    /**
     * Tests for leading zeros
     * IPv4 addresses should not have leading zeros
     */
    private fun testAddressesWithLeadingZeros() {
        assertFalse(isValidIPv4("01.1.1.1"), "01.1.1.1 should be invalid")
        assertFalse(isValidIPv4("1.01.1.1"), "1.01.1.1 should be invalid")
        assertFalse(isValidIPv4("1.1.01.1"), "1.1.01.1 should be invalid")
        assertFalse(isValidIPv4("1.1.1.01"), "1.1.1.01 should be invalid")
        assertFalse(isValidIPv4("00.1.1.1"), "00.1.1.1 should be invalid")
        assertFalse(isValidIPv4("000.1.1.1"), "000.1.1.1 should be invalid")
    }

    /**
     * Tests for invalid characters
     * Only digits and dots are allowed
     */
    private fun testAddressesWithInvalidCharacters() {
        // Alphabetic characters
        assertFalse(isValidIPv4("a.b.c.d"), "a.b.c.d should be invalid")
        assertFalse(isValidIPv4("192.168.1.1a"), "192.168.1.1a should be invalid")
        assertFalse(isValidIPv4("x.168.1.1"), "x.168.1.1 should be invalid")

        // Special characters
        assertFalse(isValidIPv4("192.168.1.1#"), "192.168.1.1# should be invalid")
        assertFalse(isValidIPv4("192.168.1,1"), "192.168.1,1 should be invalid")
        assertFalse(isValidIPv4("192:168:1:1"), "192:168:1:1 should be invalid")

        // Spaces
        assertFalse(isValidIPv4(" 192.168.1.1"), " 192.168.1.1 should be invalid")
        assertFalse(isValidIPv4("192.168.1.1 "), "192.168.1.1  should be invalid")
        assertFalse(isValidIPv4("192. 168.1.1"), "192. 168.1.1 should be invalid")
        assertFalse(isValidIPv4("192.168. 1.1"), "192.168. 1.1 should be invalid")
    }

    /**
     * Tests for special cases and edge cases
     */
    private fun testSpecialCases() {
        // Empty and null cases
        assertFalse(isValidIPv4(""), "Empty string should be invalid")
        assertFalse(isValidIPv4(" "), "Space should be invalid")
        assertFalse(isValidIPv4("..."), "... should be invalid")

        // Malformed inputs
        assertFalse(isValidIPv4("."), ". should be invalid")
        assertFalse(isValidIPv4(".."), ".. should be invalid")
        assertFalse(isValidIPv4(".192.168.1.1"), ".192.168.1.1 should be invalid")
        assertFalse(isValidIPv4("192.168.1.1."), "192.168.1.1. should be invalid")

        // Invalid decimal formats
        assertFalse(isValidIPv4("192.168.1.1."), "192.168.1.1. should be invalid")
        assertFalse(isValidIPv4("192.168.1."), "192.168.1. should be invalid")
        assertFalse(isValidIPv4(".192.168.1.1"), ".192.168.1.1 should be invalid")
    }

    /**
     * Tests for specific edge cases in each octet
     */
    private fun testEdgeCasesForEachOctet() {
        // Valid edge cases
        assertTrue(isValidIPv4("0.0.0.0"), "0.0.0.0 should be valid")
        assertTrue(isValidIPv4("255.255.255.255"), "255.255.255.255 should be valid")

        // Invalid edge cases
        assertFalse(isValidIPv4("256.255.255.255"), "256.255.255.255 should be invalid")
        assertFalse(isValidIPv4("255.256.255.255"), "255.256.255.255 should be invalid")
        assertFalse(isValidIPv4("255.255.256.255"), "255.255.256.255 should be invalid")
        assertFalse(isValidIPv4("255.255.255.256"), "255.255.255.256 should be invalid")
    }
}