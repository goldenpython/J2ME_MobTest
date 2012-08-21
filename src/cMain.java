/*******************************************************************************
*    Cristian Alexandrescu                                                     *
*    2163013577ba2bc237f22b3f4d006856                                          *
*    11a4bb2c77aca6a9927b85f259d9af10db791ce5cf884bb31e7f7a889d4fb385          *
*    bc9a53289baf23d369484f5343ed5d6c                                          *
*******************************************************************************/


import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.game.*;

public class cMain extends Canvas implements Runnable {

	public cMain(MIDlet midlet) {
		m_midlet = midlet;

		int[] params = {1000};
		ChangeState(STATE.FIRST.ID, params);

		m_sTimer = 0;

		new Thread(this).start();
	}
	static int frame = 0;

	public void run() {
		while (true) {
			Thread.yield();

			UpdateState();

			repaint();
			serviceRepaints();

			if (m_bLimitFPS) {
				try {
					Thread.sleep(50);
				} catch (Exception e) {
				}
			}

			frame++;
			ProcessKeys();
		}
	}

	public void start() {
		Display.getDisplay(cMIDlet._MIDlet).setCurrent(this);
		setFullScreenMode(true);
	}

	synchronized public void keyPressed(int keyCode) {
		int code = keyCodeToKeyBit(keyCode);
		m_KeyPressed |= code;

		if (CONFIG.TRACE_KEYS) {
			System.out.println("keypressed " + code + " " + frame);
		}
	}

	synchronized public void keyReleased(int keyCode) {
		int code = keyCodeToKeyBit(keyCode);

		m_KeyReleased |= code;

		if (CONFIG.TRACE_KEYS) {
			System.out.println("keyreleased " + code + " " + frame);
		}
	}

	synchronized public void keyRepeated(int keyCode) {
	//int code =  keyCodeToKeyBit(keyCode);

	//m_KeyPressed |= code;

	//System.out.println("keyrepeated " + code + " " + frame);
	}

	private static int keyCodeToKeyBit(int keyCode) {
		switch (keyCode) {
			case Canvas.KEY_NUM0:
				return KEY.NUMERIC0;

			case Canvas.KEY_NUM1:
				return KEY.NUMERIC1;

			case Canvas.KEY_NUM2:
				return KEY.NUMERIC2;

			case Canvas.KEY_NUM3:
				return KEY.NUMERIC3;

			case Canvas.KEY_NUM4:
				return KEY.NUMERIC4;

			case Canvas.KEY_NUM5:
				return KEY.NUMERIC5;

			case Canvas.KEY_NUM6:
				return KEY.NUMERIC6;

			case Canvas.KEY_NUM7:
				return KEY.NUMERIC7;

			case Canvas.KEY_NUM8:
				return KEY.NUMERIC8;

			case Canvas.KEY_NUM9:
				return KEY.NUMERIC9;

			case Canvas.KEY_POUND:
				return KEY.POUND;

			case Canvas.KEY_STAR:
				return KEY.STAR;
		}

		return 0;
	}

	public static boolean KeyPressed(int key) {
		return (m_KeyActiveSingle & key) != 0;
	}

	public static boolean KeyPressedRepetition(int key) {
		return (m_KeyActiveRepeat & key) != 0;
	}

	public static void ProcessKeys() {
		m_KeyActiveSingle = m_KeyPressed & ~m_KeyReleased;

		m_KeyActiveRepeat &= ~m_KeyReleased;
		m_KeyActiveRepeat |= m_KeyPressed;

		if (CONFIG.TRACE_KEYS) {
			System.out.println("keys active " + m_KeyActiveSingle + " " + frame + " " + m_KeyReleased);
		}

		m_KeyPressed = 0;
		m_KeyReleased = 0;
	}

	synchronized public void paint(Graphics g) {
		//UpdateState();

		PaintState(g);

		m_sTimer++;
		m_sStateTimer++;

		if (CONFIG.SHOW_FPS) {
			long time = System.currentTimeMillis();
			m_sTimePaintSum += time - m_sTimeLastPaint;
			m_sTimeLastPaint = System.currentTimeMillis();

			if (m_sTimer % CONFIG.FPS_INTERVAL == 1) {
				//m_sFPS = "" + (10000 * CONFIG.FPS_INTERVAL / (m_sTimePaintSum + 1));
				m_sFPS = GetFractionalString(1000 * CONFIG.FPS_INTERVAL, m_sTimePaintSum + 1, 3);
				m_sTimePaintSum = 0;
			}

			g.setColor(0x00FF00);
			if (m_FontSmall == null) {
				g.drawString(m_sFPS, 1, 1, 0);
			} else {
				m_FontSmall.DrawStringLine(g, m_sFPS, 1, 1, Graphics.LEFT | Graphics.TOP);
			}
		}
	}

	public static void UpdateState() {
		switch (m_sStateID) {
			case STATE.FIRST.ID:
				UpdateStateFirst();
				break;

			case STATE.INIT.ID:
				UpdateStateInit();
				break;

			case STATE.MAIN_MENU.ID:
				UpdateStateMainMenu();
				break;

			case STATE.DISPLAY_SIZE.ID:
				UpdateStateDisplaySize();
				break;

			case STATE.CPU.ID:
				UpdateStateCPU();
				break;

			case STATE.ARITHMETIC.ID:
				UpdateStateArithmetic();
				break;

			case STATE.ADD.ID:
				UpdateStateAdd();
				break;

			case STATE.SUB.ID:
				UpdateStateSub();
				break;

			case STATE.MUL.ID:
				UpdateStateMul();
				break;

			case STATE.DIV.ID:
				UpdateStateDiv();
				break;

			case STATE.DISPLAY_RESULT.ID:
				UpdateStateDisplayResult();
				break;

			case STATE.LOGICAL.ID:
				UpdateStateLogical();
				break;

			case STATE.SHIFT_LEFT.ID:
				UpdateStateShiftL();
				break;

			case STATE.SHIFT_RIGHT.ID:
				UpdateStateShiftR();
				break;

			case STATE.AND.ID:
				UpdateStateAND();
				break;

			case STATE.OR.ID:
				UpdateStateOR();
				break;

			case STATE.XOR.ID:
				UpdateStateXOR();
				break;

			case STATE.PRIME_NUMBERS.ID:
				UpdateStatePrimeNumbers();
				break;

			case STATE.RECURSIVE.ID:
				UpdateStateRecursive();
				break;

			case STATE.SORTING.ID:
				UpdateStateSorting();
				break;

			case STATE.BUBBLE_SORT.ID:
				UpdateStateBubbleSort();
				break;

			case STATE.Q_SORT.ID:
				UpdateStateQSort();
				break;

			case STATE.MERGE_SORT.ID:
				UpdateStateMergeSort();
				break;

			case STATE.HEAP_SORT.ID:
				UpdateStateHeapSort();
				break;

			case STATE.MEMORY.ID:
				UpdateStateMemory();
				break;

			case STATE.VIDEO.ID:
				UpdateStateVideo();
				break;

			case STATE.ALLOCATE_IMAGES_MENU.ID:
				UpdateStateAllocateImagesMenu();
				break;

			case STATE.ALLOCATE_IMAGES.ID:
				UpdateStateAllocateImages();
				break;

			case STATE.FILL_RECT_TEST.ID:
				UpdateStateFillRectTest();
				break;

			case STATE.DRAW_RECT_TEST.ID:
				UpdateStateDrawRectTest();
				break;

			case STATE.DRAW_LINE_TEST.ID:
				UpdateStateDrawLineTest();
				break;

			case STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.ID:
				UpdateStateGeometricPrimitivesTestMenu();
				break;

			case STATE.IMAGE_TESTS_MENU.ID:
				UpdateStateImageTestsMenu();
				break;

			case STATE.TEST_IMAGE_SIZES.ID:
				UpdateStateTestImagesSize();
				break;

			case STATE.DRAW_IMAGE_TEST.ID:
				UpdateStateDrawImageTest();
				break;

			case STATE.TEST_IMAGE_TRANSFORM.ID:
				UpdateStateTestImageTransform();
				break;

			case STATE.DRAW_REGION_TEST.ID:
				UpdateStateDrawRegionTest();
				break;

			case STATE.FILL_TRIANGLE_TEST.ID:
				UpdateStateFillTriangleTest();
				break;

			case STATE.DRAW_ARC_TEST.ID:
				UpdateStateDrawArcTest();
				break;

			case STATE.FILL_ARC_TEST.ID:
				UpdateStateFillArcTest();
				break;
		}
	}

	public void PaintState(Graphics g) {
		switch (m_sStateID) {
			case STATE.FIRST.ID:
				PaintStateFirst(g);
				break;

			case STATE.INIT.ID:
				PaintStateInit(g);
				break;

			case STATE.MAIN_MENU.ID:
				PaintStateMainMenu(g);
				break;

			case STATE.DISPLAY_SIZE.ID:
				PaintStateDisplaySize(g);
				break;

			case STATE.CPU.ID:
				PaintStateCPU(g);
				break;

			case STATE.ARITHMETIC.ID:
				PaintStateArithmetic(g);
				break;

			case STATE.ADD.ID:
				PaintStateAdd(g);
				break;

			case STATE.SUB.ID:
				PaintStateSub(g);
				break;

			case STATE.MUL.ID:
				PaintStateMul(g);
				break;

			case STATE.DIV.ID:
				PaintStateDiv(g);
				break;

			case STATE.DISPLAY_RESULT.ID:
				PaintStateDisplayResult(g);
				break;

			case STATE.LOGICAL.ID:
				PaintStateLogical(g);
				break;

			case STATE.SHIFT_LEFT.ID:
				PaintStateShiftL(g);
				break;

			case STATE.SHIFT_RIGHT.ID:
				PaintStateShiftR(g);
				break;

			case STATE.AND.ID:
				PaintStateAND(g);
				break;

			case STATE.OR.ID:
				PaintStateOR(g);
				break;

			case STATE.XOR.ID:
				PaintStateXOR(g);
				break;

			case STATE.PRIME_NUMBERS.ID:
				PaintStatePrimeNumbers(g);
				break;

			case STATE.RECURSIVE.ID:
				PaintStateRecursive(g);
				break;

			case STATE.SORTING.ID:
				PaintStateSorting(g);
				break;

			case STATE.BUBBLE_SORT.ID:
				PaintStateQSort(g);
				break;

			case STATE.Q_SORT.ID:
				PaintStateQSort(g);
				break;

			case STATE.MERGE_SORT.ID:
				PaintStateMergeSort(g);
				break;

			case STATE.HEAP_SORT.ID:
				PaintStateHeapSort(g);
				break;

			case STATE.MEMORY.ID:
				PaintStateMemory(g);
				break;

			case STATE.VIDEO.ID:
				PaintStateVideo(g);
				break;

			case STATE.ALLOCATE_IMAGES_MENU.ID:
				PaintStateAllocateImagesMenu(g);
				break;

			case STATE.ALLOCATE_IMAGES.ID:
				PaintStateAllocateImages(g);
				break;

			case STATE.FILL_RECT_TEST.ID:
				PaintStateFillRectTest(g);
				break;

			case STATE.DRAW_RECT_TEST.ID:
				PaintStateDrawRectTest(g);
				break;

			case STATE.DRAW_LINE_TEST.ID:
				PaintStateDrawLineTest(g);
				break;

			case STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.ID:
				PaintStateGeometricPrimitivesTestMenu(g);
				break;

			case STATE.IMAGE_TESTS_MENU.ID:
				PaintStateImageTestsMenu(g);
				break;

			case STATE.TEST_IMAGE_SIZES.ID:
				PaintStateTestImagesSize(g);
				break;

			case STATE.DRAW_IMAGE_TEST.ID:
				PaintStateDrawImageTest(g);
				break;

			case STATE.TEST_IMAGE_TRANSFORM.ID:
				PaintStateTestImageTransform(g);
				break;

			case STATE.DRAW_REGION_TEST.ID:
				PaintStateDrawRegionTest(g);
				break;

			case STATE.FILL_TRIANGLE_TEST.ID:
				PaintStateFillTriangleTest(g);
				break;

			case STATE.DRAW_ARC_TEST.ID:
				PaintStateDrawArcTest(g);
				break;

			case STATE.FILL_ARC_TEST.ID:
				PaintStateFillArcTest(g);
				break;
		}
	}

