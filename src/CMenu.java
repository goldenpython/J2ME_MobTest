/*******************************************************************************
*    Cristian Alexandrescu                                                     *
*    2163013577ba2bc237f22b3f4d006856                                          *
*    11a4bb2c77aca6a9927b85f259d9af10db791ce5cf884bb31e7f7a889d4fb385          *
*    bc9a53289baf23d369484f5343ed5d6c                                          *
*******************************************************************************/


import javax.microedition.lcdui.*;

public class CMenu {

	public CMenu(String Title, CFont TitleFont, int TitleColor,
		String[] Options, int[] actions, CFont OptionsFont, int OptionsColor,
		int TitleOptionsSpacing, int OptionsOptionsSpacing, int TitleLinesSpacing,
		int SelectionRectColor, int SelectionRectWidth, int SelectionRectDY,
		int NumberOfVisibleItems,
		int MenuType) {

		m_Title = Title;
		m_TitleFont = TitleFont;
		m_TitleColor = TitleColor;

		m_Options = Options;
		m_Actions = actions;
		m_OptionsFont = OptionsFont;
		m_OptionsColor = OptionsColor;

		m_TitleOptionsSpacing = TitleOptionsSpacing;
		m_OptionsOptionsSpacing = OptionsOptionsSpacing;
		m_TitleLinesSpacing = TitleLinesSpacing;

		m_SelectionRectColor = SelectionRectColor;
		m_SelectionRectWidth = SelectionRectWidth;
		m_SelectionRectDY = SelectionRectDY;

		m_NumVisibleItems = Math.min(NumberOfVisibleItems, GetNumMenuOptions());

		m_MenuType = MenuType;

		m_SelectionIndex = 0;
	}

	public int GetNumMenuOptions() {
		return m_Options.length;
	}

	public int GetMenuHeight() {
		int NoTitleLines = cMain.GetStringLines(m_Title).length;

		switch (m_MenuType) {
			case MENU.MENU_TYPE.SIMPLE_LIST:
				if (NoTitleLines != 0) {
					return (NoTitleLines - 1) * (m_TitleLinesSpacing + m_TitleFont.GetFontHeight()) + m_TitleFont.GetFontHeight() +
						m_TitleOptionsSpacing +
						(GetNumMenuOptions() - 1) * (m_OptionsOptionsSpacing + m_OptionsFont.GetFontHeight()) + m_OptionsFont.GetFontHeight();
				} else {
					return (GetNumMenuOptions() - 1) * (m_OptionsOptionsSpacing + m_OptionsFont.GetFontHeight()) + m_OptionsFont.GetFontHeight();
				}

			case MENU.MENU_TYPE.VERTICAL_SCROLL_LIST:
				if (NoTitleLines != 0) {
					return (NoTitleLines - 1) * (m_TitleLinesSpacing + m_TitleFont.GetFontHeight()) + m_TitleFont.GetFontHeight() +
						m_TitleOptionsSpacing +
						(m_NumVisibleItems - 1) * (m_OptionsOptionsSpacing + m_OptionsFont.GetFontHeight()) + m_OptionsFont.GetFontHeight();
				} else {
					return (m_NumVisibleItems - 1) * (m_OptionsOptionsSpacing + m_OptionsFont.GetFontHeight()) + m_OptionsFont.GetFontHeight();
				}
		}

		return 0;
	}

	public void DrawMenu(Graphics g, int x, int y, int anchor) {
		switch (m_MenuType) {
			case MENU.MENU_TYPE.SIMPLE_LIST: {
				DrawSimpleListMenu(g, x, y, anchor);
				break;
			}

			case MENU.MENU_TYPE.VERTICAL_SCROLL_LIST: {
				DrawVerticalScrollListMenu(g, x, y, anchor);
				break;
			}
		}
	}

	public int DrawAndUpdateMenuKeys(Graphics g, int x, int y, int anchor) {
		DrawMenu(g, x, y, anchor);
		return UpdateMenuKeys();
	}

	public void DrawSimpleListMenu(Graphics g, int x, int y, int anchor) {
		String[] lines = null;
		if (m_Title != null) {
			lines = cMain.GetStringLines(m_Title);
		}

		int h = GetMenuHeight();
		if ((anchor & Graphics.VCENTER) != 0) {
			y -= h / 2;
			anchor &= ~Graphics.VCENTER;
		} else if ((anchor & Graphics.BOTTOM) != 0) {
			y -= h;
			anchor &= ~Graphics.BOTTOM;
		}
		anchor |= Graphics.TOP;

		if (lines != null) {
			g.setFont(m_TitleFont.GetFont());
			g.setColor(m_TitleColor);
			for (int i = 0; i < lines.length; i++, y += m_TitleLinesSpacing + m_TitleFont.GetFontHeight()) {
				m_TitleFont.DrawStringLine(g, lines[i], x, y, anchor);
			}

			y += -m_TitleLinesSpacing + m_TitleOptionsSpacing;
		}

		g.setFont(m_OptionsFont.GetFont());
		g.setColor(m_OptionsColor);
		int iNumOptions = GetNumMenuOptions();
		for (int i = 0; i < iNumOptions; i++, y += m_OptionsOptionsSpacing + m_OptionsFont.GetFontHeight()) {
			if (i == m_SelectionIndex) {
				g.setColor(m_SelectionRectColor);

				int xSelection = x;
				int w = m_SelectionRectWidth;
				if ((anchor & Graphics.HCENTER) != 0) {
					xSelection -= w / 2;
				} else if ((anchor & Graphics.RIGHT) != 0) {
					xSelection -= w;
				}

				g.drawRect(xSelection,
					y - m_SelectionRectDY,
					w,
					m_SelectionRectDY * 2 + m_OptionsFont.GetFontHeight());
				g.setColor(m_OptionsColor);
			}

			m_OptionsFont.DrawStringLine(g, m_Options[i], x, y, anchor);
		}
	}

