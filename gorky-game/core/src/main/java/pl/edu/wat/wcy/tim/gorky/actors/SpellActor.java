package pl.edu.wat.wcy.tim.gorky.actors;

public class SpellActor extends AnimatedActor {
	
	public SpellActor() {}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(animation.isAnimationFinished(stateTime)) {
			this.remove();
		}
	}
}
