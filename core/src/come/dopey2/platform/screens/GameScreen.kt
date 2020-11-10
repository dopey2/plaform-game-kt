package come.dopey2.platform.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.dopey2.platform.Game
import come.dopey2.platform.tools.GraphicsHelper
import ktx.app.KtxScreen
import ktx.graphics.use


class GameScreen(val game: Game) : KtxScreen, GraphicsHelper {

    private val camera = OrthographicCamera().apply {
        setToOrtho(false, ptm(Gdx.graphics.width), ptm(Gdx.graphics.height))
    }

    private val world = World(Vector2(0f, -16f), false)

    private val FPS = 60

    private var start = false

    override fun render(delta: Float) {
        draw(delta)
        compute(delta)
    }

    private fun draw(delta: Float) {
        camera.update()
        game.batch?.projectionMatrix = camera.combined

        game.batch.use {

        }
    }

    private fun compute(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!start) {
                start = true
            }
            if (false) {

            }
        }

        if (!start) {
            return
        }

        world.step(1f / FPS, 6, 2)
    }


    override fun show() {}
    override fun dispose() {}

}