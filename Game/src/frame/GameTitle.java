package frame;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameTitle extends JPanel implements screenSize {
	
	private ImageIcon titleIcon = new ImageIcon("images/GameTitle.png");
	private Image titleImg = titleIcon.getImage();
	
	
	private GameFrame win;

	public GameTitle(GameFrame win) {
		setLayout(null);
		this.win = win;
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

	    g.drawImage(titleImg, x, y, scaledWidth, scaledHeight, this);
	}
}
