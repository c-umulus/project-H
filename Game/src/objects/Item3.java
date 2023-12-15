package objects;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

// 생명 아이템

public class Item3 extends Item{
	private Item3 item2 = this;
	private static final String TAG = "Item1 : ";


	public Item3 (PlayerPlane player, int x, int y, int w, int h) {
		this.player = player;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.image = new ImageIcon("images/item3.png").getImage();
		this.islife = true;

		this.move();
		this.crush();

	}

	public void move() {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (islife) {
					try {
						Thread.sleep(5);
						
						movedown();

						if (y > 900) {
							System.out.println("item3 쓰레드 종료");
							islife = false;
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		t2.setName("item3 Move");
		t2.start();
	}

	public void crush() { // 비행기와 아이템 충돌시

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					if (Math.abs((player.getX() + player.getWidth() / 2) - (x + player.getWidth() / 2)) < (width / 2
							+ player.getWidth() / 2)
							&& Math.abs((player.getY() + player.getHeight() / 2) - (y + height / 2)) < (height / 2
									+ player.getHeight() / 2)) {
						collision = true;
					} else {
						collision = false;
					}

					try {
						if (collision) {
							setY(900); // 아이템 객체 맵 밖으로 던지기
							player.setLife(player.getLife()+1);
						}
						Thread.sleep(10);

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}).start();

	}


	public void itemUpdate(Graphics g) {
		itemDraw(g);
	}

	public void itemDraw(Graphics g) { // 그림그리기
		g.drawImage(image, x, y, width, height, null);
		
	}

}
