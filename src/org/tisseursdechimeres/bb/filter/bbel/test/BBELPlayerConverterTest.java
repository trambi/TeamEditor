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

import org.tisseursdechimeres.bb.filter.bbel.BBELPlayerConverter;

import junit.framework.TestCase;

public class BBELPlayerConverterTest extends TestCase {
	
	public void testMovementConverterFrom(){
		assertEquals(8.333, BBELPlayerConverter.convertMovementFrom(1), 0.001);
		assertEquals(16.666, BBELPlayerConverter.convertMovementFrom(2), 0.001);
		assertEquals(25.000, BBELPlayerConverter.convertMovementFrom(3), 0.001);
		assertEquals(33.333, BBELPlayerConverter.convertMovementFrom(4), 0.001);
		assertEquals(41.666, BBELPlayerConverter.convertMovementFrom(5), 0.001);
		assertEquals(50.000, BBELPlayerConverter.convertMovementFrom(6), 0.001);
		assertEquals(58.333, BBELPlayerConverter.convertMovementFrom(7), 0.001);
		assertEquals(66.666, BBELPlayerConverter.convertMovementFrom(8), 0.001);
		assertEquals(75.000, BBELPlayerConverter.convertMovementFrom(9), 0.001);
		assertEquals(83.333, BBELPlayerConverter.convertMovementFrom(10), 0.001);
		assertEquals(91.666, BBELPlayerConverter.convertMovementFrom(11), 0.001);
	}
	
	public void testMovementConverterTo(){
		assertEquals(1, BBELPlayerConverter.convertMovementTo((float)8.333));
		assertEquals(2, BBELPlayerConverter.convertMovementTo((float)16.666));
		assertEquals(3, BBELPlayerConverter.convertMovementTo((float)25.000));
		assertEquals(4, BBELPlayerConverter.convertMovementTo((float)33.333));
		assertEquals(5, BBELPlayerConverter.convertMovementTo((float)41.666));
		assertEquals(6, BBELPlayerConverter.convertMovementTo((float)50.000));
		assertEquals(7, BBELPlayerConverter.convertMovementTo((float)58.333));
		assertEquals(8, BBELPlayerConverter.convertMovementTo((float)66.666));
		assertEquals(9, BBELPlayerConverter.convertMovementTo((float)75.000));
		assertEquals(10, BBELPlayerConverter.convertMovementTo((float)83.333));
		assertEquals(11, BBELPlayerConverter.convertMovementTo((float)91.666));
	}
	
	public void testStrengthConverterFrom(){
		assertEquals(30.0, BBELPlayerConverter.convertStrengthFrom(1), 0.001);
		assertEquals(40.0, BBELPlayerConverter.convertStrengthFrom(2), 0.001);
		assertEquals(50.0, BBELPlayerConverter.convertStrengthFrom(3), 0.001);
		assertEquals(60.0, BBELPlayerConverter.convertStrengthFrom(4), 0.001);
		assertEquals(70.0, BBELPlayerConverter.convertStrengthFrom(5), 0.001);
		assertEquals(80.0, BBELPlayerConverter.convertStrengthFrom(6), 0.001);
		assertEquals(90.0, BBELPlayerConverter.convertStrengthFrom(7), 0.001);
		assertEquals(100.0, BBELPlayerConverter.convertStrengthFrom(8), 0.001);
	}
	
	public void testStrengthConverterTo(){
		assertEquals(1, BBELPlayerConverter.convertStrengthTo((float)30.0));
		assertEquals(2, BBELPlayerConverter.convertStrengthTo((float)40.0));
		assertEquals(3, BBELPlayerConverter.convertStrengthTo((float)50.0));
		assertEquals(4, BBELPlayerConverter.convertStrengthTo((float)60.0));
		assertEquals(5, BBELPlayerConverter.convertStrengthTo((float)70.0));
		assertEquals(6, BBELPlayerConverter.convertStrengthTo((float)80.0));
		assertEquals(7, BBELPlayerConverter.convertStrengthTo((float)90.0));
		assertEquals(8, BBELPlayerConverter.convertStrengthTo((float)100.0));
	}
	
	public void testAgilityConverterFrom(){
		assertEquals(16.666, BBELPlayerConverter.convertAgilityFrom(1), 0.001);
		assertEquals(33.333, BBELPlayerConverter.convertAgilityFrom(2), 0.001);
		assertEquals(50.000, BBELPlayerConverter.convertAgilityFrom(3), 0.001);
		assertEquals(66.666, BBELPlayerConverter.convertAgilityFrom(4), 0.001);
		assertEquals(83.333, BBELPlayerConverter.convertAgilityFrom(5), 0.001);
		assertEquals(100.000, BBELPlayerConverter.convertAgilityFrom(6), 0.001);
	}
	
	public void testAgilityConverterTo(){
		assertEquals(1, BBELPlayerConverter.convertAgilityTo((float)16.666));
		assertEquals(2, BBELPlayerConverter.convertAgilityTo((float)33.333));
		assertEquals(3, BBELPlayerConverter.convertAgilityTo((float)50.000));
		assertEquals(4, BBELPlayerConverter.convertAgilityTo((float)66.666));
		assertEquals(5, BBELPlayerConverter.convertAgilityTo((float)83.333));
		assertEquals(6, BBELPlayerConverter.convertAgilityTo((float)100.000));
	}
	
	public void testArmourConverterFrom(){
		assertEquals(8.333, BBELPlayerConverter.convertArmourFrom(3), 0.001);
		assertEquals(16.666, BBELPlayerConverter.convertArmourFrom(4), 0.001);
		assertEquals(27.777, BBELPlayerConverter.convertArmourFrom(5), 0.001);
		assertEquals(41.666, BBELPlayerConverter.convertArmourFrom(6), 0.001);
		assertEquals(58.333, BBELPlayerConverter.convertArmourFrom(7), 0.001);
		assertEquals(72.222, BBELPlayerConverter.convertArmourFrom(8), 0.001);
		assertEquals(83.333, BBELPlayerConverter.convertArmourFrom(9), 0.001);
		assertEquals(91.666, BBELPlayerConverter.convertArmourFrom(10), 0.001);
	}
	
	public void testArmourConverterTo(){
		assertEquals(3, BBELPlayerConverter.convertArmourTo((float)8.333));
		assertEquals(4, BBELPlayerConverter.convertArmourTo((float)16.666));
		assertEquals(5, BBELPlayerConverter.convertArmourTo((float)27.777));
		assertEquals(6, BBELPlayerConverter.convertArmourTo((float)41.666));
		assertEquals(7, BBELPlayerConverter.convertArmourTo((float)58.333));
		assertEquals(8, BBELPlayerConverter.convertArmourTo((float)72.222));
		assertEquals(9, BBELPlayerConverter.convertArmourTo((float)83.333));
		assertEquals(10, BBELPlayerConverter.convertArmourTo((float)91.666));
	}
}
