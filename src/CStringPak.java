/*******************************************************************************
*    Cristian Alexandrescu                                                     *
*    2163013577ba2bc237f22b3f4d006856                                          *
*    11a4bb2c77aca6a9927b85f259d9af10db791ce5cf884bb31e7f7a889d4fb385          *
*    bc9a53289baf23d369484f5343ed5d6c                                          *
*******************************************************************************/


public class CStringPak {

	public CStringPak() {
	}

	public void LoadStrings(byte[] b) {
		CResPak res = new CResPak(b);
		res.Open();

		int TotalLen = 0;
		for (int i = 0; i < res.GetNoSections(); i++) {
			TotalLen += res.ReadSection(i).length;
		}

		m_StringBuffer = new byte[TotalLen];
		m_StringBufferOffsets = new short[res.GetNoSections() + 1];
		for (int i = 0; i < res.GetNoSections(); i++) {
			m_StringBufferOffsets[i] = (short) (res.m_SectionOffsets[i] - res.m_SectionOffsets[0]);
			System.arraycopy(res.m_Sections[i], 0, m_StringBuffer, m_StringBufferOffsets[i], res.GetSectionLength(i));
		}
		m_StringBufferOffsets[res.GetNoSections()] = (short) (res.m_SectionOffsets[res.m_NoSections] - res.m_SectionOffsets[0]);
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

	public final String GetString(int index) {
		return new String(m_StringBuffer, m_StringBufferOffsets[index], m_StringBufferOffsets[index + 1] - m_StringBufferOffsets[index]);
	}
	byte[] m_StringBuffer;
	short[] m_StringBufferOffsets;
}
