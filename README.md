# Stata Plugin for working with PDF Files
Plugin provides methods to import data directly from PDF files and will contain 
tools for the construction of PDF files in the future.

# Examples

The way the insheet command will be designed will include a syntax something 
similar to:

```
// Here the file would load all of the data (text and all) into the dataset
// in memory
insheetpdf using /Users/uid/Desktop/mypdf.pdf, replace

// This would 
insheetpdf using /Users/uid/Desktop/mypdf.pdf, area(0 0 0 0)
