fun main() {
    var str = 10

    println("convert to ${isPalindrome(str)}")
}

fun isPalindrome(x: Int): Boolean {
    var str = x.toString()
    return str.equals(str.reversed())
}