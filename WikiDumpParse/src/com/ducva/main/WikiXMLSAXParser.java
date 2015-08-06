package com.ducva.main;

import java.io.InputStream;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class WikiXMLSAXParser extends WikiXMLParser {
	private XMLReader xmlReader;
	private PageCallbackHandler pageHandler = null;

	public WikiXMLSAXParser(InputStream is) {
		super(is);
		this.initReaderHandler();
	}

	public WikiXMLSAXParser(String fileName) {
		super(fileName);
		this.initReaderHandler();
	}

	private void initReaderHandler() {
		try {
			xmlReader = XMLReaderFactory.createXMLReader();
			pageHandler = new IteratorHandler(this);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPageCallback(PageCallbackHandler handler) throws Exception {
		pageHandler = handler;
	}

	public void parse() throws Exception {
		xmlReader.setContentHandler(new SAXPageCallbackHandler(pageHandler));
		xmlReader.parse(getInputSource());
	}

	@Override
	public WikiPageIterator getIterator() throws Exception {
		if (!(pageHandler instanceof IteratorHandler)) {
			throw new Exception("Custom page callback found. Will not iterate.");
		}
		throw new UnsupportedOperationException();
	}

	public static void parseWikipediaDump(String dumpFile, PageCallbackHandler handler) throws Exception {
		WikiXMLParser wxsp = WikiXMLParserFactory.getSAXParser(dumpFile);
		wxsp.setPageCallback(handler);
		wxsp.parse();
	}
}
