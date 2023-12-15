package frame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import objects.PlayerPlane;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class GameFrame extends JFrame implements screenSize {

	private GameFrame gameFrame = this;
	public boolean isgame; // 게임실행 여부
	public GamePanel gamePanel; // 인게임 패널 이거 잘 봐야된다. 오류 !!
	public GameTitle gameTitle; // 타이틀 인트로 패널
	public SelectAPI selectAPI; // 선택 패널
	public PlayerPlane player; // 플레이어 선언
	private Player jlPlayer;

	public GameFrame() {
		init();
		setting();
		listener();

		setVisible(true);
	}

	public void init() {
		player = new PlayerPlane(this, "PLANE_TYPE");
		change("gameTitle"); // 초기 타이틀 화면
		isgame = false; // 게임 중 이지 않은 상태
	}

	public void setting() {
		setTitle("Shooting Star");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void showScorePanel(int finalScore) {
	    ScorePanel scorePanel = new ScorePanel(this, finalScore);
	    getContentPane().removeAll();
	    getContentPane().add(scorePanel);
	    revalidate();
	    repaint();
	}
	
	// 패널 바꾸기 함수
	public void change(String panelName) {
		if (panelName.equals("gameTitle")) {
			bgplay();
			gameTitle = new GameTitle(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(gameTitle);
			revalidate();
			repaint();
		} else if (panelName.equals("selectAPL")) {
			bgstop();
			slbgplay();
			selectAPI = new SelectAPI(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(selectAPI);
			revalidate();
			repaint();
		} else if (panelName.equals("gameMap")) {
			bgstop();
			mainbgplay();
			gamePanel = new GamePanel(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(gamePanel);
			revalidate();
			repaint();
		} else {
			gameTitle = null;
			selectAPI = null;
			gamePanel = null;
			isgame = false;
			getContentPane().removeAll();
			revalidate();
			repaint();
		}
	}

	public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					player.setWepponLevelUp(true);
					break;
				case KeyEvent.VK_ENTER:
					change("selectAPL");
					break;
				case KeyEvent.VK_SPACE:
					player.setAttack(true);
					break;
				case KeyEvent.VK_UP:
					player.setUp(true);
					break;
				case KeyEvent.VK_DOWN:
					player.setDown(true);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(true);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(true);
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					player.setWepponLevelUp(false);
					break;
				case KeyEvent.VK_SPACE:
					player.setAttack(false);
					break;
				case KeyEvent.VK_UP:
					player.setUp(false);
					break;
				case KeyEvent.VK_DOWN:
					player.setDown(false);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				}
			}
		});
	}
	
	public void mainbgplay() { // 배경음악 재생
		try {
			FileInputStream fileInputStream = new FileInputStream("res/mainbgm.mp3");
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			jlPlayer = new Player(bufferedInputStream);
			new Thread() {
				public void run() {
					try {
						jlPlayer.play();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}.start();
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	public void slbgplay() { // 배경음악 재생
		try {
			FileInputStream fileInputStream = new FileInputStream("res/slbgm.mp3");
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			jlPlayer = new Player(bufferedInputStream);
			new Thread() {
				public void run() {
					try {
						jlPlayer.play();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}.start();
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	public void bgplay() { // 배경음악 재생
		try {
			FileInputStream fileInputStream = new FileInputStream("res/bgm.mp3");
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			jlPlayer = new Player(bufferedInputStream);
			new Thread() {
				public void run() {
					try {
						jlPlayer.play();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}.start();
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	public void bgstop() { // 배경음악 종료
		if (jlPlayer != null && !jlPlayer.isComplete()) {
            jlPlayer.close();
        }
	}
	
	public void playSound(File file) {
	    try {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(file));
	        clip.start();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
