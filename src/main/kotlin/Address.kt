 data class Address(
    val index: Int,
    val cityName: String,
    val streetName: String,
    val buildingNumber: Int
) {
     override fun toString(): String = "$index, $cityName, ул. $streetName, д. $buildingNumber"
}
