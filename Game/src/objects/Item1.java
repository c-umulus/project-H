package objects;

import java.awt.Graphics;

import java.util.ArrayList;

import javax.swing.ImageIcon;

// 궁극기 아이템

public class Item1 extends Item{
	private Item1 item1 = this;
	private static final String TAG = "Item1 : ";


	public Item1(PlayerPlane player, int x, int y, int w, int h) {
		this.player = player;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.image = new ImageIcon("images/item1.png").getImage();
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
							System.out.println("item1 쓰레드 종료");
							islife =false;
						}


					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		t2.setName("item1 Move");
		t2.start();
	}

	public void crush() { // 적비행기-Player 충돌

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
							// 궁극기 사용
							setY(900);
							player.useUltimate();
							

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






