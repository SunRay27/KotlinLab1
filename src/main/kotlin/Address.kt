 data class Address(
    val index: UInt,
    val cityName: String,
    val streetName: String,
    val buildingNumber: UInt
) {
     override fun toString(): String = "$index, $cityName, ул. $streetName, д. $buildingNumber"
}
