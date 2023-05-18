package to.grindelf.numericalmethodstwo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import to.grindelf.numericalmethodstwo.NewtonsMethod.solution

class EquationSystemSolvingTest {

    private val xStart = -0.8
    private val yStart = -0.4
    private val xResultInterval = listOf(-1.012549, -1.012540)
    private val yResultInterval = listOf(-0.524649, -0.524640)

    @Test
    fun `GIVEN functions WHEN Newtons numeric method is applied THEN solution is returned`() {
        val solution = solution(xStart, yStart)
        println(solution)

        assertThat(solution.x).isBetween(xResultInterval.min(), xResultInterval.max())
        assertThat(solution.y).isBetween(yResultInterval.min(), yResultInterval.max())
    }

    @Test
    fun `GIVEN functions WHEN Newtons partial derivative method is applied THEN solution is returned`() {
        val solution = solution(xStart, yStart, true)
        println(solution)

        assertThat(solution.x).isBetween(xResultInterval.min(), xResultInterval.max())
        assertThat(solution.y).isBetween(yResultInterval.min(), yResultInterval.max())
    }
}