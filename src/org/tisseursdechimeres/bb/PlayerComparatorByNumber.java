/*
Copyright (C) 2010-2011  Bertrand MADET

This org.tisseursdechimeres.bb is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.tisseursdechimeres.bb;

import java.util.Comparator;

public class PlayerComparatorByNumber implements Comparator<Player> {

	@Override
	public int compare(Player player1, Player player2) {
		int intReturn = player1.getNumber() - player2.getNumber(); 
		if( 0 == intReturn ){
			intReturn = player1.getValue() - player2.getValue();
		}
		return intReturn;
	}

}
