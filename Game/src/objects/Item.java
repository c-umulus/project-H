package objects;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public abstract class Item extends JLabel{
	
	protected int x;
	protected int y;
	protected PlayerPlane player;
	protected int width;
	protected int height;
	protected Image image;
	protected boolean collision = false;// 플레이어와 적기몸체가 충돌시 체크
	protected boolean islife; // 스레드 생명
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public PlayerPlane getPlayer() {
		return player;
	}

	public void setPlayer(PlayerPlane player) {
		this.player = player;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}


	public void movedown() {
		y++;
		setLocation(x, y);
	}

	public void moveleft() {
		x--;
		setLocation(x, y); // repaint()

	}

	public void moveup() {
		y--;
		setLocation(x, y); // repaint()

	}

	public void moveright() {
		x++;
		setLocation(x, y); // repaint()

	}

	public abstract void move();

	public abstract void itemUpdate(Graphics g);
	
	public void explosePlayer(PlayerPlane player, Item item) { // 충돌후 이미지 변경 및 목숨카운트

		try {
			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
			player.setIcon(explosionIcon);
			item.image = explosionIcon.getImage();
			System.out.println("아이템 획득");
			Thread.sleep(1000);

			item.y = 1000; // 맵 바깥으로 적 던짐
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
