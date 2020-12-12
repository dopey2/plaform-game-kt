package com.dopey2.platform.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.dopey2.platform.Game
import com.dopey2.platform.objects.WorldGenerator
import com.dopey2.platform.objects.Player
import com.dopey2.platform.tools.AssetsLoader
import com.dopey2.platform.tools.GraphicsHelper
import ktx.app.KtxScreen
import ktx.graphics.use


class GameScreen(val game: Game) : KtxScreen, GraphicsHelper {

    private val camera = OrthographicCamera().apply {
        setToOrtho(false, ptm(Gdx.graphics.width), ptm(Gdx.graphics.height))
    }

    private val world = World(Vector2(0f, -16f), false)

    private val player = Player(game.batch, world)
    private val platformGenerator = WorldGenerator(game.batch, world, player)


    private val FPS = 60
    private var start = false

    val debugRenderer = Box2DDebugRenderer()


    init {
        AssetsLoader.init()
    }

    override fun render(delta: Float) {
        draw(delta)
        compute(delta)
    }

    private fun draw(delta: Float) {
        camera.update()
        game.batch?.projectionMatrix = camera.combined

        //debugRenderer.render(world, camera.combined);

        game.batch.use {
            player.draw(delta)
            platformGenerator.draw(delta)
        }
    }

    private fun compute(delta: Float) {

        if(player.getY() > camera.position.y) {
            camera.position.y = player.getY()
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!start && AssetsLoader.isLoaded()) {
                start = true
            }
            if (false) {

            }
        }

        if (!start) {
            return
        }

        world.step(1f / FPS, 6, 2)
        player.compute(delta)
        platformGenerator.compute(delta)

    }

    override fun show() {}
    override fun dispose() {}

}