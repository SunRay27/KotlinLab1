import kotlin.test.Test
import kotlin.test.assertFailsWith


internal class Tests {

    @Test
    fun testFileNotExists() {
        val path = "${System.getProperty("user.dir")}\\NONEXISTENT.txt"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parseFile(path)
        })
    }

    @Test
    fun testPathDirectory() {
        val path = System.getProperty("user.dir")

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parseFile(path)
        })
    }

    //test parse exceptions
    @Test
    fun testInvalidParameterCount() {
        val address = "7666, Локосово, ул. Старых лопат, д. 26, 11034, Владивосток, ул. Первооткрывателей, д. 20"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(address)
        })
    }

    @Test
    fun testInvalidParameterCount2() {
        val address = "7666, Локосово, ул. Старых лопат"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(address)
        })
    }

    @Test
    fun testInvalidParameterOrder() {
        val address = "Лангепас, ул. Детворы, д. 8,2681"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(address)
        })
    }

    @Test
    fun testWSMadness() {
        val address = "17981, Челябинск, ул. Человеков, д. 29\n" +
                "11034                        ,                             Владивосток           ,                ул. Первооткрывателей,                    д. 20\n" +
                "                   5296,                  Нижневартовск              , ул. Пролетарская, д. 16\n" +
                "1674, Санкт-Петербург, ул             .              Марсиан, д. 25\n" +
                "8833, Мегион,                      ул. Первооткрывателей,                      д. 13\n" +
                "11383,                 Владивосток,             ул. Детворы,              д. 14\n" +
                "2713, Лангепас,                ул         .                   Людская,    д    .         11\n" +
                "                    2874, Лангепас,                         ул. Буржуазии,                       д. 13\n" +
                "16134, Чебоксары, ул.                     Садовая, д .            15\n" +
                "9922, Ульяновск, ул. Первооткрывателей, д .  15"

        val parser = AddressParser()
        parser.parse(address)
    }

    @Test
    fun testInvalidParameterType() {
        val address = "17KKK981, Челябинск, ул. Человеков, д. 29/2\n" +
                "76B66, Локосово, ул. Старых лопат, д. 26"

        assertFailsWith<Exception>(block = {
            val parser = AddressParser()
            parser.parse(address)
        })
    }

    //test parse results
    @Test
    fun testResultCount()
    {
        val address = "17981, Челябинск, ул. Человеков, д. 29\n" +
                "11034                        ,                             Владивосток           ,                ул. Первооткрывателей,                    д. 20\n" +
                "                   5296,                  Нижневартовск              , ул. Пролетарская, д. 16\n" +
                "1674, Санкт-Петербург, ул             .              Марсиан, д. 25\n" +
                "8833, Мегион,                      ул. Первооткрывателей,                      д. 13\n" +
                "11383,                 Владивосток,             ул. Детворы,              д. 14\n" +
                "2713, Лангепас,                ул         .                   Людская,    д    .         11\n" +
                "                    2874, Лангепас,                         ул. Буржуазии,                       д. 13\n" +
                "16134, Чебоксары, ул.                     Садовая, д .            15\n" +
                "9922, Ульяновск, ул. Первооткрывателей, д .  15"
        val parser = AddressParser()
        val parsed = parser.parse(address)
        val count = parsed.count() == 10

        assert(count)
    }
    @Test
    fun testResultIndexes()
    {
        val address = "17981, Челябинск, ул. Человеков, д. 29\n" +
                "11034                        ,                             Владивосток           ,                ул. Первооткрывателей,                    д. 20\n" +
                "                   5296,                  Нижневартовск              , ул. Пролетарская, д. 16\n" +
                "1674, Санкт-Петербург, ул             .              Марсиан, д. 25\n" +
                "8833, Мегион,                      ул. Первооткрывателей,                      д. 13\n" +
                "11383,                 Владивосток,             ул. Детворы,              д. 14\n" +
                "2713, Лангепас,                ул         .                   Людская,    д    .         11\n" +
                "                    2874, Лангепас,                         ул. Буржуазии,                       д. 13\n" +
                "16134, Чебоксары, ул.                     Садовая, д .            15\n" +
                "9922, Ульяновск, ул. Первооткрывателей, д .  15"
        val parser = AddressParser()
        val parsed = parser.parse(address)

        val index = "17981" == parsed[0].index.toString() &&
                    "11034" == parsed[1].index.toString() &&
                    "5296" == parsed[2].index.toString() &&
                    "1674" == parsed[3].index.toString() &&
                    "8833" == parsed[4].index.toString() &&
                    "11383" == parsed[5].index.toString() &&
                    "2713" == parsed[6].index.toString() &&
                    "2874" == parsed[7].index.toString() &&
                    "16134" == parsed[8].index.toString() &&
                    "9922" == parsed[9].index.toString()
        assert(index)
    }
    @Test
    fun testResultCities()
    {
        val address = "17981, Челябинск, ул. Человеков, д. 29\n" +
                "11034                        ,                             Владивосток           ,                ул. Первооткрывателей,                    д. 20\n" +
                "                   5296,                  Нижневартовск              , ул. Пролетарская, д. 16\n" +
                "1674, Санкт-Петербург, ул             .              Марсиан, д. 25\n" +
                "8833, Мегион,                      ул. Первооткрывателей,                      д. 13\n" +
                "11383,                 Владивосток,             ул. Детворы,              д. 14\n" +
                "2713, Лангепас,                ул         .                   Людская,    д    .         11\n" +
                "                    2874, Лангепас,                         ул. Буржуазии,                       д. 13\n" +
                "16134, Чебоксары, ул.                     Садовая, д .            15\n" +
                "9922, Ульяновск, ул. Первооткрывателей, д .  15"
        val parser = AddressParser()
        val parsed = parser.parse(address)

        val city = "Челябинск" == parsed[0].cityName &&
                "Владивосток" == parsed[1].cityName &&
                "Нижневартовск" == parsed[2].cityName &&
                "Санкт-Петербург" == parsed[3].cityName &&
                "Мегион" == parsed[4].cityName &&
                "Владивосток" == parsed[5].cityName &&
                "Лангепас" == parsed[6].cityName &&
                "Лангепас" == parsed[7].cityName &&
                "Чебоксары" == parsed[8].cityName &&
                "Ульяновск" == parsed[9].cityName
        assert(city)
    }
    @Test
    fun testResultStreets()
    {
        val address = "17981, Челябинск, ул. Человеков, д. 29\n" +
                "11034                        ,                             Владивосток           ,                ул. Первооткрывателей,                    д. 20\n" +
                "                   5296,                  Нижневартовск              , ул. Пролетарская, д. 16\n" +
                "1674, Санкт-Петербург, ул             .              Марсиан, д. 25\n" +
                "8833, Мегион,                      ул. Первооткрывателей,                      д. 13\n" +
                "11383,                 Владивосток,             ул. Детворы,              д. 14\n" +
                "2713, Лангепас,                ул         .                   Людская,    д    .         11\n" +
                "                    2874, Лангепас,                         ул. Буржуазии,                       д. 13\n" +
                "16134, Чебоксары, ул.                     Садовая, д .            15\n" +
                "9922, Ульяновск, ул. Первооткрывателей, д .  15"
        val parser = AddressParser()
        val parsed = parser.parse(address)

        val streets = "Человеков" == parsed[0].streetName &&
                "Первооткрывателей" == parsed[1].streetName &&
                "Пролетарская" == parsed[2].streetName &&
                "Марсиан" == parsed[3].streetName &&
                "Первооткрывателей" == parsed[4].streetName &&
                "Детворы" == parsed[5].streetName &&
                "Людская" == parsed[6].streetName &&
                "Буржуазии" == parsed[7].streetName &&
                "Садовая" == parsed[8].streetName &&
                "Первооткрывателей" == parsed[9].streetName
        assert(streets)
    }
    @Test
    fun testResultBuildings()
    {
        val address = "17981, Челябинск, ул. Человеков, д. 29\n" +
                "11034                        ,                             Владивосток           ,                ул. Первооткрывателей,                    д. 20\n" +
                "                   5296,                  Нижневартовск              , ул. Пролетарская, д. 16\n" +
                "1674, Санкт-Петербург, ул             .              Марсиан, д. 25\n" +
                "8833, Мегион,                      ул. Первооткрывателей,                      д. 13\n" +
                "11383,                 Владивосток,             ул. Детворы,              д. 14\n" +
                "2713, Лангепас,                ул         .                   Людская,    д    .         11\n" +
                "                    2874, Лангепас,                         ул. Буржуазии,                       д. 13\n" +
                "16134, Чебоксары, ул.                     Садовая, д .            15\n" +
                "9922, Ульяновск, ул. Первооткрывателей, д .  15"
        val parser = AddressParser()
        val parsed = parser.parse(address)

        val buildings = 29u == parsed[0].buildingNumber &&
                20u  == parsed[1].buildingNumber &&
                16u == parsed[2].buildingNumber &&
                25u == parsed[3].buildingNumber &&
                13u == parsed[4].buildingNumber &&
                14u == parsed[5].buildingNumber &&
                11u == parsed[6].buildingNumber &&
                13u == parsed[7].buildingNumber &&
                15u == parsed[8].buildingNumber &&
                15u == parsed[9].buildingNumber
        assert(buildings)
    }

    @Test
    fun testResultImportExport()
    {
        val address = "17981, Челябинск, ул. Человеков, д. 29"

        val parser = AddressParser()
        val parsed = parser.parse(address)

        assert(parsed[0].toString() == address)
    }
}