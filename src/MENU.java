/*******************************************************************************
*    Cristian Alexandrescu                                                     *
*    2163013577ba2bc237f22b3f4d006856                                          *
*    11a4bb2c77aca6a9927b85f259d9af10db791ce5cf884bb31e7f7a889d4fb385          *
*    bc9a53289baf23d369484f5343ed5d6c                                          *
*******************************************************************************/


public interface MENU {

	public interface MAIN {

		public static int OPT_DIPLAY_SIZE = 0;
		public static int OPT_KEY_CODES = 1;
		public static int OPT_VIDEO = 2;
		public static int OPT_AUDIO = 3;
		public static int OPT_CPU = 4;
		public static int OPT_MEMORY = 5;
		public static int OPT_EXIT = 100;
	}

	public interface CPU {

		public static int OPT_ARITHMETIC = 0;
		public static int OPT_LOGICAL = 1;
		public static int OPT_SORTING = 2;
		public static int OPT_PRIME_NUMBERS = 3;
		public static int OPT_RECURSIVE = 4;
		public static int OPT_BACK = 100;
	}

	public interface ARITHMETIC {

		public static int OPT_ADD = 0;
		public static int OPT_SUBSTRACTION = 1;
		public static int OPT_MULTIPLY = 2;
		public static int OPT_DIVIDE = 3;
		public static int OPT_BACK = 100;
	}

	public interface LOGICAL {

		public static int OPT_SHIFT_LEFT = 0;
		public static int OPT_SHIFT_RIGHT = 1;
		public static int OPT_AND = 2;
		public static int OPT_OR = 3;
		public static int OPT_XOR = 4;
		public static int OPT_BACK = 100;
	}

	public interface SORTING {

		public static int OPT_BUBBLE_SORT = 0;
		public static int OPT_Q_SORT = 1;
		public static int OPT_MERGE_SORT = 2;
		public static int OPT_HEAPSORT = 3;
		public static int OPT_BACK = 100;
	}

	public interface VIDEO {

		public static int OPT_VIDEO_MEMORY_SIZE = 0;
		public static int OPT_GEOMETRIC_PRIMITIVES_TESTS = 1;
		public static int OPT_IMAGE_TESTS = 2;
		public static int OPT_BACK = 100;
	}

	public interface GEOMETRIC_PRIMITIVES_TESTS_MENU {

		public static int OPT_DRAW_RECT_TEST = 0;
		public static int OPT_FILL_RECT_TEST = 1;
		public static int OPT_DRAW_LINE_TEST = 2;
		public static int OPT_FILL_TRIANGLE_TEST = 3;
		public static int OPT_DRAW_ARC_TEST = 4;
		public static int OPT_FILL_ARC_TEST = 5;
		public static int OPT_BACK = 100;
	}

	public interface ALLOCATE_IMAGES_MENU {

		public static int OPT_100 = 0;
		public static int OPT_50 = 1;
		public static int OPT_20 = 2;
		public static int OPT_10 = 3;
		public static int OPT_5 = 4;
		public static int OPT_4 = 5;
		public static int OPT_BACK = 100;
	}

	public interface IMAGE_TESTS_MENU {

		public static int OPT_DRAW_IMAGE = 0;
		public static int OPT_DRAW_REGION = 1;
		public static int OPT_BACK = 100;
	}

	public interface TEST_IMAGE_SIZES {

		public static int OPT_IMAGE_TEST_4X4 = 0;
		public static int OPT_IMAGE_TEST_8X8 = 1;
		public static int OPT_IMAGE_TEST_10X10 = 2;
		public static int OPT_IMAGE_TEST_12X12 = 3;
		public static int OPT_IMAGE_TEST_16X16 = 4;
		public static int OPT_IMAGE_TEST_20X20 = 5;
		public static int OPT_IMAGE_TEST_32X32 = 6;
		public static int OPT_IMAGE_TEST_48X48 = 7;
		public static int OPT_IMAGE_TEST_64X64 = 8;
		public static int OPT_BACK = 100;
	}

	public interface TEST_IMAGE_TRANSFORM {

		public static int OPT_IMAGE_TRANS_NONE = 0;
		public static int OPT_IMAGE_TRANS_ROT90 = 1;
		public static int OPT_IMAGE_TRANS_ROT180 = 2;
		public static int OPT_IMAGE_TRANS_ROT270 = 3;
		public static int OPT_IMAGE_TRANS_MIRROR = 4;
		public static int OPT_IMAGE_TRANS_MIRROR_ROT90 = 5;
		public static int OPT_IMAGE_TRANS_MIRROR_ROT180 = 6;
		public static int OPT_IMAGE_TRANS_MIRROR_ROT270 = 7;
		public static int OPT_BACK = 100;
	}
	public static int NO_SELECTION = -1;

	public interface MENU_TYPE {

		public static int SIMPLE_LIST = 0;
		public static int VERTICAL_SCROLL_LIST = 1;
	}
}
