package to.grindelf.numericalmethodstwo

import to.grindelf.numericalmethodstwo.CommonUtility.TOLERANCE
import to.grindelf.numericalmethodstwo.CommonUtility.firstFunction
import to.grindelf.numericalmethodstwo.CommonUtility.jacobian
import to.grindelf.numericalmethodstwo.CommonUtility.secondFunction
import kotlin.math.abs

object NewtonsMethod{
    fun solution(xStart: Double, yStart: Double, partialDerivative: Boolean = false): Result {
        var x = xStart
        var y = yStart
        var counter = -1 // -1 because first iteration is iteration's initialization
        var jacobian: Jacobian
        var xNext = x
        var yNext = y

        do {
            x = xNext
            y = yNext
            jacobian = jacobian(x, y, partialDerivative)
            xNext = getNextX(x, y, jacobian)
            yNext = getNextY(x, y, jacobian)
            counter++
        } while (iterationIsPossible(x, xNext, y, yNext))

        return when {
            partialDerivative -> Result(x, y, counter, "Newton's method with analytic partial derivative")
            else -> Result(x, y, counter, "Newton's method with numeric partial derivative")
        }
    }

    private fun iterationIsPossible(x: Double, xNext: Double, y: Double, yNext: Double) =
        abs(xNext - x) >= TOLERANCE || abs(yNext - y) >= TOLERANCE

    private fun getNextX(x: Double, y: Double, jacobian: Jacobian) =
        x - ((jacobian.d * firstFunction(x, y) - jacobian.b * secondFunction(x, y)) / jacobian.determinant())

    private fun getNextY(x: Double, y: Double, jacobian: Jacobian) =
        y - ((jacobian.a * secondFunction(x, y) - jacobian.c * firstFunction(x, y)) / jacobian.determinant())
}
