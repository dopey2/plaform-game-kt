package come.dopey2.platform.objects

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.World
import com.dopey2.platform.Game
import come.dopey2.platform.tools.GraphicsHelper

class PlatformGenerator(
        private val game: Game,
        private var world: World,
        private var player: Player

): GraphicsHelper {

    private val platforms = mutableListOf<Platform>().apply {
        add(Platform(game.batch, world, 0f, 0f, CONSTANTS.width, 20f))
    }

    private var lastPlatformY = 0f
    private val platformDistance = 100f

    fun compute(delta: Float): Unit {

        if(mtp(player.getY() + platformDistance * 3) > lastPlatformY) {
            platforms.add(generatePlatform())
            lastPlatformY += platformDistance
        }
    }

    fun generatePlatform(): Platform {
        val width = 400f // MathUtils.random(50f, 400f)
        val x = MathUtils.random(0f, CONSTANTS.width - (width + 30f ))



        return Platform(
                game.batch,
                world,
                x,
                lastPlatformY + platformDistance,
                width,
                20f
        )


    }


}