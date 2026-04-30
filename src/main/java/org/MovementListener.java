package org;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementListener implements KeyListener {

    private Player player;

    public MovementListener(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setDirection(Player.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setDirection(Player.LEFT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.setDirection(Player.HOLD);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
