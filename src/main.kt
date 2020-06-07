import de.ulrich_boeing.basics.*
import processing.core.PApplet

class MyApplet : PApplet() {
    companion object {
        val app = MyApplet()
        fun runSketch(args: Array<String>) {
            app.args = args
            app.setSize(600, 800)
            app.runSketch()
        }
    }

    val axiom = "F"
    val rules = mapOf('F' to "FF+[+F-F-F]-[-F+F+F]")
    val actions = mapOf(
        'F' to {
            line(0f, 0f, 0f, -len)
//            ellipse(-2f, 0f, +2f, -len)
            translate(0f, -len)
        },
        '+' to { rotate(angle) },
        '-' to { rotate(-angle) },
        '[' to { push() },
        ']' to { pop() }
    )

    var str = axiom

    var len = 400f
    var angle = (Math.PI / 9).toFloat()

    override fun setup() {
        background(COLOR_SHIFFMAN)
        surface.setLocation(800, 500)
        stroke(COLOR_GREEN.setAlpha(255))
//        noStroke()

    }

    override fun draw() {
        println(str)
        repeat(4)
        {
            resetMatrix()
            translate(260f, 800f)
            len *= 0.49f
            turtle(str)

            str = generateNext(str)
        }
//        turtle(str)
//        if (frameCount == 6)
        noLoop()
    }
    fun generateNext(str: String): String {
        val next = StringBuilder()
        for (c in str) {
            next.append(
                if (rules.containsKey(c)) {
                    rules[c]
                } else {
                    c
                }
            )
        }
        return next.toString()
    }

    fun turtle(str: String) = str.forEach {
        actions[it]?.invoke()
    }

}

fun main(args: Array<String>) {
    MyApplet.runSketch(args)
}