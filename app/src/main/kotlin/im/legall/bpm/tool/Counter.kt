package im.legall.bpm.tool

class Counter {
    private val taps: ArrayList<Long> = arrayListOf()

    fun compute(): Long? {
        if (this.taps.size < 2) return null
        return 60 * 1000 / ((this.taps.last() - this.taps.first()) / (this.taps.size - 1))
    }

    fun mark(timestamp: Long) {
        this.taps.add(timestamp)
    }

    fun mark() {
        this.mark(System.currentTimeMillis())
    }

    fun reset() {
        this.taps.clear()
    }
}
