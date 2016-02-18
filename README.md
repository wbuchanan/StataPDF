# Stata Plugin for working with PDF Files
To make the project a bit easier to maintain, this package contains a 
repackaging of the same parser used by 
[Tabula](https://github.com/tabulapdf/tabula-java).  Some modifications have 
been made to make it more applicable to the Stata environment (e.g., checking
 and removing repeated instances of table headers, etc...).  It will also be 
 set up to load the data directly into Stata vs writing a file.  
 
Due to limitations in the Java API, there is currently only support available
 for setting double and/or string values.  In the future it may be possible 
 to better control the return numeric types and reduce the load on the 
 system's memory.   