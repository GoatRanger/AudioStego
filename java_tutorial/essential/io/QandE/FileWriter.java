import java.io.*;

public class FileWriter {

    public static void main(String[] args) {
	try {
	    long dataPosition = 0; //to be determined later
	    int data = 123;
            RandomAccessFile raf = new RandomAccessFile("datafile", "rw");

	    //Write the file.
	    raf.writeLong(0); //placeholder
	    raf.writeChars("blahblahblah");
	    dataPosition = raf.getFilePointer();
	    raf.writeInt(data);
	    raf.writeUTF("yadayadayada");

	    //Rewrite the first byte to reflect updated data position.
	    raf.seek(0);
	    raf.writeLong(dataPosition);
	    raf.close();

	} catch (FileNotFoundException e) {
	    System.err.println("This shouldn't happen: " + e);
	} catch (IOException e) {
	    System.err.println("Writing error: " + e);
	}
    }
}
