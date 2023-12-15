package frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objects.PlayerPlane;

public class SelectAPI extends JPanel implements screenSize{

	private GameFrame gameFrame;
	private SelectAPI selectAPI = this;
	private ImageIcon player1Icon, player2Icon, player3Icon, player4Icon, player5Icon; // 플레이어 기체 이미지
	private ImageIcon bigPlayer1icon, bigPlayer2icon, bigPlayer3icon, bigPlayer4icon, bigPlayer5icon; // 버튼 누를시 커지는 이미지
	private ImageIcon selectPlaneIcon = new ImageIcon("images/SelectPlane1.png");
	private Image selectPlaneImg = selectPlaneIcon.getImage();
	

	public SelectAPI(GameFrame gameFrame) {
		
		setLayout(null);
		
		this.gameFrame = gameFrame;

		player1Icon = new ImageIcon("images/PlayerPlane1.png");
		player2Icon = new ImageIcon("images/PlayerPlane2.png");
		player3Icon = new ImageIcon("images/PlayerPlane3.png");
		player4Icon = new ImageIcon("images/PlayerPlane4.png");
		player5Icon = new ImageIcon("images/PlayerPlane5.png");

		bigPlayer1icon = new ImageIcon("images/BigPlane1.png");
		bigPlayer2icon = new ImageIcon("images/BigPlane2.png");
		bigPlayer3icon = new ImageIcon("images/BigPlane3.png");
		bigPlayer4icon = new ImageIcon("images/BigPlane4.png");
		bigPlayer5icon = new ImageIcon("images/BigPlane5.png");

		JButton btn1 = new JButton("", player1Icon);
		JButton btn2 = new JButton("", player2Icon);
		JButton btn3 = new JButton("", player3Icon);
		JButton btn4 = new JButton("", player4Icon);
		JButton btn5 = new JButton("", player5Icon);
		JLabel planeImg = new JLabel("");

		// 버튼 테두리 없음
		btn1.setBorderPainted(false);
		btn2.setBorderPainted(false);
		btn3.setBorderPainted(false);
		btn4.setBorderPainted(false);
		btn5.setBorderPainted(false);

		// 버튼 채우기 없음
		btn1.setContentAreaFilled(false);
		btn2.setContentAreaFilled(false);
		btn3.setContentAreaFilled(false);
		btn4.setContentAreaFilled(false);
		btn5.setContentAreaFilled(false);

		// 버튼 투명
		btn1.setOpaque(false);
		btn2.setOpaque(false);
		btn3.setOpaque(false);
		btn4.setOpaque(false);
		btn5.setOpaque(false);

		// 버튼 액션
		btn1.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				gameFrame.change("gameMap");
				batch("playerPlane");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
	            btn1.setSize(100, 95);
	            btn1.setIcon(bigPlayer1icon);
	         }

			@Override
			public void mouseExited(MouseEvent e) {
				planeImg.setIcon(null);
				btn1.setSize(85, 80);
				btn1.setIcon(player1Icon);
			}
		});
		
		btn2.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				gameFrame.change("gameMap");
				batch("playerPlane2");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
	            btn2.setSize(100, 89);
	            btn2.setIcon(bigPlayer2icon);
	         }

			@Override
			public void mouseExited(MouseEvent e) {
				planeImg.setIcon(null);
				btn2.setSize(95, 80);
				btn2.setIcon(player2Icon);
			}
		});

		btn3.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				gameFrame.change("gameMap");
				batch("playerPlane3");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
	            btn3.setSize(100, 89);
	            btn3.setIcon(bigPlayer3icon);
	         }

			@Override
			public void mouseExited(MouseEvent e) {
				planeImg.setIcon(null);
				btn3.setSize(85, 80);
				btn3.setIcon(player3Icon);
			}
		});
		
		btn4.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				gameFrame.change("gameMap");
				batch("playerPlane4");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
	            btn4.setSize(100, 89);
	            btn4.setIcon(bigPlayer4icon);
	         }

			@Override
			public void mouseExited(MouseEvent e) {
				planeImg.setIcon(null);
				btn4.setSize(95, 80);
				btn4.setIcon(player4Icon);
			}
		});
		
		btn5.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				gameFrame.change("gameMap");
				batch("playerPlane5");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
	            btn5.setSize(100, 89);
	            btn5.setIcon(bigPlayer5icon);
	         }

			@Override
			public void mouseExited(MouseEvent e) {
				planeImg.setIcon(null);
				btn5.setSize(85, 80);
				btn5.setIcon(player5Icon);
			}
		});

		btn1.setBounds(170, 400, 85, 80);
		btn2.setBounds(340, 410, 95, 80);
		btn3.setBounds(100, 640, 85, 80);
		btn4.setBounds(250, 640, 95, 80);
		btn5.setBounds(410, 640, 85, 80);
		
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);
		this.add(btn4);
		this.add(btn5);
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    int imgWidth = 620;
	    int imgHeight = 820;

	    // 이미지의 비율을 유지하면서 화면에 맞추기
	    int panelWidth = getWidth();
	    int panelHeight = getHeight();

	    double widthRatio = (double) panelWidth / imgWidth;
	    double heightRatio = (double) panelHeight / imgHeight;

	    double scale = Math.min(widthRatio, heightRatio);

	    int scaledWidth = (int) (imgWidth * scale);
	    int scaledHeight = (int) (imgHeight * scale);

	    int x = (panelWidth - scaledWidth) / 2;
	    int y = (panelHeight - scaledHeight) / 2;

	    g.drawImage(selectPlaneImg, x, y, scaledWidth, scaledHeight, this);
	}
	
	
	public void batch(String playerPlane) { // 비행기 선택 후 비행기 new add
		if (playerPlane == "playerPlane") {
			gameFrame.player = new PlayerPlane(gameFrame,"PLANE1");
			gameFrame.gamePanel.add(gameFrame.player);
		} else if (playerPlane == "playerPlane2") {
			gameFrame.player = new PlayerPlane(gameFrame,"PLANE2");
			gameFrame.gamePanel.add(gameFrame.player);
		} else if (playerPlane == "playerPlane3") {
			gameFrame.player = new PlayerPlane(gameFrame, "PLANE3");
			gameFrame.gamePanel.add(gameFrame.player);
		} else if (playerPlane == "playerPlane4") {
			gameFrame.player = new PlayerPlane(gameFrame, "PLANE4");
			gameFrame.gamePanel.add(gameFrame.player);
		} else if (playerPlane == "playerPlane5") {
			gameFrame.player = new PlayerPlane(gameFrame, "PLANE5");
			gameFrame.gamePanel.add(gameFrame.player);
		}
	}
	


}
