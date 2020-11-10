package come.dopey2.platform.tools

import com.flappybird.game.res.CONSTANTS

interface GraphicsHelper {
    fun ptm(pixel: Int): Float {
        return (pixel / CONSTANTS.PTM).toFloat()
    }

    fun ptm(pixel: Float) : Float {
        return pixel / CONSTANTS.PTM
    }
}