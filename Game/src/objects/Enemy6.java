package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy6 extends EnemyUnit {

	private Enemy6 enemy6 = this;
	private static final String TAG = "Enemy6 : ";

	ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy6(PlayerPlane player, int x, int y, int w, int h) {
		this.player = player;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.image = new ImageIcon("images/enemy6_2.png").getImage();
		this.life = 3;
		this.crushCheck = false;
		this.islife = true;

		this.player.contextAdd(enemy6);

		this.move();
		this.crush();

	}

	public void move() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				count = 0;
				while (islife) {
					try {
						Thread.sleep(5);

						if (y > 50) {
							moveup();
							moveleft();
						}

						bulletCreate();
						count++;

						if (y > 900) {
							// System.out.println("enemy6 쓰레드 종료");
							islife = false;
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
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
							explosePlayer(player, enemy6); // 충돌 폭발 메서드
						}
						Thread.sleep(10);

						if (crushCheck) {
							explosePlayer(enemy6);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}).start();

	}

	private void bulletCreate() {
		if (count % 100 == 0) {
			enemyAttack = new EnemyAttack(enemy6, player, x + 20, y + 40, 270, 2, 50, 50);
			enemyAttackkList.add(enemyAttack);
			enemyAttack = new EnemyAttack(enemy6, player, x + 140, y + 40, 270, 2, 50, 50);
			enemyAttackkList.add(enemyAttack);

		}
	}

	public void enemyUpdate(Graphics g) {
		enemyDraw(g);
	}

	public void enemyDraw(Graphics g) { // 그림그리기
		g.drawImage(image, x, y, width, height, null);
		for (int i = 0; i < enemyAttackkList.size(); i++) {
			enemyAttack = enemyAttackkList.get(i);
			g.drawImage(enemyAttack.bulletImg5, enemyAttack.getX(), enemyAttack.getY(), enemyAttack.getWidth(),
					enemyAttack.getHeight(), null);

		}
	}

}
