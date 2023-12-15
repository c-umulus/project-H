package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ScorePanel extends JPanel implements screenSize {
	private GameFrame gameFrame;
    private int finalScore;
    private ImageIcon scoreIcon = new ImageIcon("images/SelectPlane1.png");
	private Image scoreImg = scoreIcon.getImage();
	
    public ScorePanel(GameFrame gameFrame, int score) {
        setLayout(null);
        this.finalScore = score;
        this.gameFrame = gameFrame;
		
		JButton btn1 = new JButton("메인 화면");
		JButton btn2 = new JButton("다시 시작");
		JButton btn3 = new JButton("게임 종료");
		
		btn1.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				gameFrame.playSound(new File("res/ch.wav"));
				gameFrame.change("gameTitle");
			}
		});
		btn2.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				gameFrame.playSound(new File("res/ch.wav"));
				gameFrame.change("selectAPL");
				gameFrame.isgame = true;
			}
		});
		btn3.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				gameFrame.playSound(new File("res/ch.wav"));
				System.exit(0);
			}
		});
		btn1.setBounds(360, 100, 120, 59);
		btn2.setBounds(120, 100, 120, 59);
		btn3.setBounds(240, 640, 120, 59);
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);
		
		init();
    }

    private void init() {
        setLayout(new BorderLayout());


        // 점수 표시 레이블
        JLabel scoreLabel = new JLabel("최종 점수: " + finalScore);
        scoreLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 40));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setForeground(Color.BLACK);

        add(scoreLabel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(scoreImg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, 196, 182, this);
		repaint();
	}
}
