package hr.fer.zemris.project.geometry.dash.model.drawables.environment;

import hr.fer.zemris.project.geometry.dash.model.drawables.Vector2D;
import hr.fer.zemris.project.geometry.dash.model.drawables.player.Player;
import javafx.scene.canvas.GraphicsContext;

public class Spike extends Obstacle {
    public Spike(int positionX, int positionY) {
        super(positionX, positionY);
    }
    
    
    @Override
    public boolean checkCollisions() {
        Vector2D obstacleUL = new Vector2D(getPositionX(), getPositionY());
        Vector2D obstacleDR = new Vector2D(getPositionX()+getWidth(), getPositionY()+getHeight());
        //Vector2D player = Player.getPosition(); //TODO Player.getPosition()
        Vector2D playerDL = new Vector2D(0, 0); //TODO fix position
        Vector2D playerDR = new Vector2D(0, 0); //TODO fix position

        if(this.contains(playerDR) || this.contains(playerDL)){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Vector2D p) {
        return false;
    }

	@Override
	public void drawImage(GraphicsContext graphics) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calucalateNewPosition(Vector2D oldPosition) {
		// TODO Auto-generated method stub
		
	}



}
