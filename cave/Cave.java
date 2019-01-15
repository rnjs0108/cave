package com.yongyong.cave;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.yongyong.objects.GameData;
import com.yongyong.objects.SavedGameData;
import com.yongyong.screens.IntroScreen;

public class Cave extends Game implements ApplicationListener {
    private SavedGameData player;
	public GameData gameData;
	private IActivityRequestHandler myRequestHandler;
    public static final int V_WIDTH = 800;
    public static final int V_HEIGHT = 480;
	//로그 상수
	public static java.lang.String LOG = Cave.class.getSimpleName();
	// 매 초마다 현재 FPS를 로깅하는 클래스
	private FPSLogger fpsLogger;
	//개발모드 구분을 위한 상수
	public static boolean DEV_MODE = false;
    public static final java.lang.String TITLE = "Cave", VERSION = "1.0.0.6 BETA";

	public Cave(IActivityRequestHandler handler, GameData gameData) {
		myRequestHandler = handler;
		this.gameData = gameData;
	}

    @Override
	public void create () {
		Gdx.app.log(Cave.LOG, "create()");
		this.fpsLogger = new FPSLogger();
		this.player = new SavedGameData(gameData);
		setScreen(new IntroScreen(this,player));
	}

	@Override
	public void render () {
		super.render();
		if (DEV_MODE) { this.fpsLogger.log(); }
}

	@Override
	public void pause() {
		super.pause();
		Gdx.app.log(Cave.LOG, "pause()");
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log(Cave.LOG, "resume()");
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log(Cave.LOG, "resize() :" + width + "*" + height);
	}

	@Override
	public void setScreen(Screen screen) {
		if(this.getScreen() != null)
		this.getScreen().dispose();
		super.setScreen(screen);

	}

	@Override
	public Screen getScreen() {
		return super.getScreen();
	}

	public void showAD(boolean ab){
		myRequestHandler.showAds(ab);
	}
	public void showReward(boolean ab){
		myRequestHandler.showReward(ab);
	}
	public void showForward(boolean ab){
		myRequestHandler.showForward(ab);
	}

}