	public static void UpdateStateFirst() {
		if (KeyPressed(KEY.NUMERIC0)) {
			sss += 1;
		} else if (KeyPressed(KEY.NUMERIC1)) {
			sss -= 1;
		}

		if (System.currentTimeMillis() - m_sStateTime >= m_sStateParams[STATE.FIRST.NO_MILLIS_IN_STATE]) {
			ChangeState(STATE.INIT.ID);
		}
	}

	public static void PaintStateFirst(Graphics g) {
		g.setColor(0x00FF00);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	public static void UpdateStateInit() {
		switch (m_sStateTimer) {
			case 0: {
				Font f = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
				m_FontSmall = new CFont(f);

				break;
			}

			case 1: {
				Font f = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
				m_FontLarge = new CFont(f);

				break;
			}

			case 2: {
				ResPak = new CResPak("/ResPack0.bin");
				ResPak.Open();
				break;
			}

			case 3: {
				//LoadStrings(ReadResPackSection(ResPack0.STRINGS_PACK));
				StringPak = new CStringPak();
				StringPak.LoadStrings(ResPak.ReadSection(ResPack0.STRINGS_PACK));
				break;
			}

			case 4: {
				byte b[] = ResPak.ReadSection(ResPack0.IMAGE_PACK);
				m_img1 = Image.createImage(b, 0, b.length);

				break;
			}

			case 5: {
				ResPak.Close();
				break;
			}

			case 6: {
				InitMenus();
				break;
			}

			default:
				ChangeState(STATE.MAIN_MENU.ID);
				break;
		}
	}

	public static void PaintStateInit(Graphics g) {
		g.setClip(0, 0, WIDTH, HEIGHT);
		g.setColor(STATE.INIT.BACKGROUND_COLOR);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, 7,
			STATE.INIT.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.INIT.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.INIT.PROGRESS_BAR_X,
			STATE.INIT.PROGRESS_BAR_H,
			STATE.INIT.PROGRESS_BAR_BORDER_COLOR,
			STATE.INIT.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateMainMenu() {
		int selection = m_MenuMain.UpdateMenuKeys();
		switch (selection) {
			case MENU.MAIN.OPT_DIPLAY_SIZE:
				ChangeState(STATE.DISPLAY_SIZE.ID);
				break;

			case MENU.MAIN.OPT_VIDEO:
				ChangeState(STATE.VIDEO.ID);
				break;

			case MENU.MAIN.OPT_CPU:
				ChangeState(STATE.CPU.ID);
				break;

			case MENU.MAIN.OPT_MEMORY:
				ChangeState(STATE.MEMORY.ID);
				break;

			case MENU.MAIN.OPT_EXIT:
				m_midlet.notifyDestroyed();
				break;
		}
	}

	public static void PaintStateMainMenu(Graphics g) {
		g.setColor(0x000000);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		m_MenuMain.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);

	/* g.setColor(0x0000FF);
	g.drawLine(0, 100, 200, 100);
	g.drawLine(100, 0, 100, 100);
	g.drawLine(150, 0, 150, 100);
	//g.drawString("textTEXT", 100, 100, Graphics.TOP |  Graphics.HCENTER);
	SetCurrentFont(g, FONT.FONT_SMALL);
	DrawMultilineString(g, "123\nEu merg\nTEXT", 100, 100, 9, Graphics.BOTTOM | Graphics.LEFT);
	DrawMultilineString(g, "123\nEu merg\nTEXT", 80, 100, 9, Graphics.BOTTOM | Graphics.RIGHT);
	SetCurrentFont(g, FONT.FONT_LARGE);
	DrawMultilineString(g, "123\nEu merg\nTEX", 40, 100, 9, Graphics.VCENTER | Graphics.RIGHT);
	DrawMultilineString(g, "123\nEu merg\nTEX", 150, 100, 9, Graphics.HCENTER | Graphics.VCENTER);
	DrawStringLine(g, GetString(0), 0, 0, Graphics.LEFT | Graphics.TOP);
	DrawStringLine(g, GetString(1), 0, 0, Graphics.LEFT | Graphics.TOP);
	DrawStringLine(g, GetString(2), 0, 0, Graphics.LEFT | Graphics.TOP);                */
	}

	public static void UpdateStateCPU() {
		int selection = m_MenuCPU.UpdateMenuKeys();
		switch (selection) {
			case MENU.CPU.OPT_ARITHMETIC:
				ChangeState(STATE.ARITHMETIC.ID);
				break;

			case MENU.CPU.OPT_LOGICAL:
				ChangeState(STATE.LOGICAL.ID);
				break;

			case MENU.CPU.OPT_SORTING:
				ChangeState(STATE.SORTING.ID);
				break;

			case MENU.CPU.OPT_PRIME_NUMBERS:
				ChangeState(STATE.PRIME_NUMBERS.ID);
				break;

			case MENU.CPU.OPT_RECURSIVE:
				ChangeState(STATE.RECURSIVE.ID);
				break;

			case MENU.CPU.OPT_BACK:
				ChangeState(STATE.MAIN_MENU.ID);
				break;
		}
	}

	public static void PaintStateCPU(Graphics g) {
		g.setColor(STATE.CPU.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		m_MenuCPU.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateArithmetic() {
		int selection = m_MenuArithmetic.UpdateMenuKeys();
		switch (selection) {
			case MENU.ARITHMETIC.OPT_ADD:
				ChangeState(STATE.ADD.ID);
				break;

			case MENU.ARITHMETIC.OPT_SUBSTRACTION:
				ChangeState(STATE.SUB.ID);
				break;

			case MENU.ARITHMETIC.OPT_MULTIPLY:
				ChangeState(STATE.MUL.ID);
				break;

			case MENU.ARITHMETIC.OPT_DIVIDE:
				ChangeState(STATE.DIV.ID);
				break;

			case MENU.ARITHMETIC.OPT_BACK:
				ChangeState(STATE.CPU.ID);
				break;
		}
	}

	public static void PaintStateLogical(Graphics g) {
		g.setColor(STATE.LOGICAL.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		m_MenuLogical.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateLogical() {
		int selection = m_MenuLogical.UpdateMenuKeys();
		switch (selection) {
			case MENU.LOGICAL.OPT_SHIFT_LEFT:
				ChangeState(STATE.SHIFT_LEFT.ID);
				break;

			case MENU.LOGICAL.OPT_SHIFT_RIGHT:
				ChangeState(STATE.SHIFT_RIGHT.ID);
				break;

			case MENU.LOGICAL.OPT_AND:
				ChangeState(STATE.AND.ID);
				break;

			case MENU.LOGICAL.OPT_OR:
				ChangeState(STATE.OR.ID);
				break;

			case MENU.LOGICAL.OPT_XOR:
				ChangeState(STATE.XOR.ID);
				break;

			case MENU.LOGICAL.OPT_BACK:
				ChangeState(STATE.CPU.ID);
				break;
		}
	}

	public static void PaintStateArithmetic(Graphics g) {
		g.setColor(STATE.ARITHMETIC.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		m_MenuArithmetic.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateAdd() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else if (m_sStateTimer >= 1 && m_sStateTimer <= STATE.ADD.NUM_FRAMES) {
			StartTimer();
			AddBenchmark();
			StopTimer();
		} else {
			int params[] = {STATE.ARITHMETIC.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
		}
	}

	public static void PaintStateAdd(Graphics g) {
		g.setColor(STATE.ADD.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, STATE.ADD.NUM_FRAMES,
			STATE.ADD.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.ADD.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.ADD.PROGRESS_BAR_X,
			STATE.ADD.PROGRESS_BAR_H,
			STATE.ADD.PROGRESS_BAR_BORDER_COLOR,
			STATE.ADD.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateSub() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else if (m_sStateTimer >= 1 && m_sStateTimer <= STATE.SUB.NUM_FRAMES) {
			StartTimer();
			SubBenchmark();
			StopTimer();
		} else {
			int params[] = {STATE.ARITHMETIC.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
		}
	}

	public static void PaintStateSub(Graphics g) {
		g.setColor(STATE.SUB.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, STATE.SUB.NUM_FRAMES,
			STATE.SUB.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.SUB.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.SUB.PROGRESS_BAR_X,
			STATE.SUB.PROGRESS_BAR_H,
			STATE.SUB.PROGRESS_BAR_BORDER_COLOR,
			STATE.SUB.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateMul() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else if (m_sStateTimer >= 1 && m_sStateTimer <= STATE.MUL.NUM_FRAMES) {
			StartTimer();
			MulBenchmark();
			StopTimer();
		} else {
			int params[] = {STATE.ARITHMETIC.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
		}
	}

	public static void PaintStateMul(Graphics g) {
		g.setColor(STATE.MUL.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, STATE.MUL.NUM_FRAMES,
			STATE.MUL.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.MUL.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.MUL.PROGRESS_BAR_X,
			STATE.MUL.PROGRESS_BAR_H,
			STATE.MUL.PROGRESS_BAR_BORDER_COLOR,
			STATE.MUL.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateDiv() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else if (m_sStateTimer >= 1 && m_sStateTimer <= STATE.DIV.NUM_FRAMES) {
			StartTimer();
			DivBenchmark();
			StopTimer();
		} else {
			int params[] = {STATE.ARITHMETIC.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
		}
	}

	public static void PaintStateDiv(Graphics g) {
		g.setColor(STATE.DIV.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, STATE.DIV.NUM_FRAMES,
			STATE.DIV.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.DIV.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.DIV.PROGRESS_BAR_X,
			STATE.DIV.PROGRESS_BAR_H,
			STATE.DIV.PROGRESS_BAR_BORDER_COLOR,
			STATE.DIV.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateShiftL() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else if (m_sStateTimer >= 1 && m_sStateTimer <= STATE.SHIFT_LEFT.NUM_FRAMES) {
			StartTimer();
			ShiftLBenchmark();
			StopTimer();
		} else {
			int params[] = {STATE.LOGICAL.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
		}
	}

	public static void PaintStateShiftL(Graphics g) {
		g.setColor(STATE.SHIFT_LEFT.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, STATE.SHIFT_LEFT.NUM_FRAMES,
			STATE.SHIFT_LEFT.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.SHIFT_LEFT.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.SHIFT_LEFT.PROGRESS_BAR_X,
			STATE.SHIFT_LEFT.PROGRESS_BAR_H,
			STATE.SHIFT_LEFT.PROGRESS_BAR_BORDER_COLOR,
			STATE.SHIFT_LEFT.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateShiftR() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else if (m_sStateTimer >= 1 && m_sStateTimer <= STATE.SHIFT_RIGHT.NUM_FRAMES) {
			StartTimer();
			ShiftRBenchmark();
			StopTimer();
		} else {
			int params[] = {STATE.LOGICAL.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
		}
	}

	public static void PaintStateShiftR(Graphics g) {
		g.setColor(STATE.SHIFT_RIGHT.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, STATE.SHIFT_RIGHT.NUM_FRAMES,
			STATE.SHIFT_RIGHT.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.SHIFT_RIGHT.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.SHIFT_RIGHT.PROGRESS_BAR_X,
			STATE.SHIFT_RIGHT.PROGRESS_BAR_H,
			STATE.SHIFT_RIGHT.PROGRESS_BAR_BORDER_COLOR,
			STATE.SHIFT_RIGHT.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateAND() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else if (m_sStateTimer >= 1 && m_sStateTimer <= STATE.AND.NUM_FRAMES) {
			StartTimer();
			ANDBenchmark();
			StopTimer();
		} else {
			int params[] = {STATE.LOGICAL.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
		}
	}

	public static void PaintStateAND(Graphics g) {
		g.setColor(STATE.AND.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, STATE.AND.NUM_FRAMES,
			STATE.AND.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.AND.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.AND.PROGRESS_BAR_X,
			STATE.AND.PROGRESS_BAR_H,
			STATE.AND.PROGRESS_BAR_BORDER_COLOR,
			STATE.AND.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateOR() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else if (m_sStateTimer >= 1 && m_sStateTimer <= STATE.OR.NUM_FRAMES) {
			StartTimer();
			ORBenchmark();
			StopTimer();
		} else {
			int params[] = {STATE.LOGICAL.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
		}
	}

	public static void PaintStateOR(Graphics g) {
		g.setColor(STATE.OR.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, STATE.OR.NUM_FRAMES,
			STATE.OR.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.OR.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.OR.PROGRESS_BAR_X,
			STATE.OR.PROGRESS_BAR_H,
			STATE.OR.PROGRESS_BAR_BORDER_COLOR,
			STATE.OR.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateXOR() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else if (m_sStateTimer >= 1 && m_sStateTimer <= STATE.XOR.NUM_FRAMES) {
			StartTimer();
			XORBenchmark();
			StopTimer();
		} else {
			int params[] = {STATE.LOGICAL.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
		}
	}

	public static void PaintStateXOR(Graphics g) {
		g.setColor(STATE.XOR.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		DrawProgressBar(g, m_sStateTimer, STATE.XOR.NUM_FRAMES,
			STATE.XOR.PROGRESS_BAR_X,
			cMain.HEIGHT - STATE.XOR.PROGRESS_BAR_BOTTOM_Y_DIFF,
			cMain.WIDTH - 2 * STATE.XOR.PROGRESS_BAR_X,
			STATE.XOR.PROGRESS_BAR_H,
			STATE.XOR.PROGRESS_BAR_BORDER_COLOR,
			STATE.XOR.PROGRESS_BAR_FILL_COLOR);
	}

	public static void UpdateStateDisplayResult() {
		if (KeyPressed(KEY.STAR)) {
			ChangeState(m_sStateParams[0]);
		}
	}

	public static void PaintStateDisplayResult(Graphics g) {
		g.setColor(STATE.DISPLAY_RESULT.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.DISPLAY_RESULT.TEXT_COLOR);
		m_FontSmall.DrawMultilineString(g, m_DisplayResultString, WIDTH / 2, HEIGHT / 2, 5, Graphics.VCENTER | Graphics.HCENTER);

		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), 0, HEIGHT, Graphics.LEFT | Graphics.BOTTOM);
	}

	public static int AddBenchmark() {
		int[] vector = new int[15];

		for (int i = 0; i < vector.length; i++) {
			vector[i] = 19 * i + 7;
		}

		int a = 0;

		for (int loop = 0; loop < STATE.ADD.NUM_ITERATIONS; loop++) {
			a += vector[10] + vector[0];
			a += vector[11] + vector[1];
			a += vector[12] + vector[2];
			a += vector[13] + vector[3];
			a += vector[14] + vector[4];
			a += vector[9] + vector[5];
			a += vector[8] + vector[6];
			a += vector[7] + vector[7];
			a += vector[9] + vector[8];
			a += vector[11] + vector[9];
			a += vector[11] + vector[10];
			a += vector[12] + vector[11];
			a += vector[13] + vector[12];
			a += vector[14] + vector[13];
			a += vector[14] + vector[14];
			a += vector[4] + vector[3];
			a += vector[8] + vector[4];
			a += vector[7] + vector[5];
			a += vector[10] + vector[6];
			a += vector[14] + vector[7];
			a += vector[9] + vector[8];
			a += vector[14] + vector[9];
			a += vector[12] + vector[10];
			a += vector[14] + vector[11];
			a += vector[13] + vector[12];
			a += vector[14] + vector[14];
			a += vector[14] + vector[13];
			a += vector[13] + vector[12];
			a += vector[14] + vector[13];
			a += vector[12] + vector[3];

			vector[loop % vector.length] += 7;
		}

		return a;
	}

	public static int SubBenchmark() {
		int[] vector = new int[15];

		for (int i = 0; i < vector.length; i++) {
			vector[i] = 19 * i + 7;
		}

		int a = 0;

		for (int loop = 0; loop < STATE.ADD.NUM_ITERATIONS; loop++) {
			a -= vector[10] - vector[0];
			a -= vector[11] - vector[1];
			a -= vector[12] - vector[2];
			a -= vector[13] - vector[3];
			a -= vector[14] - vector[4];
			a -= vector[9] - vector[5];
			a -= vector[8] - vector[6];
			a -= vector[7] - vector[7];
			a -= vector[9] - vector[8];
			a -= vector[11] - vector[9];
			a -= vector[11] - vector[10];
			a -= vector[12] - vector[11];
			a -= vector[13] - vector[12];
			a -= vector[14] - vector[13];
			a -= vector[14] - vector[14];
			a -= vector[4] - vector[3];
			a -= vector[8] - vector[4];
			a -= vector[7] - vector[5];
			a -= vector[10] - vector[6];
			a -= vector[14] - vector[7];
			a -= vector[9] - vector[8];
			a -= vector[14] - vector[9];
			a -= vector[12] - vector[10];
			a -= vector[14] - vector[11];
			a -= vector[13] - vector[12];
			a -= vector[14] - vector[14];
			a -= vector[14] - vector[13];
			a -= vector[13] - vector[12];
			a -= vector[14] - vector[13];
			a -= vector[12] - vector[3];

			vector[loop % vector.length] += 7;
		}

		return a;
	}

	public static int MulBenchmark() {
		int[] vector = new int[15];

		for (int i = 0; i < vector.length; i++) {
			vector[i] = 19 * i + 7;
		}

		int a = 0;

		for (int loop = 0; loop < STATE.ADD.NUM_ITERATIONS; loop++) {
			a *= vector[10] * vector[0];
			a *= vector[11] * vector[1];
			a *= vector[12] * vector[2];
			a *= vector[13] * vector[3];
			a *= vector[14] * vector[4];
			a *= vector[9] * vector[5];
			a *= vector[8] * vector[6];
			a *= vector[7] * vector[7];
			a *= vector[9] * vector[8];
			a *= vector[11] * vector[9];
			a *= vector[11] * vector[10];
			a *= vector[12] * vector[11];
			a *= vector[13] * vector[12];
			a *= vector[14] * vector[13];
			a *= vector[14] * vector[14];
			a *= vector[4] * vector[3];
			a *= vector[8] * vector[4];
			a *= vector[7] * vector[5];
			a *= vector[10] * vector[6];
			a *= vector[14] * vector[7];
			a *= vector[9] * vector[8];
			a *= vector[14] * vector[9];
			a *= vector[12] * vector[10];
			a *= vector[14] * vector[11];
			a *= vector[13] * vector[12];
			a *= vector[14] * vector[14];
			a *= vector[14] * vector[13];
			a *= vector[13] * vector[12];
			a *= vector[14] * vector[13];
			a *= vector[12] * vector[3];

			vector[loop % vector.length] += 7;
		}

		return a;
	}

	public static int DivBenchmark() {
		int[] vector = new int[15];

		for (int i = 0; i < vector.length; i++) {
			vector[i] = 19 * i + 7;
		}

		int a = 0;

		for (int loop = 0; loop < STATE.ADD.NUM_ITERATIONS; loop++) {
			a /= vector[10] / vector[0];
			a /= vector[11] / vector[1];
			a /= vector[12] / vector[2];
			a /= vector[13] / vector[3];
			a /= vector[14] / vector[4];
			a /= vector[9] / vector[5];
			a /= vector[8] / vector[6];
			a /= vector[7] / vector[7];
			a /= vector[9] / vector[8];
			a /= vector[11] / vector[9];
			a /= vector[11] / vector[10];
			a /= vector[12] / vector[11];
			a /= vector[13] / vector[12];
			a /= vector[14] / vector[13];
			a /= vector[14] / vector[14];
			a /= vector[4] / vector[3];
			a /= vector[8] / vector[4];
			a /= vector[7] / vector[5];
			a /= vector[10] / vector[6];
			a /= vector[14] / vector[7];
			a /= vector[9] / vector[8];
			a /= vector[14] / vector[9];
			a /= vector[12] / vector[10];
			a /= vector[14] / vector[11];
			a /= vector[13] / vector[12];
			a /= vector[14] / vector[14];
			a /= vector[14] / vector[13];
			a /= vector[13] / vector[12];
			a /= vector[14] / vector[13];
			a /= vector[12] / vector[3];

			vector[loop % vector.length] += 7;
		}

		return a;
	}

	public static int ShiftLBenchmark() {
		int[] vector = new int[15];

		for (int i = 0; i < vector.length; i++) {
			vector[i] = i + 1;
		}

		int a = 0;

		for (int loop = 0; loop < STATE.SHIFT_LEFT.NUM_ITERATIONS; loop++) {
			a <<= vector[10] << vector[0];
			a <<= vector[11] << vector[1];
			a <<= vector[12] << vector[2];
			a <<= vector[13] << vector[3];
			a <<= vector[14] << vector[4];
			a <<= vector[9] << vector[5];
			a <<= vector[8] << vector[6];
			a <<= vector[7] << vector[7];
			a <<= vector[9] << vector[8];
			a <<= vector[11] << vector[9];
			a <<= vector[11] << vector[10];
			a <<= vector[12] << vector[11];
			a <<= vector[13] << vector[12];
			a <<= vector[14] << vector[13];
			a <<= vector[14] << vector[14];
			a <<= vector[4] << vector[3];
			a <<= vector[8] << vector[4];
			a <<= vector[7] << vector[5];
			a <<= vector[10] << vector[6];
			a <<= vector[14] << vector[7];
			a <<= vector[9] << vector[8];
			a <<= vector[14] << vector[9];
			a <<= vector[12] << vector[10];
			a <<= vector[14] << vector[11];
			a <<= vector[13] << vector[12];
			a <<= vector[14] << vector[14];
			a <<= vector[14] << vector[13];
			a <<= vector[13] << vector[12];
			a <<= vector[14] << vector[13];
			a <<= vector[12] << vector[3];

			vector[loop % vector.length] += 7;
		}

		return a;
	}

	public static int ShiftRBenchmark() {
		int[] vector = new int[15];

		for (int i = 0; i < vector.length; i++) {
			vector[i] = i + 1;
		}

		int a = 0;

		for (int loop = 0; loop < STATE.SHIFT_RIGHT.NUM_ITERATIONS; loop++) {
			a >>= vector[10] >> vector[0];
			a >>= vector[11] >> vector[1];
			a >>= vector[12] >> vector[2];
			a >>= vector[13] >> vector[3];
			a >>= vector[14] >> vector[4];
			a >>= vector[9] >> vector[5];
			a >>= vector[8] >> vector[6];
			a >>= vector[7] >> vector[7];
			a >>= vector[9] >> vector[8];
			a >>= vector[11] >> vector[9];
			a >>= vector[11] >> vector[10];
			a >>= vector[12] >> vector[11];
			a >>= vector[13] >> vector[12];
			a >>= vector[14] >> vector[13];
			a >>= vector[14] >> vector[14];
			a >>= vector[4] >> vector[3];
			a >>= vector[8] >> vector[4];
			a >>= vector[7] >> vector[5];
			a >>= vector[10] >> vector[6];
			a >>= vector[14] >> vector[7];
			a >>= vector[9] >> vector[8];
			a >>= vector[14] >> vector[9];
			a >>= vector[12] >> vector[10];
			a >>= vector[14] >> vector[11];
			a >>= vector[13] >> vector[12];
			a >>= vector[14] >> vector[14];
			a >>= vector[14] >> vector[13];
			a >>= vector[13] >> vector[12];
			a >>= vector[14] >> vector[13];
			a >>= vector[12] >> vector[3];

			vector[loop % vector.length] += 7;
		}

		return a;
	}

	public static int ANDBenchmark() {
		int[] vector = new int[15];

		for (int i = 0; i < vector.length; i++) {
			vector[i] = 19 * i + 7;
		}

		int a = 0;

		for (int loop = 0; loop < STATE.AND.NUM_ITERATIONS; loop++) {
			a &= vector[10] & vector[0];
			a &= vector[11] & vector[1];
			a &= vector[12] & vector[2];
			a &= vector[13] & vector[3];
			a &= vector[14] & vector[4];
			a &= vector[9] & vector[5];
			a &= vector[8] & vector[6];
			a &= vector[7] & vector[7];
			a &= vector[9] & vector[8];
			a &= vector[11] & vector[9];
			a &= vector[11] & vector[10];
			a &= vector[12] & vector[11];
			a &= vector[13] & vector[12];
			a &= vector[14] & vector[13];
			a &= vector[14] & vector[14];
			a &= vector[4] & vector[3];
			a &= vector[8] & vector[4];
			a &= vector[7] & vector[5];
			a &= vector[10] & vector[6];
			a &= vector[14] & vector[7];
			a &= vector[9] & vector[8];
			a &= vector[14] & vector[9];
			a &= vector[12] & vector[10];
			a &= vector[14] & vector[11];
			a &= vector[13] & vector[12];
			a &= vector[14] & vector[14];
			a &= vector[14] & vector[13];
			a &= vector[13] & vector[12];
			a &= vector[14] & vector[13];
			a &= vector[12] & vector[3];

			vector[loop % vector.length] += 7;
		}

		return a;
	}

	public static int ORBenchmark() {
		int[] vector = new int[15];

		for (int i = 0; i < vector.length; i++) {
			vector[i] = 19 * i + 7;
		}

		int a = 0;

		for (int loop = 0; loop < STATE.OR.NUM_ITERATIONS; loop++) {
			a |= vector[10] | vector[0];
			a |= vector[11] | vector[1];
			a |= vector[12] | vector[2];
			a |= vector[13] | vector[3];
			a |= vector[14] | vector[4];
			a |= vector[9] | vector[5];
			a |= vector[8] | vector[6];
			a |= vector[7] | vector[7];
			a |= vector[9] | vector[8];
			a |= vector[11] | vector[9];
			a |= vector[11] | vector[10];
			a |= vector[12] | vector[11];
			a |= vector[13] | vector[12];
			a |= vector[14] | vector[13];
			a |= vector[14] | vector[14];
			a |= vector[4] | vector[3];
			a |= vector[8] | vector[4];
			a |= vector[7] | vector[5];
			a |= vector[10] | vector[6];
			a |= vector[14] | vector[7];
			a |= vector[9] | vector[8];
			a |= vector[14] | vector[9];
			a |= vector[12] | vector[10];
			a |= vector[14] | vector[11];
			a |= vector[13] | vector[12];
			a |= vector[14] | vector[14];
			a |= vector[14] | vector[13];
			a |= vector[13] | vector[12];
			a |= vector[14] | vector[13];
			a |= vector[12] | vector[3];

			vector[loop % vector.length] += 7;
		}

		return a;
	}

	public static int XORBenchmark() {
		int[] vector = new int[15];

		for (int i = 0; i < vector.length; i++) {
			vector[i] = 19 * i + 7;
		}

		int a = 0;

		for (int loop = 0; loop < STATE.XOR.NUM_ITERATIONS; loop++) {
			a ^= vector[10] ^ vector[0];
			a ^= vector[11] ^ vector[1];
			a ^= vector[12] ^ vector[2];
			a ^= vector[13] ^ vector[3];
			a ^= vector[14] ^ vector[4];
			a ^= vector[9] ^ vector[5];
			a ^= vector[8] ^ vector[6];
			a ^= vector[7] ^ vector[7];
			a ^= vector[9] ^ vector[8];
			a ^= vector[11] ^ vector[9];
			a ^= vector[11] ^ vector[10];
			a ^= vector[12] ^ vector[11];
			a ^= vector[13] ^ vector[12];
			a ^= vector[14] ^ vector[13];
			a ^= vector[14] ^ vector[14];
			a ^= vector[4] ^ vector[3];
			a ^= vector[8] ^ vector[4];
			a ^= vector[7] ^ vector[5];
			a ^= vector[10] ^ vector[6];
			a ^= vector[14] ^ vector[7];
			a ^= vector[9] ^ vector[8];
			a ^= vector[14] ^ vector[9];
			a ^= vector[12] ^ vector[10];
			a ^= vector[14] ^ vector[11];
			a ^= vector[13] ^ vector[12];
			a ^= vector[14] ^ vector[14];
			a ^= vector[14] ^ vector[13];
			a ^= vector[13] ^ vector[12];
			a ^= vector[14] ^ vector[13];
			a ^= vector[12] ^ vector[3];

			vector[loop % vector.length] += 7;
		}

		return a;
	}

	public static void UpdateStateDisplaySize() {
		if (m_sStateTimer == 0) {
			m_DisplaySizeW = WIDTH;
			m_DisplaySizeH = HEIGHT;
		}

		if (KeyPressedRepetition(KEY.NUMERIC6 | KEY.RIGHT)) {
			m_DisplaySizeW++;
		}

		if (KeyPressedRepetition(KEY.NUMERIC8 | KEY.DOWN)) {
			m_DisplaySizeH++;
		}

		if (KeyPressedRepetition(KEY.NUMERIC4 | KEY.LEFT)) {
			m_DisplaySizeW--;
		}

		if (KeyPressedRepetition(KEY.NUMERIC2 | KEY.UP)) {
			m_DisplaySizeH--;
		}

		if (KeyPressed(KEY.NUMERIC5)) {
			ChangeState(STATE.MAIN_MENU.ID);
		}

		if (KeyPressed(KEY.STAR)) {
			WIDTH = m_DisplaySizeW;
			HEIGHT = m_DisplaySizeH;

			ChangeState(STATE.MAIN_MENU.ID);
		}
	}

	public static void PaintStateDisplaySize(Graphics g) {
		g.setColor(STATE.DISPLAY_SIZE.COLOR_BACKGROUND);
		g.fillRect(0, 0, m_DisplaySizeW + 1, m_DisplaySizeH + 1);

		g.setColor(STATE.DISPLAY_SIZE.COLOR_SCREEN_BOUNDING_RECTANGLE_COLOR);
		g.drawRect(0, 0, m_DisplaySizeW - 1, m_DisplaySizeH - 1);

		String s = StringPak.GetString(STRPACK.PRESS_5_TO_RETURN) + "\n" + StringPak.GetString(STRPACK.PRESS_STAR_TO_RETURN) + "\n" + StringPak.GetString(STRPACK.WIDTH) + m_DisplaySizeW + "\n" + StringPak.GetString(STRPACK.HEIGHT) + m_DisplaySizeH;
		m_FontSmall.DrawMultilineString(g, s, 3, 25, 5, Graphics.LEFT | Graphics.TOP);
	}

	public static void UpdateStatePrimeNumbers() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else {
			StartTimer();
			PrimeNumbersBenchmark();
			StopTimer();

			int params[] = {STATE.CPU.ID};
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
		}
	}

	public static void PaintStatePrimeNumbers(Graphics g) {
		g.setColor(STATE.PRIME_NUMBERS.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.PRIME_NUMBERS.COLOR_TEXT);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PLEASE_WAIT), cMain.WIDTH >> 1, cMain.HEIGHT >> 1, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void PrimeNumbersBenchmark() {
		int[] primes = new int[STATE.PRIME_NUMBERS.ARRAY_SIZE];
		int num_primes;

		primes[0] = 2;
		primes[1] = 3;
		num_primes = 2;

		for (int i = 5; i < STATE.PRIME_NUMBERS.MAX_NUMBER; i += 2) {
			int j;

			for (j = 1; j < num_primes; j++) {
				if (i % primes[j] == 0) {
					j = -1;
					break;
				}

				if (i < primes[j] * primes[j]) {
					break;
				}
			}

			if (j != -1) {
				primes[num_primes++] = i;
			}
		}
	}

	public static void UpdateStateRecursive() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();
		} else {
			StartTimer();
			int a = RecursiveBenchmark();
			StopTimer();

			String s = a + "";

			int params[] = {STATE.CPU.ID};
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
		}
	}

	public static void PaintStateRecursive(Graphics g) {
		g.setColor(STATE.RECURSIVE.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.RECURSIVE.COLOR_TEXT);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PLEASE_WAIT), cMain.WIDTH >> 1, cMain.HEIGHT >> 1, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static int RecursiveBenchmark() {
		return Fibonacci(STATE.RECURSIVE.NUMBER);
	}

	public static int Fibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return Fibonacci(n - 1) + Fibonacci(n - 2);
		}
	}

	public static void UpdateStateSorting() {
		int selection = m_MenuSorting.UpdateMenuKeys();
		switch (selection) {
			case MENU.SORTING.OPT_BUBBLE_SORT:
				ChangeState(STATE.BUBBLE_SORT.ID);
				break;

			case MENU.SORTING.OPT_Q_SORT:
				ChangeState(STATE.Q_SORT.ID);
				break;

			case MENU.SORTING.OPT_MERGE_SORT:
				ChangeState(STATE.MERGE_SORT.ID);
				break;

			case MENU.SORTING.OPT_HEAPSORT:
				ChangeState(STATE.HEAP_SORT.ID);
				break;

			case MENU.SORTING.OPT_BACK:
				ChangeState(STATE.CPU.ID);
				break;
		}
	}

	public static void PaintStateSorting(Graphics g) {
		g.setColor(STATE.SORTING.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		m_MenuSorting.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateBubbleSort() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();

			AllocVector(STATE.BUBBLE_SORT.NUM_NUMBERS);
		} else {
			StartTimer();
			BubbleSortBenchmark();
			StopTimer();

			int params[] = {STATE.SORTING.ID};
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
			ChangeState(STATE.DISPLAY_RESULT.ID, params);

			DeallocVector();
		}
	}

	public static void PaintStateBubbleSort(Graphics g) {
		g.setColor(STATE.BUBBLE_SORT.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.BUBBLE_SORT.COLOR_TEXT);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PLEASE_WAIT), cMain.WIDTH >> 1, cMain.HEIGHT >> 1, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void BubbleSortBenchmark() {
		for (int i = 0; i < m_VectorSort.length; i++) {
			for (int j = i + 1; j < m_VectorSort.length; j++) {
				if (m_VectorSort[i] > m_VectorSort[j]) {
					int aux = m_VectorSort[i];
					m_VectorSort[i] = m_VectorSort[j];
					m_VectorSort[j] = aux;
				}
			}
		}

		if (CONFIG.TRACE_SORT) {
			OutVector(m_VectorSort, "Vector after BubbleSort");
		}
	}

	public static void UpdateStateQSort() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();

			AllocVector(STATE.Q_SORT.NUM_NUMBERS);
		} else {
			StartTimer();
			QSortBenchmark();
			StopTimer();

			int params[] = {STATE.SORTING.ID};
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
			ChangeState(STATE.DISPLAY_RESULT.ID, params);

			DeallocVector();
		}
	}

	public static void PaintStateQSort(Graphics g) {
		g.setColor(STATE.Q_SORT.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.Q_SORT.COLOR_TEXT);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PLEASE_WAIT), cMain.WIDTH >> 1, cMain.HEIGHT >> 1, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void QSortBenchmark() {
		qsort(0, m_VectorSort.length - 1);

		if (CONFIG.TRACE_SORT) {
			OutVector(m_VectorSort, "Vector after QSort");
		}
	}

	public static void qsort(int lo, int hi) {
		int i = lo, j = hi;
		int mid = m_VectorSort[(i + j) >> 1];

		while (i <= j) {
			while (m_VectorSort[i] < mid) {
				i++;
			}

			while (m_VectorSort[j] > mid) {
				j--;
			}

			if (i <= j) {
				int aux = m_VectorSort[i];
				m_VectorSort[i] = m_VectorSort[j];
				m_VectorSort[j] = aux;

				i++;
				j--;
			}
		}

		if (i < hi) {
			qsort(i, hi);
		}

		if (j > lo) {
			qsort(lo, j);
		}
	}

	public static void UpdateStateMergeSort() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();

			AllocVector(STATE.MERGE_SORT.NUM_NUMBERS);
		} else {
			StartTimer();
			MergeSortBenchmark();
			StopTimer();

			int params[] = {STATE.SORTING.ID};
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
			ChangeState(STATE.DISPLAY_RESULT.ID, params);

			DeallocVector();
		}
	}

	public static void PaintStateMergeSort(Graphics g) {
		g.setColor(STATE.MERGE_SORT.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.MERGE_SORT.COLOR_TEXT);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PLEASE_WAIT), cMain.WIDTH >> 1, cMain.HEIGHT >> 1, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void MergeSortBenchmark() {
		mergesort(0, m_VectorSort.length - 1);

		if (CONFIG.TRACE_SORT) {
			OutVector(m_VectorSort, "Vector after MergeSort");
		}
	}

	static void mergesort(int lo, int hi) {
		if (lo < hi) {
			int mid = (lo + hi) >> 1;
			mergesort(lo, mid);
			mergesort(mid + 1, hi);
			merge(lo, mid, hi);
		}
	}

	static void merge(int lo, int mid, int hi) {
		int i, j, k;

		for (i = lo; i <= hi; i++) {
			m_VectorAux[i] = m_VectorSort[i];
		}

		i = lo;
		j = mid + 1;
		k = lo;

		while (i <= mid && j <= hi) {
			if (m_VectorAux[i] <= m_VectorAux[j]) {
				m_VectorSort[k++] = m_VectorAux[i++];
			} else {
				m_VectorSort[k++] = m_VectorAux[j++];
			}
		}

		while (i <= mid) {
			m_VectorSort[k++] = m_VectorAux[i++];
		}
	}

	public static void UpdateStateHeapSort() {
		if (m_sStateTimer == 0) {
			InitializeTimeInterval();

			AllocVector(STATE.HEAP_SORT.NUM_NUMBERS);
		} else {
			StartTimer();
			HeapSortBenchmark();
			StopTimer();

			int params[] = {STATE.SORTING.ID};
			m_DisplayResultString = GetTimeInterval() + " " + StringPak.GetString(STRPACK.SECONDS);
			ChangeState(STATE.DISPLAY_RESULT.ID, params);

			DeallocVector();
		}
	}

	public static void PaintStateHeapSort(Graphics g) {
		g.setColor(STATE.HEAP_SORT.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.HEAP_SORT.COLOR_TEXT);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PLEASE_WAIT), cMain.WIDTH >> 1, cMain.HEIGHT >> 1, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateMemory() {
		if (m_sStateTimer == 0) {
			m_VectorForAlloc = new int[STATE.MEMORY.MAX_KEY_PRESSED][];
			m_VectorForAllocIndex = 0;
			m_iTotalAllocatedMemory = 0;
			m_iSystemGCTimer = 0;
		}

		if (m_iSystemGCTimer-- > 0) {
			System.gc();
		}

		if (KeyPressed(KEY.NUMERIC1)) {
			m_VectorForAlloc[m_VectorForAllocIndex++] = AllocVectorForStateMemory(100 * 1024 / 4);
			m_iTotalAllocatedMemory += 100 * 1024;
		}

		if (KeyPressed(KEY.NUMERIC2)) {
			m_VectorForAlloc[m_VectorForAllocIndex++] = AllocVectorForStateMemory(50 * 1024 / 4);
			m_iTotalAllocatedMemory += 50 * 1024;
		}

		if (KeyPressed(KEY.NUMERIC3)) {
			m_VectorForAlloc[m_VectorForAllocIndex++] = AllocVectorForStateMemory(5 * 1024 / 4);
			m_iTotalAllocatedMemory += 5 * 1024;
		}

		if (KeyPressed(KEY.NUMERIC4)) {
			m_VectorForAlloc[m_VectorForAllocIndex++] = AllocVectorForStateMemory(1024 / 4);
			m_iTotalAllocatedMemory += 1024;
		}

		if (KeyPressed(KEY.NUMERIC5)) {
			m_iSystemGCTimer = STATE.MEMORY.NUM_FRAMES_FOR_SYSTEM_GC;
		}

		if (KeyPressed(KEY.STAR)) {
			ChangeState(STATE.MAIN_MENU.ID);
			m_VectorForAlloc = null;
		}

		if (KeyPressed(KEY.POUND)) {
			int params[] = {STATE.MEMORY.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = StringPak.GetString(STRPACK.MEMORY_HELP);
		}
	}

	public static void PaintStateMemory(Graphics g) {
		g.setColor(STATE.MEMORY.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.MEMORY.COLOR_TEXT);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), 0, HEIGHT, Graphics.LEFT | Graphics.BOTTOM);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.SHARP_FOR_HELP), WIDTH, HEIGHT, Graphics.RIGHT | Graphics.BOTTOM);

		String s = StringPak.GetString(STRPACK.ALLOCATED_MEMORY) + " " + GetMemorySizeString(m_iTotalAllocatedMemory) + "\n\n" + StringPak.GetString(STRPACK.REPORTED_FREE_MEMORY) + " " + GetMemorySizeString(Runtime.getRuntime().freeMemory());
		m_FontSmall.DrawMultilineString(g, s, WIDTH / 2, HEIGHT / 2, 5, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateVideo() {
		int selection = m_MenuVideo.UpdateMenuKeys();
		switch (selection) {
			case MENU.VIDEO.OPT_VIDEO_MEMORY_SIZE:
				ChangeState(STATE.ALLOCATE_IMAGES_MENU.ID);
				break;

			case MENU.VIDEO.OPT_GEOMETRIC_PRIMITIVES_TESTS:
				ChangeState(STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.ID);
				break;

			case MENU.VIDEO.OPT_IMAGE_TESTS:
				ChangeState(STATE.IMAGE_TESTS_MENU.ID);
				break;

			case MENU.VIDEO.OPT_BACK:
				ChangeState(STATE.MAIN_MENU.ID);
				break;
		}
	}

	public static void PaintStateVideo(Graphics g) {
		g.setColor(STATE.VIDEO.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		m_MenuVideo.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateAllocateImagesMenu() {
		int selection = m_MenuAllocateImages.UpdateMenuKeys();
		switch (selection) {
			case MENU.ALLOCATE_IMAGES_MENU.OPT_100:
			case MENU.ALLOCATE_IMAGES_MENU.OPT_50:
			case MENU.ALLOCATE_IMAGES_MENU.OPT_20:
			case MENU.ALLOCATE_IMAGES_MENU.OPT_10:
			case MENU.ALLOCATE_IMAGES_MENU.OPT_5:
			case MENU.ALLOCATE_IMAGES_MENU.OPT_4: {
				int opts[] = {100, 50, 20, 10, 5, 4};
				int perframe[] = {1, 1, 4, 8, 10, 15};

				m_AllocatedImagesSize = opts[selection - MENU.ALLOCATE_IMAGES_MENU.OPT_100];
				m_AllocatedImagesPerFrame = perframe[selection - MENU.ALLOCATE_IMAGES_MENU.OPT_100];

				ChangeState(STATE.ALLOCATE_IMAGES.ID);
				break;
			}

			case MENU.ALLOCATE_IMAGES_MENU.OPT_BACK:
				ChangeState(STATE.VIDEO.ID);
				break;
		}
	}

	public static void PaintStateAllocateImagesMenu(Graphics g) {
		g.setColor(STATE.ALLOCATE_IMAGES_MENU.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		m_MenuAllocateImages.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateAllocateImages() {
		if (m_sStateTimer == 0) {
			m_AllocatedImages = new Image[STATE.ALLOCATE_IMAGES.MAX_IMAGES];
			m_NumAllocatedImages = 0;
			m_iSystemGCTimer = 0;
		}

		for (int i = 0; i < m_AllocatedImagesPerFrame; i++) {
			Image img = AllocateImage(m_AllocatedImagesSize, m_AllocatedImagesSize);

			if (img != null) {
				m_AllocatedImages[m_NumAllocatedImages++] = img;
			}
		}

		if (m_iSystemGCTimer-- > 0) {
			System.gc();
		}

		if (KeyPressed(KEY.NUMERIC5)) {
			m_iSystemGCTimer = STATE.MEMORY.NUM_FRAMES_FOR_SYSTEM_GC;
		}

		if (KeyPressed(KEY.POUND)) {
			int params[] = {STATE.ALLOCATE_IMAGES.ID};
			ChangeState(STATE.DISPLAY_RESULT.ID, params);
			m_DisplayResultString = StringPak.GetString(STRPACK.VIDEO_MEMORY_HELP);
		}

		if (KeyPressed(KEY.STAR)) {
			ChangeState(STATE.ALLOCATE_IMAGES_MENU.ID);

			m_AllocatedImages = null;
			m_NumAllocatedImages = 0;

			System.gc();
		}
	}

	public static void PaintStateAllocateImages(Graphics g) {
		g.setColor(STATE.ALLOCATE_IMAGES.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.ALLOCATE_IMAGES.TEXT_COLOR);

		String s1 = m_AllocatedImagesSize + "*" + m_AllocatedImagesSize + "\n";
		String s2 = StringPak.GetString(STRPACK.NUMBER_OF_ALLOCATED_IMAGES) + m_NumAllocatedImages + "\n";
		String s3 = StringPak.GetString(STRPACK.TOTAL_ALLOCATED_NUMBER_OF_PIXELS) + (m_AllocatedImagesSize * m_AllocatedImagesSize * m_NumAllocatedImages) + "\n";
		String s4 = StringPak.GetString(STRPACK.REPORTED_FREE_MEMORY) + Runtime.getRuntime().freeMemory();


		/* if (m_NumAllocatedImages != 0)
		g.drawImage(m_AllocatedImages[m_NumAllocatedImages - 1], 10, 10, 0); */

		m_FontSmall.DrawMultilineString(g, s1 + s2 + s3 + s4, WIDTH / 2, HEIGHT / 2, 5, Graphics.VCENTER | Graphics.HCENTER);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), 0, HEIGHT, Graphics.LEFT | Graphics.BOTTOM);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.SHARP_FOR_HELP), WIDTH, HEIGHT, Graphics.RIGHT | Graphics.BOTTOM);
	}

	public static Image AllocateImage(int w, int h) {
		Image img;

		try {
			img = Image.createImage(w, h);

			if (img != null) {
				Graphics g = img.getGraphics();

				g.setColor(((rand() % 0xFF) << 16) | ((rand() % 0xFF) << 8) | (rand() % 0xFF));
				g.fillRect(0, 0, w, h);

				int NumPrimitives = 20 + rand() % Math.max(w, h);
				for (int i = 0; i < NumPrimitives; i++) {
					int primitive = rand() % 3;

					switch (primitive) {
						case 0: {
							g.setColor(((rand() % 0xFF) << 16) | ((rand() % 0xFF) << 8) | (rand() % 0xFF));
							int x = rand() % (w - 1), y = rand() % (h - 1);
							g.fillRect(x, y, rand() % (w - x), rand() % (h - y));
							break;
						}

						case 1: {
							g.setColor(((rand() % 0xFF) << 16) | ((rand() % 0xFF) << 8) | (rand() % 0xFF));
							int x = rand() % (w - 1), y = rand() % (h - 1);
							g.drawRect(x, y, rand() % (w - x), rand() % (h - y));
							break;
						}

						case 2: {
							g.setColor(((rand() % 0xFF) << 16) | ((rand() % 0xFF) << 8) | (rand() % 0xFF));
							g.drawLine(rand() % w, rand() % h, rand() % w, rand() % h);
							break;
						}
					}

				}
			}
		} catch (Exception e) {
			img = null;
		}

		return img;
	}

	public static void UpdateStateFillRectTest() {
		if (KeyPressed(KEY.STAR)) {
			ChangeState(STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.ID);
			m_bLimitFPS = true;
		}
	}

	public static void PaintStateFillRectTest(Graphics g) {
		int iMaxWH = STATE.FILL_RECT_TEST.MAX_WH_PERCENTAGE_FROM_SCREEN_WH * Math.min(WIDTH, HEIGHT) / 100;

		for (int i = 0; i < STATE.FILL_RECT_TEST.NUM_RECTANGLES; i++) {
			int x = rand() % WIDTH;
			int y = rand() % HEIGHT;
			int w = 1 + (rand() % iMaxWH);
			int h = 1 + (rand() % iMaxWH);

			int color = (rand() % 255) | ((rand() % 255) << 8) | ((rand() % 255) << 16);
			g.setColor(color);
			g.fillRect(x, y, w, h);
		}

		g.setColor(STATE.FILL_RECT_TEST.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, m_FontSmall.GetFontHeight() + 2);
		g.setColor(STATE.FILL_RECT_TEST.TEXT_COLOR);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), WIDTH / 2, 1, Graphics.LEFT | Graphics.TOP);
	}

	public static void UpdateStateDrawRectTest() {
		if (KeyPressed(KEY.STAR)) {
			ChangeState(STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.ID);
			m_bLimitFPS = true;
		}
	}

	public static void PaintStateDrawRectTest(Graphics g) {
		int iMaxWH = STATE.DRAW_RECT_TEST.MAX_WH_PERCENTAGE_FROM_SCREEN_WH * Math.min(WIDTH, HEIGHT) / 100;

		for (int i = 0; i < STATE.DRAW_RECT_TEST.NUM_RECTANGLES; i++) {
			int x = rand() % WIDTH;
			int y = rand() % HEIGHT;
			int w = 1 + (rand() % iMaxWH);
			int h = 1 + (rand() % iMaxWH);

			int color = (rand() % 255) | ((rand() % 255) << 8) | ((rand() % 255) << 16);
			g.setColor(color);
			g.drawRect(x, y, w, h);
		}

		g.setColor(STATE.DRAW_RECT_TEST.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, m_FontSmall.GetFontHeight() + 2);
		g.setColor(STATE.DRAW_RECT_TEST.TEXT_COLOR);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), WIDTH / 2, 1, Graphics.LEFT | Graphics.TOP);
	}

	public static void UpdateStateDrawLineTest() {
		if (KeyPressed(KEY.STAR)) {
			ChangeState(STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.ID);
			m_bLimitFPS = true;
		}
	}

	public static void PaintStateDrawLineTest(Graphics g) {
		for (int i = 0; i < STATE.DRAW_LINE_TEST.NUM_LINES; i++) {
			int x1 = rand() % WIDTH;
			int y1 = rand() % HEIGHT;
			int x2 = 1 + (rand() % WIDTH);
			int y2 = 1 + (rand() % HEIGHT);

			int color = (rand() % 255) | ((rand() % 255) << 8) | ((rand() % 255) << 16);
			g.setColor(color);
			g.drawLine(x1, y1, x2, y2);
		}

		g.setColor(STATE.DRAW_LINE_TEST.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, m_FontSmall.GetFontHeight() + 2);
		g.setColor(STATE.DRAW_LINE_TEST.TEXT_COLOR);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), WIDTH / 2, 1, Graphics.LEFT | Graphics.TOP);
	}

	public static void UpdateStateFillTriangleTest() {
		if (KeyPressed(KEY.STAR)) {
			ChangeState(STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.ID);
			m_bLimitFPS = true;
		}
	}

	public static void PaintStateFillTriangleTest(Graphics g) {
		for (int i = 0; i < STATE.FILL_TRIANGLE_TEST.NUM_TRIANGLES; i++) {
			int x1 = rand() % WIDTH;
			int y1 = rand() % HEIGHT;
			int x2 = rand() % WIDTH;
			int y2 = rand() % HEIGHT;
			int x3 = rand() % WIDTH;
			int y3 = rand() % HEIGHT;

			int color = (rand() % 255) | ((rand() % 255) << 8) | ((rand() % 255) << 16);
			g.setColor(color);
			g.fillTriangle(x1, y1, x2, y2, x3, y3);
		}

		g.setColor(STATE.FILL_TRIANGLE_TEST.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, m_FontSmall.GetFontHeight() + 2);
		g.setColor(STATE.FILL_TRIANGLE_TEST.TEXT_COLOR);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), WIDTH / 2, 1, Graphics.LEFT | Graphics.TOP);
	}

	public static void UpdateStateDrawArcTest() {
		if (KeyPressed(KEY.STAR)) {
			ChangeState(STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.ID);
			m_bLimitFPS = true;
		}
	}

	public static void PaintStateDrawArcTest(Graphics g) {
		for (int i = 0; i < STATE.DRAW_ARC_TEST.NUM_ARCS; i++) {
			int x = rand() % WIDTH;
			int y = rand() % HEIGHT;
			int w = rand() % WIDTH;
			int h = rand() % HEIGHT;
			int startAngle = rand() % 360;
			int angle = rand() % 360;

			int color = (rand() % 255) | ((rand() % 255) << 8) | ((rand() % 255) << 16);
			g.setColor(color);
			g.drawArc(x, y, w, h, startAngle, angle);
		}

		g.setColor(STATE.DRAW_ARC_TEST.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, m_FontSmall.GetFontHeight() + 2);
		g.setColor(STATE.DRAW_ARC_TEST.TEXT_COLOR);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), WIDTH / 2, 1, Graphics.LEFT | Graphics.TOP);
	}

	public static void UpdateStateFillArcTest() {
		if (KeyPressed(KEY.STAR)) {
			ChangeState(STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.ID);
			m_bLimitFPS = true;
		}
	}

	public static void PaintStateFillArcTest(Graphics g) {
		for (int i = 0; i < STATE.FILL_ARC_TEST.NUM_ARCS; i++) {
			int x = rand() % WIDTH;
			int y = rand() % HEIGHT;
			int w = rand() % WIDTH;
			int h = rand() % HEIGHT;
			int startAngle = rand() % 360;
			int angle = rand() % 360;

			int color = (rand() % 255) | ((rand() % 255) << 8) | ((rand() % 255) << 16);
			g.setColor(color);
			g.fillArc(x, y, w, h, startAngle, angle);
		}

		g.setColor(STATE.FILL_ARC_TEST.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, m_FontSmall.GetFontHeight() + 2);
		g.setColor(STATE.FILL_ARC_TEST.TEXT_COLOR);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), WIDTH / 2, 1, Graphics.LEFT | Graphics.TOP);
	}

