package come.dopey2.platform.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import come.dopey2.platform.tools.AssetsLoader
import come.dopey2.platform.tools.GraphicsHelper

class Plant(
        private val batch: Batch,
        private val x: Float,
        private val y: Float
        ) : GraphicsHelper {


    companion object {
        private var animationTime = 0f

        fun compute(delta: Float) {
            animationTime += delta * 2
        }
    }


    fun draw(delta: Float) {

        var texture = AssetsLoader.plant1Animation.getKeyFrame(animationTime, true)

        batch.draw(
                texture,
                ptm(x),
                ptm(y),
                ptm(200f),
                ptm(200f)
        )
    }
}