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

import org.tisseursdechimeres.bb.Skill;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import junit.framework.TestCase;

public class SkillTest extends TestCase {

	public void testExtractFromElement(){
		Skill extracted = new Skill();
		try {
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			Document doc = constructeur.newDocument();
			Element skillElt = doc.createElement(Skill.XML_TAG);
			skillElt.setTextContent("Test content");
			skillElt.setAttribute(Skill.XML_COST_ATTRIBUTE,String.valueOf(1));
			skillElt.setAttribute(Skill.XML_MOV_BONUS_ATTRIBUTE,String.valueOf(2));
			skillElt.setAttribute(Skill.XML_STR_BONUS_ATTRIBUTE,String.valueOf(3));
			skillElt.setAttribute(Skill.XML_AG_BONUS_ATTRIBUTE,String.valueOf(4));
			skillElt.setAttribute(Skill.XML_AR_BONUS_ATTRIBUTE,String.valueOf(5));
			extracted.extractFromElement(skillElt);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		assertEquals(extracted.getName(),"Test content");
		assertEquals(extracted.getValue(),new Integer(1));
		assertEquals(extracted.getMovBonus(),new Integer(2));
		assertEquals(extracted.getStrBonus(),new Integer(3));
		assertEquals(extracted.getAgBonus(),new Integer(4));
		assertEquals(extracted.getArBonus(),new Integer(5));
		
	}
	
	public void testToDOMLinkElement(){
		Skill skill = new Skill();
		Element elt = null ;
		try {
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			Document doc = constructeur.newDocument();
			skill.setName("Test content");
			elt = skill.toDOMLinkElement(doc);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		assertNotNull(elt);
		assertEquals(skill.getName(),elt.getTextContent());
		assertEquals(Skill.XML_TAG,elt.getTagName());
	}
}
