package pl.edu.wat.tim.gorky.screens;

import pl.edu.wat.tim.gorky.model.World;
import pl.edu.wat.tim.gorky.view.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class FightScreen implements Screen, InputProcessor {
	
	private World world;
	private WorldRenderer renderer;
	
	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render(float arg0) {
		// --- UPDATE ---
		
		// --- DRAW ---
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render();
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}

	@Override
	public void resume() {
		
	}
	
	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("MOUSE CLICK TEST");
		System.out.println("screenX = " + screenX);
		System.out.println("screenY = " + screenY);
		System.out.println("pointer = " + pointer);
		System.out.println("button = " + button);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

}
