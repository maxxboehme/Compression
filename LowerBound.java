/*********************
* Author: Maxx Boehme
*********************/

import java.text.DecimalFormat;
public class LowerBound{

	public static void main(String[] args) throws Exception {

	IO.Compressor compressor = new IO.Compressor(args[0]);
	//Create charArray to hold all the characters in the file.
	char[] charArray = compressor.getCharacters();
	compressor.finalize();
	double[] freq = new double[256];
	/*Calculating the frequency of each character in the charArray*/
	for(int i = 0; i<charArray.length; i++){
		freq[charArray[i]]++;
	}
	//Calculating the probability of each character
	for(int i = 0; i<freq.length; i++){
		freq[i] = freq[i]/(charArray.length-1);
	}
	double entropy = 0;
	//Calculating the entropy. Not including the end of file character
	for(int i = 1; i<freq.length; i++){
		if(freq[i]!=0)
			entropy += freq[i]*(Math.log(freq[i])/Math.log(2));
	}
	entropy *= -1;
	DecimalFormat df = new DecimalFormat("0.000");
	System.out.println("The lower bound is "+df.format(entropy*(charArray.length-1))+"; the entropy is "+df.format(entropy)+".");
}
}
