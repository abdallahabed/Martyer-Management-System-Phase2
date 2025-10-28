
import javafx.scene.control.Button;

public class Location implements Comparable<Location> {

	private String location;
	AVlTree1<Martyer> aVlTree1 = new AVlTree1<>();
	AvlTree2<DateStack> avlTree2 = new AvlTree2<>();
	Button infoBT = new Button("info");
	Button summeryBT = new Button("summery");

	public Location(String location) {
		this.location = location;
		infoActin();
		summeryAction();

	}

	public Location(String location, Martyer martyer) {
		aVlTree1.insert(martyer);
		setLocation(location);
		infoActin();
		summeryAction();

	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public AVlTree1<Martyer> getaVlTree1() {
		return aVlTree1;
	}

	public void setaVlTree1(AVlTree1<Martyer> aVlTree1) {
		this.aVlTree1 = aVlTree1;
	}

	public AvlTree2<DateStack> getAvlTree2() {
		return avlTree2;
	}

	public void setAvlTree2(AvlTree2<DateStack> avlTree2) {
		this.avlTree2 = avlTree2;
	}

	public void setMartyer(Martyer martyer) {
		aVlTree1.insert(martyer);
		if (avlTree2.find(martyer.getDateOfDeath()) == null) {
			avlTree2.insert(new DateStack(martyer.getDateOfDeath(), martyer));
			avlTree2.find(martyer.getDateOfDeath()).element.getStackList().push(martyer);
		} else if (avlTree2.find(martyer.getDateOfDeath()) != null) {
			avlTree2.find(martyer.getDateOfDeath()).element.getStackList().push(martyer);
		}
	}

	public Button getInfoBT() {
		return infoBT;
	}

	public void setInfoBT(Button infoBT) {
		this.infoBT = infoBT;
	}

	public Button getSummeryBT() {
		return summeryBT;
	}

	public void setSummeryBT(Button summeryBT) {
		this.summeryBT = summeryBT;
	}

	public void infoActin() {
		infoBT.setOnAction(e -> {

			new MartyesInfo(this);

		});

	}

	public void summeryAction() {
		summeryBT.setOnAction(e -> {
			new SummeryScreen(this);
		});

	}

	@Override
	public int compareTo(Location o) {

		if (this.getLocation().compareTo(o.getLocation()) >= 1) {
			return 1;

		}

		else if (this.getLocation().compareTo(o.getLocation()) < 1) {
			return -1;

		}

		return 0;
	}
}
