package days

class Day4 : Day(4) {

    private val passports = inputString.split("\n\n")

    private val fields = mapOf<String, String>(
        "byr" to "19[2-9][0-9]|200[0-2]",
        "iyr" to "20(1[0-9]|20)",
        "eyr" to "20(2[0-9]|30)",
        "hcl" to "#[0-9a-f]{6}",
        "ecl" to "amb|blu|brn|gry|grn|hzl|oth",
        "pid" to "[0-9]{9}",
        "hgt" to "1[5-8][0-9]cm|19[0-3]cm|59in|6[0-9]in|7[0-6]in"
    )


    override fun partOne() = passports.count { fields.keys.all(it::contains) }

    override fun partTwo() = passports.count { fields.map { (f, p) -> "$f:$p\\b".toRegex() }.all(it::contains) }
}