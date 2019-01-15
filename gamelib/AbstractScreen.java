package com.yongyong.gamelib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.yongyong.cave.Cave;
import com.yongyong.objects.SavedGameData;

/**
 * Created by USER on 2017-03-26.
 */

public abstract class AbstractScreen implements Screen {
    protected final Cave game;
    protected BitmapFont mFont;
    protected Sprite fadeScreen;
    protected final SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport gamePort;
    protected SavedGameData player;
    protected Stage stage;

    public float fadeSpeed = 0.025f;
    public float screenDelay = 0.75f;
    public boolean fadeOn = true;
    public boolean fadeOut = false;

    public AbstractScreen(Cave game, SavedGameData player) {
        this.game = game;
        this.mFont = new BitmapFont();
        this.batch = new SpriteBatch();
        this.player = player;
        camera = new OrthographicCamera();
        this.gamePort = new FitViewport(game.V_WIDTH, game.V_HEIGHT, camera);
        stage = new Stage(gamePort,batch);
        fadeScreen = new Sprite(new Texture("gfx/background/blackScreen.png"));
        fadeScreen.getColor().a = 1f;
    } @Override
    public void show() {
        Gdx.app.log(Cave.LOG, "show() : " + getName());
        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2,0);
        camera.setToOrtho(false,game.V_WIDTH, game.V_HEIGHT);
        batch.setProjectionMatrix(camera.combined);
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void resize(int width, int height) {
        Gdx.app.log(Cave.LOG, "resize() : " + getName() + " " + width + "*" + height);
        gamePort.update(width, height);
    }
    @Override public void render(float delta) {
        // 아래의 코드는 화면을 RGB(검은색)으로 초기화 합니다.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
    }
    public void update(){
        /*
        boolean gyroscopeAvail = Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);
        if(gyroscopeAvail){
            float gyroX = Gdx.input.getGyroscopeX();
            float gyroY = Gdx.input.getGyroscopeY();
            float gyroZ = Gdx.input.getGyroscopeZ();
        }
        */
        if(fadeOn == true){
            if(fadeOut == true){//이거 먼저
                if(fadeScreen.getColor().a >= 0.995f) {
                    Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                        @Override
                        public void run() {
                            fadeOut = false;
                        }
                    }, 0.2f);
                }
                else {
                    if(fadeScreen.getColor().a + fadeSpeed >= 1f)
                        fadeScreen.setAlpha(1f);
                    else
                        fadeScreen.setAlpha(fadeScreen.getColor().a + fadeSpeed);
                }
            }
            else{
                if(fadeScreen.getColor().a <= 0) {
                    fadeOn = false;
                    fadeOut = true;
                }
                else {
                    if(fadeScreen.getColor().a - fadeSpeed <= 0)
                        fadeScreen.setAlpha(0);
                    else
                        fadeScreen.setAlpha(fadeScreen.getColor().a - fadeSpeed);
                }
            }
        }
    }
    @Override
    public void hide() {
        Gdx.app.log(Cave.LOG, "hide() : " + getName());
        stage.act();
    }
    @Override
    public void pause() {
        Gdx.app.log(Cave.LOG, "pause() : " + getName());
    }
    @Override
    public void resume() {
        Gdx.app.log(Cave.LOG, "resume() : " + getName());
        stage.act();
    }
    @Override
    public void dispose() {
        Gdx.app.log(Cave.LOG, "dispose() : " + getName());
        mFont.dispose();
        stage.getBatch().dispose();
        fadeScreen = null;
    }
    /** * 해당 클래스 이름 스트링으로 반환하는 함수 */
    protected String getName() { return getClass().getSimpleName();}
}
