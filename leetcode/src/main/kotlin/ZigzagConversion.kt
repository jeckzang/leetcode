import kotlin.math.sin

fun main() {
    var str = "ab"

    println("convert to " + convert(str, 2))
}

fun convert(s: String, numRows: Int): String {
    var str = ""
    var strArray = Array(numRows){String()}
    var i = 0
    while(i<numRows){
        strArray[i]=""
        i++
    }
    var mIndex = numRows-2
    var sIndex = 0
    var flag = true
    s.forEachIndexed { i, c ->
        if (flag){
            sIndex++
        }
        if (sIndex % (numRows+1)==0){
            sIndex = 1
            flag =false
        }
        if (mIndex==0){
            flag = true
            mIndex = numRows-2
        }
        if ( flag){
            if (strArray[sIndex-1]==null){
                strArray[sIndex-1] = ""
            }
            strArray[sIndex-1]=strArray[sIndex-1] + c
        }else{
            var subI = mIndex
            if (mIndex<=0){
                subI = 0
            }else{
                mIndex--
            }
            strArray[subI]=strArray[subI] + c
        }
    }
    strArray.forEach{
        str+=it
    }

    return str
}