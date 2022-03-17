fun main() {
    val path = "${System.getProperty("user.dir")}\\addresses.txt"
    println(path)

    val parser = AddressParser()
    val parseResult = parser.parse(path)

    parseResult.forEach { println(it) }

    println()

    println("Address with max index: ${parseResult.maxByOrNull { i -> i.index }}")
    println("Address with min index: ${parseResult.minByOrNull { i -> i.index }}")

    println("Address with longest street name: ${parseResult.maxByOrNull { i -> i.streetName.length }}")
    println("Address with shortest street name: ${parseResult.minByOrNull { i -> i.streetName.length }}")
}