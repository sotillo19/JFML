/*
*  This file is a part of the package "frbsJpmml".
*  
*  Author: Lala Septem Riza
*  Co-author: Christoph Bergmeir
*  Supervisors: Francisco Herrera Triguero and Jose Manuel Benitez
*  Copyright (c) DiCITS Lab, Sci2s group, DECSAI, University of Granada.
*  
*  This package is free software: you can redistribute it and/or modify it under
*  the terms of the GNU General Public License as published by the Free Software
*  Foundation, either version 2 of the License, or (at your option) any later version.
*
*  This package is distributed in the hope that it will be useful, but WITHOUT
*  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
*  A PARTICULAR PURPOSE. See the GNU General Public License for more details.
*/

package jfml.compatibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * It is a supporting class.
 * @author Lala
 */
public class SuppCollection{
    
    /**
     * It is used to find posision of string.
     * @param data reference data
     * @param val data
     * @return a position/index
     */
    public static int findString(String[] data, String val){
         ArrayList list = new ArrayList(Arrays.asList(data));
         int index = list.indexOf(val);
         return(index);
    }
    
    /**
     * It is used for normalization.
     * @param dtOri original data
     * @param rangeData range of data
     * @param minScale minimum value
     * @param maxScale maximum value
     * @return normalized data
     */
    public static double[][] normData(double[][] dtOri, double[][] rangeData, double minScale, double maxScale){
        int rowData = dtOri.length;
        int colData = dtOri[1].length;
        double[][] dataNorm = new double[rowData][colData];
        
        for (int j=0; j < colData; j++){
            double minData = rangeData[0][j];
            double maxData = rangeData[1][j];
            for (int i=0;i < rowData; i++){
                dataNorm[i][j] = minScale + (dtOri[i][j] - minData) * (maxScale - minScale) / (maxData - minData);
            }
        }
        
        return(dataNorm);
    }
     
    /**
     * It is used for de-normalization.
     * @param dtNorm normalized data.
     * @param rangeData range of data.
     * @param minScale minimum value.
     * @param maxScale maximum value.
     * @return real data.
     */
    public static double[][] denormData(double[][] dtNorm, double[][] rangeData, double minScale, double maxScale){
        int rowData = dtNorm.length;
        int colData = dtNorm[1].length;
        double[][] dataDenorm = new double[rowData][colData];
        
        for (int j=0; j<colData;j++){
            double minData = rangeData[0][j];
            double maxData = rangeData[1][j];
            for (int i=0; i<rowData;i++){
                dataDenorm[i][j] = minData + ((dtNorm[i][j] - minScale) * (maxData - minData))/(maxScale - minScale);
            }
        }
        return(dataDenorm);
    }
    
    /**
     * It is used for de-normalization.
     * @param dtNorm normalized data.
     * @param rangeData range of data.
     * @param minScale minimum value.
     * @param maxScale maximum value.
     * @return real data.
     */
    public static double[] denormData(double[] dtNorm, double[] rangeData, double minScale, double maxScale){
        int rowData = dtNorm.length;
        double[] dataDenorm = new double[rowData];
        
            double minData = rangeData[0];
            double maxData = rangeData[1];
            for (int i=0; i<rowData;i++){
                dataDenorm[i] = minData + ((dtNorm[i] - minScale) * (maxData - minData))/(maxScale - minScale);
            }
 
        return(dataDenorm);
    }
    

    /**
     * It is used to search a position of string
     * @param data data
     * @param val value
     * @return a position
     */
    public static int[] which(String[] data, String val){
        int[] index = null;
        int k = 0;
        for (int i = 0; i < data.length; i++){
            if (data[i].equalsIgnoreCase(val)){
                index[k] = i;
                k++;
            }
        }
        return(index);
    }

    /**
     * It is used to search positions of string
     * @param data data
     * @param val values
     * @param operator operator, e.g., "!=", "==", etc.
     * @return positions.
     */
    public static List<Integer> which(double[] data, double val, String operator){
        List<Integer> indx = new ArrayList<Integer>();
        for (int i = 0; i < data.length; i++){
            if (operator.equals("!=")){
                if (Math.abs(data[i] - val) >= 0.001)
                    indx.add(i);
            }
            else if (operator.equals("==")){
                if (Math.abs(data[i] - val) < 0.001)
                    indx.add(i);
            }
        }
        return(indx);
    }
    
