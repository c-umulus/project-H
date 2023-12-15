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

public class Mission5 extends JLabel{ // 
	
	private GameFrame gameframe;
	boolean op = false; // 쓰레드 시작과 종료
	protected int x;
	protected int y;
	protected PlayerPlane player;
	protected int width;
	protected int height;
	protected Image image;
	protected int back; // 누르면 1, 안누르면 0
    private final Object backLock = new Object(); // 왼쪽키 동기화를 위한 객체

	
	
	public Mission5 (PlayerPlane player, int x, int y, int width, int height) {
		this.player = player;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = new ImageIcon("images/mission5.png").getImage();
		
		missionDo();
	
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	
	public void missionDo() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				long startTime = System.currentTimeMillis() / 1000;
				while(!op) {
					long endTime = System.currentTimeMillis() / 1000;
					if(endTime - startTime >= 5) { // 5초가 지나면 미션성공
						missionSuccess();
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
        synchronized (backLock) {
            if (back == 1) { // 뒤로가기 방향키 입력시 미션 실패
                missionFail();
            }
        }
		
	}
	
	public void setBack(int back) { //back 입력 확인
        synchronized (backLock) {
            this.back = back;
        }
	}
	
	
	public void missionSuccess() { //미션 성공
		
		player.setWepponLevelUp(true); // 무기 업그레이드
		
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