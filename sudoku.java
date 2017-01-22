/* NIM/Nama : 13514069/Sekar Anglila Hapsari
 Nama file : sudoku.java
 Tanggal :  22 Januari 2016*/
 
import java.util.Scanner;
import java.io.*;

public class sudoku{
	//Atribut
	int [][] S = new int[9][9];
	int i, j, b, k;
	int NB = 0;
	int NK = 0;
	int assign = 0;
	boolean same;
	Scanner sc = new Scanner(new File("C:\\Users\\ASUS\\Documents\\Stima\\Tucil1\\SudokuInput.txt")); 
	
	//Metode
	//Konstruktor
	sudoku() throws IOException {
		for (i = 0; i < 9; i++){
			for (j = 0; j < 9; j++){
				S[i][j] = 0;
			}
		}
	}
	//Pembacaan File Eksternal dan Penulisan Matriks (ke layar dan File Eksternal)
	public void bacaFile() { 
		//Algoritma
		while(sc.hasNextLine()){ 
			String str = sc.nextLine();
			String str2[] = str.split(" ");
			NK = str2.length;
			for(j = 0; j < NK; j++){
				S[NB][j] = Integer.parseInt(str2[j]);
			}
			NB++;
		}
	}
	
	public void tampilMatriks(){
		//Algoritma
		System.out.println("---------------------");
		for(i = 0; i < NB; i++){
			for(j = 0; j < NK; j++){
					System.out.print(S[i][j] + " ");
				if ((j == 2) || (j == 5)){
					System.out.print("| ");
				}
			}
			System.out.println();
			if ((i == 2) || (i == 5)){
				System.out.println("---------------------");
			}
		}
		System.out.println("---------------------");
	}
	
	//Pemeriksaan apakah angka valid dalam Baris, Kolom, Box, dan HyperBox
	public boolean validBar(int row, int col, int isiN)
	{
		//Algoritma
		k = 0; same = true;
		//Pemeriksaan
		while ((k < 9) && same){
			if ((isiN == S[row][k]) && (k != col)){
				same = false;
			}
			else
			{
				k++;
			}
		}
		return same;
	}
	
	public boolean validKol(int row, int col, int isiN)
	{
		//Algoritma
		b = 0; same = true;
		//Pemeriksaan
		while ((b < 9) && same)
		{
			if ((isiN == S[b][col]) && (b != row))
			{
				same = false;
			}
			else
			{
				b++;
			}
		}
		return same;
	}
	
	public boolean validBox (int row, int col, int isiN)
	{
		//Kamus Lokal
		int btemp, ktemp;
		
		//Algoritma
		b = (row / 3) * 3; k = (col / 3) * 3;
		btemp = b + 3; ktemp = k + 3;
		same = true;
		//Pemeriksaan
		while ((b < btemp) && same){
			while ((k < ktemp) && same){
				if ((isiN == S[b][k]) && !((b == row) && (k == col))){
					same = false;
				}
				else
				{
					k++;
				}
			}
			k = ktemp - 3;
			b++;
		}
		return same;
	}
	
	//Pengecekan box hyper
	public int countBK2 (int N)
	{
		int c = 0;
		if ((N >= 1) && (N <= 3))
		{
			c = 1;
		}
		else if ((N >= 5) && (N <= 7))
		{
			c = 5;
		}
		return c;
	}
	
	public boolean validBox2 (int row, int col, int isiN)
	{
		//Kamus Lokal
		int btemp, ktemp;
		
		//Algoritma
		b = countBK2(row); k = countBK2(col);
		btemp = b + 3; ktemp = k + 3; same = true;
		
		//Pemeriksaan
		while ((b < btemp) && same)
		{
			while ((k < ktemp) && same) 
			{
				if ((isiN == S[b][k]) && !((b == row) && (k == col)))
				{
					same = false;
				}
				else
				{
					k++;
				}
			}
			k = ktemp - 3;
			b++;
		}
		return same;
	}
	
	public boolean isValid(int row, int col, int isiN)
	{
		//Kamus Lokal
		boolean valid = false;
		
		//Algoritma
		if ((((row >= 1) && (row <= 3)) || ((row >= 5) && (row <= 7))) && (((col >= 1) && (col <= 3)) || ((col >= 5) && (col <= 7))))
		{
			valid = (validBar(row, col, isiN) && validKol(row, col, isiN) && validBox(row, col, isiN) && validBox2(row, col, isiN));
		}
		else
		{
			valid = (validBar(row, col, isiN) && validKol(row, col, isiN) && validBox(row, col, isiN));
		}
		return valid;
	}
	
	//Pemeriksaan apakah elemen kosong
	public boolean isEmpty(int row, int col)
	{
		//Algoritma
		if (S[row][col] == 0){
			return true;
		}
		else {
			return false;
		}
	}
	
	//Pengisian matriks sudoku
	public void isiMatriks(int row, int col){
		//Kamus Lokal
		int x, y;
		
		//Algoritma
		//Basis
		if (row == 9)
		{
			tampilMatriks();
			System.out.println();
			return;
		} 
		else { //Rekurens
			//Mencari elemen yang bisa diisi
			if (!(isEmpty(row, col))){
				if (col == 8){
					isiMatriks((row + 1), 0);
				}
				else {
					isiMatriks(row, (col + 1));
				}
			} else {
				//Pengisian Matriks
				for (int n = 1; n <= 9; n++){
					if (isValid(row, col, n)){
						S[row][col] = n;
						assign++; 
						//Next element
						if(col == 8){
							x = row + 1; y = 0;
							isiMatriks(x, y);
						}else {
							x = row; y = col + 1;
							isiMatriks(x, y);
						}
						//Jika tidak berhasil, kembali ke sebelumnya
						S[row][col] = 0;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException { 
		//Kamus
		sudoku s = new sudoku();
		long start, finish;
		long time = 0;
		double finaltime;
		
		//Algoritma
		//Pembacaan File dan pembuatan matriks boolean
		s.bacaFile();
		
		//Menampilkan matriks sudoku awal
		System.out.println("Sudoku Awal :");
		s.tampilMatriks(); 
		System.out.println();
		System.out.println();
		
		//Menghitung waktu start
		start = System.nanoTime();
		
		//Mengisi sudoku
		System.out.println("Solusi Sudoku :");
		s.isiMatriks(0, 0);
		System.out.println();
		
		//Menghitung waktu selesai
		finish = System.nanoTime();
		time = (finish - start);
		finaltime = (double) time / 1000000;
		
		//Jumlah assignment dan waktu eksekusi
		System.out.println("Jumlah assignment = " + s.assign + " kali");
		System.out.println("Waktu eksekusi = " + finaltime + " ms");
	}
}
