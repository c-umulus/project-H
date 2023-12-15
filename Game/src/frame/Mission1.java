package frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import objects.EnemyUnit;
import objects.PlayerPlane;

public class Mission1 extends JLabel{ // 10초 이내에 적기 5체 파괴
	
	boolean op = false; 
	protected int x;
	protected int y;
	protected PlayerPlane player;
	protected int width;
	protected int height;
	protected Image image;
	public static ArrayList<EnemyUnit> destroyedEnemyUnitList = new ArrayList<EnemyUnit>(); // 파괴한 적 담는 배열

	
	
	public Mission1 (PlayerPlane player, int x, int y, int width, int height) {
		this.player = player;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = new ImageIcon("images/mission1.png").getImage();
		player.contextMission(this);
		
		missionDo();
	
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public static synchronized void setDestroyedEnemyUnitList(EnemyUnit enemyUnit) {
		destroyedEnemyUnitList.add(enemyUnit);

	}
	
	public void missionDo() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				long startTime = System.currentTimeMillis() / 1000;
				while(!op) {
					long endTime = System.currentTimeMillis() / 1000;
					if(endTime - startTime >= 10) { // 10초가 지나면 미션실패
						missionFail();
						System.out.println("미션 실패");
					}
					else {
						missionCheck();
					} 
				}
			}
			
		});
		
		t1.start();
	}
	
	public void missionCheck() { // 미션 성공,실패 확인
        synchronized (destroyedEnemyUnitList) {
            if (destroyedEnemyUnitList.size() >= 5) {
                missionSuccess();
            }
        }    
	}
	
	
	public void missionSuccess() { //미션 성공

		player.bonusScore(500); // 보너스 점수 500점 추가
		
		try {
			ImageIcon failIcon = new ImageIcon("images/mission_success.png");
			this.image = failIcon.getImage();	
			repaint();
			
			Thread.sleep(1000);
			setY(900);
			this.op = true;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void missionFail() { // 미션 실패
		try {
			ImageIcon failIcon = new ImageIcon("images/mission_fail.png");
			this.image = failIcon.getImage();	
			repaint();
			
			Thread.sleep(1000);
			setY(900);
			this.op = true;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void missionDraw(Graphics g) { // 그림그리기
		g.drawImage(image, x, y, width, height, null);
		
	} 
	

}