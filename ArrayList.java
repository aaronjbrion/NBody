import java.util.*;

public class ArrayList<E> implements List<E>{
	E [] arr;
	int size;

	public ArrayList(){
		arr= (E[]) new Object[10];
	}

	public int size(){
		return size;
	}

	public E get(int pos){

		if(pos<0||pos>=size){
			return null;
		}
		return arr[pos];
	}

	private void grow_array(){

		E [] new_arr= (E[]) new Object[arr.length*2];

		for(int i=0; i< arr.length; i++){
			new_arr[i]= arr[i];
		}
		arr=new_arr;
	}

	public boolean add (E item){
		if(size== arr.length){
			grow_array();
		}
		arr[size++]=item;
		return true;
	}

	public void add(E item, int pos){
		if(size==arr.length){
			grow_array();
		}
		for(int i=size; i>pos; i--){
			arr[i]=arr[i-1];
		}
		arr[pos]=item;
		++size;
	}
	
}