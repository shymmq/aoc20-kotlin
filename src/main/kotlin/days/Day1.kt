package days

import java.lang.Exception

class Day1 : Day(1) {

    override fun partOne(): Int {

        val nums = input.map(String::toInt).sorted()
        val count = nums.count()

        for (i in 0..(count - 2)) {
            if (nums[i] > 2020) break
            for (j in (i + 1)..(count - 1)) {
                if (nums[i] + nums[j] > 2020) break
                if (nums[i] + nums[j] == 2020) {
                    return nums[i] * nums[j]
                }
            }
        }

        throw Exception("Result not found")
    }


    override fun partTwo(): Any {
        val nums = input.map(String::toInt).sorted()
        val count = nums.count()

        for (i in 0..(count - 3)) {
            if (nums[i] > 2020) break
            for (j in (i + 1)..(count - 2)) {
                if (nums[i] + nums[j] > 2020) break
                for (k in (j + 1)..(count - 1)) {
                    if (nums[i] + nums[j] + nums[k] > 2020) break
                    if (nums[i] + nums[j] + nums[k] == 2020) {
                        return nums[i] * nums[j] * nums[k]
                    }
                }
            }
        }

        throw Exception("Result not found")
    }
}
