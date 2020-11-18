package come.dopey2.platform.tools

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.Array

class AssetsLoader {

    companion object {
        val platform_medium = Texture("images/platform/platform_medium.png")
        val platform_medium_large = Texture("images/platform/platform_medium_large.png")
        val platform_large = Texture("images/platform/platform_large.png")

        lateinit var plant1Animation: Animation<Sprite>

        private var loaded = false

        fun init() {
            val plant1Sprites = mutableListOf<Sprite>()

            for (i in 0..89) {
                var key = i.toString()
                if(i < 10) {
                    key = "0$i"
                }

                plant1Sprites.add(Sprite(Texture("images/plant/Plant_1/Plant1_000$key.png")))
            }

            plant1Animation = Animation(0.1f, Array(plant1Sprites.toTypedArray()), Animation.PlayMode.LOOP)

            loaded = true
        }

        fun isLoaded(): Boolean {
            return loaded
        }
    }

}