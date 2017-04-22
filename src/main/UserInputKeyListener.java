package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInputKeyListener implements KeyListener {

	private ShapePanel shapePanel;

	public UserInputKeyListener(ShapePanel sp) {
		this.shapePanel = sp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		case (KeyEvent.VK_ENTER):
			shapePanel.userInputResponse();
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
