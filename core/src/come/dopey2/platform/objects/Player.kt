package come.dopey2.platform.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import come.dopey2.platform.tools.GraphicsHelper

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.utils.Array
import come.dopey2.platform.const.ObjectsFilter

class Player : GraphicsHelper, ContactListener {

    val batch: Batch
    private val world: World

    private val idleAnimation: Animation<Sprite>
    private val runAnimation: Animation<Sprite>
    private val jumpAnimation: Animation<Sprite>

    private val width = 50f
    private val height = 80f

    private val bodyDef: BodyDef
    private val body: Body
    private val fdef: FixtureDef


    private var animationTime = 0f

    private enum class PlayerStateEnum {
        IDLE,
        RUN,
        JUMP
    }

    private enum class PlayerDirection {
        LEFT,
        RIGHT
    }

    private var playerState = PlayerStateEnum.IDLE
    private var playerDirection = PlayerDirection.RIGHT

    constructor(batch: Batch, world: World) {
        this.batch = batch
        this.world = world.apply {
            setContactListener(this@Player)
        }

        val idleSprite = mutableListOf<Sprite>()
        val runSprite = mutableListOf<Sprite>()
        val jumpSprite = mutableListOf<Sprite>()

        for (i in 0..9) {
            idleSprite.add(Sprite(Texture("images/player/idle/Idle__00$i.png")))
            runSprite.add(Sprite(Texture("images/player/run/Run__00$i.png")))
            jumpSprite.add(Sprite(Texture("images/player/jump/Jump__00$i.png")))
        }

        idleAnimation = Animation(0.1f, Array(idleSprite.toTypedArray()), Animation.PlayMode.LOOP)
        runAnimation = Animation(0.3f, Array(runSprite.toTypedArray()), Animation.PlayMode.LOOP)
        jumpAnimation = Animation(0.03f, Array(jumpSprite.toTypedArray()), Animation.PlayMode.NORMAL)

        val y = 240f - height / 2
        val x = 400f - width

        bodyDef = BodyDef().apply {
            type = BodyDef.BodyType.DynamicBody
            position.set(
                    ptm(x + width / 2),
                    ptm(y + height / 2)
            )
        }

        bodyDef.linearDamping = 2f

        body = world.createBody(bodyDef)
        fdef = FixtureDef()
        fdef.shape = PolygonShape().apply { setAsBox(ptm(width / 2), ptm(height / 2)) }
        fdef.density = 1f
        fdef.friction = 1f
        fdef.restitution = 0f

        body.createFixture(fdef)
        body.isFixedRotation = true
    }

    public fun getY(): Float {
        return body.position.y
    }

    fun playerControl() {
        val vector2 = Vector2(0f, 0f)

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            vector2.x = 0.6f
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            vector2.x = -0.6f
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            vector2.y = 20 + Math.abs(body.linearVelocity.x)
        }

        if(vector2.x != 0f || vector2.y != 0f ) {
            body.applyLinearImpulse(vector2, body.worldCenter, false)
        }
    }

    fun definePlayerState() {
        if (Math.abs(body.linearVelocity.x) < 0.3f) {
            playerState = PlayerStateEnum.IDLE
        } else {
            playerState = PlayerStateEnum.RUN
        }

        if (Math.abs(body.linearVelocity.y) > 0.3f) {
            playerState = PlayerStateEnum.JUMP
        }

        if (body.linearVelocity.x < 0) {
            playerDirection = PlayerDirection.RIGHT
        } else {
            playerDirection = PlayerDirection.LEFT
        }
    }

    fun computeAnimationTime(delta: Float) {
        if(playerState == PlayerStateEnum.RUN) {
            animationTime += delta * Math.abs(body.linearVelocity.x) * 1.5f
        }
        else {
            animationTime += delta
        }
    }

    fun compute(delta: Float) {
        playerControl()
        definePlayerState()
        computeAnimationTime(delta)
    }


    fun draw(delta: Float) {

        val sprite: Sprite
        var width = width
        var height = height

        if (playerState === PlayerStateEnum.IDLE) {
            sprite = idleAnimation.getKeyFrame(animationTime, true)
        } else if (playerState === PlayerStateEnum.RUN) {
            sprite = runAnimation.getKeyFrame(animationTime, true)
            width *= 1.4f
            height *= 1.1f
        } else {
            sprite = jumpAnimation.getKeyFrame(animationTime, false)
            width *= 1.4f
        }

        batch.draw(
                sprite.apply {
                    if (
                            (playerDirection == PlayerDirection.LEFT && isFlipX) ||
                            (playerDirection == PlayerDirection.RIGHT && !isFlipX)
                    ) {
                        flip(true, false)
                    }
                },
                body.position.x - ptm(width / 2),
                body.position.y - ptm(height / 2),
                ptm(width),
                ptm(height)
        )

    }

    override fun endContact(contact: Contact?) {}
    override fun beginContact(contact: Contact?) {}


    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {

        if(contact?.fixtureB?.filterData?.groupIndex == ObjectsFilter.Platform.toShort()) {
            val yA = contact?.fixtureA?.body?.position?.y
            val yB = contact?.fixtureB?.body?.position?.y

            if (yA == null || yB == null) {
                return
            }

            contact?.isEnabled = yA - ptm(height / 2) > yB
        }

    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {}

}