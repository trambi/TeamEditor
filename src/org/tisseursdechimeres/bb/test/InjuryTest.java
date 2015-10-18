/*
Copyright (C) 2010  Bertrand MADET

This org.tisseursdechimeres.bb.test is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.test is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.test.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.tisseursdechimeres.bb.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.tisseursdechimeres.bb.Injury;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import junit.framework.TestCase;

public class InjuryTest extends TestCase {
	public void testExtractFromElement(){
		boolean noException = true;
		Injury extracted = new Injury();
		try {
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			Document doc = constructeur.newDocument();
			Element injuryElt = doc.createElement(Injury.XML_TAG);
			injuryElt.setTextContent("Test content");
			injuryElt.setAttribute(Injury.XML_MISS_NEXT_GAME_ATTRIBUTE,String.valueOf(true));
			injuryElt.setAttribute(Injury.XML_MOV_MALUS_ATTRIBUTE,String.valueOf(1));
			injuryElt.setAttribute(Injury.XML_STR_MALUS_ATTRIBUTE,String.valueOf(2));
			injuryElt.setAttribute(Injury.XML_AG_MALUS_ATTRIBUTE,String.valueOf(3));
			injuryElt.setAttribute(Injury.XML_AR_MALUS_ATTRIBUTE,String.valueOf(4));
			injuryElt.setAttribute(Injury.XML_MAX_NUMBER_ATTRIBUTE,String.valueOf(5));
			injuryElt.setAttribute(Injury.XML_SYMBOL_ATTRIBUTE,"TEST");
			extracted.extractFromElement(injuryElt);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			noException = false;
		}
		assertEquals(noException,true);
		assertEquals(extracted.getName(),"Test content");
		assertEquals(extracted.isMissNextGame(),true);
		assertEquals(extracted.getMovMalus(),1);
		assertEquals(extracted.getStrMalus(),2);
		assertEquals(extracted.getAgMalus(),3);
		assertEquals(extracted.getArMalus(),4);
		assertEquals(extracted.getMaxNumber(),5);
		assertEquals(extracted.getSymbol(),"TEST");
		
	}

}
