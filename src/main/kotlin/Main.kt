fun main() {
    val path = "${System.getProperty("user.dir")}\\addresses.txt"
    println(path)

    val parser = AddressParser()
    val parseResult = parser.parse(path)

    parseResult.forEach { println(it) }


    println("\nAddresses with max index:")
    val maxIndexValue = parseResult.maxOf { i -> i.index }
    parseResult.forEach{if(it.index == maxIndexValue) println(it)}

    println("\nAddresses with min index:")
    val minIndexValue = parseResult.minOf { i -> i.index }
    parseResult.forEach{if(it.index == minIndexValue) println(it)}

    println("\nAddresses with longest street name:")
    val maxStreetLen = parseResult.maxOf { i -> i.streetName.length }
    parseResult.forEach{if(it.streetName.length == maxStreetLen) println(it)}

    println("\nAddresses with shortest street name:")
    val minStreetLen = parseResult.minOf { i -> i.streetName.length }
    parseResult.forEach{if(it.streetName.length == minStreetLen) println(it)}

}