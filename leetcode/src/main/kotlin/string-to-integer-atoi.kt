fun main() {
    var str = "9223372036854775808"

    println("convert to ${myAtoi(str)}")
}

fun myAtoi(str: String): Int {
    if (str.trim().isEmpty()) {
        return 0
    }
    var tstr = str.trim()
    var value = 0.toDouble()
    var flag = 1
    var valid = false
    run loop@{
        tstr.forEach {
            when (it) {
                '1' -> {
                    value = value * 10 + 1
                    valid = true
                }
                '2' -> {
                    value = value * 10 + 2
                    valid = true
                }
                '3' -> {
                    value = value * 10 + 3
                    valid = true
                }
                '4' -> {
                    value = value * 10 + 4
                    valid = true
                }
                '5' -> {
                    value = value * 10 + 5
                    valid = true
                }
                '6' -> {
                    value = value * 10 + 6
                    valid = true
                }
                '7' -> {
                    value = value * 10 + 7
                    valid = true
                }
                '8' -> {
                    value = value * 10 + 8
                    valid = true
                }
                '9' -> {
                    value = value * 10 + 9
                    valid = true
                }
                '0' -> {
                    value = value * 10 + 0
                    valid = true
                }
                '-' -> {
                    if (!valid) {
                        valid = true
                        flag = -1
                    } else {
                        return@loop
                    }
                }
                '+' -> {
                    if (valid) {
                        return@loop
                    } else {
                        valid = true
                    }
                }
                ' ' -> {
                    if (valid) {
                        return@loop
                    }
                }
                else -> {
                    return@loop
                }
            }
        }
    }

    if (valid) {
        value *= flag
        if (value > Int.MAX_VALUE) {
            return Int.MAX_VALUE
        }
        if (value < Int.MIN_VALUE) {
            return Int.MIN_VALUE
        }
        return value.toInt()
    }
    return 0
}