	public static void UpdateStateGeometricPrimitivesTestMenu() {
		int selection = m_MenuGeometricPrimitivesTests.UpdateMenuKeys();

		switch (selection) {
			case MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_DRAW_RECT_TEST:
				ChangeState(STATE.DRAW_RECT_TEST.ID);
				m_bLimitFPS = false;
				break;

			case MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_FILL_RECT_TEST:
				ChangeState(STATE.FILL_RECT_TEST.ID);
				m_bLimitFPS = false;
				break;

			case MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_DRAW_LINE_TEST:
				ChangeState(STATE.DRAW_LINE_TEST.ID);
				m_bLimitFPS = false;
				break;

			case MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_FILL_TRIANGLE_TEST:
				ChangeState(STATE.FILL_TRIANGLE_TEST.ID);
				m_bLimitFPS = false;
				break;

			case MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_DRAW_ARC_TEST:
				ChangeState(STATE.DRAW_ARC_TEST.ID);
				m_bLimitFPS = false;
				break;

			case MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_FILL_ARC_TEST:
				ChangeState(STATE.FILL_ARC_TEST.ID);
				m_bLimitFPS = false;
				break;

			case MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_BACK:
				ChangeState(STATE.VIDEO.ID);
				break;
		}
	}

	public static void PaintStateGeometricPrimitivesTestMenu(Graphics g) {
		g.setColor(STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.GEOMETRIC_PRIMITIVES_TEST_MENU.TEXT_COLOR);
		m_MenuGeometricPrimitivesTests.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateImageTestsMenu() {
		int selection = m_MenuImageTests.UpdateMenuKeys();

		switch (selection) {
			case MENU.IMAGE_TESTS_MENU.OPT_DRAW_IMAGE: {
				int params[] = {GetStateID()};
				ChangeState(STATE.TEST_IMAGE_SIZES.ID, params);
				break;
			}

			case MENU.IMAGE_TESTS_MENU.OPT_DRAW_REGION: {
				ChangeState(STATE.TEST_IMAGE_TRANSFORM.ID);
				break;
			}

			case MENU.IMAGE_TESTS_MENU.OPT_BACK:
				ChangeState(STATE.VIDEO.ID);
				break;
		}
	}

	public static void PaintStateImageTestsMenu(Graphics g) {
		g.setColor(STATE.IMAGE_TESTS_MENU.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.IMAGE_TESTS_MENU.TEXT_COLOR);
		m_MenuImageTests.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static int GetTransform(int menuID) {
		int[] transform = {
			Sprite.TRANS_NONE,
			Sprite.TRANS_ROT90,
			Sprite.TRANS_ROT180,
			Sprite.TRANS_ROT270,
			Sprite.TRANS_MIRROR,
			Sprite.TRANS_MIRROR_ROT90,
			Sprite.TRANS_MIRROR_ROT180,
			Sprite.TRANS_MIRROR_ROT270
		};

		return transform[menuID];
	}

	public static void UpdateStateTestImagesSize() {
		int selection = m_MenuTestImagesSize.UpdateMenuKeys();

		switch (selection) {
			case MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_4X4:
			case MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_8X8:
			case MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_10X10:
			case MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_12X12:
			case MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_16X16:
			case MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_20X20:
			case MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_32X32:
			case MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_48X48:
			case MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_64X64: {
				int[] sizes = {4, 8, 10, 12, 16, 20, 32, 48, 64};
				int[] stateparams = GetStateParams();
				int[] params = {stateparams[0], sizes[selection - MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_4X4], selection - MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_4X4, stateparams.length > 1 ? stateparams[1] : -1};
				if (stateparams[0] == STATE.TEST_IMAGE_TRANSFORM.ID) {
					ChangeState(STATE.DRAW_REGION_TEST.ID, params);
				} else {
					ChangeState(STATE.DRAW_IMAGE_TEST.ID, params);
				}

				m_bLimitFPS = false;
				break;
			}

			case MENU.TEST_IMAGE_SIZES.OPT_BACK:
				ChangeState(GetStateParams()[0]);
				break;
		}
	}

	public static void PaintStateTestImagesSize(Graphics g) {
		g.setColor(STATE.TEST_IMAGE_SIZES.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.TEST_IMAGE_SIZES.TEXT_COLOR);
		m_MenuTestImagesSize.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateDrawImageTest() {
		if (m_sStateTimer == 0) {
			ResPak.Open();

			byte[] section = ResPak.ReadSection(ResPack0.IMAGE1_4x4 + GetStateParams()[2]);
			m_img1 = Image.createImage(section, 0, section.length);

			section = ResPak.ReadSection(ResPack0.IMAGE2_4x4 + GetStateParams()[2]);
			m_img2 = Image.createImage(section, 0, section.length);

			ResPak.Close();

			srand((int) (System.currentTimeMillis() / 5));
		}

		if (KeyPressed(KEY.STAR)) {
			int params[] = {GetStateParams()[0]};
			ChangeState(STATE.TEST_IMAGE_SIZES.ID, params);

			m_img1 = m_img2 = null;
			System.gc();

			m_bLimitFPS = true;
		}
	}

	public static void PaintStateDrawImageTest(Graphics g) {
		if (m_sStateTimer < 1) {
			return;
		}

		int ImageSize = GetStateParams()[1];
		int W = WIDTH / ImageSize + (WIDTH % ImageSize == 0 ? 0 : 1);
		int H = HEIGHT / ImageSize + (WIDTH % ImageSize == 0 ? 0 : 1);

		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				Image img = (rand() / 19) % 4 == 0 ? m_img1 : m_img2;

				g.drawImage(img, i * ImageSize, j * ImageSize, 0);
			}
		}

		g.setColor(STATE.DRAW_IMAGE_TEST.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, m_FontSmall.GetFontHeight() + 2);
		g.setColor(STATE.DRAW_IMAGE_TEST.TEXT_COLOR);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), WIDTH / 2, 1, Graphics.LEFT | Graphics.TOP);
	}

	public static void UpdateStateTestImageTransform() {
		int selection = m_MenuTestImageTransform.UpdateMenuKeys();

		switch (selection) {
			case MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_NONE:
			case MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_ROT90:
			case MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_ROT180:
			case MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_ROT270:
			case MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_MIRROR:
			case MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_MIRROR_ROT90:
			case MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_MIRROR_ROT180:
			case MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_MIRROR_ROT270: {
				int[] params = {GetStateID(), GetTransform(selection - MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_NONE)};
				ChangeState(STATE.TEST_IMAGE_SIZES.ID, params);
				break;
			}

			case MENU.TEST_IMAGE_TRANSFORM.OPT_BACK:
				ChangeState(STATE.IMAGE_TESTS_MENU.ID);
				break;
		}
	}

	public static void PaintStateTestImageTransform(Graphics g) {
		g.setColor(STATE.TEST_IMAGE_TRANSFORM.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(STATE.TEST_IMAGE_TRANSFORM.TEXT_COLOR);
		m_MenuTestImageTransform.DrawMenu(g, WIDTH / 2, HEIGHT / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public static void UpdateStateDrawRegionTest() {
		if (m_sStateTimer == 0) {
			ResPak.Open();

			byte[] section = ResPak.ReadSection(ResPack0.IMAGE1_4x4 + GetStateParams()[2]);
			m_img1 = Image.createImage(section, 0, section.length);

			section = ResPak.ReadSection(ResPack0.IMAGE2_4x4 + GetStateParams()[2]);
			m_img2 = Image.createImage(section, 0, section.length);

			ResPak.Close();

			srand((int) (System.currentTimeMillis() / 5));
		}

		if (KeyPressed(KEY.STAR)) {
			int params[] = {GetStateParams()[0], GetStateParams()[3]};
			ChangeState(STATE.TEST_IMAGE_SIZES.ID, params);

			m_img1 = m_img2 = null;
			System.gc();

			m_bLimitFPS = true;
		}
	}

	public static void PaintStateDrawRegionTest(Graphics g) {
		if (m_sStateTimer < 1) {
			return;
		}

		int transform = GetStateParams()[3];
		int ImageSize = GetStateParams()[1];
		int W = WIDTH / ImageSize + (WIDTH % ImageSize == 0 ? 0 : 1);
		int H = HEIGHT / ImageSize + (WIDTH % ImageSize == 0 ? 0 : 1);

		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				Image img = (rand() / 19) % 4 == 0 ? m_img1 : m_img2;

				g.drawRegion(img, 0, 0, ImageSize, ImageSize, transform, i * ImageSize, j * ImageSize, 0);
			}
		}

		g.setColor(STATE.DRAW_IMAGE_TEST.COLOR_BACKGROUND);
		g.fillRect(0, 0, WIDTH, m_FontSmall.GetFontHeight() + 2);
		g.setColor(STATE.DRAW_IMAGE_TEST.TEXT_COLOR);
		m_FontSmall.DrawStringLine(g, StringPak.GetString(STRPACK.PRESS_STAR_FOR_BACK), WIDTH / 2, 1, Graphics.LEFT | Graphics.TOP);
	}

	public static String GetMemorySizeString(long lMemorySize) {
		if (lMemorySize < 1024) {
			return "" + lMemorySize + StringPak.GetString(STRPACK.BYTES);
		} else if (lMemorySize >= 1024 && lMemorySize < 1024 * 1024) {
			return "" + GetFractionalString(lMemorySize, 1024, 1) + StringPak.GetString(STRPACK.KILO_BYTES);
		} else {
			return "" + GetFractionalString(lMemorySize, 1024 * 1024, 2) + StringPak.GetString(STRPACK.MEGA_BYTES);
		}
	}

	public static String GetFractionalString(long lNominator, long lDenominator, long lNumDecimals) {
		String i = "" + (lNominator / lDenominator);

		long pow = 1;
		for (int loop = 0; loop < lNumDecimals; loop++) {
			pow *= 10;
		}

		String f = "" + ((lNominator % lDenominator) * pow / lDenominator);

		int numZeros = (int) lNumDecimals - f.length();
		for (int loop = 0; loop < numZeros; loop++) {
			f = "0" + f;
		}

		return i + "." + f;
	}

	public static int[] AllocVectorForStateMemory(int size) {
		try {
			return new int[size];
		} catch (Exception e) {
			return null;
		}
	}

	public static void HeapSortBenchmark() {
		m_ArrayLen = m_VectorSort.length;
		heapsort();

		if (CONFIG.TRACE_SORT) {
			OutVector(m_VectorSort, "Vector after HeapSort");
		}
	}

	private static void heapsort() {
		buildheap();


		while (m_ArrayLen > 1) {
			m_ArrayLen--;

			//ca exchange(0, n);
			int aux = m_VectorSort[0];
			m_VectorSort[0] = m_VectorSort[m_ArrayLen];
			m_VectorSort[m_ArrayLen] = aux;

			downheap(0);
		}
	}

	private static void buildheap() {
		for (int v = (m_VectorSort.length >> 1) - 1; v >= 0; v--) {
			downheap(v);
		}
	}

	private static void downheap(int v) {
		int w = 2 * v + 1;
		while (w < m_ArrayLen) {
			if (w + 1 < m_ArrayLen) {
				if (m_VectorSort[w + 1] > m_VectorSort[w]) {
					w++;
				}
			}

			if (m_VectorSort[v] >= m_VectorSort[w]) {
				return;
			}

			//exchange(v, w);  // exchange labels of v and w

			int aux = m_VectorSort[v];
			m_VectorSort[v] = m_VectorSort[w];
			m_VectorSort[w] = aux;

			v = w;
			w = 2 * v + 1;
		}
	}

	public static void AllocVector(int size) {
		m_VectorSort = new int[size];
		for (int i = 0; i < m_VectorSort.length; i++) {
			m_VectorSort[i] = rand() % (size * 3);
		}

		m_VectorAux = new int[size];

		if (CONFIG.TRACE_SORT) {
			OutVector(m_VectorSort, "Original vector");
		}

		System.gc();
	}

	public static void OutVector(int[] vector, String message) {
		System.out.println(message);
		for (int i = 0; i < vector.length; i++) {
			System.out.print(vector[i] + " ");
		}

		System.out.println("");
	}

	public static void DeallocVector() {
		m_VectorSort = null;
		m_VectorAux = null;

		System.gc();
	}

	/*
	 * Random numbers generator
	 */
	static int rand() {
		m_next = m_next * 1103515245 + 12345;
		return (int) m_next;
	}

	static void srand(int seed) {
		m_next = seed;
	}

	public static void ChangeState(int NewStateID, int[] StateParams) {
		m_sStateID = NewStateID;
		m_sStateParams = StateParams;
		m_sStateTime = System.currentTimeMillis();
		m_sStateTimer = -1;
	}

	public static void ChangeState(int NewStateID) {
		ChangeState(NewStateID, null);
	}

	public static int GetStateID() {
		return m_sStateID;
	}

	public static int[] GetStateParams() {
		return m_sStateParams;
	}

	public static void DrawProgressBar(Graphics g, int iValue, int iMax, int x, int y, int width, int height, int colorBorder, int colorFill) {
		int w = iValue * width / iMax;

		g.setColor(colorBorder);
		g.drawRect(x, y, width, height);

		g.setColor(colorFill);
		g.fillRect(x + 1, y + 1, w - 1, height - 1);

	}

	public static String[] GetStringLines(String s) {
		int len = s.length();
		int iNumLines = 1;

		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == '\n') {
				iNumLines++;
			}
		}

		String arr[] = new String[iNumLines];
		for (int i = 0; i < iNumLines - 1; i++) {
			int index;
			arr[i] = s.substring(0, index = s.indexOf('\n'));
			s = s.substring(index + 1);
		}

		arr[iNumLines - 1] = s;

		return arr;
	}

	public static void InitMenus() {
		{
			String[] opts = {StringPak.GetString(STRPACK.DISPLAY_SIZE), StringPak.GetString(STRPACK.KEY_CODES), StringPak.GetString(STRPACK.VIDEO), StringPak.GetString(STRPACK.AUDIO), StringPak.GetString(STRPACK.CPU), StringPak.GetString(STRPACK.MEMORY), StringPak.GetString(STRPACK.EXIT)};
			int[] actions = {MENU.MAIN.OPT_DIPLAY_SIZE, MENU.MAIN.OPT_KEY_CODES, MENU.MAIN.OPT_VIDEO, MENU.MAIN.OPT_AUDIO, MENU.MAIN.OPT_CPU, MENU.MAIN.OPT_MEMORY, MENU.MAIN.OPT_EXIT};

			m_MenuMain = new CMenu(StringPak.GetString(STRPACK.MAIN_MENU_TITLE), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(120, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {StringPak.GetString(STRPACK.ARITHMETIC), StringPak.GetString(STRPACK.LOGICAL), StringPak.GetString(STRPACK.SORTING), StringPak.GetString(STRPACK.PRIME_NUMBERS), StringPak.GetString(STRPACK.RECURSIVE), StringPak.GetString(STRPACK.BACK)};
			int[] actions = {MENU.CPU.OPT_ARITHMETIC, MENU.CPU.OPT_LOGICAL, MENU.CPU.OPT_SORTING, MENU.CPU.OPT_PRIME_NUMBERS, MENU.CPU.OPT_RECURSIVE, MENU.CPU.OPT_BACK};

			m_MenuCPU = new CMenu(StringPak.GetString(STRPACK.CPU_MENU_TITLE), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(120, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {StringPak.GetString(STRPACK.ADD), StringPak.GetString(STRPACK.SUB), StringPak.GetString(STRPACK.MULTIPLY), StringPak.GetString(STRPACK.DIVIDE), StringPak.GetString(STRPACK.BACK)};
			int[] actions = {MENU.ARITHMETIC.OPT_ADD, MENU.ARITHMETIC.OPT_SUBSTRACTION, MENU.ARITHMETIC.OPT_MULTIPLY, MENU.ARITHMETIC.OPT_DIVIDE, MENU.ARITHMETIC.OPT_BACK};

			m_MenuArithmetic = new CMenu(StringPak.GetString(STRPACK.ARITHMETIC_TESTS), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(120, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {StringPak.GetString(STRPACK.SHIFT_LEFT), StringPak.GetString(STRPACK.SHIFT_RIGHT), StringPak.GetString(STRPACK.AND), StringPak.GetString(STRPACK.OR), StringPak.GetString(STRPACK.XOR), StringPak.GetString(STRPACK.BACK)};
			int[] actions = {MENU.LOGICAL.OPT_SHIFT_LEFT, MENU.LOGICAL.OPT_SHIFT_RIGHT, MENU.LOGICAL.OPT_AND, MENU.LOGICAL.OPT_OR, MENU.LOGICAL.OPT_XOR, MENU.LOGICAL.OPT_BACK};

			m_MenuLogical = new CMenu(StringPak.GetString(STRPACK.ARITHMETIC_TESTS), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(120, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {
				StringPak.GetString(STRPACK.BUBBLE_SORT),
				StringPak.GetString(STRPACK.Q_SORT),
				StringPak.GetString(STRPACK.MERGE_SORT),
				StringPak.GetString(STRPACK.HEAP_SORT),
				StringPak.GetString(STRPACK.BACK)
			};

			int[] actions = {
				MENU.SORTING.OPT_BUBBLE_SORT,
				MENU.SORTING.OPT_Q_SORT,
				MENU.SORTING.OPT_MERGE_SORT,
				MENU.SORTING.OPT_HEAPSORT,
				MENU.LOGICAL.OPT_BACK
			};

			m_MenuSorting = new CMenu(StringPak.GetString(STRPACK.SORTING_TESTS), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(120, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {
				StringPak.GetString(STRPACK.VIDEO_MEMORY_SIZE),
				StringPak.GetString(STRPACK.GEOMETRIC_PRIMITIVES_TESTS),
				StringPak.GetString(STRPACK.IMAGE_TESTS),
				StringPak.GetString(STRPACK.BACK)
			};

			int[] actions = {
				MENU.VIDEO.OPT_VIDEO_MEMORY_SIZE,
				MENU.VIDEO.OPT_GEOMETRIC_PRIMITIVES_TESTS,
				MENU.VIDEO.OPT_IMAGE_TESTS,
				MENU.VIDEO.OPT_BACK
			};

			m_MenuVideo = new CMenu(StringPak.GetString(STRPACK.VIDEO_TESTS), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(140, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {
				StringPak.GetString(STRPACK.ALLOCATE_IMAGES_100),
				StringPak.GetString(STRPACK.ALLOCATE_IMAGES_50),
				StringPak.GetString(STRPACK.ALLOCATE_IMAGES_20),
				StringPak.GetString(STRPACK.ALLOCATE_IMAGES_10),
				StringPak.GetString(STRPACK.ALLOCATE_IMAGES_5),
				StringPak.GetString(STRPACK.ALLOCATE_IMAGES_4),
				StringPak.GetString(STRPACK.BACK)
			};

			int[] actions = {
				MENU.ALLOCATE_IMAGES_MENU.OPT_100,
				MENU.ALLOCATE_IMAGES_MENU.OPT_50,
				MENU.ALLOCATE_IMAGES_MENU.OPT_20,
				MENU.ALLOCATE_IMAGES_MENU.OPT_10,
				MENU.ALLOCATE_IMAGES_MENU.OPT_5,
				MENU.ALLOCATE_IMAGES_MENU.OPT_4,
				MENU.VIDEO.OPT_BACK
			};

			m_MenuAllocateImages = new CMenu(StringPak.GetString(STRPACK.ALLOCATE_IMAGES), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(120, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {
				StringPak.GetString(STRPACK.DRAW_RECT_TEST),
				StringPak.GetString(STRPACK.FILL_RECT_TEST),
				StringPak.GetString(STRPACK.DRAW_LINE_TEST),
				StringPak.GetString(STRPACK.FILL_TRIANGLE_TEST),
				StringPak.GetString(STRPACK.DRAW_ARC_TEST),
				StringPak.GetString(STRPACK.FILL_ARC_TEST),
				StringPak.GetString(STRPACK.BACK)
			};

			int[] actions = {
				MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_DRAW_RECT_TEST,
				MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_FILL_RECT_TEST,
				MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_DRAW_LINE_TEST,
				MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_FILL_TRIANGLE_TEST,
				MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_DRAW_ARC_TEST,
				MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_FILL_ARC_TEST,
				MENU.GEOMETRIC_PRIMITIVES_TESTS_MENU.OPT_BACK
			};

			m_MenuGeometricPrimitivesTests = new CMenu(StringPak.GetString(STRPACK.GEOMETRIC_PRIMITIVES_TESTS), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(140, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {
				StringPak.GetString(STRPACK.DRAW_IMAGE_TEST),
				StringPak.GetString(STRPACK.DRAW_REGION_TEST),
				StringPak.GetString(STRPACK.BACK)
			};

			int[] actions = {
				MENU.IMAGE_TESTS_MENU.OPT_DRAW_IMAGE,
				MENU.IMAGE_TESTS_MENU.OPT_DRAW_REGION,
				MENU.IMAGE_TESTS_MENU.OPT_BACK
			};

			m_MenuImageTests = new CMenu(StringPak.GetString(STRPACK.IMAGE_TESTS), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(140, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {
				StringPak.GetString(STRPACK.IMAGE_TEST_4X4),
				StringPak.GetString(STRPACK.IMAGE_TEST_8X8),
				StringPak.GetString(STRPACK.IMAGE_TEST_10X10),
				StringPak.GetString(STRPACK.IMAGE_TEST_12X12),
				StringPak.GetString(STRPACK.IMAGE_TEST_16X16),
				StringPak.GetString(STRPACK.IMAGE_TEST_20X20),
				StringPak.GetString(STRPACK.IMAGE_TEST_32X32),
				StringPak.GetString(STRPACK.IMAGE_TEST_48X48),
				StringPak.GetString(STRPACK.IMAGE_TEST_64X64),
				StringPak.GetString(STRPACK.BACK)
			};

			int[] actions = {
				MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_4X4,
				MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_8X8,
				MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_10X10,
				MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_12X12,
				MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_16X16,
				MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_20X20,
				MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_32X32,
				MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_48X48,
				MENU.TEST_IMAGE_SIZES.OPT_IMAGE_TEST_64X64,
				MENU.IMAGE_TESTS_MENU.OPT_BACK
			};

			m_MenuTestImagesSize = new CMenu(StringPak.GetString(STRPACK.IMAGE_TESTS), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(140, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}

		{
			String[] opts = {
				StringPak.GetString(STRPACK.IMAGE_TRANS_NONE),
				StringPak.GetString(STRPACK.IMAGE_TRANS_ROT90),
				StringPak.GetString(STRPACK.IMAGE_TRANS_ROT180),
				StringPak.GetString(STRPACK.IMAGE_TRANS_ROT270),
				StringPak.GetString(STRPACK.IMAGE_TRANS_MIRROR),
				StringPak.GetString(STRPACK.IMAGE_TRANS_MIRROR_ROT90),
				StringPak.GetString(STRPACK.IMAGE_TRANS_MIRROR_ROT180),
				StringPak.GetString(STRPACK.IMAGE_TRANS_MIRROR_ROT270),
				StringPak.GetString(STRPACK.BACK)
			};

			int[] actions = {
				MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_NONE,
				MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_ROT90,
				MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_ROT180,
				MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_ROT270,
				MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_MIRROR,
				MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_MIRROR_ROT90,
				MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_MIRROR_ROT180,
				MENU.TEST_IMAGE_TRANSFORM.OPT_IMAGE_TRANS_MIRROR_ROT270,
				MENU.TEST_IMAGE_TRANSFORM.OPT_BACK
			};

			m_MenuTestImageTransform = new CMenu(StringPak.GetString(STRPACK.IMAGE_TRANSFORM), m_FontLarge, 0xFF0000,
				opts, actions, m_FontSmall, 0x00FF00,
				15, 7, 10,
				0x0000FF, Math.min(140, WIDTH - 4), 5,
				3,
				MENU.MENU_TYPE.VERTICAL_SCROLL_LIST);
		}
	}

	public static void InitializeTimeInterval() {
		m_TimeInterval = 0;
	}

	public static void StartTimer() {
		m_TimeInterval -= System.currentTimeMillis();
	}

	public static void StopTimer() {
		m_TimeInterval += System.currentTimeMillis();
	}

	public static String GetTimeInterval() {
		String s = (m_TimeInterval / 1000) + ".";
		long t = m_TimeInterval % 1000;

		for (int i = 0; i < 3 - (t + "").length(); i++) {
			s += "0";
		}

		s += t;

		return s;
	}
	
	public static int m_sStateID;
	public static int[] m_sStateParams;
	public static long m_sStateTime;
	public static int m_sStateTimer;
	static MIDlet m_midlet;
	public static int m_sTimer;
	public static int m_KeyPressed,  m_KeyReleased,  m_KeyActiveSingle,  m_KeyActiveRepeat;
	static CResPak ResPak;
	static CStringPak StringPak;
	static CMenu m_MenuMain,  m_MenuCPU,  m_MenuArithmetic,  m_MenuLogical;
	static CMenu m_MenuSorting,  m_MenuVideo,  m_MenuAllocateImages;
	static CMenu m_MenuGeometricPrimitivesTests,  m_MenuImageTests;
	static CMenu m_MenuTestImagesSize,  m_MenuTestImageTransform;
	static int m_VectorSort[],  m_VectorAux[];
	static int m_ArrayLen;
	static CFont m_FontLarge,  m_FontSmall;
	public static String m_DisplayResultString;
	public static long m_TimeInterval;
	
	/*
	 *  Images for image tests
	 */
	static Image m_img1,  m_img2;
	static long m_next = 1;
	static int[][] m_VectorForAlloc;
	static int m_VectorForAllocIndex;
	static int m_iTotalAllocatedMemory;
	static int m_iSystemGCTimer;
	
	/*
	 *  Display Size
	 */
	static int m_DisplaySizeW,  m_DisplaySizeH;
	
	/*
	 *  Allocate Images
	 */
	static int m_AllocatedImagesSize,  m_AllocatedImagesPerFrame,  m_NumAllocatedImages;
	static Image m_AllocatedImages[];
	static long m_sTimeLastPaint,  m_sTimePaintSum;
	static String m_sFPS;
	static boolean m_bLimitFPS = true;
	static int sss = 1;
	static int WIDTH = 100;
	static int HEIGHT = 100;
}
