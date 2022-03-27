import java.io.File
import java.io.IOException


class AddressParser {

    private val SEPARATOR: Char = ','


    fun parseFile(filePath: String): List<Address> {

        val file = File(filePath)
        if (!file.isFile || !file.exists()) throw IOException("$filePath is invalid")

        return parse(file.readText())
    }

    fun parse(addresses: String): List<Address> {
        val lines: List<String> = addresses.lines()
        val addressList: MutableList<Address> = ArrayList()
        lines.forEach {
            addressList.add(parseLine(it))
        }
        return addressList
    }

    private fun parseLine(address: String): Address {
        val params: List<String> = address.split(SEPARATOR)

        if (params.count() != 4) throw IllegalStateException("invalid parameter count found ${params.count()}")

        return Address(
            params[0].trim().toUInt(),
            params[1].trim(),
            params[2].trim().split('.')[1].trim(),
            params[3].trim().split('.')[1].trim().toUInt()
        )

    }
}