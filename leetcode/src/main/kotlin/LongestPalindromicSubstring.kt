fun longestPalindrome(s: String): String {
    if (s.isEmpty()) {
        return ""
    }
    var max = s[0] + ""
    if (s.length ==1){
        return max
    }

    if (check(s,0,s.length-1)){
        return s
    }
    var current = ""
    var i = 1
    while(i<s.length){
        var j = 1
        while (j<=i){
            if (i-j>=0 && i+j+1<=s.length){
                //check odd number
//                println("odd number start:${i-j} end:${i+j+1}")
                current = s.substring(i-j,i+j+1)
                if (current.length>1){
                    var found = check(current,0,current.length-1)
                    if (current.length > max.length && found) {
                        max = current
                    }
                }
            }
            if (i+1-j>=0&& i+j+1<=s.length){
                //check right even number
//                println("odd number start:${i+1-j} end:${i+j+1}")
                current = s.substring(i+1-j,i+j+1)
                if (current.length>1){
                    var found = check(current,0,current.length-1)
                    if (current.length > max.length && found) {
                        max = current
                    }
                }
            }

            if (i-1-j>=0 && i+j<=s.length){
                //check left even number
//                println("odd number start:${i+1-j} end:${i+j+1}")
                current = s.substring(i-1-j,i+j-1)
                if (current.length>1){
                    var found = check(current,0,current.length-1)
                    if (current.length > max.length && found) {
                        max = current
                    }
                }
            }
            j++
        }
        i++
    }
    return max
}

fun check(sub: String,s:Int,e:Int): Boolean {
    var i = s
    var j = e
    var found = true
    while (i <= j) {
        var ch1 = sub[i]
        var ch2 = sub[j]
        if (ch1 != ch2) {
            found = false
            break
        }
        i++
        j--
    }
    return found
}


fun main() {
    var ts = arrayOf("ccd","bb","bbbb","abcdbbfcba")
    ts.forEach {
        println(it+":"+longestPalindrome(it))
    }
}