    /**
     * It is used to find the maximum data.
     * @param data data
     * @return a position of the maximum data.
     */
    public static int whichMax(double[] data){
        int indx = 0;
        double max = data[0];
        for (int i = 1; i < data.length; i++){
            if (data[i] > max){
                indx = i;
                max = data[i];
            }
        }
        return(indx);
    }
    
    /**
     * It is used to generate a sequence numbers.
     * @param from start number.
     * @param to end number.
     * @param by lag of number.
     * @return a sequence of numbers
     */
    public static double[] seq(double from, double to, double by){
        double[] res = null;
        int num = (int)Math.round((to - from)/by);
        for (int i = 0; i < num; i++){
            res[i] = from + by * i;
        }
        
        return(res);
    }
    
    /**
     * It is used to generate a sequence numbers.
     * @param from start number.
     * @param to end number.
     * @param by lag of number.
     * @return a sequence of numbers
     */
    public static int[] seq(int from, int to, int by){
        int[] res = new int[(int)Math.round((to - from)/by)];
        for (int i = 0; i < res.length; i++){
            res[i] = from + by * i;
        }
        
        return(res);
    }
    
    /**
     * It is used to search the maximum values.
     * @param data data
     * @return the maximum value.
     */
    public static double maxVector(double[] data){
        double max = data[0];
        for (int i = 1; i < data.length; i++){
            if (data[i] > max){
                max = data[i];
            }
        }
        return(max);
    }
    
    /**
     * It is used for tranpose.
     * @param data data.
     * @return a tranpose matrix.
     */
    public static double[][] tranpose(double[][] data){
        double[][] res = new double[data.length][data[0].length];
        for (int i = 0;i < data.length; i++){
            for (int j = 0; j < data[i].length;j++){
                res[j][i] = data[i][j];
            }
        }
        return(res);
    }
    
    /**
     * It is used for multiplication of matrix and vector.
     * @param data data
     * @param vec vector
     * @return a vector
     */
    public static double[] multiArray(double[][] data, double[] vec){
        double[] res = new double[data.length];
        for (int i = 0; i < data.length; i++){
            double temp = 0;
            for (int j = 0; j < vec.length; j++){
                temp = temp + data[i][j] * vec[j];
            }
            res[i] = temp;
        }
        return(res);
    }
    
    /**
     * It is used for multiplication among vectors.
     * @param vec1 vector 1
     * @param vec2 vector 2
     * @return result.
     */
    public static double multiArray(double[] vec1, double[] vec2){
        double res = 0;
        for (int j = 0; j < vec1.length; j++){
            res = res + vec1[j] * vec2[j];
        }
        return(res);
    }
    
    /**
     * It is used to calculate cumulative sum. 
     * @param data data 
     * @return the total.
     */
    public static int sumArray(int[] data){
        int total = 0;
        for (int i = 0; i < data.length; i++){
            total = total + data[i];
        }
        return(total);
    }
    
    /**
     * It is used for calculating cumulative sum
     * @param data data
     * @return the total
     */
    public static double sumArray(double[] data){
        double total = 0.0;
        for (int i = 0; i < data.length; i++){
            total = total + data[i];
        }
        return(total);
    }
    
    /**
     * It is used to add two vectors.
     * @param data1 data 1
     * @param data2 data 2
     * @return a vector of summation.
     */
    public static double[] sumElement(double[] data1, double[] data2){
        double[] res = new double[data1.length];
        for (int i = 0; i < data1.length; i++){
           res[i] = data1[i] + data2[i];
        }
        return(res);
    }
      
    /**
     * It is used to calculate summation of matrix
     * @param data data
     * @return the summation values
     */
    public static double[] sumArray(double[][] data){
        double[] res = null;
        for (int i = 0; i < data.length; i++){
            double total = 0;
            for (int j = 0; j < data[i].length; j++){
                total = total + data[i][j];
            }
            res[i] = total;
        }
        return(res);
    }
    
