package com.izforge.izpack.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.ArrayList;

import com.izforge.izpack.installer.AutomatedInstallData;
import com.izforge.izpack.installer.IzPanel;

public class InstallPannelTailProcessor implements Runnable{

	/**
	 * @param args
	 */	
	public static void main(String[] args) 
	{
		(new Thread(new InstallPannelTailProcessor())).start();		
	}
 
 
	/**
	 * Given a byte array this method: a. creates a String out of it b. reverses
	 * the string c. extracts the lines d. characters in extracted line will be
	 * in reverse order, so it reverses the line just before storing in
	 * ArrayList.
	 * 
	 * On extracting required numer of lines, this method returns TRUE, Else it
	 * returns FALSE.
	 * 
	 * @param bytearray
	 * @param lineCount
	 * @param lastNlines
	 * @return
	 */
	private boolean parseLinesFromLast(byte[] bytearray, int lineCount, ArrayList lastNlines)
	{
		String lastNChars = new String (bytearray);
		StringBuffer sb = new StringBuffer(lastNChars);
		lastNChars = sb.reverse().toString();		
		StringTokenizer tokens= new StringTokenizer(lastNChars,"\n");
		while(tokens.hasMoreTokens())
		{
			StringBuffer sbLine = new StringBuffer((String)tokens.nextToken());			
			lastNlines.add(sbLine.reverse().toString());
			if(lastNlines.size() == lineCount)
			{
				return true;// indicates we got 'lineCount' lines
			}
		}
		return false; // indicates didn't read 'lineCount' lines
	}
	
	
    public void tail(String fileName,int lineCount, int chunkSize)
    {
		ArrayList lastNlines = new ArrayList();			
    	long curPos;
    	long fromPos;
    	long nowPos;
    	byte[] bytearray;

        try 
        {
			RandomAccessFile raf = new RandomAccessFile(fileName,"r");	
			do
			{
				System.out.println("Enter DO loop");
				curPos=raf.length();
					
					fromPos = curPos - chunkSize;
			
					if(fromPos <= 0)
					{
						raf.seek(0);
						bytearray = new byte[(int)curPos];
						raf.readFully(bytearray);
						// parseLinesFromLast(bytearray, lineCount, lastNlines);
						String lastNChars = new String (bytearray);
						StringBuffer sb = new StringBuffer(lastNChars);
						System.out.println(sb);
					}
					else
					{					
						raf.seek(fromPos);
						bytearray = new byte[chunkSize];
						raf.readFully(bytearray);
						String lastNChars = new String (bytearray);
						StringBuffer sb = new StringBuffer(lastNChars);
						System.out.println(sb);
	
					}
				System.out.println("exit true loop");
				for(int i=lastNlines.size(); i>0; i-- )
				{
					System.out.println(lastNlines.get(i-1));
				}
				System.out.println("Before sleep");
			//	Thread.sleep(1000);
				System.out.println("After sleep");
				nowPos=raf.length();
				System.out.println(curPos);
				System.out.println(nowPos);	
			}while(true);
				
		}
        catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{		
			e.printStackTrace();	
		}
    }


	public void run() {
		// TODO Auto-generated method stub
		InstallPannelTailProcessor iptp= new InstallPannelTailProcessor();		
		iptp.tail("C:\\Documents and Settings\\narram\\.installer\\antlog_installer.txt", 10, 2000);
	}
	
}
