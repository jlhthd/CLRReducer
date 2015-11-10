/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clrreducer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Gargarot
 */
public class CLRReducer {

    static final String INPUTFILE = "C:/Users/Gargarot/Documents/CLR8h1Col.csv";
    static final String OUTPUTFILE = "C:/Users/Gargarot/Documents/8hAbv.csv";
    static final float SIGNIFICANCE = 15.0f;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Declerations
        FileInputStream in = null;
        BufferedReader inStream = null;
        PrintWriter outStream = null;
        String line = null;
        int count = 0;
        
        try{
            //Open files
            in = new FileInputStream(INPUTFILE);
            inStream = new BufferedReader(new InputStreamReader(in));
            outStream = new PrintWriter(OUTPUTFILE, "UTF-8");
            outStream.print("");
            //Add headers
            outStream.print("GeneO,GeneT,Fold\n");
            
            line = inStream.readLine();
            count = 0;
            
            //For each line, check if its value is greater than threshold
            //Add to file if it is
            while(line != null) {
                String[] tempArray = line.split(",");
                
                if(Float.valueOf(tempArray[2]) > SIGNIFICANCE) {
                    outStream.write(line + "\n");
                    System.out.print("Line " + count + " added.\n");
                } else {
                    System.out.print("Line " + count + " removed.\n");
                }
                
                count++;
                line = inStream.readLine();
            }
        }catch(Exception e){
            System.err.print(e.getMessage());
        }finally{
            if(inStream != null) inStream.close();
            if(outStream != null) outStream.close();
            if(in != null) in.close();
        }
    }
    
}
