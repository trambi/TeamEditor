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

import junit.framework.TestResult;
import junit.framework.TestSuite;

public class BBELTest {
	
	public static void main(String[] args) {
		TestSuite mysuite = suite();
		TestResult result = new TestResult();
		mysuite.run(result);
	}

	public static TestSuite suite()
	{
	  TestSuite suite = new TestSuite();
	  
	  suite.addTest(new BBELInjuryConverterTest());
	  suite.addTest(new BBELSkillConverterTest());
	  suite.addTest(new BBELPositionConverterTest());
	  suite.addTest(new BBELTeamConverterTest());
	  return suite;
	}

}
