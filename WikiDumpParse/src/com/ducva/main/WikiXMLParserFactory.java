package com.ducva.main;

public class WikiXMLParserFactory {
	public static WikiXMLParser getSAXParser(String fileName) {
		return new WikiXMLSAXParser(fileName);
	}
}
