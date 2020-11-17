package come.dopey2.platform.objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.physics.box2d.*
import come.dopey2.platform.tools.GraphicsHelper

class SideWall: GraphicsHelper {

    val batch: Batch
    private val world: World

    private val bodyLeft: Body
    private val bodyRight: Body


    constructor(batch: Batch, world: World, y:Float) {
        this.batch = batch
        this.world = world

        val width = 20f
        val height = CONSTANTS.height


        val bodyDefLeft = BodyDef().apply {
            type = BodyDef.BodyType.KinematicBody
            position.set(
                    ptm(-20 + width / 2),
                    ptm(y + height / 2)
            )
        }

        val bodyDefRight = BodyDef().apply {
            type = BodyDef.BodyType.KinematicBody
            position.set(
                    ptm(CONSTANTS.width - 20),
                    ptm(y + height / 2)
            )
        }


        bodyLeft = world.createBody(bodyDefLeft)
        bodyRight = world.createBody(bodyDefRight)

        val fdef = FixtureDef()
        fdef.shape = PolygonShape().apply { setAsBox(ptm(width / 2), ptm(height / 2)) }
        fdef.density = 1f
        fdef.friction = 0f
        fdef.restitution = 1f

        bodyLeft.createFixture(fdef)
        bodyRight.createFixture(fdef)
    }

    fun getY(): Float {
        return bodyLeft.position.y
    }

    fun removeBody() {
        world.destroyBody(bodyLeft)
        world.destroyBody(bodyRight)
    }

}