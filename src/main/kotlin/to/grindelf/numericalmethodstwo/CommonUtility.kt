package to.grindelf.numericalmethodstwo

import kotlin.math.cos
import kotlin.math.sin

object CommonUtility {

    const val TOLERANCE = 10e-5

    private const val STEP = 1e-3

    fun firstFunction(x: Double, y: Double) = sin(x - y) - x * y + 1

    fun secondFunction(x: Double, y: Double) = x * x - y * y - 0.75

    fun jacobian(x: Double, y: Double, numericalDerivatives: Boolean) = Jacobian(
        firstDerivativeByX(x, y, numericalDerivatives), firstDerivativeByY(x, y, numericalDerivatives),
        secondDerivativeByX(x, y, numericalDerivatives), secondDerivativeByY(x, y, numericalDerivatives)
    )

    private fun firstDerivativeByX(x: Double, y: Double, numericalDerivatives: Boolean) = when {
        numericalDerivatives -> (firstFunction(x + STEP, y) - firstFunction(x, y)) / STEP
        else -> cos(x - y) - y
    }

    private fun firstDerivativeByY(x: Double, y: Double, numericalDerivatives: Boolean) = when {
        numericalDerivatives -> (firstFunction(x, y + STEP) - firstFunction(x, y)) / STEP
        else -> -cos(x - y) - x
    }

    private fun secondDerivativeByX(x: Double, y: Double, numericalDerivatives: Boolean) = when {
        numericalDerivatives -> (secondFunction(x + STEP, y) - secondFunction(x, y)) / STEP
        else -> 2 * x
    }

    private fun secondDerivativeByY(x: Double, y: Double, numericalDerivatives: Boolean) = when {
        numericalDerivatives -> (secondFunction(x, y + STEP) - secondFunction(x, y)) / STEP
        else -> -2 * y
    }
}

data class Jacobian(
    val a: Double, val b: Double,
    val c: Double, val d: Double
) {
    fun determinant(): Double = a * d - c * b
}

data class Result(val x: Double, val y: Double, val iterations: Int, val methodName: String) {
    private val xStringed: String = String.format("%.6f", x)
    private val yStringed: String = String.format("%.6f", y)

    override fun toString() = "$methodName: \nx = $xStringed, y = $yStringed, there was $iterations iterations."
}
