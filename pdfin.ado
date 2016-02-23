cap prog drop pdfin

prog def pdfin

	version 14

	syntax anything(name=subfile id="Subcommand and File Path") [, ///
	Rectangle(string asis) Columns(string asis) Pages(string asis) ///
	Password(string asis) Guess noBASIC LINERETurns DBug]

	if `"`basic'"' == "nobasic" loc extract spreadsheet
	else loc extract basic

    if `"`rectangle'"' == "" loc rectangle `""""'
    if `"`columns'"' == "" loc columns `""""'
	if `"`pages'"' == "" loc pages `""""'
	if `"`password'"' == "" loc password `""""'
	if `"`guess'"' == "" loc guess `""""'
	if `"`linereturns'"' == "" loc linereturns `""""'
	if `"`dbug'"' == "" loc dbug `""""'


	gettoken subcmd file : subfile

	if (`"`subcmd'"' == "intable") {


		javacall org.paces.Stata.PDF.StataPDF intable, ///
		args(`file' `rectangle' `columns' `pages' `password' ///
		`guess' `extract' `linereturns' `dbug')

	}


end