    /**
     * It is used to get one row of array
     * @param data data
     * @param a an index of row
     * @return a vector
     */
    public static double[] getRow(double[][] data, int a){     
        double[] dataRow = new double[data[1].length];
        for (int j = 0; j < data[1].length; j++){
            dataRow[j] = data[a][j];
        }
        return(dataRow);        
    }

    /**
     * It is used to get sub matrix.
     * @param data data
     * @param a index of column a
     * @param b index of column b
     * @return a sub matrix
     */
    public static double[][] getCol(double[][] data, int a, int b){
        int numCol = b - a;
        if (numCol > 0){
            double[][] temp = new double[data.length][numCol + 1];        
            for (int i = 0; i < data.length; i++){
                int k = 0;
                for (int j = a; j <= b; j++){
                    temp[i][k] = data[i][j];
                    k++;
                }
            }
            return(temp);
        }
        else {
            double[][] temp = new double[data.length][1];
            for (int i = 0; i < data.length; i++){
                temp[i][0] = data[i][a];
            }
            return(temp);
        }      
    }
    
    /**
     * It is used to get sub matrix.
     * @param data data
     * @param a index of column a
     * @param b index of column b
     * @return a sub matrix
     */
    public static Double[][] getCol(Double[][] data, int a, int b){      
        int numCol = b - a;
        Double[][] temp = new Double[data.length][numCol + 1];        
        for (int i = 0; i < data.length; i++){
            int k = 0;
            for (int j = a; j <= b; j++){
                temp[i][k] = data[i][j];
                k++;
            }
        }
        return(temp);       
    }
    
    /**
     * It is used to get one column of matrix
     * @param data data
     * @param a an index of column
     * @return a vector
     */
    public static double[] getCol(double[][] data, int a){
        double[] temp = new double[data.length];
        for (int i = 0; i < data.length; i++){
            temp[i] = data[i][a];
        }
        return(temp);
    }
        
    /**
     * It is used to get sub matrix.
     * @param data data
     * @param a index of column a
     * @param b index of column b
     * @return a sub matrix
     */
    public static int[][] getCol(int[][] data, int a, int b){
        int numCol = b - a;
        if (numCol > 0){
            int[][] temp = new int[data.length][numCol + 1];        
            for (int i = 0; i < data.length; i++){
                for (int j = a; j <= b; j++){
                    temp[i][j] = data[i][j];
                }
            }
            return(temp);
        }
        else {
            int[][] temp = new int[data.length][1];
            for (int i = 0; i < data.length; i++){
                temp[i][0] = data[i][a];
            }
            return(temp);
        }      
    }
    
    /**
     * It is used to get sub matrix.
     * @param data data
     * @param a an index of column a
     * @param b an index of column b
     * @return a sub matrix
     */
     public static int[] getCol(int[] data, int a, int b){
        int numCol = b - a;
        int[] temp = new int[numCol + 1]; 
        int k = 0;
        for (int j = a; j <= b; j++){
            temp[k] = data[j];
            k++;
        }           
        return(temp);
    }
     
     /**
      * It is used to substring
      * @param data data
      * @param a a start position of string 
      * @param b a end position of string
      * @return a sub string
      */
     public static String[] getCol(String[] data, int a, int b){
        int numCol = b - a;

        String[] temp = new String[numCol + 1]; 
        int i = 0;
        for (int j = a; j <= b; j++){
            temp[i] = data[j];
            i++;
        }           
        return(temp);
    }
    
    /**
     * It is used to combine two strings.
     * @param a String a
     * @param b String b
     * @return new string
     */
    public static String[] combineString(String[] a, String[] b){
        int numRowa = a.length;
        int numRowb = b.length;
        
        String[] res = new String[numRowa + numRowb];
        System.arraycopy(a, 0, res, 0, numRowa);
        System.arraycopy(b, 0, res, numRowa, numRowb);
 
        return res;
    }
     
}

