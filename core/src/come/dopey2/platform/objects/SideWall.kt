package come.dopey2.platform.objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.physics.box2d.*
import come.dopey2.platform.const.ObjectsFilter
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
            type = BodyDef.BodyType.StaticBody
            position.set(
                    ptm( - 20 + width / 2),
                    ptm(y + height / 2)
            )
        }

        val bodyDefRight = BodyDef().apply {
            type = BodyDef.BodyType.StaticBody
            position.set(
                    ptm(CONSTANTS.width - 20),
                    ptm(y + height / 2)
            )
        }


        bodyLeft = world.createBody(bodyDefLeft)
        bodyRight = world.createBody(bodyDefRight)

        val fdefLeft = FixtureDef()
        fdefLeft.shape = PolygonShape().apply { setAsBox(ptm(width / 2), ptm(height / 2)) }
        fdefLeft.density = 1f
        fdefLeft.friction = 0f
        fdefLeft.restitution = 1f
        fdefLeft.filter.groupIndex = ObjectsFilter.SideWallLeft.toShort()

        val fdefRight = FixtureDef()
        fdefRight.shape = PolygonShape().apply { setAsBox(ptm(width / 2), ptm(height / 2)) }
        fdefRight.density = 1f
        fdefRight.friction = 0f
        fdefRight.restitution = 1f
        fdefRight.filter.groupIndex = ObjectsFilter.SideWallRight.toShort()

        bodyLeft.createFixture(fdefLeft)
        bodyRight.createFixture(fdefRight)

    }

    fun getY(): Float {
        return bodyLeft.position.y
    }

    fun removeBody() {
        world.destroyBody(bodyLeft)
        world.destroyBody(bodyRight)
    }
}