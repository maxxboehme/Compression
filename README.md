Author: Maxx Boehme
Date: 8/9/2013
Version: 1
Last Modified: 8/9/2013

	LowerBound.java
Program that given a text file that contains only ASCII characters, compute the lower bound of bits needed for lossless compression of the file.

Example use
command-line$ java -ea LowerBound test.txt

	LZCoding.java
Compression
Program is invoked with the name of a test file.  This program will create a compressed file and write it to disk.
It is implemented using the "trie" based approach to construct the dictionary. The name of the compressed file is obtained by concatenating the name
of the input file with the string ".cpz".

Example
command-line$ java -ea LZcoding c test

Decompression
Program is invokeable with the name of a compressed file, and will write a decompressed fiel to the disk.  As with the compression part the dictionary
will be constructed using the "trie" structure. The decompressed file is determined by concatenating the name of the input file with the string ".dcz".

Example
command-line$ java -ea LZcoding d test.cpz