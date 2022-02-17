
public class Encryption {

	public Encryption() {
		super();

	}

	public String encryptePlaintext(String plaintext, String key) {
		String ciphertext = "";
		for (int x = 0; x < plaintext.length(); x++) {
			ciphertext += encrypte(plaintext.charAt(x), key);
		}
		return ciphertext;
	}

	public String decrypteCiphertext(String ciphertext, String key) {

		int counter = ciphertext.length() / 4;
		String plainText = "";
		for (int x = 0; x < counter; x++) {
			plainText += decrypte(ciphertext.substring(x * 4, (4 * x) + 4), key);

		}
		return plainText;

	}

	private String decrypte(String cipherText, String key) {
		String plainText = "";

		String stepOne = hexToBin(cipherText);
		cipherText = stepOne.substring(8, stepOne.length());
		String keyOfStepTwo = stepOne.substring(0, 8);

		String steptwo = decryptionStepTwo(cipherText, keyOfStepTwo);

		plainText = decryptionStepThree(steptwo, key);
		return plainText;
	}

	private String decryptionStepThree(String steptwo, String key) {
		String plainText = "";

		if (key.equals("preorder")) {
			int arr[] = new int[steptwo.length()];
			for (int y = 0; y < arr.length; y++) {
				arr[y] = Integer.parseInt(String.valueOf(steptwo.charAt(y)));
			}

			int counter = steptwo.length() / 2;
			for (int x = 0; x < counter; x++) {
				Tree tree = new Tree();
				tree.root = tree.insertLevelOrder(arr, tree.root, 0);
				String temp = "";
				temp = tree.preOrder(tree.root, temp);
				plainText = temp;
				for (int y = 0; y < arr.length; y++) {
					arr[y] = Integer.parseInt(String.valueOf(temp.charAt(y)));
				}
			}

		}
		if (key.equals("inorder")) {
			int arr[] = new int[steptwo.length()];
			for (int y = 0; y < arr.length; y++) {
				arr[y] = Integer.parseInt(String.valueOf(steptwo.charAt(y)));
			}

			int counter = steptwo.length() - 2;
			for (int x = 0; x < counter; x++) {
				Tree tree = new Tree();
				tree.root = tree.insertLevelOrder(arr, tree.root, 0);
				String temp = "";
				temp = tree.inOrder(tree.root, temp);
				plainText = temp;
				for (int y = 0; y < arr.length; y++) {
					arr[y] = Integer.parseInt(String.valueOf(temp.charAt(y)));
				}
			}

		}
		if (key.equals("postorder")) {
			int arr[] = new int[steptwo.length()];
			for (int y = 0; y < arr.length; y++) {
				arr[y] = Integer.parseInt(String.valueOf(steptwo.charAt(y)));
			}

			int counter = steptwo.length()-1 ;
			for (int x = 0; x < counter; x++) {
				Tree tree = new Tree();
				tree.root = tree.insertLevelOrder(arr, tree.root, 0);
				String temp = "";
				temp = tree.postOrder(tree.root, temp);
				plainText = temp;
				for (int y = 0; y < arr.length; y++) {
					arr[y] = Integer.parseInt(String.valueOf(temp.charAt(y)));
				}
			}

		}
		plainText = String.valueOf((char) getDecimal(plainText));
		return plainText;
	}

	private String encrypte(char input, String key) {
		// convert input to binary code
		String binaryCode = optimizeBinary(Integer.toBinaryString((int) input));
		System.out.println(binaryCode);

		String encryptedString = "";
		int arr[] = new int[binaryCode.length()];
		for (int x = 0; x < arr.length; x++) {
			arr[x] = Integer.parseInt(String.valueOf(binaryCode.charAt(x)));
		}

		// step 1

		String stepOne = encryptionStepOne(arr, key);
System.out.println("step 1 "+stepOne);
		// step 2

		String stepTwo = encryptionStepTwo(stepOne);
		System.out.println("step 1 "+stepTwo);

		// step 3
		encryptedString = convertBinaryToHex(stepTwo);
		return encryptedString;
	}

