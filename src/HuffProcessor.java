import java.util.PriorityQueue;

/**
 * Although this class has a history of several years,
 * it is starting from a blank-slate, new and clean implementation
 * as of Fall 2018.
 * <P>
 * Changes include relying solely on a tree for header information
 * and including debug and bits read/written information
 * 
 * @author Owen Astrachan
 *
 * Revise
 */

public class HuffProcessor {

	private class HuffNode implements Comparable<HuffNode> {
		HuffNode left;
		HuffNode right;
		int value;
		int weight;

		public HuffNode(int val, int count) {
			value = val;
			weight = count;
		}
		public HuffNode(int val, int count, HuffNode ltree, HuffNode rtree) {
			value = val;
			weight = count;
			left = ltree;
			right = rtree;
		}

		public int compareTo(HuffNode o) {
			return weight - o.weight;
		}
	}

	public static final int BITS_PER_WORD = 8;
	public static final int BITS_PER_INT = 32;
	public static final int ALPH_SIZE = (1 << BITS_PER_WORD); 
	public static final int PSEUDO_EOF = ALPH_SIZE;
	public static final int HUFF_NUMBER = 0xface8200;
	public static final int HUFF_TREE  = HUFF_NUMBER | 1;

	private boolean myDebugging = false;
	
	public HuffProcessor() {
		this(false);
	}
	
	public HuffProcessor(boolean debug) {
		myDebugging = debug;
	}

	/**
	 * Compresses a file. Process must be reversible and loss-less.
	 *
	 * @param in
	 *            Buffered bit stream of the file to be compressed.
	 * @param out
	 *            Buffered bit stream writing to the output file.
	 */
public void compress(BitInputStream in, BitOutputStream out){
		int [] counts = new int [1+ ALPH_SIZE];
		counts = getCounts (in); 
		HuffNode root = makeTree(counts); 
		in.reset();
		out.writeBits(BITS_PER_INT, HUFF_TREE);
		writeTree(root,out); 
		String [] encodings = new String [ALPH_SIZE +1];
		makeEncodings(root, "", encodings); 

		in.reset();
		
		while (true) 
		{
			int newB = in.readBits(BITS_PER_WORD);
			if (newB == -1) break;

			String code = encodings[newB];
			if (code != null)
				out.writeBits(code.length(), Integer.parseInt(code, 2));
		}
	    String code = encodings[PSEUDO_EOF];
    out.writeBits(code.length(), Integer.parseInt(code,2));

		// remove all this code when implementing compress
		// while (true){
		// 	int val = in.readBits(BITS_PER_WORD);
		// 	if (val == -1) break;
		// 	out.writeBits(BITS_PER_WORD, val);
		// }
		out.close();
	}

private void makeEncodings(HuffProcessor.HuffNode node, String s, String[] encodings) {
	if (node.right == null && node.right == null)
	{
		encodings[ node.value] = s; 
	}
	makeEncodings(node.left, s+ "0", encodings); 
	makeEncodings(node.right, s+ "1", encodings); 

	}

private void writeTree(HuffProcessor.HuffNode node, BitOutputStream out) {
if (node.right != null || node.left != null) {
			out.writeBits(1, 0);
			writeTree(node.left, out);
			writeTree(node.right, out);
		}
		else 
		{
			out.writeBits(1, 1); 
			out.writeBits(1 + BITS_PER_WORD, node.value);
		}
	
	}

private HuffProcessor.HuffNode makeTree(int[] counts) {
		PriorityQueue<HuffNode> pq = new PriorityQueue<>();
	for(int i =0; i<counts.length; i++) {

    pq.add(new HuffNode(i,counts[i],null,null));
	}pq.add(new HuffNode(PSEUDO_EOF,1,null,null)); // account for PSEUDO_EOF having a single occurrence

	while (pq.size() > 1) {
   HuffNode left = pq.remove();
   HuffNode right = pq.remove();
   HuffNode t = new HuffNode (0, left.weight + right.weight, left, right);
   // create new HuffNode t with weight from
   // left.weight+right.weight and left, right subtrees
   pq.add(t);
	}
	HuffNode root = pq.remove();
	return root;

	}

	private int[] getCounts(BitInputStream in) {
		int [] vals = new int [ALPH_SIZE+1]; 
		int bits = in.readBits(BITS_PER_WORD);

		while (bits != -1) 
		{
			vals[bits]++;
			bits = in.readBits(BITS_PER_WORD);
		}
		vals [PSEUDO_EOF] =1;
		return vals;
	}

	/**
	 * Decompresses a file. Output file must be identical bit-by-bit to the
	 * original.
	 *
	 * @param in
	 *            Buffered bit stream of the file to be decompressed.
	 * @param out
	 *            Buffered bit stream writing to the output file.
	 */
	public void decompress(BitInputStream in, BitOutputStream out){

		// remove all code when implementing decompress

		int bits = in.readBits(BITS_PER_INT);
		if (bits != HUFF_TREE)
		{
			throw new HuffException("invalid magic number" + bits);
		}
		HuffNode root = readTree (in); 
		HuffNode current = root;
		while (true)
		{
			int oneB = in.readBits(1); 
			if (oneB == -1)
			{
				throw new HuffException ("bit reading failed");
			}
			else 
			{
				if (oneB == 0)
				{
					current = current.left;
				}
				else 
				{
					current  = current.right; 
				}
				if (current.left == null && current.right == null) 
				{
					if (current.value == PSEUDO_EOF)
					{
						break; 
					}
					else 
					{
						out.writeBits(BITS_PER_WORD, current.value); 
						current = root;
					}
				}
			}

		}
		out.close();
		// while (true){
		// 	int val = in.readBits(BITS_PER_WORD);
		// 	if (val == -1) break;
		// 	out.writeBits(BITS_PER_WORD, val);
		// }
		
	}
	private HuffNode readTree(BitInputStream in) {
       int  bit = in.readBits(1);
        if (bit == -1) throw new HuffException ("bit reading failed");
        if (bit == 0) {
                HuffNode left = readTree(in);
                HuffNode right = readTree(in);
                 return new HuffNode(0,0,left,right);
        }
        else {
            int value = in.readBits( BITS_PER_WORD+1);
            return new HuffNode(value,0,null,null);
        }
  }

}