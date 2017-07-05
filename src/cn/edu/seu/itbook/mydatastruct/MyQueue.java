package cn.edu.seu.itbook.mydatastruct;

import java.util.Arrays;

public class MyQueue<T> {

	private int DEFAULT_SIZE = 10;
	
	private int capacity;
	
	private Object[] elementData;
	
	private int front = 0;
	private int rear = 0;
	
	public MyQueue()
	{
		capacity = DEFAULT_SIZE;
		elementData = new Object[capacity];
	}
	
	public MyQueue(T element)
	{
		this();
		elementData[0] = element;
		rear++;
	}

	public MyQueue(T element , int initSize)
	{
		this.capacity = initSize;
		elementData = new Object[capacity];
		elementData[0] = element;
		rear++;
	}
	
	public int size()
	{
		return rear - front;
	}
	
	public void add(T element)
	{
		if (rear > capacity - 1)
		{
			throw new IndexOutOfBoundsException("the queue is full!");
		}
		elementData[rear++] = element;
	}

    public T remove()
	{
		if (empty())
		{
			throw new IndexOutOfBoundsException("queue is empty");
		}
		
		@SuppressWarnings("unchecked")
		T oldValue = (T)elementData[front];
		
		elementData[front++] = null; 
		return oldValue;
	}
    
    @SuppressWarnings("unchecked")
	public T element()  
    {  
        if (empty())  
        {  
            throw new IndexOutOfBoundsException("queue is empty");  
        }  
        return (T)elementData[front];  
    }  
	
	public boolean empty()
	{
		return rear == front;
	}
	
	public void clear()
	{
		
		Arrays.fill(elementData , null);
		front = 0;
		rear = 0;
	}
	public String toString()
	{
		if (empty())
		{
			return "[]";
		}
		else
		{
			StringBuilder sb = new StringBuilder("[");
			for (int i = front  ; i < rear ; i++ )
			{
				sb.append(elementData[i].toString() + ", ");
			}
			int len = sb.length();
			return sb.delete(len - 2 , len).append("]").toString();
		}
	}
	public static void main(String[] args){
		MyQueue<String> queue = new MyQueue<String>("ABC", 20);
		queue.add("DEF");
		queue.add("egg");
		System.out.println(queue.empty());
		System.out.println(queue.size());
		System.out.println(queue.element());
		queue.clear();
		System.out.println(queue.empty());
		System.out.println(queue.size());
	}
}