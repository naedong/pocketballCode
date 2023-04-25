import kotlin.math.pow

fun main(args: Array<String>) {
    var m = 10  // 가로길이
    var n = 10 // 세로길이
    var startX = 3 // 시작 X 좌표
    var startY = 7 // 시작 Y 좌표
    var balls = arrayOf(intArrayOf(7,7),intArrayOf(2,7),intArrayOf(7,3))

    println(solution(m,n,startX,startY, balls).contentToString())
}

// 세로길이                                          //공의 좌표            `
//fun solution(m : Int, n : Int, startX : Int, startY : Int, balls : Array<IntArray>) : IntArray {
//    var answer = intArrayOf()
//
//    var startXToLeft = startX  //출발점에서 왼쪽까지의 거리
//    var startXToRight = m - startX // 출발점에서 오른쪽까지의 거리
//    var startYToDown = startY // 출발점에서 아래쪽 끝까지 거리
//    var startYToUp = n - startY // 출발점에서 위쪽 끝까지의 거리
//
//    var ansList = 0
//    for(ball in balls.indices){
//        var destX = balls[ball][0]
//        var destY = balls[ball][1]
//
//
//        var destXToLeft = destX // 각 공의 x좌표에서 왼쪽끝까지의 거리
//        var destXToRight = m - destX
//        var destYToDown = destY
//        var destYToUp = n - destY
//
//        // 왼쪽 방향의 거리 - 출발점에서 왼쪽, 각 X 좌표에서 왼쪽끝까지 거리를 더합다음  2제곱근을 넣는다
//
//
//        // y의 경우
//        var left = (startXToLeft + destXToLeft).toDouble().pow(2) + (startY - destY).toDouble().pow(2)
//
//        var right = (startXToRight + destXToRight).toDouble().pow(2) + (startY - destY).toDouble().pow(2)
//        var down = (startYToDown + destYToDown).toDouble().pow(2) + (startX - destX).toDouble().pow(2)
//        var up = (startYToUp + destYToUp).toDouble().pow(2) + (startX - destX).toDouble().pow(2)
//
//        var end = 0
//
//        if(destX == startX){
//            if(destY > startY) end = Math.min(Math.min(left.toInt(), right.toInt()), down.toInt())
//            else end = Math.min(Math.min(left.toInt(), right.toInt()), up.toInt());
//        }
//        else if (destY == startY){
//            if(destX > startX) end = Math.min(Math.min(up.toInt(), down.toInt()), left.toInt())
//            else end = Math.min(Math.min(up.toInt(), down.toInt()), right.toInt())
//        }else {
//            end = Math.min(Math.min(left.toInt(), right.toInt()), Math.min(up.toInt(), down.toInt()))
//        }
//
//        answer = answer.plus(end)
//    }
//
//    return answer
//}

fun solution(m: Int, n: Int, startX: Int, startY: Int, balls: Array<IntArray>): IntArray {
    val answer = IntArray(balls.size)

    val startXToLeft = startX
    val startXToRight = m - startX
    val startYToDown = startY
    val startYToUp = n - startY

    val destXToLeft = balls.map { it[0] }
    val destXToRight = destXToLeft.map { m - it }
    val destYToDown = balls.map { it[1] }
    val destYToUp = destYToDown.map { n - it }

    val left = destXToLeft.mapIndexed { index, destX ->
        (startXToLeft + destX).toDouble().pow(2) + (startY - balls[index][1]).toDouble().pow(2)
    }
    val right = destXToRight.mapIndexed { index, destX ->
        (startXToRight + destX).toDouble().pow(2) + (startY - balls[index][1]).toDouble().pow(2)
    }
    val down = destYToDown.mapIndexed { index, destY ->
        (startYToDown + destY).toDouble().pow(2) + (startX - balls[index][0]).toDouble().pow(2)
    }
    val up = destYToUp.mapIndexed { index, destY ->
        (startYToUp + destY).toDouble().pow(2) + (startX - balls[index][0]).toDouble().pow(2)
    }

    balls.forEachIndexed { index, ball ->
        answer[index] = when {
            ball[0] == startX -> {
                if (ball[1] > startY) minOf(left[index], right[index], down[index])
                else minOf(left[index], right[index], up[index])
            }

            ball[1] == startY -> {
                if (ball[0] > startX) minOf(up[index], down[index], left[index])
                else minOf(up[index], down[index], right[index])
            }

            else -> minOf(left[index], right[index], up[index], down[index])
        }.toInt()
    }
    return answer
}