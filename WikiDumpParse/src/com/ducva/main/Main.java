package com.ducva.main;

public class Main {
	
	public static void main(String[] args) {
		try {
			WikiXMLParser wikiXMLParser = new WikiXMLSAXParser(Constant.PATH);
			
			wikiXMLParser.setPageCallback(new PageCallbackHandler() {
				
				@Override
				public void process(WikiPage page) {
					// TODO Auto-generated method stub
					System.out.println(page.getText());
					//page.getText();
				}
			});
                
			wikiXMLParser.parse();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
