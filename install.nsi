!define PRODUCT_NAME "TeamEditor"
!define PRODUCT_VERSION 0.99.26

Name "${PRODUCT_NAME} ${PRODUCT_VERSION}"
OutFile "Install${PRODUCT_NAME}.exe"

LicenseText "You must agree to this license before installing."
LicenseData "COPYING"

InstallDir "$PROGRAMFILES\TeamEditor"
InstallDirRegKey HKEY_LOCAL_MACHINE "SOFTWARE\TeamEditor" ""
DirText "Select the directory to install TeamEditor in:"

Section "" ; (default section)
SetOutPath "$INSTDIR"
File "${PRODUCT_NAME}-${PRODUCT_VERSION}.jar"
File COPYING
CreateDirectory "$INSTDIR\libs"
SetOutPath "$INSTDIR\libs"
File "libs\sqlitejdbc-v056.jar"
File "libs\bb-${PRODUCT_VERSION}.jar"
File "libs\bb_bbcode-${PRODUCT_VERSION}.jar"
File "libs\bb_filter-${PRODUCT_VERSION}.jar"
File "libs\bb_xml-${PRODUCT_VERSION}.jar"
File "libs\bb_bbel-${PRODUCT_VERSION}.jar"
File "libs\bb_obblm-${PRODUCT_VERSION}.jar"

; add files / whatever that need to be installed here.
WriteRegStr HKEY_LOCAL_MACHINE "SOFTWARE\TeamEditor" "" "$INSTDIR"
WriteRegStr HKEY_LOCAL_MACHINE "Software\Microsoft\Windows\CurrentVersion\Uninstall\TeamEditor" "DisplayName" "TeamEditor"
WriteRegStr HKEY_LOCAL_MACHINE "Software\Microsoft\Windows\CurrentVersion\Uninstall\TeamEditor" "UninstallString" '"$INSTDIR\uninst.exe"'
; write out uninstaller
WriteUninstaller "$INSTDIR\uninst.exe"
SectionEnd ; end of default section


; begin uninstall settings/section
UninstallText "This will uninstall TeamEditor from your system"

Section Uninstall
; add delete commands to delete whatever files/registry keys/etc you installed here.
Delete "$INSTDIR\uninst.exe"
DeleteRegKey HKEY_LOCAL_MACHINE "SOFTWARE\TeamEditor"
DeleteRegKey HKEY_LOCAL_MACHINE "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\TeamEditor"

Delete "$INSTDIR\${PRODUCT_NAME}-${PRODUCT_VERSION}.jar"
Delete "$INSTDIR\COPYING"

Delete "$INSTDIR\libs\sqlitejdbc-v056.jar"
Delete "$INSTDIR\libs\bb-${PRODUCT_VERSION}.jar"
Delete "$INSTDIR\libs\bb_bbcode-${PRODUCT_VERSION}.jar"
Delete "$INSTDIR\libs\bb_filter-${PRODUCT_VERSION}.jar"
Delete "$INSTDIR\libs\bb_xml-${PRODUCT_VERSION}.jar"
Delete "$INSTDIR\libs\bb_bbel-${PRODUCT_VERSION}.jar"
Delete "$INSTDIR\libs\bb_obblm-${PRODUCT_VERSION}.jar"
RMDir "$INSTDIR\libs"

RMDir "$INSTDIR"
SectionEnd ; end of uninstall section

; eof
