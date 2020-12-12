package com.dopey2.platform.objects

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.dopey2.platform.tools.AssetsLoader
import com.dopey2.platform.tools.GraphicsHelper

class Plant(
        private val batch: Batch,
        private val x: Float,
        private val y: Float
        ) : GraphicsHelper {

    val plantAnimation: Animation<Sprite>

    init {
        val plantType = MathUtils.random(0, 8)

        if(plantType == 0){ plantAnimation = AssetsLoader.plant1Animation }
        else if(plantType == 1){ plantAnimation = AssetsLoader.plant2Animation }
        else if(plantType == 2){ plantAnimation = AssetsLoader.plant3Animation }
        else if(plantType == 3){ plantAnimation = AssetsLoader.plant4Animation }
        else if(plantType == 4){ plantAnimation = AssetsLoader.plant5Animation }
        else if(plantType == 5){ plantAnimation = AssetsLoader.plant6Animation }
        else if(plantType == 6){ plantAnimation = AssetsLoader.plantWindAnimation }
        else if(plantType == 7){ plantAnimation = AssetsLoader.blueFlower1Animation }
        else { plantAnimation = AssetsLoader.blueFlower2Animation }
    }


    companion object {
        private var animationTime = 0f

        fun compute(delta: Float) {
            animationTime += delta * 2
        }
    }


    fun draw(delta: Float) {
        var texture: Sprite = plantAnimation.getKeyFrame(animationTime, true)

        batch.draw(
                texture,
                ptm(x),
                ptm(y),
                ptm(100f),
                ptm(100f)
        )
    }
}