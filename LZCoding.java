/*********************
* Author: Maxx Boehme
*********************/

/*
   Implementing the trie and traverse it recursively
   Used the LZ78 compression algorithm
   Implemented how to calculate entropy and lower bound
*/

import java.util.ArrayList;

public class LZCoding
{
	public static void main(String[] args) throws Exception {
	//Compression algorithm
	if("c".equals(args[0])){
		IO.Compressor compressor = new IO.Compressor(args[1]);
		char[] charArray = compressor.getCharacters();
		int i = 0;
		int trieIndex = 1;
		Node root = new Node(0, "");
		Node current = root;
		String cWord = "";
		//Loop through every character in the file
		while(i < charArray.length){
			cWord += charArray[i];
			ArrayList<Node> children = current.getChildren();
			boolean found = false;
			//Iterate through current level of trie and see if character is found
			for(int j = 0; j < children.size(); j++){
				if(cWord.equals(children.get(j).getWord())){
					current = children.get(j);
					found = true;
					break;
				}
			}
			//If character is not found create new node in trie and send the index of parent
			//and character that was used for new node.
			if(!found){
				current.addChild(new Node(trieIndex++, cWord));
				compressor.encode(current.getIndex(), charArray[i]);
				current = root;
				cWord = "";
			}

			i++;
		}

		compressor.finalize();
	//Decompression algorithm
	}else if("d".equals(args[0])){
		IO.Decompressor io = new IO.Decompressor(args[1]);
		//Initialize dictionary at 0 index and made the character an empty String
		Node root = new Node(0, "");
		int trieIndex = 1;

		IO.Pair current = io.decode();
		//Loops through all valid pairs in compress 
		while(current.isValid()){
			int index = current.getIndex();
			char letter = current.getCharacter();
			//recursively search through trie for index
			String output =  recTrie(root, index, letter, trieIndex++);
			io.append(output);

			current = io.decode();
		}

		io.append("");
		io.finalize();
	}else{
		//If user enters invalid command
		System.out.println("Did not enter correct command, use c for compress and d for decompress");
	}
	}
	//recursively search through trie for given index
	//When index is found creates new node and then returns string of new node
	private static  String recTrie(Node node, int i, char c, int t){
			ArrayList<Node> children = node.getChildren();
			if(node.getIndex() == i){
				if(c != 0){
					String s = node.getWord() + c;
					node.addChild(new Node(t, s));
					return s;
				}else{
					return node.getWord();
				}
			}else{
				for(Node n : children){
					String s = recTrie(n, i, c, t);
					if(s != null)
						return s;
				}
			}
			return null;
	}


	//Created Node class to handle creation of a trie
	private static class Node {
		private int index;
		private String word;
		private ArrayList<Node> children = new ArrayList<Node>();

		public Node(int i, String c){
			index = i;
			word = c;
		}

		public int getIndex(){
			return index;
		}

		public String getWord(){
			return word;
		}

		public ArrayList<Node> getChildren() {
			return children;
		}

		public void addChild(Node n) {
			children.add(n);
		}
	}
}
