package com.dopey2.platform.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.dopey2.platform.Game

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = CONSTANTS.width.toInt()
        config.height = CONSTANTS.height.toInt()
        LwjglApplication(Game(), config)
    }
}