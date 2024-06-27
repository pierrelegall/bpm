package dev.pierrelegall.tempai

import dev.pierrelegall.tempai.tool.Counter
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CounterTest {
  @Test
  fun testCounputeBpm() {
    val counter = Counter()
    val computedBpm = counter.computeBpm()

    if (computedBpm == null) {
      assert(false)
    } else {
      assertNotNull(computedBpm)
      assertEquals(computedBpm, 120)
    }
  }
}
