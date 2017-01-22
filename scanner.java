/* NIM/Nama : 13514069/Sekar Anglila Hapsari
 Nama file : scanner.java
 Tanggal :  22 Januari 2016
 Deskripsi : */

/*Program sudoku*/

import java.util.Scanner;
import java.io.*;

public class scanner{
	//Atribut
	public Scanner sc; 
	public FileWriter fw;
	public PrintWriter pw;
	public int NB = 0;
	public int NK = 0;
	//Sudoku
	public int[][] M;
	public int i, j;

	public scanner() throws IOException { 
		//Kamus
		sc = new Scanner(new File("C:\\Users\\ASUS\\Documents\\Stima\\Tucil1\\SudokuInput.txt")); 
		M = new int[9][9];
		fw = new FileWriter("C:\\Users\\ASUS\\Documents\\Stima\\Tucil1\\SudokuOutput.txt");
		pw = new PrintWriter(fw);
	}

	public void bacaFile(){ 
		//Algoritma
		while(sc.hasNextLine()){ 
			String str = sc.nextLine();
			String str2[] = str.split(" ");
			NK = str2.length;
			for(j = 0; j < NK; j++){
				M[NB][j] = Integer.parseInt(str2[j]);
			}
			NB++;
		}
	}

	public void tampilMatriks(){
		//Algoritma
		for(i = 0; i < NB; i++){
			for(j = 0; j < NK; j++){
				System.out.print(M[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void tulisfile(){
		//Algoritma
		for(i = 0; i < NB; i++){
			for(j = 0; j < NK; j++){
				pw.print(M[i][j] + " ");
			}
			pw.println();
		}
		pw.flush();
		pw.close();
	}

	public static void main(String[] args) throws IOException { 
		scanner scan = new scanner();
		scan.bacaFile();
		System.out.println("NBaris: " + scan.NB);
		System.out.println("NKolom: " + scan.NK);
		scan.tampilMatriks();
		scan.tulisfile();
	}
}	

public class sudokusolver{
		//Atribut
		public int isiN;
		public int b, k;
		public boolean same;
		
		public boolean validBar(int NB, int NK, int isiN)
		{
			//Algoritma
			k = 0;
			same = false;
			while ((k < 9) && (!(same)))
			{
				if ((isiN == M[NB][k]) && (k != NK))
				{
					same = true;
				}
				else
				{
					k++;
				}
			}
			return same;
		}
		
		public boolean validKol(int NB, int NK, int isiN)
		{
			//Algoritma
			b = 0;
			same = false;
			while ((b < 9) && (!(same)))
			{
				if ((isiN == M[b][NK]) && (b != NB))
				{
					same = true;
				}
				else
				{
					b++;
				}
			}
			return same;
		}
		
		public int countBK (int N, int c)
		{
			if (c == 0)
			{
				return N;
			}
			else
			{
				if (c == 1)
				{
					return (N - 1);
				}
				else // c = 2
				{
					return (N - 2);
				}
			}
		}
		
		public int countBK2 (int N, int c)
		{
			if (c == 1)
			{
				return N;
			}
			else
			{
				if (c == 2)
				{
					return (N - 1);
				}
				else // c = 0
				{
					return (N - 2);
				}
			}
		}
		
		public boolean validBox (int NB, int NK, int isiN)
		{
			//Kamus Lokal
			int btemp, ktemp, bb, kk;
			
			//Algoritma
			bb = NB % 3; kk = NK % 3;
			b = countBK(NB, bb); k = countBK(NK, kk);
			btemp = b + 3; ktemp = k + 3;
			same = false;
			
			while ((b < btemp) && (!(same)))
			{
				while ((k < ktemp) && (!(same))) 
				{
					if ((isiN == M[b][k]) && (b != NB) && (k != NK))
					{
						same = true;
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
		
		public boolean validBox2 (int NB, int NK, int isiN)
		{
			//Kamus Lokal
			int btemp, ktemp, bb, kk;
			
			//Algoritma
			bb = NB % 3; kk = NK % 3;
			b = countBK2(NB, bb); k = countBK2(NK, kk);
			btemp = b + 3; ktemp = k + 3;
			same = false;
			
			while ((b < btemp) && (!(same)))
			{
				while ((k < ktemp) && (!(same))) 
				{
					if ((isiN == M[b][k]) && (b != NB) && (k != NK))
					{
						same = true;
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
}

