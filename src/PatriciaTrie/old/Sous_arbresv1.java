package PatriciaTrie;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class Sous_arbres {
	private Deque<PatriciaTrie> sous_arbres;
	
	public Sous_arbres() {
		sous_arbres = new Deque<PatriciaTrie>() {

			@Override
			public boolean addAll(Collection<? extends PatriciaTrie> arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean containsAll(Collection<?> arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean retainAll(Collection<?> arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> T[] toArray(T[] arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean add(PatriciaTrie arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void addFirst(PatriciaTrie arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addLast(PatriciaTrie arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean contains(Object arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<PatriciaTrie> descendingIterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PatriciaTrie element() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PatriciaTrie getFirst() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PatriciaTrie getLast() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Iterator<PatriciaTrie> iterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean offer(PatriciaTrie arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean offerFirst(PatriciaTrie arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean offerLast(PatriciaTrie arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public PatriciaTrie peek() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PatriciaTrie peekFirst() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PatriciaTrie peekLast() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PatriciaTrie poll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PatriciaTrie pollFirst() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PatriciaTrie pollLast() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PatriciaTrie pop() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void push(PatriciaTrie arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public PatriciaTrie remove() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean remove(Object arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public PatriciaTrie removeFirst() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean removeFirstOccurrence(Object arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public PatriciaTrie removeLast() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean removeLastOccurrence(Object arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}

	public Deque<PatriciaTrie> getSous_arbres() {
		return sous_arbres;
	}

	public void setSous_arbres(Deque<PatriciaTrie> sous_arbres) {
		this.sous_arbres = sous_arbres;
	};

}
