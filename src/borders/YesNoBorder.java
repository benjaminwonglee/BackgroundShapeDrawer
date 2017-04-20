package borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class YesNoBorder implements Border {
	private String yesOrNo;

	public YesNoBorder(String yesOrNo) {
		this.yesOrNo = yesOrNo;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int wd, int ht) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, wd, ht);
		System.out.println("Cleared");
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.BOLD, 32));
		g.drawString(yesOrNo, wd / 2 - (g.getFontMetrics().stringWidth(yesOrNo) / 2), ht / 2 + 14);
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

	@Override
	public Insets getBorderInsets(Component arg0) {
		return new Insets(0, 0, 0, 0);
	}

	public String getText() {
		return yesOrNo;
	}

	public void setText(String yesOrNo) {
		this.yesOrNo = yesOrNo;
	}
}
