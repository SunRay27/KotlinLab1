import kotlin.test.Test
import kotlin.test.assertFailsWith


internal class Tests {


    @Test
    fun testFileNotExists() {
        val path = "${System.getProperty("user.dir")}\\NONEXISTENT.txt"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(path)
        })
    }

    @Test
    fun testDirectory() {
        val path = System.getProperty("user.dir")

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(path)
        })
    }

    @Test
    fun testInvalidParameterCount() {
        val path = "${System.getProperty("user.dir")}\\testAddresses1.txt"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(path)
        })
    }

    @Test
    fun testInvalidParameterCount2() {
        val path = "${System.getProperty("user.dir")}\\testAddresses2.txt"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(path)
        })
    }

    @Test
    fun testInvalidParameterOrder() {
        val path = "${System.getProperty("user.dir")}\\testAddresses3.txt"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(path)
        })
    }

    @Test
    fun testWSMadness() {
        val path = "${System.getProperty("user.dir")}\\testAddresses4.txt"

        val parser = AddressParser()
        parser.parse(path)
    }

    @Test
    fun testInvalidParameterType() {
        val path = "${System.getProperty("user.dir")}\\testAddresses5.txt"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(path)
        })
    }
}