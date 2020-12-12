package com.dopey2.platform.tools

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
        lateinit var plant2Animation: Animation<Sprite>
        lateinit var plant3Animation: Animation<Sprite>
        lateinit var plant4Animation: Animation<Sprite>
        lateinit var plant5Animation: Animation<Sprite>
        lateinit var plant6Animation: Animation<Sprite>
        lateinit var plant7Animation: Animation<Sprite>
        lateinit var plantWindAnimation: Animation<Sprite>
        lateinit var blueFlower1Animation: Animation<Sprite>
        lateinit var blueFlower2Animation: Animation<Sprite>

        private var loaded = false

        fun init() {
            val plant1Sprites = mutableListOf<Sprite>()
            val plant2Sprites = mutableListOf<Sprite>()
            val plant3Sprites = mutableListOf<Sprite>()
            val plant4Sprites = mutableListOf<Sprite>()
            val plant5Sprites = mutableListOf<Sprite>()
            val plant6Sprites = mutableListOf<Sprite>()
            val plant7Sprites = mutableListOf<Sprite>()
            val blueFlower1Sprites = mutableListOf<Sprite>()
            val blueFlower2Sprites = mutableListOf<Sprite>()
            val plantWindSprites = mutableListOf<Sprite>()

            for (i in 0..89) {
                var key = i.toString()

                if(i < 10) {
                    key = "0$i"
                }

                plant1Sprites.add(Sprite(Texture("images/plant/Plant_1/Plant1_000$key.png")))
                plant2Sprites.add(Sprite(Texture("images/plant/Plant_2/Plant2_000$key.png")))
                plant3Sprites.add(Sprite(Texture("images/plant/Plant_3/Plant3_000$key.png")))

                if(i < 60) {
                    plant4Sprites.add(Sprite(Texture("images/plant/Plant_4/Plant4_000$key.png")))
                    plant5Sprites.add(Sprite(Texture("images/plant/Plant_5/Plant5_000$key.png")))
                    plant6Sprites.add(Sprite(Texture("images/plant/Plant_6/Plant6_000$key.png")))
                    plant7Sprites.add(Sprite(Texture("images/plant/Plant_7/Plant7_000$key.png")))
                    blueFlower1Sprites.add(Sprite(Texture("images/plant/BlueFlower1/BlueFlower_000$key.png")))
                    blueFlower2Sprites.add(Sprite(Texture("images/plant/BlueFlower2/BluePlantClosed_000$key.png")))
                }

                if(i < 30) {
                    plantWindSprites.add(Sprite(Texture("images/plant/Plant_Wind_1/PlantWind1_000$key.png")))
                }

            }

            plant1Animation = Animation(0.1f, Array(plant1Sprites.toTypedArray()), Animation.PlayMode.LOOP)
            plant2Animation = Animation(0.1f, Array(plant2Sprites.toTypedArray()), Animation.PlayMode.LOOP)
            plant3Animation = Animation(0.1f, Array(plant3Sprites.toTypedArray()), Animation.PlayMode.LOOP)
            plant4Animation = Animation(0.1f, Array(plant4Sprites.toTypedArray()), Animation.PlayMode.LOOP)
            plant5Animation = Animation(0.1f, Array(plant5Sprites.toTypedArray()), Animation.PlayMode.LOOP)
            plant6Animation = Animation(0.1f, Array(plant6Sprites.toTypedArray()), Animation.PlayMode.LOOP)
            plant7Animation = Animation(0.1f, Array(plant7Sprites.toTypedArray()), Animation.PlayMode.LOOP)
            plantWindAnimation = Animation(0.1f, Array(plantWindSprites.toTypedArray()), Animation.PlayMode.LOOP)
            blueFlower1Animation = Animation(0.1f, Array(blueFlower1Sprites.toTypedArray()), Animation.PlayMode.LOOP)
            blueFlower2Animation = Animation(0.1f, Array(blueFlower2Sprites.toTypedArray()), Animation.PlayMode.LOOP)

            loaded = true
        }

        fun isLoaded(): Boolean {
            return loaded
        }
    }

}