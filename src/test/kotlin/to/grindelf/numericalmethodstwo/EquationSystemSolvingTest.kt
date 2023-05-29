package to.grindelf.numericalmethodstwo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import to.grindelf.numericalmethodstwo.NewtonsMethod.solution

class EquationSystemSolvingTest {

    private val xesStart = listOf(-0.8, 1.0)
    private val ysStart = listOf(-0.4, 0.5)
    private val xesResultIntervals = listOf(listOf(-1.012559, -1.012530), listOf(1.321030, 1.321059))
    private val ysResultInterval = listOf(listOf(-0.524659, -0.524630), listOf(0.997570, 0.997599))

    @Test
    fun `GIVEN functions WHEN Newtons analytic derivative method is applied THEN solution is returned`() {
        xesStart.zip(ysStart).forEachIndexed { index, (xStart, yStart) ->
            val solution = solution(xStart, yStart)
            println(
                "$solution The functions: f(x, y) = ${
                    String.format(
                        "%.6f",
                        CommonUtility.firstFunction(solution.x, solution.y)
                    )
                }, " +
                        "g(x, y) = ${String.format("%.6f", CommonUtility.secondFunction(solution.x, solution.y))}"
            )

            assertThat(solution.x).isBetween(xesResultIntervals[index].min(), xesResultIntervals[index].max())
            assertThat(solution.y).isBetween(ysResultInterval[index].min(), ysResultInterval[index].max())
        }
    }

    @Test
    fun `GIVEN functions WHEN Newtons numeric derivative method is applied THEN solution is returned`() {
        xesStart.zip(ysStart).forEachIndexed { index, (xStart, yStart) ->
            val solution = solution(xStart, yStart, true)
            println(
                "$solution The functions: f(x, y) = ${
                    String.format(
                        "%.6f",
                        CommonUtility.firstFunction(solution.x, solution.y)
                    )
                }, " +
                        "g(x, y) = ${String.format("%.6f", CommonUtility.secondFunction(solution.x, solution.y))}"
            )

            assertThat(solution.x).isBetween(xesResultIntervals[index].min(), xesResultIntervals[index].max())
            assertThat(solution.y).isBetween(ysResultInterval[index].min(), ysResultInterval[index].max())
        }
    }
}
