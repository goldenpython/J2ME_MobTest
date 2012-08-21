/*******************************************************************************
*    Cristian Alexandrescu                                                     *
*    2163013577ba2bc237f22b3f4d006856                                          *
*    11a4bb2c77aca6a9927b85f259d9af10db791ce5cf884bb31e7f7a889d4fb385          *
*    bc9a53289baf23d369484f5343ed5d6c                                          *
*******************************************************************************/


public interface STATE {

	public interface FIRST {

		public static int ID = 0;
		public static int NO_MILLIS_IN_STATE = 0;
	}

	public interface INIT {

		public static int ID = 1;
		public static int BACKGROUND_COLOR = 0XFFFF8040;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface MAIN_MENU {

		public static int ID = 2;
	}

	public interface DISPLAY_SIZE {

		public static int ID = 3;
		public static int COLOR_BACKGROUND = 0x800080;
		public static int COLOR_SCREEN_BOUNDING_RECTANGLE_COLOR = 0x00FF00;
	}

	public interface CPU {

		public static int ID = 4;
		public static int COLOR_BACKGROUND = 0x000000;
	}

	public interface ARITHMETIC {

		public static int ID = 5;
		public static int COLOR_BACKGROUND = 0x000000;
	}

	public interface SORTING {

		public static int ID = 6;
		public static int COLOR_BACKGROUND = 0x000000;
	}

	public interface ADD {

		public static int ID = 7;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int NUM_FRAMES = 50;
		public static int NUM_ITERATIONS = 10000;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface SUB {

		public static int ID = 8;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int NUM_FRAMES = ADD.NUM_FRAMES;
		public static int NUM_ITERATIONS = ADD.NUM_ITERATIONS;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface MUL {

		public static int ID = 9;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int NUM_FRAMES = ADD.NUM_FRAMES;
		public static int NUM_ITERATIONS = ADD.NUM_ITERATIONS;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface DIV {

		public static int ID = 10;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int NUM_FRAMES = ADD.NUM_FRAMES;
		public static int NUM_ITERATIONS = ADD.NUM_ITERATIONS;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface DISPLAY_RESULT {

		public static int ID = 11;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00F100;
	}

	public interface LOGICAL {

		public static int ID = 12;
		public static int COLOR_BACKGROUND = 0x000000;
	}

	public interface SHIFT_LEFT {

		public static int ID = 13;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int NUM_FRAMES = ADD.NUM_FRAMES;
		public static int NUM_ITERATIONS = ADD.NUM_ITERATIONS;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface SHIFT_RIGHT {

		public static int ID = 14;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int NUM_FRAMES = ADD.NUM_FRAMES;
		public static int NUM_ITERATIONS = ADD.NUM_ITERATIONS;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface AND {

		public static int ID = 15;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int NUM_FRAMES = ADD.NUM_FRAMES;
		public static int NUM_ITERATIONS = ADD.NUM_ITERATIONS;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface OR {

		public static int ID = 16;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int NUM_FRAMES = ADD.NUM_FRAMES;
		public static int NUM_ITERATIONS = ADD.NUM_ITERATIONS;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface XOR {

		public static int ID = 17;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int NUM_FRAMES = ADD.NUM_FRAMES;
		public static int NUM_ITERATIONS = ADD.NUM_ITERATIONS;
		public static int PROGRESS_BAR_BORDER_COLOR = 0x0000FF;
		public static int PROGRESS_BAR_FILL_COLOR = 0xFF0000;
		public static int PROGRESS_BAR_X = 20;
		public static int PROGRESS_BAR_BOTTOM_Y_DIFF = 20;
		public static int PROGRESS_BAR_H = 10;
	}

	public interface PRIME_NUMBERS {

		public static int ID = 18;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int COLOR_TEXT = 0x00FF00;
		public static int MAX_NUMBER = 300000;
		public static int ARRAY_SIZE = 30000;
	}

	public interface RECURSIVE {

		public static int ID = 19;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int COLOR_TEXT = 0x00FF00;
		public static int NUMBER = 33;
	}

	public interface BUBBLE_SORT {

		public static int ID = 20;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int COLOR_TEXT = 0x00FF00;
		public static int NUM_NUMBERS = 2500;
	}

	public interface Q_SORT {

		public static int ID = 21;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int COLOR_TEXT = 0x00FF00;
		public static int NUM_NUMBERS = 10000;
	}

	public interface MERGE_SORT {

		public static int ID = 22;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int COLOR_TEXT = 0x00FF00;
		public static int NUM_NUMBERS = 10000;
	}

	public interface HEAP_SORT {

		public static int ID = 23;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int COLOR_TEXT = 0x00FF00;
		public static int NUM_NUMBERS = 10000;
	}

	public interface MEMORY {

		public static int ID = 24;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int COLOR_TEXT = 0x00FF00;
		public static int MAX_KEY_PRESSED = 100;
		public static int NUM_FRAMES_FOR_SYSTEM_GC = 10;
	}

	public interface VIDEO {

		public static int ID = 25;
		public static int COLOR_BACKGROUND = 0x000000;
	}

	public interface ALLOCATE_IMAGES_MENU {

		public static int ID = 26;
		public static int COLOR_BACKGROUND = 0x000000;
	}

	public interface ALLOCATE_IMAGES {

		public static int ID = 27;
		public static int MAX_IMAGES = 10000;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface FILL_RECT_TEST {

		public static int ID = 28;
		public static int NUM_RECTANGLES = 1000;
		public static int MAX_WH_PERCENTAGE_FROM_SCREEN_WH = 50;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface DRAW_RECT_TEST {

		public static int ID = 29;
		public static int NUM_RECTANGLES = 1000;
		public static int MAX_WH_PERCENTAGE_FROM_SCREEN_WH = 50;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface DRAW_LINE_TEST {

		public static int ID = 30;
		public static int NUM_LINES = 1000;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface GEOMETRIC_PRIMITIVES_TEST_MENU {

		public static int ID = 31;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface IMAGE_TESTS_MENU {

		public static int ID = 32;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface TEST_IMAGE_SIZES {

		public static int ID = 33;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface DRAW_IMAGE_TEST {

		public static int ID = 34;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface TEST_IMAGE_TRANSFORM {

		public static int ID = 35;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface DRAW_REGION_TEST {

		public static int ID = 36;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
	}

	public interface FILL_TRIANGLE_TEST {

		public static int ID = 37;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
		public static int NUM_TRIANGLES = 1000;
	}

	public interface DRAW_ARC_TEST {

		public static int ID = 38;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
		public static int NUM_ARCS = 1000;
	}

	public interface FILL_ARC_TEST {

		public static int ID = 39;
		public static int COLOR_BACKGROUND = 0x000000;
		public static int TEXT_COLOR = 0x00FF00;
		public static int NUM_ARCS = 1000;
	}
}
