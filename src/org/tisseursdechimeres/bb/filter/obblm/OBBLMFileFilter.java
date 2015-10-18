/*
Copyright (C) 2011  Bertrand MADET

This org.tisseursdechimeres.bb.filter.obblm is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.filter.obblm is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.filter.obblm.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tisseursdechimeres.bb.filter.obblm;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.filechooser.FileFilter;

public class OBBLMFileFilter extends FileFilter
{
  public boolean accept(File iFile)
  {
    boolean bReturn = false;
    String filename = iFile.getName();
    if (filename.endsWith(".xml"))
      bReturn = true;
    else if (iFile.isDirectory()) {
      bReturn = true;
    }
    return bReturn;
  }

  public String getDescription() {
    ResourceBundle i18nResource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.teameditor.TeamEditor", Locale.getDefault());
    return i18nResource.getString("team_obblm_file_filer_description");
  }
}