/*******************************************************************************
*    Cristian Alexandrescu                                                     *
*    2163013577ba2bc237f22b3f4d006856                                          *
*    11a4bb2c77aca6a9927b85f259d9af10db791ce5cf884bb31e7f7a889d4fb385          *
*    bc9a53289baf23d369484f5343ed5d6c                                          *
*******************************************************************************/


import javax.microedition.lcdui.*;

public class CFont {

	public CFont(Font f) {
		InitFont(f);
	}

	private void InitFont(Font f) {
		Image img = Image.createImage(FONT.INIT_IMAGE_WIDTH, FONT.INIT_IMAGE_HEIGHT);
		int[] rgbData = new int[FONT.INIT_IMAGE_WIDTH * FONT.INIT_IMAGE_HEIGHT];

		Graphics g = img.getGraphics();
		g.setColor(0xFF000000);
		g.fillRect(0, 0, FONT.INIT_IMAGE_WIDTH, FONT.INIT_IMAGE_HEIGHT);

		g.setColor(0xFF0000FF);
		g.setFont(f);
		g.drawString("A", 0, 0, 0);

		img.getRGB(rgbData, 0, FONT.INIT_IMAGE_WIDTH, 0, 0, FONT.INIT_IMAGE_WIDTH, FONT.INIT_IMAGE_HEIGHT);

		int minY = FONT.INIT_IMAGE_HEIGHT, maxY = 0;
		int x;

		for (int y = 0; y < FONT.INIT_IMAGE_HEIGHT; y++) {
			for (x = 0; x < FONT.INIT_IMAGE_WIDTH; x++) {
				if (rgbData[y * FONT.INIT_IMAGE_WIDTH + x] != 0xFF000000) {
					x = -1;
					break;
				}
			}

			if (x == -1) {
				minY = y;
				break;
			}
		}

		for (int y = FONT.INIT_IMAGE_HEIGHT - 1; y >= 0; y--) {
			for (x = 0; x < FONT.INIT_IMAGE_WIDTH; x++) {
				if (rgbData[y * FONT.INIT_IMAGE_WIDTH + x] != 0xFF000000) {
					x = -1;
					break;
				}
			}

			if (x == -1) {
				maxY = y;
				break;
			}
		}

		m_FontHeight = maxY - minY + 1;
		m_FontOffsetY = minY;
		m_Font = f;
	}

	public void DrawStringLine(Graphics g, String s, int x, int y, int anchor) {
		g.setFont(m_Font);

		if ((anchor & Graphics.VCENTER) != 0) {
			y -= m_FontHeight / 2;
			anchor &= ~Graphics.VCENTER;
		} else if ((anchor & Graphics.BOTTOM) != 0) {
			y -= m_FontHeight;
			anchor &= ~Graphics.BOTTOM;
		}

		g.drawString(s, x, y - m_FontOffsetY, anchor | Graphics.TOP);
	}

	public void DrawMultilineString(Graphics g, String s, int x, int y, int iLineSpacing, int anchor) {
		String[] lines = CStringPak.GetStringLines(s);

		int h = (iLineSpacing + m_FontHeight) * (lines.length - 1) + m_FontHeight;

		if ((anchor & Graphics.VCENTER) != 0) {
			y -= h / 2;
			anchor &= ~Graphics.VCENTER;
		} else if ((anchor & Graphics.BOTTOM) != 0) {
			y -= h;
			anchor &= ~Graphics.BOTTOM;
		}

		for (int i = 0; i < lines.length; i++) {
			DrawStringLine(g, lines[i], x, y, anchor);
			y += iLineSpacing + m_FontHeight;
		}
	}

	public Font GetFont() {
		return m_Font;
	}

	public int GetFontHeight() {
		return m_FontHeight;
	}
	private Font m_Font;
	private int m_FontHeight;
	private int m_FontOffsetY;
}
