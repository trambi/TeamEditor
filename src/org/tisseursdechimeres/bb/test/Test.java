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


import junit.framework.TestResult;
import junit.framework.TestSuite;

public class Test {
	
	public static void main(String[] args) {
		TestSuite mysuite = suite();
		TestResult result = new TestResult();
		mysuite.run(result);
	}

	public static TestSuite suite()
	{
	  TestSuite suite = new TestSuite();
	  suite.addTest(new SkillTest());
	  suite.addTest(new InjuryTest());
	  suite.addTest(new SkillCategoryTest());
	  return suite;
	}

}
