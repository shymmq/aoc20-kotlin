package days

class Day4 : Day(4) {

    private val passports = inputString.split("\n\n")

    private val fields = mapOf<String, (String) -> Boolean>(
        "byr" to { it.toIntOrNull() in 1920..2002 },
        "iyr" to { it.toIntOrNull() in 2010..2020 },
        "eyr" to { it.toIntOrNull() in 2020..2030 },
        "hcl" to "#[0-9a-f]{6}".toRegex()::matches,
        "ecl" to "amb|blu|brn|gry|grn|hzl|oth".toRegex()::matches,
        "pid" to "[0-9]{9}".toRegex()::matches,
        "hgt" to {
            val hgt = it.filter(Char::isDigit).toIntOrNull()
            when {
                it.endsWith("cm") -> hgt in 150..193
                it.endsWith("in") -> hgt in 59..76
                else -> false
            }
        }
    )


    override fun partOne() = passports.count { fields.keys.all(it::contains) }


    override fun partTwo() = passports.count { passport ->
        fields.all { (field, isValid) ->
            val pattern = "$field:([a-z0-9#]+)\\b".toRegex()
            val value = pattern.find(passport)?.groupValues?.getOrNull(1)
            value != null && isValid(value)
        }
    }
}
