/*******************************************************************************
*    Cristian Alexandrescu                                                     *
*    2163013577ba2bc237f22b3f4d006856                                          *
*    11a4bb2c77aca6a9927b85f259d9af10db791ce5cf884bb31e7f7a889d4fb385          *
*    bc9a53289baf23d369484f5343ed5d6c                                          *
*******************************************************************************/


public interface KEY {

	public static int NUMERIC0 = 1 << 1;
	public static int NUMERIC1 = 1 << 2;
	public static int NUMERIC2 = 1 << 3;
	public static int NUMERIC3 = 1 << 4;
	public static int NUMERIC4 = 1 << 5;
	public static int NUMERIC5 = 1 << 6;
	public static int NUMERIC6 = 1 << 7;
	public static int NUMERIC7 = 1 << 8;
	public static int NUMERIC8 = 1 << 9;
	public static int NUMERIC9 = 1 << 10;
	public static int SOFT_L = 1 << 11;
	public static int SOFT_R = 1 << 12;
	public static int SELECT = 1 << 13;
	public static int LEFT = 1 << 14;
	public static int RIGHT = 1 << 15;
	public static int UP = 1 << 16;
	public static int DOWN = 1 << 17;
	public static int POUND = 1 << 18;
	public static int STAR = 1 << 19;
}
