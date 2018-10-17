package tgozdek.com.anotherweatherapp

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class ExampleAggregateTests {

    private val list = listOf(1,2,3,4,5)

    @Test
    fun testAny(){
        assertTrue(list.any{ it % 2 == 0})
        assertFalse(list.any {it > 10})
    }

    @Test
    fun testAll(){
        assertTrue(list.all { it < 10 })
        assertFalse(list.all { it %2 == 0 })
    }

    @Test
    fun testCount() = assertEquals(2, list.count { it % 2 == 0 })

    @Test
    fun testFold() = assertEquals(15, list.fold(0, {acc, next -> acc + next}))

    @Test
    fun testFoldRight() = assertEquals(15, list.foldRight(0, {acc, next -> acc + next}))

    @Test
    fun testForEach() {
        list.forEach { println(it)}
        assertTrue(true)
    }

    @Test
    fun testForEachIndexed() = list.forEachIndexed { index, i -> println("index $index : $i") }

    @Test
    fun testMax() = assertEquals(5, list.max())

    @Test
    fun testMaxBy() = assertEquals(5, list.maxBy { it + 1 })

    @Test
    fun testNone() = assertTrue(list.none { it % 7 == 0 })

    @Test
    fun testReduce() = assertEquals(list.fold(0, {acc, next -> acc + next}), list.reduce { acc, next -> acc + next })

    @Test
    fun testDrop() = assertEquals(listOf(4,5), list.drop(3))

    @Test
    fun testDropWhile() = assertEquals(listOf(2,3,4,5), list.dropWhile { it % 2 != 0 })
}