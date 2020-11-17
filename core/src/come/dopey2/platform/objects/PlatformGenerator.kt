package come.dopey2.platform.objects

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.World
import com.dopey2.platform.Game
import come.dopey2.platform.tools.GraphicsHelper

class PlatformGenerator(
        private val game: Game,
        private var world: World,
        private var player: Player

) : GraphicsHelper {

    private val platforms = mutableListOf<Platform>().apply {
        add(Platform(game.batch, world, 0f, 0f, CONSTANTS.width, 20f))
    }

    private val sideWalls = mutableListOf<SideWall>()

    private var lastPlatformY = 0f
    private val platformDistance = 100f

    private var lastSideWallY = 0f

    fun compute(delta: Float): Unit {
        spawnPlatform()
        spawnSideWall()
        destroyOutOfTheScreenPlatform()
        destroyOutOfTheScreenSideWalls()
    }

    fun spawnPlatform() {
        if (mtp(player.getY()) + platformDistance * 3 > lastPlatformY) {
            platforms.add(generatePlatform())
            lastPlatformY += platformDistance
        }
    }

    fun spawnSideWall() {
        if (mtp(player.getY()) + CONSTANTS.height > lastSideWallY) {
            sideWalls.add(SideWall(game.batch, world, lastSideWallY))
            lastSideWallY += CONSTANTS.height
        }
    }

    fun generatePlatform(): Platform {
        val width = MathUtils.random(150f, 350f)
        val x = MathUtils.random(0f, CONSTANTS.width - (width + 30f))

        return Platform(
                game.batch,
                world,
                x,
                lastPlatformY + platformDistance,
                width,
                20f
        )
    }

    fun destroyOutOfTheScreenPlatform() {
        if (mtp(player.getY()) > mtp(platforms.get(0).getY()) + CONSTANTS.height) {
            platforms.get(0).removeBody()
            platforms.removeAt(0)
        }
    }

    fun destroyOutOfTheScreenSideWalls() {
        if(mtp(player.getY()) > mtp(sideWalls.get(0).getY()) + CONSTANTS.height * 2) {
            sideWalls.get(0).removeBody()
            sideWalls.removeAt(0)
        }
    }
}