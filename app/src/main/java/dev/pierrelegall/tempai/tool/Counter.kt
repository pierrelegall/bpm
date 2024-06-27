package dev.pierrelegall.tempai.tool

class Counter {
  private var taps: ArrayList<Long> = arrayListOf()

  fun computeBpm(): Long? {
    if (this.taps.size < 2) return null
    return 60 * 1000 / ((this.taps.last() - this.taps.first()) / (this.taps.size - 1))
  }

  fun tapAt(timestamp: Long) {
    this.taps.add(timestamp)
  }

  fun tapNow() {
    this.tapAt(this.now())
  }

    fun reset() {
        this.taps.clear()
    }

  fun now(): Long {
    return System.currentTimeMillis()
  }
}
