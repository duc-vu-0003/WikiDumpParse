package com.ducva.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.tools.bzip2.CBZip2InputStream;
import org.xml.sax.InputSource;

public abstract class WikiXMLParser {
	private String wikiXMLFile = null;
	private BufferedReader wikiXMLBufferedReader = null;
	protected WikiPage currentPage = null;

	public WikiXMLParser(String fileName) {
		wikiXMLFile = fileName;
	}

	public WikiXMLParser(InputStream is) {
		wikiXMLBufferedReader = new BufferedReader(new InputStreamReader(is));
	}

	public abstract void setPageCallback(PageCallbackHandler handler) throws Exception;

	public abstract void parse() throws Exception;

	public abstract WikiPageIterator getIterator() throws Exception;

	protected InputSource getInputSource() throws Exception {
		BufferedReader br = null;

		if (this.wikiXMLBufferedReader != null) {
			br = this.wikiXMLBufferedReader;
		} else if (wikiXMLFile.endsWith(".gz")) {
			br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(wikiXMLFile))));
		} else if (wikiXMLFile.endsWith(".bz2")) {
			FileInputStream fis = new FileInputStream(wikiXMLFile);
			byte[] ignoreBytes = new byte[2];
			fis.read(ignoreBytes); 
			br = new BufferedReader(new InputStreamReader(new CBZip2InputStream(fis)));
		} else {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(wikiXMLFile)));
		}

		return new InputSource(br);
	}

	protected void notifyPage(WikiPage page) {
		currentPage = page;

	}
}
