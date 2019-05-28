


fun lengthOfLongestSubstring(s: String): Int {
    if("".equals(s)){
        return 0
    }
    var result =""
    var currentStr = s
    var finished = false
    var inited = false
    var currentIndex = 0
    while(true){
        var maxStr = if (inited) currentStr else ""
        for ((i, c) in s.withIndex()){
            if (i>=currentIndex){
                if (!maxStr.contains(c)){
                    maxStr += c
                }else{
                    currentIndex = i
                    break
                }
            }
            if (i==s.length-1){
                finished =true
            }
        }
        if (maxStr.length>result.length){
            result = maxStr
        }
        maxStr = maxStr.drop(1)
        currentStr = maxStr
        if (finished){
            break
        }
        inited=true
    }
    println("result:$result")
    return result.length
}


fun main() {
    var t = ""
    println(lengthOfLongestSubstring(t))
}