	public void DrawVerticalScrollListMenu(Graphics g, int x, int y, int anchor) {
		int firstIndex = m_FirstVisibleItem;
		int lastIndex = Math.min(m_FirstVisibleItem + m_NumVisibleItems - 1, GetNumMenuOptions() - 1);
		int numVisibleItems = Math.min(m_NumVisibleItems, GetNumMenuOptions());

		String[] lines = null;
		if (m_Title != null) {
			lines = cMain.GetStringLines(m_Title);
		}

		int h = GetMenuHeight();
		if ((anchor & Graphics.VCENTER) != 0) {
			y -= h / 2;
			anchor &= ~Graphics.VCENTER;
		} else if ((anchor & Graphics.BOTTOM) != 0) {
			y -= h;
			anchor &= ~Graphics.BOTTOM;
		}
		anchor |= Graphics.TOP;

		if (lines != null) {
			g.setFont(m_TitleFont.GetFont());
			g.setColor(m_TitleColor);
			for (int i = 0; i < lines.length; i++, y += m_TitleLinesSpacing + m_TitleFont.GetFontHeight()) {
				m_TitleFont.DrawStringLine(g, lines[i], x, y, anchor);
			}

			y += -m_TitleLinesSpacing + m_TitleOptionsSpacing;
		}

		g.setFont(m_OptionsFont.GetFont());
		g.setColor(m_OptionsColor);
		int iNumOptions = numVisibleItems;
		for (int i = firstIndex; i <= lastIndex; i++, y += m_OptionsOptionsSpacing + m_OptionsFont.GetFontHeight()) {
			if (i == m_SelectionIndex) {
				g.setColor(m_SelectionRectColor);

				int xSelection = x;
				int w = m_SelectionRectWidth;
				if ((anchor & Graphics.HCENTER) != 0) {
					xSelection -= w / 2;
				} else if ((anchor & Graphics.RIGHT) != 0) {
					xSelection -= w;
				}

				g.drawRect(xSelection,
					y - m_SelectionRectDY,
					w,
					m_SelectionRectDY * 2 + m_OptionsFont.GetFontHeight());
				g.setColor(m_OptionsColor);
			}

			m_OptionsFont.DrawStringLine(g, m_Options[i], x, y, anchor);
		}
	}

	public int UpdateMenuKeys() {
		switch (m_MenuType) {
			case MENU.MENU_TYPE.SIMPLE_LIST:
				return UpdateSimpleListMenuKeys();

			case MENU.MENU_TYPE.VERTICAL_SCROLL_LIST:
				return UpdateVerticalScrollMenuKeys();
		}

		return MENU.NO_SELECTION;
	}

	public int UpdateSimpleListMenuKeys() {
		if (cMain.KeyPressed(KEY.NUMERIC8 | KEY.DOWN)) {
			m_SelectionIndex = (m_SelectionIndex + 1) % m_Options.length;
		} else if (cMain.KeyPressed(KEY.NUMERIC2 | KEY.UP)) {
			m_SelectionIndex = (m_SelectionIndex - 1 + m_Options.length) % m_Options.length;
		} else if (cMain.KeyPressed(KEY.NUMERIC5 | KEY.SELECT)) {
			return m_Actions[m_SelectionIndex];
		}

		return MENU.NO_SELECTION;
	}

	public int UpdateVerticalScrollMenuKeys() {
		if (cMain.KeyPressed(KEY.NUMERIC8 | KEY.DOWN)) {
			m_SelectionIndex++;

			if (m_SelectionIndex >= GetNumMenuOptions()) {
				m_SelectionIndex = 0;
				m_FirstVisibleItem = 0;
			} else {
				if (m_SelectionIndex >= m_FirstVisibleItem + m_NumVisibleItems) {
					m_FirstVisibleItem++;
				}
			}
		} else if (cMain.KeyPressed(KEY.NUMERIC2 | KEY.UP)) {
			m_SelectionIndex--;

			if (m_SelectionIndex < 0) {
				m_SelectionIndex = GetNumMenuOptions() - 1;
				m_FirstVisibleItem = GetNumMenuOptions() - m_NumVisibleItems;
			} else {
				if (m_SelectionIndex < m_FirstVisibleItem) {
					m_FirstVisibleItem--;
				}
			}
		} else if (cMain.KeyPressed(KEY.NUMERIC5 | KEY.SELECT)) {
			return m_Actions[m_SelectionIndex];
		}

		return MENU.NO_SELECTION;
	}
	
	private String m_Title;
	private CFont m_TitleFont;
	private int m_TitleColor;
	private String[] m_Options;
	private int[] m_Actions;
	private CFont m_OptionsFont;
	private int m_OptionsColor;
	private int m_SelectionRectColor;
	private int m_SelectionRectWidth;
	private int m_SelectionRectDY;
	private int m_TitleOptionsSpacing,  m_OptionsOptionsSpacing,  m_TitleLinesSpacing;
	private int m_NumVisibleItems;
	private int m_FirstVisibleItem;
	private int m_MenuType;
	private int m_SelectionIndex;
}