	// encryption process
	private String encryptionStepOne(int[] arr, String key) {
		Tree t2 = new Tree();
		t2.root = t2.insertLevelOrder(arr, t2.root, 0);
		String encryptedInput = "";
		if (key.equals("preorder")) {
			encryptedInput = t2.preOrder(t2.root, encryptedInput);
		}
		if (key.equals("inorder")) {
			encryptedInput = t2.inOrder(t2.root, encryptedInput);
		}
		if (key.equals("postorder")) {
			encryptedInput = t2.postOrder(t2.root, encryptedInput);
		}

		return encryptedInput;
	}

	private String encryptionStepTwo(String stepOne) {
		int sum = 0;
		for (int x = 0; x < stepOne.length(); x++) {
			sum += Integer.parseInt(String.valueOf(stepOne.charAt(x)));
		}
		// calculate the difference
		int result = 8 - sum;
		// convert to binary
		String binaryCode = optimizeBinary(Integer.toBinaryString((int) (String.valueOf(result).charAt(0))));
		System.out.println(binaryCode);

		String value = XORFunction(binaryCode, stepOne);

		return binaryCode + value;
	}

	private String decryptionStepTwo(String stepOne, String number) {
		String value = XORFunction(number, stepOne);
		return value;
	}

	// extra method
	private String XORFunction(String stringOne, String stringTwo) {

		String encryptedString = "";
		for (int x = 0; x < stringOne.length(); x++) {
			if (stringOne.charAt(x) == stringTwo.charAt(x)) {
				encryptedString += "0";
			} else {
				encryptedString += "1";
			}
		}
		return encryptedString;
	}

	private String convertBinaryToHex(String input) {
		String output = "";

		int counter = input.length() / 4;

		for (int x = 0; x < counter; x++) {
			String binaryVal = input.substring(x * 4, (4 * x) + 4);
			output += getHexVal(binaryVal);
		}
		return output;
	}

	private String getHexVal(String input) {
		String value = "";
		char values[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		int sum = 0;
		for (int x = input.length() - 1; x >= 0; x--) {
			int num = Integer.parseInt(String.valueOf(input.charAt(x)));
			if (num == 1) {

				sum += Math.pow(num * 2, (input.length() - 1 - x));

			}

		}
		value += values[sum];
		return value;
	}

	private String hexToBin(String hex) {
		hex = hex.replaceAll("0", "0000");
		hex = hex.replaceAll("1", "0001");
		hex = hex.replaceAll("2", "0010");
		hex = hex.replaceAll("3", "0011");
		hex = hex.replaceAll("4", "0100");
		hex = hex.replaceAll("5", "0101");
		hex = hex.replaceAll("6", "0110");
		hex = hex.replaceAll("7", "0111");
		hex = hex.replaceAll("8", "1000");
		hex = hex.replaceAll("9", "1001");
		hex = hex.replaceAll("A", "1010");
		hex = hex.replaceAll("B", "1011");
		hex = hex.replaceAll("C", "1100");
		hex = hex.replaceAll("D", "1101");
		hex = hex.replaceAll("E", "1110");
		hex = hex.replaceAll("F", "1111");
		return hex;
	}

	private int getDecimal(String binary) {
		int decimal = 0;
		for (int x = binary.length() - 1; x >= 0; x--) {
			int num = Integer.parseInt(String.valueOf(binary.charAt(x)));
			if (num == 1) {
				decimal += Math.pow(num * 2, (binary.length() - 1 - x));

			}
		}
		return decimal;
	}

	private String optimizeBinary(String binary) {
		if (binary.length() < 8) {
			while (binary.length() != 8) {
				binary = "0" + binary;
			}
		}
		return binary;
	}
}
