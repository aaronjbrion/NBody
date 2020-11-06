import java.util.*;

public class LinkedList<E> implements List<E>{
	private class Node<E>{
		E data;
		Node<E> next;
		public Node(E value){
			data = value;
			next=null;
		}
	}
	Node<E> head;
	int size;
	public LinkedList(){
		head= null;
		size=0;
	}
	public boolean add(E item){
		Node<E> node= new Node <E>(item);
		if(head==null){
			head= node;
			++size;
		}else{
			Node<E>prev=head;
			while(prev.next!= null){
				prev=prev.next;
			}
			prev.next=node;
			++size;
			return true;
		}
		return false;
	}
	public void add(E item, int pos){
		Node<E> node= new Node<E>(item);
		Node<E> curr= head;
		if(pos==0){
			node.next=curr;
			head=node;
			++size;
		}
		for(int i=0; i<pos-1; i++){
			curr=curr.next;
		}
		curr=node;
	}
	public E get(int pos){
		if(pos<0||pos>=size){
			return null;
		}
		Node<E> curr=head;
		for(int i=0; i<pos-1; i++){
			curr=curr.next;
		}
		return curr.data;
	}
	public void linkToString(){
		Node<E> node= head;
		while(node !=null){
			System.out.println(node.data+"==> ");
			node=node.next;
		}
		System.out.println();
	}
	public int size(){
		return size;
	}
}