package objects;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

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
		this.life = 10;
		this.islife = true;

		if (x < 100) {
			this.move();
			this.crush();

		} else {
			this.move2();
			this.crush();
		}

	}

	public void move() {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (islife) {
					try {
						Thread.sleep(5);

						if (y > 100) {
							y++; // 속도는 여기서 조절하면 됨
							moveup();
							moveright();
						}

						if (y > 900) {
							System.out.println("item1 쓰레드 종료");
							islife = false;
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

	public void move2() {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (islife) {
					try {
						Thread.sleep(5);

						y--;

						if (y > 100) {
							movedown();
							moveleft();
						}

						if (y < -300) {
							System.out.println("item1 쓰레드 종료");
							islife =false; //break 말고 이게 더 보기 좋은 거 같음.
						}


					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		t2.setName("enemy2Move");
		t2.start();
	}

	public void crush() { // 적비행기-Player 충돌

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (!player.getInvincible() && y < 900 && y > -300) {

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
							explosePlayer(player, item1); // 충돌 폭발 메서드
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

//	private void enemyAttack() { //이제 안쓰는 메서드
//		for (int i = 0; i < enemyAttackkList.size(); i++) {
//			enemyAttack = enemyAttackkList.get(i);
////			if (enemy2.life > 0)  //발사는 여기서 구현하면 곤란
////				enemyAttack.fire();
//		}
//	}

	public void itemDraw(Graphics g) { // 그림그리기
		g.drawImage(image, x, y, width, height, null);
		
	}

}
