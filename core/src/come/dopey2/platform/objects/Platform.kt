package come.dopey2.platform.objects

import come.dopey2.platform.tools.GraphicsHelper

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.physics.box2d.*
import come.dopey2.platform.const.ObjectsFilter

class Platform: GraphicsHelper {

    val batch: Batch
    private val world: World

    private val width: Float
    private val height: Float

    private val bodyDef: BodyDef
    private val body: Body
    private val fdef: FixtureDef

    constructor(batch: Batch, world: World, x: Float, y: Float, w: Float, h: Float) {
        this.batch = batch
        this.world = world

        width = w
        height = h

        bodyDef = BodyDef().apply {
            type = BodyDef.BodyType.StaticBody
            position.set(
                    ptm(x + width / 2),
                    ptm(y + height / 2)
            )
        }

        body = world.createBody(bodyDef)
        fdef = FixtureDef()
        fdef.shape = PolygonShape().apply { setAsBox(ptm(width / 2), ptm(height / 2)) }
        fdef.density = 1f
        fdef.friction = 0f
        fdef.restitution = 0f
        fdef.filter.groupIndex = ObjectsFilter.Platform.toShort()
        body.createFixture(fdef)
    }


//    override fun draw(delta: Float) {
//        batch.draw(
//                image,
//                body.position.x - ptm(width  / 2),
//                body.position.y - ptm(height / 2),
//                ptm(width),
//                ptm(height)
//        )
//    }
//
//    fun jump() {
//        body.applyLinearImpulse(Vector2(0f, 4f ), body.worldCenter, false)
//    }
//
//    override fun dispose() {
//        world.destroyBody(body)
//    }
}