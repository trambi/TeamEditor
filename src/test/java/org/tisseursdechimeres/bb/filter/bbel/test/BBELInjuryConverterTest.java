/*
Copyright (C) 2011  Bertrand MADET

This org.tisseursdechimeres.bb.filter.bbel.test is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.filter.bbel.test is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.filter.bbel.test.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.tisseursdechimeres.bb.filter.bbel.test;

import org.tisseursdechimeres.bb.Injury;
import org.tisseursdechimeres.bb.filter.bbel.BBELInjuryConverter;

import junit.framework.TestCase;

public class BBELInjuryConverterTest extends TestCase {
	public void testFromMissNextGame(){
		Injury inj = new Injury();
		BBELInjuryConverter converter = new BBELInjuryConverter();
		int index = 0;
		
		inj.setMissNextGame(true);
		inj.setSymbol("M");
		converter.fromInjury(inj);
		index = converter.getIndex();
		assertTrue( (index >= BBELInjuryConverter.MIN_MISS_NEXT_GAME_INDEX) && (index <= BBELInjuryConverter.MAX_MISS_NEXT_GAME_INDEX) );
	}
	
	public void testFromNiggling(){
		Injury inj = new Injury();
		BBELInjuryConverter converter = new BBELInjuryConverter();
		int index = 0;
		
		inj.setSymbol("N");
		converter.fromInjury(inj);
		index = converter.getIndex();
		assertTrue( (index >= BBELInjuryConverter.MIN_NIGGLING_INDEX) && (index <= BBELInjuryConverter.MAX_NIGGLING_INDEX) );
	}
	
	public void testFromMovMalus(){
		Injury inj = new Injury();
		BBELInjuryConverter converter = new BBELInjuryConverter();
		int index = 0;
		
		inj.setMovMalus(1);
		converter.fromInjury(inj);
		index = converter.getIndex();
		assertTrue( (index >= BBELInjuryConverter.MIN_MOV_MALUS_INDEX) && (index <= BBELInjuryConverter.MAX_MOV_MALUS_INDEX) );
	}
	
	public void testFromStrMalus(){
		Injury inj = new Injury();
		BBELInjuryConverter converter = new BBELInjuryConverter();
		int index = 0;
		
		inj.setStrMalus(1);
		converter.fromInjury(inj);
		index = converter.getIndex();
		assertTrue( (index >= BBELInjuryConverter.MIN_STR_MALUS_INDEX) && (index <= BBELInjuryConverter.MAX_STR_MALUS_INDEX) );
	}
	
	public void testFromAgMalus(){
		Injury inj = new Injury();
		BBELInjuryConverter converter = new BBELInjuryConverter();
		int index = 0;
		
		inj.setAgMalus(1);
		converter.fromInjury(inj);
		index = converter.getIndex();
		assertTrue( (index >= BBELInjuryConverter.MIN_AG_MALUS_INDEX) && (index <= BBELInjuryConverter.MAX_AG_MALUS_INDEX) );
	}
	
	public void testFromArMalus(){
		Injury inj = new Injury();
		BBELInjuryConverter converter = new BBELInjuryConverter();
		int index = 0;
		
		inj.setArMalus(1);
		converter.fromInjury(inj);
		index = converter.getIndex();
		assertTrue( (index >= BBELInjuryConverter.MIN_AR_MALUS_INDEX) && (index <= BBELInjuryConverter.MAX_AR_MALUS_INDEX) );
	}
}
