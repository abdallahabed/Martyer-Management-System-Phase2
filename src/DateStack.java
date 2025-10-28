import java.util.Date;

public class DateStack implements Comparable<DateStack> {

	StackList stackList = new StackList();;
	Date date;

	public DateStack(Date date, Martyer martyer) {
		stackList.push(martyer);
		this.date = date;
	}

	public StackList getStackList() {
		return stackList;
	}

	public void setStackList(StackList stackList) {
		this.stackList = stackList;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(DateStack o) {
		if (this.date.compareTo(o.date) > 0) {
			return 1;
		} else if (this.date.compareTo(o.date) < 0) {
			return -1;
		}
		return 0;
	}

}
