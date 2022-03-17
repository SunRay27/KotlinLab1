import com.sun.nio.sctp.InvalidStreamException
import java.io.File
import java.security.InvalidParameterException

class AddressParser {

    private val SEPARATOR: Char = ','

    fun tryParse(filePath: String): List<Address> {

        //for debug purposes
        var line = 0
        var char = 0

        try {
            //init file class and check if filePath correct
            val file = File(filePath)
            if (!file.isFile || !file.exists()) throw InvalidParameterException("$filePath is invalid")

            //init list of parsed addresses
            val returnList: MutableList<Address> = ArrayList()

            //start parsing line by line
            val lines: List<String> = File(filePath).readLines()
            lines.forEach {
                //split line by separator
                val params: List<String> = it.split(SEPARATOR)

                //check splitted array length
                if (params.count() != 4) throw InvalidStreamException()

                //just parse
                char = 0
                val parsedIndex = params[0].trim().toUInt()

                char = params[0].length
                val parsedCityName = params[1].trim()

                char = params[0].length + params[1].length
                val parsedStreetName = params[2].trim().split('.')[1].trim()

                char = params[0].length + params[1].length + params[2].length
                val parsedBuildingNumber = params[3].trim().split('.')[1].trim().toUInt()

                //add new address to
                returnList.add(Address(parsedIndex, parsedCityName, parsedStreetName, parsedBuildingNumber))
                line++
            }
            return returnList
        } catch (e: Exception) {
            if (e is NumberFormatException) println("invalid parameter at: $line:$char in file $filePath")
            if (e is InvalidStreamException) println("invalid args count at line $line in file $filePath")
            if (e is InvalidStreamException) println("invalid file path $filePath")
            return emptyList()
        }
    }

    fun parse(filePath: String): List<Address> {
        //the same as tryParse but without any checks

        val file = File(filePath)
        if (!file.isFile || !file.exists()) throw Exception("$filePath is invalid")

        val returnList: MutableList<Address> = ArrayList()
        val lines: List<String> = file.readLines()
        lines.forEach {
            val params: List<String> = it.split(SEPARATOR)

            if (params.count() != 4) throw Exception("invalid parameter count")

            returnList.add(
                Address(
                    params[0].trim().toUInt(),
                    params[1].trim(),
                    params[2].trim().split('.')[1].trim(),
                    params[3].trim().split('.')[1].trim().toUInt()
                )
            )
        }
        return returnList
    }
}