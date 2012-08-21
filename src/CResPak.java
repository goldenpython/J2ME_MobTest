/*******************************************************************************
*    Cristian Alexandrescu                                                     *
*    2163013577ba2bc237f22b3f4d006856                                          *
*    11a4bb2c77aca6a9927b85f259d9af10db791ce5cf884bb31e7f7a889d4fb385          *
*    bc9a53289baf23d369484f5343ed5d6c                                          *
*******************************************************************************/


import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CResPak {

	public CResPak(String ResPakName) {
		m_ResPackName = ResPakName;
	}

	public CResPak(byte[] buffer) {
		m_buffer = buffer;
	}

	public boolean Open() {
		if (m_ResPackName != null) {
			OpenStream(m_ResPackName);
		} else {
			m_InputStream = new ByteArrayInputStream(m_buffer);
		}

		ReadHeader(m_InputStream);

		return true;
	}

	public boolean OpenStream(String sResPackName) {
		System.gc();

		m_InputStream = "".getClass().getResourceAsStream(m_ResPackName);

		try {
			m_buffer = new byte[ReadInt(m_InputStream)];
			m_InputStream.read(m_buffer, 4, m_buffer.length - 4);
			m_InputStream.close();
			m_InputStream = new ByteArrayInputStream(m_buffer);
		} catch (Exception e) {
			return false;
		}


		if (m_InputStream == null) {
			return false;
		}

		return true;
	}

	private boolean ReadHeader(InputStream stream) {
		m_InputStream = stream;

		ReadInt(stream);
		int version = ReadInt(stream);
		if (version != 0) {
			return false;
		}

		m_NoSections = ReadInt(stream);

		m_SectionOffsets = new int[m_NoSections + 1];

		for (int i = 0; i < m_NoSections + 1; i++) {
			m_SectionOffsets[i] = ReadInt(stream);
		}

		m_Sections = new byte[m_NoSections][];

		return true;
	}

	public byte[] ReadSection(int SectionIndex) {
		if (m_Sections[SectionIndex] != null) {
			return m_Sections[SectionIndex];
		}

		try {
			m_InputStream.reset();
			m_InputStream.skip(m_SectionOffsets[SectionIndex]);


			byte[] b = new byte[m_SectionOffsets[SectionIndex + 1] - m_SectionOffsets[SectionIndex]];
			m_InputStream.read(b);

			return m_Sections[SectionIndex] = b;
		} catch (Exception e) {
			return null;
		}
	}

	public int GetNoSections() {
		return m_NoSections;
	}

	public void FreeSection(int SectionIndex) {
		m_Sections[SectionIndex] = null;
	}

	public int GetSectionLength(int SectionIndex) {
		return m_SectionOffsets[SectionIndex + 1] - m_SectionOffsets[SectionIndex];
	}

	public void Close() {
		try {
			m_InputStream.close();
			m_InputStream = null;

			System.gc();
		} catch (Exception e) {
		}
	}

	public static int ReadInt(InputStream stream) {
		try {
			return (stream.read() & 0xFF) | ((stream.read() & 0xFF) << 8) | ((stream.read() & 0xFF) << 16) | ((stream.read() & 0xFF) << 24);
		} catch (Exception e) {
			return 0;
		}
	}
	InputStream m_InputStream;
	byte[] m_buffer;
	String m_ResPackName;
	int m_NoSections;
	int[] m_SectionOffsets;
	byte[][] m_Sections;
}
