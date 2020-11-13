package come.dopey2.platform.tools

interface GraphicsHelper {
    fun ptm(pixel: Int): Float {
        return (pixel / CONSTANTS.PTM).toFloat()
    }

    fun ptm(pixel: Float) : Float {
        return pixel / CONSTANTS.PTM
    }

    fun mtp(meters: Float) : Float {
        return meters * CONSTANTS.PTM
    }

    fun mtp(meters: Int) : Float {
        return meters.toFloat() * CONSTANTS.PTM
    }
}